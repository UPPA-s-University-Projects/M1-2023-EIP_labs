PGDMP     8                    {            DATABASE_TEST    15.1    15.1     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16493    DATABASE_TEST    DATABASE     �   CREATE DATABASE "DATABASE_TEST" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'French_France.1252';
    DROP DATABASE "DATABASE_TEST";
                postgres    false            �            1259    16501    PRODUCT    TABLE     �   CREATE TABLE public."PRODUCT" (
    "ID" integer NOT NULL,
    "PRICE" real,
    "AMOUNT" integer,
    "DESCRIPTION" character varying(20)
);
    DROP TABLE public."PRODUCT";
       public         heap    postgres    false            �          0    16501    PRODUCT 
   TABLE DATA           K   COPY public."PRODUCT" ("ID", "PRICE", "AMOUNT", "DESCRIPTION") FROM stdin;
    public          postgres    false    214   �       e           2606    16505    PRODUCT PRODUCT_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public."PRODUCT"
    ADD CONSTRAINT "PRODUCT_pkey" PRIMARY KEY ("ID");
 B   ALTER TABLE ONLY public."PRODUCT" DROP CONSTRAINT "PRODUCT_pkey";
       public            postgres    false    214            �      x������ � �     