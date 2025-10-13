package org.example.monstres

import org.example.IndividuMonstre

class PalierEvolution(
    val id: Int,
    val niveauRequis: Int,
    val evolution: EspeceMonstre
) {
    /**
     * Vérifie si un individu peut évoluer selon ce palier.
     *
     * @param individu Le monstre à vérifier.
     * @return true si le niveau du monstre est supérieur ou égal au niveau requis, sinon false.
     */
    fun peutEvoluer(individu: IndividuMonstre): Boolean {
        return individu.niveau >= niveauRequis
    }
}