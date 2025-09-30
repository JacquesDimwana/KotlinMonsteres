package org.example

import org.example.combat.CombatMonstre
import org.example.dresseur.Entraineur
import org.example.item.Badge
import org.example.item.MonsterKube
import org.example.item.Potion
import org.example.monde.Zone
import org.example.monstres.EspeceMonstre


/**
     * Change la couleur du message donné selon le nom de la couleur spécifié.
     * Cette fonction utilise les codes d'échappement ANSI pour appliquer une couleur à la sortie console. Si un nom de couleur
     * non reconnu ou une chaîne vide est fourni, aucune couleur n'est appliquée.
     *
     * @param message Le message auquel la couleur sera appliquée.
     * @param couleur Le nom de la couleur à appliquer (ex: "rouge", "vert", "bleu"). Par défaut c'est une chaîne vide, ce qui n'applique aucune couleur.
     * @return Le message coloré sous forme de chaîne, ou le même message si aucune couleur n'est appliquée.
     */

fun changeCouleur(message: String, couleur:String=""): String {
    val reset = "\u001B[0m"
    val codeCouleur = when (couleur.lowercase()) {
        "rouge" -> "\u001B[31m"
        "vert" -> "\u001B[32m"
        "jaune" -> "\u001B[33m"
        "bleu" -> "\u001B[34m"
        "magenta" -> "\u001B[35m"
        "cyan" -> "\u001B[36m"
        "blanc" -> "\u001B[37m"
        "marron" -> "\u001B[33m"

        else -> "" // pas de couleur si non reconnu
    }
    return "$codeCouleur$message$reset"
}

var joueur = Entraineur(1, "Sacha", 100)
var rival = Entraineur(2,"Regis",200)

var especeFlamkip = EspeceMonstre(4,"flamkip","Animal",
    12,8,13,16,
    7,50,105.5,9.5,
    9.5,9.5,6.5,22.0,
    "Petit animal entouré de flammes, déteste le froid.",
    "Sa flamme change d'intensité selon son énergie",
    "impulsif,joueur,loyal")

var especeSpringLeaf= EspeceMonstre (1,"Springleaf","Graine",9,
    11, 10,12,13,60,
    6.5,9.0,8.0,7.0,10.0,
    34.0,"Petir monstre espiègle rond comme une graine, adore le soleil ",
    "Sa feuille sur ca tete indique son humeur","Curieux, amical, timide"
)

var especeAquamy = EspeceMonstre(1,nom="Aquamy",type="Meteo",10,11,
    9,14,14,55,9.0,10.0,
    7.5,12.0,12.0,27.0,
    "Créature vaporeuse semblable à un nuage, produit des gouttes pures.",
    "Fait baisser la température en s’endormant.",
    "Calme, rêveur, mystérieux")

val zoneTropical = Zone(10,"Tropical",19,mutableListOf(especeAquamy, especeSpringLeaf, especeFlamkip))

val monstre1 = IndividuMonstre(1,"springleaf",especeSpringLeaf,joueur,0.0)
val monstre2 = IndividuMonstre(2, "flamkip",  especeFlamkip,joueur,0.0)
val monstre3 = IndividuMonstre(3, "aquamy", especeAquamy,joueur,0.0)

// Création d’un entraineur (champion)
val pierre = Entraineur(1, "Pierre", 100)

// Création d’un badge
val badgeRoche = Badge(
    1,
    "Badge Roche",
    "Permet d’utiliser Flash en dehors des combats",
    pierre
)

// Création d'une potion
val potion = Potion(1, "Potion", "Soigne 20 PV", 20)
// création d'objet objet

val kubeFeu = MonsterKube(
    id = 1,
    nom = "MonsterKube Feu",
    description = "Peut capturer un monstre sauvage",
    chanceCapture = 25.0
)


// Ajout dans le sac de l'entraîneur
val sacha = Entraineur(1, "Sacha", 100)

fun main(){
//    route1.zoneSuivante = route2
//    route2.zonePrecedente = route1
//    println("Monstre 1 : ${monstre1.nom} : ${monstre1.espece.nom} : ${monstre1.experience} :${monstre1.entraineur?.nom}")
//    println("Monstre 2 : ${monstre2.nom} : ${monstre2.espece.nom}: ${monstre2.experience} : ${monstre2.entraineur?.nom}" )
//    println("Monstre 3 : ${monstre3.nom} : ${monstre3.espece.nom}: ${monstre3.experience} : ${monstre3.entraineur?.nom}")

//    monstre1.attaquer(monstre2)
//    monstre2.attaquer(monstre1)
//    monstre1.attaquer(monstre3)
//    monstre3.attaquer(monstre1)
//    monstre1.renommer()
//    monstre2.renommer()
//    monstre3.renommer()
// {println("${especeAquamy.afficheArt()}, ${especeSpringLeaf.afficheArt()}")
//
//
//    println("=== Test affichage des détails ===")
//
//    println("=== Monstre 1 ===")
//    monstre1.afficheDetail()
//
//    println("\n=== Monstre 2 ===")
//    monstre2.afficheDetail()
//
//    println("\n=== Monstre 3 ===")
//    monstre3.afficheDetail()

        // Test d’affichage
//        println("Badge : ${badgeRoche.nom}")
//        println("Description : ${badgeRoche.description}")
//         println("Champion : ${badgeRoche.champion.nom}")
//
//    println("PV avant potion : ${monstre1.pv}/${monstre1.pvMax}")
//    // Utilisation de la potion
//    potion.utiliser(monstre1)
//    println("PV après potion : ${monstre1.pv}/${monstre1.pvMax}")

// Ajout dans le sac de l'entraîneur
//    val sacha = Entraineur(1, "Sacha", 100)
//sacha.sacAItems.add(kubeFeu)
//
                  // lancer le combat
//    // On ajoute un monstre dans l'équipe du joueur
//    joueur.equipeMonstre.add(monstre1)
//
//    // Création d’un monstre sauvage (sans entraîneur)
//    val monstreSauvage = IndividuMonstre(
//        99,
//        "Aquamy sauvage",
//        especeAquamy,
//        null, // pas d'entraîneur car c'est un sauvage
//        0.0
//    )
//
//    // combat
//    val combat = CombatMonstre(
//        joueur = joueur,
//        monstreJoueur = monstre1,
//        monstreSauvage = monstreSauvage
//    )
//
//    // Lancement du combat
//    combat.lancerCombat()

  // Ajouter un monstre vivant dans l'équipe du joueur
  sacha.equipeMonstre.add(monstre1) // monstre1 doit avoir pv > 0
//
// Lancer une rencontre avec un monstre sauvage dans la zone tropicale
    zoneTropical.rencontreMonstre(sacha)
    // Ajout des monstres à l’équipe du joueur



  }



