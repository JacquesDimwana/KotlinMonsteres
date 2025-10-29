# Compte-Rendu Sprint 3 – Kotlin Monsters : Base de Données & DAO

## 1. Introduction

Le Sprint 3 avait pour objectif de **connecter le projet Kotlin Monsters à une base de données MySQL** pour stocker les informations des entraîneurs, monstres et espèces.  

J’ai pu :  

- Créer et configurer une base de données relationnelle.
- Implémenter des **DAO (Data Access Object)** pour gérer le CRUD.
- Faire communiquer le code Kotlin avec la BDD via JDBC.
- Tester que les données pouvaient être insérées, récupérées et mises à jour correctement.

Ce sprint m’a permi de **comprendre comment passer d’un modèle orienté objet à un modèle relationnel** et comment automatiser la gestion des données.

---

## 2. Création de la base de données et des tables

### 2.1 Création de la base

- J’ai créé la base `db_monsters_monlogin` via le terminal ou IntelliJ IDEA.
- J’ai configuré une **Data Source MariaDB** dans IntelliJ avec mes identifiants et testé la connexion.
- J’ai créé un fichier `tables.sql` dans le dossier `resources` pour **garder toutes les requêtes**.

> Cela m’a permis de centraliser les commandes SQL et de ne pas perdre le travail si IntelliJ est fermé.

### 2.2 Tables principales

- J’ai commencé par la table `Entraineurs` :

sql
CREATE TABLE Entraineurs(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(255),
    argents INTEGER
);
Explication : j’ai commencé par une table primaire (pas de clés étrangères) pour simplifier l’insertion des données ensuite.

Ensuite, j’ai créé les tables EspecesMonstre et IndividuMonstre en suivant le diagramme ERD que j’avais réalisé.

J’ai ajouté les clés primaires et étrangères pour lier les individus à leurs espèces et entraîneurs.

## 3. Insertion des données de base

J’ai inséré des entraîneurs NPC : Bob, Alice, Clara.

J’ai ajouté des espèces de monstres avec leurs statistiques et descriptions (ex. springleaf, flamkip, pyrokip…).

Puis j’ai ajouté des individus de monstres pour tester les relations entre entraîneurs et monstres.

Exemple : Alice a un springleaf, Bob a aquamy et bugsyface, etc.
Cette étape m’a permis de tester la cohérence de la BDD et la structure des tables.

## 4. Connexion et gestion de la BDD dans Kotlin

J’ai importé le driver JDBC via Gradle pour connecter Kotlin à MySQL.

J’ai créé une classe BDD pour gérer :

La connexion à la base (getConnection())

L’exécution des requêtes (executePreparedStatement())

La fermeture de la connexion (close())

Explication : cette classe centralise la gestion de la BDD et évite de répéter du code dans le projet.
J’ai testé la connexion en lançant le projet et en récupérant les données via Main.kt.

## 5. Tests unitaires

J’ai créé un test pour vérifier que executePreparedStatement() fonctionnait correctement.

J’ai récupéré tous les entraîneurs dans la BDD et vérifié qu’il y avait bien 3 entraîneurs.

Cela m’a confirmé que la communication Kotlin → BDD fonctionne.

Exemple de logique testée :

val bdd = BDD()
val sql = bdd.connectionBDD!!.prepareStatement("SELECT * FROM entraineurs")
val result = bdd.executePreparedStatement(sql)


Puis on parcourt result pour créer des objets Entraineur et vérifier la taille de la liste.

##6. Création du DAO (Data Access Object)

J’ai créé un package DAO pour centraliser les interaction avec la BDD.

Exemple : EntraineurDAO :

findAll() → récupère tous les entraîneur

findById() → cherche un entraîneur par son ID

findByNom() → cherche un entraîneur par son nom

save() → insère ou met à jour un entraîneur

saveAll() → insère plusieurs entraîneurs

deleteById() → supprime un entraîneur

Explication : j’ai utilisé des requêtes préparées avec ? pour éviter toute injection SQL.
J’ai testé chaque méthode pour m’assurer qu’elles fonctionnaient correctement et que les objets Kotlin étaient bien synchronisés avec la BDD.

## 7. Intégration dans le projet

Dans Main.kt, j’ai remplacé les données codées en dur par des appels aux DAO :

Récupération de la liste des entraîneurs : val listeEntraineur = entraineurDAO.findAll()

Sauvegarde du joueur lors d’une nouvelle partie : entraineurDAO.save(joueur)

Cela permet de simplifier le code et d’utiliser directement la BDD pour toutes les données.

## 8. Extension du modèle

J’ai créé également EspeceMonstreDAO et IndividuMonstreDAO pour gérer les autres tables.

Les relations clés étrangères sont prises en compte pour lier chaque monstre à son entraîneur et son espèce.

J’ai testé la récupération des listes de monstres et d’espèces depuis la BDD.

Cette étape m’a permis de comprendre comment gérer des relations complexes et comment reproduire les associations du code Kotlin dans la BDD.

## 9. Création d’une nouvelle espèce de monstre

Pour terminer, j’ai créé ma propre espèce de monstre dans Main.kt :

J’ai fait le ASCII-Art front et back

J’ai défini ses statistiques, éléments et description

J’ai ajouté son enregistrement dans la BDD via EspeceMonstreDAO et IndividuMonstreDAO

Cela m’a permis de mettre en pratique tout ce que j’avais appris : design d’objet, relations BDD, DAO, et test dans Kotlin.

## 10. Conclusion du Sprint 3

La connexion Kotlin → BDD fonctionne parfaitement.

J’ai compris et mis en pratique :

La transformation d’un modèle orienté objet en modèle relationnel

La création de DAO pour centraliser les opérations CRUD

La sécurisation des requêtes avec des requêtes préparées ```

Le projet est maintenant capable de charger automatiquement les entraîneurs, espèces et monstres depuis la BDD. ```
