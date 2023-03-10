package eip.fileintegration.mom;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ernestoexposito
 */
public class Importer extends Thread {
    
     // the name of the queue to be used to communicate
    private final static String QUEUE_NAME = "products";
    // the connection to the broker
    Connection connection = null; 
    // the channel to communicate with the broker
    Channel channel = null;
    // the channel consumer
    QueueingConsumer consumer = null;
    // the consumer appliction in order to add the received products
    private Consumer c=null;

   public Importer(Consumer c) throws Exception {
        // the consumer reference is stored
        this.c=c;
        // Create a factory of connections to the MOM
        ConnectionFactory factory = new ConnectionFactory();
        // Specify the address of the MOM server
        factory.setHost("localhost");
        // Create a new connection to the MOM
       Connection connection = factory.newConnection();
        // Create a new channel within this connection
        // to consume and to produce messages
        Channel channel = connection.createChannel();
        // Declare the queue to be used with the parameters:
        // durable:false, exclusive: false, autodelete:false,
        // additional-arguments:null
        channel.queueDeclare(QUEUE_NAME, false,false,false,null);
        // a queue consumer is created on the chanel
        consumer = new QueueingConsumer(channel);
        // the consumer is associated is associate with the queue
        channel.basicConsume(QUEUE_NAME,  true, consumer);
        this.start();
    }

    @Override
    public void run() {
        
        while (true) {
            try {
                // receive products from the Queue
                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                String msg = new String(delivery.getBody());
                // add the products to the GUI
                c.addProduct(Product.stringToProduct(msg));
            } catch (InterruptedException ex) {
                break;
            } catch (ShutdownSignalException ex) {
                Logger.getLogger(Importer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ConsumerCancelledException ex) {
                Logger.getLogger(Importer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Importer.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
    }

    public void close() {
        try {
            this.interrupt();
            channel.close();
            connection.close();
        } catch (IOException ex) {
            Logger.getLogger(Exporter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TimeoutException ex) {
            Logger.getLogger(Exporter.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
}
