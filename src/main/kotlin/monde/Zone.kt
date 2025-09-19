package org.example.monde

import org.example.monstres.EspeceMonstre

class Zone(
    val id : Int,
    val nom :String,
    val expZone :Int,
    val especesMonstres: MutableList<EspeceMonstre> = mutableListOf(),
    var zoneSuivante: Zone? = null,
    var zonePrecedente: Zone? = null,
)
//    Ajoutez deux TODO : - faire la méthode genereMonstre()
//    - faire la méthode rencontreMonstre()

    fun genereMonstre(){
      // à implémenter
    }
    fun rencontreMonstre(){
        // à implémenter
    }


