package org.example.item

import org.example.IndividuMonstre

/**
 * Exemple de potion qui soigne un [IndividuMonstre].
 */
class Potion(
    id: Int,
    nom: String,
    description: String,
    val soin: Int
) : Item(id, nom, description), Utilisable {

    override fun utiliser(cible: IndividuMonstre): Boolean {
        val pvAvant = cible.pv
        cible.pv += soin
        println("${cible.nom} récupère $soin PV ! (PV : ${cible.pv}/${cible.pvMax})")
        return cible.pv > pvAvant
    }
}
