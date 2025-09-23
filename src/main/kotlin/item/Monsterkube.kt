package org.example.item

import org.example.IndividuMonstre
import kotlin.random.Random

/**
 * Classe représentant un MonsterKube, un objet pouvant capturer un [IndividuMonstre].
 */
class MonsterKube(
    id: Int,
    nom: String,
    description: String,
    var chanceCapture: Double
) : Item(id, nom, description), Utilisable {

    /**
     * Tente de capturer le [cible] selon la [chanceCapture] et ses PV.
     *
     * @param cible Le monstre à capturer.
     * @return `true` si la capture a réussi, `false` sinon.
     */
    override fun utiliser(cible: IndividuMonstre): Boolean {
        val ratioVie = cible.pv.toDouble() / cible.pvMax
        val chanceEffective = (chanceCapture * (1.5 - ratioVie)).coerceAtLeast(5.0)
        val roll = Random.nextDouble(0.0, 100.0)

        println("Tentative de capture de ${cible.nom} (PV: ${cible.pv}/${cible.pvMax})")
        println("Chance effective: ${"%.2f".format(chanceEffective)}%, Roll: ${"%.2f".format(roll)}")

        return if (roll <= chanceEffective) {
            println("${cible.nom} a été capturé !")
            true
        } else {
            println("${cible.nom} résiste à la capture...")
            false
        }
    }
}

