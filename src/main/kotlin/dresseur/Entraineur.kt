package org.example.dresseur

import org.example.IndividuMonstre
import org.example.item.Item


/**
 * Représente un entraîneur dans le contexte du jeuu.
 *
 * Un entraîneur est responsable de gérer une équipe de monstres, une boîte pour stocker des monstres supplémentaires
 * et un sac contenant des objets appelés MonsterKubes. L'entraîneur a également une somme d'argent associée.
 *
 * @property id L'identifiant unique de l'entraîneur.
 * @property nom Le nom de l'entraîneur.
 * @property argents La quantité d'argent en possession de l'entraîneur.

 */

class Entraineur (
    var id: Int,
    var nom: String,
    var argents: Int,
    var equipeMonstre: MutableList<IndividuMonstre> = mutableListOf(),
    var boiteMonstre: MutableList<IndividuMonstre> = mutableListOf(),
    var sacAItems: MutableList<Item> = mutableListOf()
    //TODO sacAKube
    ) {

    /**
     * Affiche les détails de l'entraîneur, y compris son nom et la quantité d'argent en sa possession.
     *
     * Cette méthode affiche les informations de l'entraîneur sous la forme de deux lignes :
     * 1. Le nom de l'entraîneur.
     * 2. La somme d'argent qu'il possède.
     */
    fun afficheDetail() {
        println("Dresseur : ${this.nom}")
        println("Argents: ${this.argents} ")
    }
    fun ajouterMonstre(monstre: IndividuMonstre) {
        if (equipeMonstre.size < 6) {
            equipeMonstre.add(monstre)
            println("${monstre.nom} a été ajouté à l’équipe !")
        } else {
            boiteMonstre.add(monstre)
            println("${monstre.nom} a été envoyé dans la boîte.")
        }
    }

    /**
     * Ajoute un item dans le sac.
     */
    fun ajouterItem(item: Item) {
        sacAItems.add(item)
        println("L’item ${item.nom} a été ajouté au sac.")
    }
}

