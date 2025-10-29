# Compte-Rendu Sprint 1 – Kotlin Monsters

## 1. Introduction

Le Sprint 1 avait pour objectif de poser les bases du projet **Kotlin Monsters**, un jeu inspiré des Pokémon.  
Pendant ce sprint, j’ai travaillé **seul** et j’ai pu :

- Créer la structure du projet sur IntelliJ IDEA.
- Mettre en place l’architecture orientée objet avec les classes principales (`Entraineur`, `EspeceMonstre`, `IndividuMonstre`, etc.).
- Développer des fonctionnalités de base comme la manipulation de monstres et d’objets.
- Écrire des tests pour vérifier le bon fonctionnement des composants que j’avais créés.

Ce sprint m’a permis de comprendre **le fonctionnement de Kotlin**, la gestion des classes et objets, et comment structurer un projet pour qu’il soit maintenable.

---

## 2. Création et configuration du projet

### 2.1 Création du projet

- J’ai créé le projet dans IntelliJ IDEA : `File > New > Project > Kotlin > JVM`.
- J’ai choisi **Gradle** pour gérer les dépendances, ce qui sera utile pour les prochains sprints (tests, BDD, etc.).

### 2.2 Configuration du proxy

Comme je travaillais depuis le lycée, j’ai dû configurer le proxy dans `gradle.properties` :

properties
systemProp.http.proxyHost=172.16.0.54
systemProp.http.proxyPort=8080
systemProp.https.proxyHost=172.16.0.54
systemProp.https.proxyPort=8080

2.3 Gestion du versionnage

J’ai créé un repository Git pour suivre toutes les versions du projet.

Cela m’a permis de tracer mes modifications, revenir en arrière si quelque chose ne fonctionne pas, et garder un historique clair.

3. Organisation des ressources et packages
3.1 Import des ressources ASCII-Art

J’ai téléchargé et placé les fichiers ASCII-Art des monstres dans resources/art.

Exemple : pour springleaf, j’ai mis front.txt et back.txt.

Cela permet d’afficher une représentation visuelle du monstre directement dans la console.

3.2 Création des packages

Pour organiser mon code, j’ai créé les packages suivants :

Package	Rôle
dresseur	Classes liées aux entraîneurs
item	Classes pour les objets
jeu	Mécanismes du jeu (combat, partie)
monde	Gestion des zones et environnement
monstres	Gestion des espèces et individus

Explication : les packages facilitent l’importation des classes, évitent les conflits de noms et structurent mon projet.

4. Développement des fonctionnalités principales
4.1 Fonction utilitaire changeCouleur()

J’ai créé une fonction pour afficher du texte en couleur dans la console :

fun changeCouleur(message: String, couleur: String = ""): String {
    val reset = "\u001B[0m"
    val codeCouleur = when (couleur.lowercase()) {
        "rouge" -> "\u001B[31m"
        "vert" -> "\u001B[32m"
        "jaune" -> "\u001B[33m"
        "bleu" -> "\u001B[34m"
        "magenta" -> "\u001B[35m"
        "cyan" -> "\u001B[36m"
        "blanc" -> "\u001B[37m"
        else -> ""
    }
    return "$codeCouleur$message$reset"
}


Testé avec différents scénarios :

println(changeCouleur("Hello", "rouge"))
println(changeCouleur("World", "bleu"))
println("Hello ${changeCouleur("my", "jaune")} World")


Explication : Cette fonction utilise les codes ANSI pour colorer le texte.
Si la couleur n’est pas reconnue, le texte reste blanc.

4.2 Classes principales
4.2.1 Classe Entraineur
var joueur = Entraineur(1, "Sacha", 100)
joueur.afficheDetail()


Représente un joueur ou un PNJ.

Contient l’équipe de monstres, la boîte de stockage et le sac d’objets.

afficheDetail() permet d’afficher les informations principales (nom et argent).

Exemple de sortie console :

Dresseur : Sacha
Argents: 100

4.2.2 Classes EspeceMonstre et IndividuMonstre

EspeceMonstre définit les stats de base et l’ASCII-Art.

IndividuMonstre représente un monstre unique avec niveau, expérience, PV, et méthodes : attaquer(), levelUp(), renommer().

Méthode afficheArt() pour afficher le monstre dans la console :

fun afficheArt(deFace: Boolean=true): String {
    val nomFichier = if(deFace) "front" else "back"
    val art = File("src/main/resources/art/${this.nom.lowercase()}/$nomFichier.txt").readText()
    return art.replace("\\u001B", "\u001B")
}


Explication : J’ai pu tester que chaque monstre s’affiche correctement avec son ASCII-Art en face avant ou arrière.

4.3 Tests fonctionnels

Tests de changeCouleur() pour toutes les couleurs.

Création et affichage d’un Entraineur et d’un IndividuMonstre.

Vérification de l’évolution d’un monstre grâce à levelUp().

Test d’un combat simple avec attaquer().

Explication : ces tests m’ont permis de valider que les fonctionnalités de base fonctionnent avant de passer au sprint 2.

5. Difficultés rencontrées

Gestion des chemins pour les fichiers ASCII-Art.

Mise en place des setters PV et expérience pour déclencher le level-up automatiquement.

Compréhension du mot-clé this pour référencer l’objet courant.

Gestion de l’inventaire et des équipes de monstres dans Entraineur.

Ces difficultés m’ont obligé à relire la documentation Kotlin et à tester beaucoup d’exemples pour comprendre.

6. Conclusion du Sprint 1

Le noyau du projet est opérationnel.

Les classes principales sont créées avec documentation et tests.

J’ai compris le fonctionnement des classes, objets, packages et tests en Kotlin. ```
