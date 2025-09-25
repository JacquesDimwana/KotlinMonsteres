package org.example.item

import org.example.IndividuMonstre
import org.example.dresseur.Entraineur
import org.intellij.lang.annotations.Identifier
import javax.management.Descriptor
open class Item(
    var id: Int,
    var nom: String,
    var description: String,
)

class Badge(
    id: Int,
    nom: String,
    description: String,
    var champion: Entraineur
) : Item(id, nom, description)
