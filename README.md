# EIP file integration design pattern based on a shared Database

Ce quatrième lab reprend les concepts des trois premiers labs. Cependant, nous ne sauvegardons plus dans un simple fichier en local, mais nous utilisons une base de données (NetBeans pour le professeur, PostgreSQL pour nous), afin d'y stocker les données de nos objets. **Cependant, nous n'utiliserons pas de JPA comme nous avions pu le faire précédemment pour gérer nos objets et le lien avec la base de données !**

Avant de coder, veuillez suivre la partie "Installation" pour installer la base de données et configurer votre projet correctement.

## Installation

1. Lancez pgAdmin et créez une nouvelle base de données "PRODUCT". Gardez le mot de passe et l'utilisateur en tête, nous en aurons besoin plus tard (de base, l'utilisateur est postgres et le mot de passe est celui que vous aviez choisi lors de l'installation).
2. Quand vous aurez créé la base de données PRODUCT, faites un clic-droit (s'il elle n'apparaît pas dans la liste des bases de données, faites un clic-droit sur "Databases > Refresh") > Restore...
3. Une nouvelle fenêtre s'ouvre, sélectionnez le fichier `lab3.sql` disponible dans ce dossier (`eip-lab3`) > Restore.
4. Vous pouvez maintenant débuter le lab !
