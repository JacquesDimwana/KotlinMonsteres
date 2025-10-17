# Monster Adventure – Compte rendu des Sprints

---

## 🏹 Sprint 1 – Base du projet et gestion des entraîneurs

### Objectifs
- Créer la structure du projet Kotlin.
- Définir les classes de base : `Entraineur`, `Partie`, `Zone`.
- Connecter le projet à une base de données MySQL via `EntraineurDAO`.
- Préparer les premières interactions avec l’utilisateur.

### Ce que j’ai fait
**1️⃣ Gestion des entraîneurs**
- Classe `Entraineur` : contient id, nom, points de vie, équipe de monstres et inventaire.
- Classe `EntraineurDAO` : CRUD complet pour manipuler les entraîneurs dans la BDD.
- Ajout et mise à jour des entraîneurs :
-  val sacha = Entraineur(1, "Sacha", 100)
 entraineurDAO.save(sacha)
- 2️⃣ Gestion des zones
Classe Zone avec nom, expérience moyenne et liste des espèces de monstres.
Permet de générer un monstre sauvage aléatoire et de lancer un combat.
-          val monstreSauvage = zoneTropical.genereMonstre()
           zoneTropical.rencontreMonstre(joueur)
- 3️⃣ Partie et interactions
Classe Partie : gère la progression du joueur et le choix du starter.
Menu interactif pour se déplacer entre les zones, examiner l’équipe, lancer des combats.
  -     val partie = nouvellePartie()
        partie.choixStarter()
        partie.jouer()


- Sprint 3 – Monstres, expérience et combat
Objectifs

- Créer les monstres (EspeceMonstre et IndividuMonstre).
- Implémenter l’expérience, le level-up et l’évolution.
- Afficher les détails et l’ASCII art des monstres.
- Préparer le système de combat et de capture.

- Ce que j’ai fait

1️⃣ Création des monstres
- EspeceMonstre : statistiques de base, type, art ASCII et description.
- IndividuMonstre : niveau, PV, statistiques, expérience et potentiel.
### Exemple : Création d'une espèce et d'un monstre

// Définition de l'espèce Aquamy
val especeAquamy = EspeceMonstre(
    id = 1,
    nom = "Aquamy",
    type = "Meteo",
    baseAttaque = 10,
    baseDefense = 11,
    baseVitesse = 9,
    baseAttaqueSpe = 14,
    baseDefenseSpe = 14,
    basePv = 55,
    modAttaque = 9.0,
    modDefense = 10.0,
    modVitesse = 7.5,
    modAttaqueSpe = 12.0,
    modDefenseSpe = 12.0,
    modPv = 27.0,
    description = "Créature vaporeuse semblable à un nuage, produit des gouttes pures.",
    particularites =

- 2⃣ Level-up automatique
- L’expérience déclenche automatiquement le niveau supérieur si le palier est atteint.
- Les stats augmentent selon le potentiel et un facteur aléatoire.

//
monstre.experience += 500.0  // Déclenche le level-up
println("Niveau : ${monstre.niveau}")
println("Stats : ATQ ${monstre.attaque}, PV ${monstre.pv}/${monstre.pvMax}")
//

3️⃣ Affichage détaillé des monstres
afficheDetail() montre :
Nom, espèce, type
PV et statistiques
Entraîneur
ASCII art

monstre.afficheDetail()


4️⃣ Combat simplifié

Actions possibles :
Attaquer, utiliser un item, changer de monstre.
Dégâts calculés : attaque - (défenseCible / 2).
Le monstre sauvage attaque ensuite selon la vitesse.

val combat = CombatMonstre(joueur, monstreJoueur = monstre, monstreSauvage = monstreSauvage)
combat.lancerCombat()

5)Gestion de l’inventaire
Les items (Potion, MonsterKube) permettent :
Soigner un monstre (Potion)
Capturer un monstre sauvage (MonsterKube)

val potion = Potion(1, "Potion", "Soigne 20 PV", 20)
potion.utiliser(monstre)

val kubeFeu = MonsterKube(1, "MonsterKube Feu", "Capture un monstre sauvage", 25.0)
joueur.sacAItems.add(kubeFeu)

zzz
```kotlin
// Exemple : récupérer tous les entraîneurs
val listeEntraineur = entraineurDAO.findAll()

