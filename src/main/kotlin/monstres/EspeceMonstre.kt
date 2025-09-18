package org.example.monstres

import java.io.File
/**
 * Classe EspeceMonstre
 *
 * Représente une espèce de monstre (et non un individu précis).
 * Elle définit les caractéristiques communes de l’espèce : statistiques de base,
 * multiplicateurs de croissance, type, description et art ASCII.
 *
 * Propriétés principales :
 * @property id Identifiant unique de l’espèce (clé primaire).
 * @property nom Nom de l’espèce de monstre (exemple : "Dragonfeu").
 * @property type Type élémentaire de l’espèce (exemple : "Feu", "Eau", "Plante").
 *
 * Statistiques de base :
 * @property baseAttaque Puissance physique de base (dégâts infligés par les attaques directes).
 * @property baseDefense Résistance physique de base (réduction des dégâts reçus).
 * @property baseVitesse Vitesse de base (détermine l’ordre des actions en combat).
 * @property baseAttaqueSpe Puissance magique/élémentaire de base.
 * @property baseDefenseSpe Résistance aux attaques spéciales (magiques ou élémentaires).
 * @property basePv Points de vie de base (endurance de l’espèce).
 *
 * Modificateurs de croissance :
 * @property modAttaque Multiplicateur appliqué à l’attaque.
 * @property modDefense Multiplicateur appliqué à la défense.
 * @property modVitesse Multiplicateur appliqué à la vitesse.
 * @property modAttaqueSpe Multiplicateur appliqué à l’attaque spéciale.
 * @property modDefenseSpe Multiplicateur appliqué à la défense spéciale.
 * @property modPv Multiplicateur appliqué aux points de vie.
 *
 * Informations descriptives :
 * @property description Description textuelle de l’espèce (apparence, habitat, comportement).
 * @property particularites Particularités propres à l’espèce (exemple : "Résistant au feu").
 * @property caractères Traits de caractère dominants (exemple : "agressif", "protecteur").
 *
 * Méthode :
 * @function afficheArt Affiche la représentation ASCII de l’espèce.
 *  - Si deFace = true → vue de face.
 *  - Si deFace = false → vue de dos.
 *  Les fichiers ASCII sont lus dans le dossier resources/art/nom_du_monstre/front.txt ou back.txt.
 */

class EspeceMonstre (
    var id : Int,
    var nom: String,
    var type: String,
    val baseAttaque: Int,
    val baseDefense: Int,
    val baseVitesse: Int,
    val baseAttaqueSpe: Int,
    val baseDefenseSpe: Int,
    val basePv: Int,
    val modAttaque: Double,
    val modDefense: Double,
    val modVitesse: Double,
    val modAttaqueSpe: Double,
    val modDefenseSpe: Double,
    val modPv: Double,
    val description: String = "",
    val particularites: String = "",
    val caractères: String = "",
)

/**
 * Affiche la représentation artistique ASCII du monstree.
 *
 * @param deFace Détermine si l'art affiché est de face (true) ou de dos (false).
 *               La valeur par défaut est true.
 * @return Une chaîne de caractères contenant l'art ASCII du monstre avec les codes couleur ANSI.
 *         L'art est lu à partir d'un fichier texte dans le dossier resources/art.
 */
{
    fun afficheArt(deFace: Boolean = true): String {
        val nomFichier = if (deFace) "front" else "back";
        val art = File("src/main/resources/art/${this.nom.lowercase()}/$nomFichier.txt").readText()
        val safeArt = art.replace("/", "∕")
        return safeArt.replace("\\u001B", "\u001B")
    }
}