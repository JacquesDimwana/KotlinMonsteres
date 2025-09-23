package org.example.combat

import org.example.IndividuMonstre
import org.example.dresseur.Entraineur
import org.example.item.Utilisable
import kotlin.system.exitProcess

class CombatMonstre(
    val joueur: Entraineur,
    var monstreJoueur: IndividuMonstre,
    var monstreSauvage: IndividuMonstre
) {
    var round: Int = 1

    /**
     * Vérifie si le joueur a perdu le combat.
     * Retourne vrai si aucun monstre de l'équipe du joueur n'a de PV > 0
     */
    fun gameOver(): Boolean {
        return joueur.equipeMonstre.all { it.pv <= 0 }
    }

    /**
     * Vérifie si le joueur a gagné le combat.
     * Victoire si le monstre sauvage est KO ou capturé.
     */
    fun joueurGagne(): Boolean {
        return monstreSauvage.pv <= 0 || monstreSauvage.entraineur != null
    }

    /**
     * Action de l'adversaire : attaque si PV > 0
     */
    fun actionAdversaire() {
        if (monstreSauvage.pv > 0) {
            monstreSauvage.attaquer(monstreJoueur)
        }
    }

    /**
     * Action du joueur : attaque, utilise un item ou change de monstre
     * Retourne false si le combat doit s'arrêter, true sinon
     */
    fun actionJoueur(): Boolean {
        println("Que voulez-vous faire ?")
        println("1 - Attaquer")
        println("2 - Utiliser un item")
        println("3 - Changer de monstre")
        print("Votre choix : ")
        val choix = readLine()?.toIntOrNull() ?: 1

        when (choix) {
            1 -> {
                monstreJoueur.attaquer(monstreSauvage)
            }
            2 -> {
                if (joueur.sacAItems.isEmpty()) {
                    println("Vous n'avez aucun item !")
                } else {
                    println("Items disponibles :")
                    joueur.sacAItems.forEachIndexed { index, item ->
                        println("${index + 1} - ${item.nom}")
                    }
                    print("Choisissez un item : ")
                    val itemIndex = (readLine()?.toIntOrNull() ?: 1) - 1
                    val item = joueur.sacAItems.getOrNull(itemIndex)
                    if (item != null && item is Utilisable) {
                        val capture = item.utiliser(monstreSauvage)
                        if (capture) {
                            monstreSauvage.entraineur = joueur
                            println("Monstre capturé ! Vous gagnez le combat.")
                            return false
                        }
                    } else {
                        println("Item non utilisable ou invalide.")
                    }
                }
            }
            3 -> {
                println("Choisissez un monstre de votre équipe :")
                joueur.equipeMonstre.forEachIndexed { index, m ->
                    println("${index + 1} - ${m.nom} (PV: ${m.pv}/${m.pvMax})")
                }
                print("Votre choix : ")
                val choixMonstre = (readLine()?.toIntOrNull() ?: 1) - 1
                val nouveauMonstre = joueur.equipeMonstre.getOrNull(choixMonstre)
                if (nouveauMonstre != null && nouveauMonstre.pv > 0) {
                    monstreJoueur = nouveauMonstre
                    println("Vous changez de monstre : ${monstreJoueur.nom}")
                } else {
                    println("Choix invalide ou PV = 0")
                }
            }
        }
        return true
    }

    /**
     * Affiche les informations des deux monstres et leur ASCII art
     */
    fun afficheCombat() {
        println("=== Round $round ===")
        println("Votre monstre : ${monstreJoueur.nom} (PV: ${monstreJoueur.pv}/${monstreJoueur.pvMax})")
        println(monstreJoueur.espece.afficheArt())
        println("Monstre sauvage : ${monstreSauvage.nom} (PV: ${monstreSauvage.pv}/${monstreSauvage.pvMax})")
        println(monstreSauvage.espece.afficheArt())
    }

    /**
     * Déroule un round complet
     */
    fun jouer() {
        afficheCombat()

        val joueurAvant = monstreJoueur.vitesse >= monstreSauvage.vitesse

        if (joueurAvant) {
            if (!actionJoueur()) return
            actionAdversaire()
        } else {
            actionAdversaire()
            if (!actionJoueur()) return
        }

        println("Fin du round $round")
    }

    /**
     * Lance le combat tant que gameOver() et joueurGagne() sont faux
     */
    fun lancerCombat() {
        while (!gameOver() && !joueurGagne()) {
            jouer()
            println("======== Fin du Round : $round ========")
            round++
        }
        if (gameOver()) {
            joueur.equipeMonstre.forEach { it.pv = it.pvMax }
            println("Game Over ! Tous les monstres ont été soignés.")
        } else {
            println("Vous avez gagné le combat !")
            if (monstreSauvage.pv <= 0) {
                monstreJoueur.experience += 50.0 // exemple d'XP
            }
        }
    }
}
