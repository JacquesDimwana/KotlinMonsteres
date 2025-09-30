package org.example.monde

import org.example.IndividuMonstre
import org.example.combat.CombatMonstre
import org.example.dresseur.Entraineur
import org.example.monstres.EspeceMonstre
import kotlin.random.Random

class Zone(
    val id : Int,
    val nom : String,
    val expZone : Int,
    val especesMonstres: MutableList<EspeceMonstre> = mutableListOf(),
    var zoneSuivante: Zone? = null,
    var zonePrecedente: Zone? = null
) {
    /**
     * Génère un monstre sauvage aléatoire dans la zone.
     */
    fun genereMonstre(): IndividuMonstre {
        val espece = especesMonstres.random()           // utiliser la propriété de la classe
        val variation = Random.nextDouble(0.8, 1.2)
        val expInit = expZone * variation               // utiliser la propriété de la classe
        return IndividuMonstre(
            id = Random.nextInt(1000, 9999),
            nom = "${espece.nom} sauvage",
            espece = espece,
            entraineur = null,
            expInit = expInit
        )
    }
    fun rencontreMonstre(joueur: Entraineur) {
        // Générer un monstre sauvage aléatoire grâce à la méthode genereMonstre()
        val monstreSauvage = genereMonstre()

        // Chercher le premier monstre du joueur qui a encore des PV > 0
        val premierMonstre = joueur.equipeMonstre.firstOrNull { it.pv > 0 }

        if (premierMonstre != null) {
            // Créer un combat entre le monstre du joueur et le monstre sauvage
            val combat = CombatMonstre(
                joueur = joueur,
                monstreJoueur = premierMonstre,
                monstreSauvage = monstreSauvage
            )

            // Lancer le combat
            combat.lancerCombat()
        } else {
            // Aucun monstre jouable → le combat ne peut pas se dérouler
            println("Tous vos monstres sont KO, vous ne pouvez pas combattre.")
        }
    }
}