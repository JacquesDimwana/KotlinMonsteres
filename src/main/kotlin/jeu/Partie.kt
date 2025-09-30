package org.example.jeu

import org.example.IndividuMonstre
import org.example.dresseur.Entraineur
import org.example.especeAquamy
import org.example.especeFlamkip
import org.example.especeSpringLeaf
import org.example.monde.Zone

class Partie(
    val id: Int,
    val joueur: Entraineur,
    var zone: Zone
) {

    fun choixStarter() {
        // Création des individus à partir des espèces déjà existantes
        val monstre1 = IndividuMonstre(1, "springleaf", especeSpringLeaf, org.example.joueur, 0.0)
        val monstre2 = IndividuMonstre(2, "flamkip", especeFlamkip, org.example.joueur, 0.0)
        val monstre3 = IndividuMonstre(3, "aquamy", especeAquamy, org.example.joueur, 0.0)


        // Affichage des détails
        println("Voici les starters disponibles :")
        println("1 -")
        monstre1.afficheDetail()
        println("\n2 -")
        monstre2.afficheDetail()
        println("\n3 -")
        monstre3.afficheDetail()

        // Choix du joueur
        var choixSelection: Int
        do {
            println("\nChoisissez votre starter (1 = Springleaf, 2 = Flamkip, 3 = Aquamy) : ")
            choixSelection = readLine()?.toIntOrNull() ?: -1
        } while (choixSelection !in 1..3)

        // Sélection
        val starter = when (choixSelection) {
            1 -> monstre1
            2 -> monstre2
            else -> monstre3
        }

        // Renommer le starter
        starter.renommer()

        // Ajout à l’équipe du joueur
        joueur.equipeMonstre.add(starter)

        // Attribution de l’entraîneur
        starter.entraineur = joueur

        println("\nFélicitations ${joueur.nom} ! Tu commences ton aventure avec ${starter.nom} 🎉")
    }

    fun modifierOrdreEquipe() {
        val equipe = joueur.equipeMonstre

        // Vérif : il faut au moins 2 monstres pour réorganiser
        if (equipe.size < 2) {
            println("Impossible de modifier l'ordre : il n'y a qu'un seul monstre dans l'équipe.")
            return
        }

        // Afficher l'équipe
        println("Équipe actuelle :")
        for ((index, monstre) in equipe.withIndex()) {
            println("${index + 1} - ${monstre.nom} (Niveau ${monstre.niveau})")
        }

        // Demande de la position du premier monstre
        var pos1: Int
        do {
            print("Entrez la position du monstre à déplacer : ")
            pos1 = readLine()?.toIntOrNull() ?: -1
        } while (pos1 !in 1..equipe.size)

        // Demande de la nouvelle position
        var pos2: Int
        do {
            print("Entrez la nouvelle position : ")
            pos2 = readLine()?.toIntOrNull() ?: -1
        } while (pos2 !in 1..equipe.size)

        // Échange des deux monstres
        val temp = equipe[pos1 - 1]
        equipe[pos1 - 1] = equipe[pos2 - 1]
        equipe[pos2 - 1] = temp

        println("✅ Ordre de l’équipe modifié avec succès !")
        println("Nouvelle équipe :")
        for ((index, monstre) in equipe.withIndex()) {
            println("${index + 1} - ${monstre.nom} (Niveau ${monstre.niveau})")
        }


        fun examineEquipe(joueur: Entraineur) {
            while (true) {
                println("=== Équipe de ${joueur.nom} ===")
                joueur.equipeMonstre.forEachIndexed { index, monstre ->
                    println("${index + 1} - ${monstre.nom} (PV: ${monstre.pv}/${monstre.pvMax})")
                }

                println("\nTapez le numéro d’un monstre pour voir ses détails, 'm' pour modifier l’ordre, 'q' pour quitter :")
                val input = readLine()?.trim()?.lowercase() ?: ""

                when {
                    input == "q" -> {
                        println("Retour au menu principal...")
                        return
                    }
                    input == "m" -> {
                        modifierOrdreEquipe()
                    }
                    input.toIntOrNull() != null -> {
                        val choix = input.toInt()
                        if (choix in 1..joueur.equipeMonstre.size) {
                            println("=== Détails de ${joueur.equipeMonstre[choix - 1].nom} ===")
                            joueur.equipeMonstre[choix - 1].afficheDetail()
                        } else {
                            println("Numéro invalide.")
                        }
                    }
                    else -> println("Entrée non reconnue, réessayez.")
                }
            }
        }

        /**
         * Permet de modifier l’ordre de deux monstres dans l’équipe du joueur.
         */
        fun modifierOrdreEquipe(joueur: Entraineur) {
            if (joueur.equipeMonstre.size < 2) {
                println("Impossible de modifier l’ordre avec moins de 2 monstres.")
                return
            }

            println("Entrez le numéro du premier monstre à échanger :")
            val first = readLine()?.toIntOrNull()
            println("Entrez le numéro du second monstre à échanger :")
            val second = readLine()?.toIntOrNull()

            if (first != null && second != null &&
                first in 1..joueur.equipeMonstre.size &&
                second in 1..joueur.equipeMonstre.size) {
                val temp = joueur.equipeMonstre[first - 1]
                joueur.equipeMonstre[first - 1] = joueur.equipeMonstre[second - 1]
                joueur.equipeMonstre[second - 1] = temp
                println("Échange effectué !")
            } else {
                println("Numéros invalides, aucun échange réalisé.")
            }

            /**
             * Affiche l'équipe de monstres du joueur et permet de consulter les détails
             * ou de modifier l'ordre des monstres.
             */

            fun examineEquipe(joueur: Entraineur) {
                while (true) { // Boucle infinie jusqu'à ce que l'utilisateur décide de quitter
                    println("=== Équipe de ${joueur.nom} ===")

                    // Affiche tous les monstres de l'équipe avec leur position et PV
                    joueur.equipeMonstre.forEachIndexed { index, monstre ->
                        println("${index + 1} - ${monstre.nom} (PV: ${monstre.pv}/${monstre.pvMax})")
                    }

                    // Instructions à l'utilisateur
                    println("\nTapez le numéro d’un monstre pour voir ses détails, 'm' pour modifier l’ordre, 'q' pour quitter :")
                    val input = readLine()?.trim()?.lowercase() ?: "" // Lecture et normalisation de l'entrée

                    when {
                        input == "q" -> { // Si l'utilisateur veut quitter
                            println("Retour au menu principal...")
                            return
                        }
                        input == "m" -> { // Si l'utilisateur veut modifier l'ordre des monstres
                            modifierOrdreEquipe(joueur)
                        }
                        input.toIntOrNull() != null -> { // Si l'utilisateur entre un numéro
                            val choix = input.toInt()
                            // Vérifie que le numéro est valide
                            if (choix in 1..joueur.equipeMonstre.size) {
                                println("=== Détails de ${joueur.equipeMonstre[choix - 1].nom} ===")
                                // Affiche les détails du monstre sélectionné
                                joueur.equipeMonstre[choix - 1].afficheDetail()
                            } else {
                                println("Numéro invalide.")
                            }
                        }
                        else -> println("Entrée non reconnue, réessayez.") // Cas d'une entrée incorrecte
                    }
                }
            }

            /**
             * Permet de modifier l’ordre de deux monstres dans l’équipe du joueur.
             */
            fun modifierOrdreEquipe(joueur: Entraineur) {
                // Vérifie qu'il y a au moins deux monstres pour échanger
                if (joueur.equipeMonstre.size < 2) {
                    println("Impossible de modifier l’ordre avec moins de 2 monstres.")
                    return
                }

                // Demande à l'utilisateur le numéro du premier monstre
                println("Entrez le numéro du premier monstre à échanger :")
                val first = readLine()?.toIntOrNull() // Convertit l'entrée en entier, null si invalide

                // Demande à l'utilisateur le numéro du second monstre
                println("Entrez le numéro du second monstre à échanger :")
                val second = readLine()?.toIntOrNull()

                // Vérifie que les deux numéros sont valides et dans la plage de l'équipe
                if (first != null && second != null &&
                    first in 1..joueur.equipeMonstre.size &&
                    second in 1..joueur.equipeMonstre.size) {

                    // Échange les deux monstres dans la liste
                    val temp = joueur.equipeMonstre[first - 1]
                    joueur.equipeMonstre[first - 1] = joueur.equipeMonstre[second - 1]
                    joueur.equipeMonstre[second - 1] = temp

                    println("Échange effectué !")
                } else {
                    println("Numéros invalides, aucun échange réalisé.")
                }
            }

        }


    }
    fun Entraineur.examineEquipe() {
        while (true) {
            println("===== Votre équipe =====")
            // Affiche la liste des monstres avec leur position dans l'équipe
            equipeMonstre.forEachIndexed { index, monstre ->
                println("${index + 1} => ${monstre.nom} (PV: ${monstre.pv})")
            }

            // Propose les options au joueur
            println("Tapez le numéro d'un monstre pour voir le détail")
            println("Tapez 'm' pour modifier l'ordre des monstres")
            println("Tapez 'q' pour retourner au menu principal")

            when (val choix = readLine()) {
                "q" -> {
                    // Sort de la fonction et retourne au menu principal
                    return
                }
                "m" -> {
                    // Appel d'une fonction pour modifier l'ordre des monstres
                    modifierOrdreEquipe()
                }
                else -> {
                    // Vérifie si l'entrée est un numéro valide
                    val numero = choix?.toIntOrNull()
                    if (numero != null && numero in 1..equipeMonstre.size) {
                        val monstre = equipeMonstre[numero - 1]
                        // Affiche tous les détails du monstre sélectionné
                        println("===== Détails du monstre =====")
                        println("Nom: ${monstre.nom}")
                        println("Espèce: ${monstre.espece.nom}")
                        println("PV: ${monstre.pv}")
                        println("Attaque: ${monstre.attaque}")
                        println("Défense: ${monstre.defense}")
                        println("Art du monstre: \n${monstre.espece.afficheArt()}")
                    } else {
                        println("Entrée invalide, veuillez réessayer.")
                    }
                }
            }
        }
    }

    // Exemple de fonction pour modifier l'ordre des monstres
    fun Entraineur.modifierOrdreEquipe() {
        println("Modification de l'ordre de l'équipe :")
        // On pourrait par exemple demander à l'utilisateur d'entrer les positions à échanger
        println("Entrez le numéro du premier mon")


    }
    fun jouer(joueur: Entraineur, zoneActuelle: Zone) {
        var zoneCourante = zoneActuelle   // On garde la zone où le joueur se trouve actuellement
        var continuer = true              // Variable pour contrôler la boucle du jeu

        while (continuer) {
            // Affiche la zone actuelle et les options disponibles
            println("Vous êtes dans la zone : ${zoneCourante.nom}")
            println("Que voulez-vous faire ?")
            println("1 => Rencontrer un monstre sauvage")
            println("2 => Examiner votre équipe de monstres")
            println("3 => Aller à la zone suivante")
            println("4 => Aller à la zone précédente")
            println("q => Quitter le jeu")

            // Lecture de la commande du joueur
            when (readLine()) {
                "1" -> {
                    // Rencontre un monstre sauvage dans la zone actuelle
                    zoneCourante.rencontreMonstre(joueur)
                }
                "2" -> {
                    // Examine l'équipe du joueur
                    joueur.examineEquipe()
                }
                "3" -> {
                    // Vérifie si la zone suivante existe
                    if (zoneCourante.zoneSuivante != null) {
                        zoneCourante = zoneCourante.zoneSuivante!!   // Change de zone
                        println("Vous vous déplacez vers la zone suivante : ${zoneCourante.nom}")
                    } else {
                        println("Il n'y a pas de zone suivante.")
                    }
                }
                "4" -> {
                    // Vérifie si la zone précédente existe
                    if (zoneCourante.zonePrecedente != null) {
                        zoneCourante = zoneCourante.zonePrecedente!! // Change de zone
                        println("Vous retournez à la zone précédente : ${zoneCourante.nom}")
                    } else {
                        println("Il n'y a pas de zone précédente.")
                    }
                }
                "q" -> {
                    // Quitte la boucle et donc le jeu
                    println("Vous quittez le jeu.")
                    continuer = false
                }
                else -> {
                    // Gestion des entrées incorrectes
                    println("Commande inconnue, veuillez réessayer.")
                }
            }
        }
    }

}