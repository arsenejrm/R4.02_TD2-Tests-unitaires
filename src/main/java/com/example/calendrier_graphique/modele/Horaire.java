package com.example.calendrier_graphique.modele;

/**
 * La classe Horaire permet de produire des heures de la journée.
 *
 */
public class Horaire {
    private int chHeure;
    private int chMinutes;

    /**
     * Ce constructeur permet de rentrer toute les caractéristique d'un Horaire,
     * c'est-à-dire l'heure et le quart d'heure
     *
     * @param parHeure L'heure de l'horaire, en integer et comprise entre 0 et 23
     * @param parMinutes Le quart d'heure de l'horaire, en integer et comprise entre 0 et 55 avec un pas de 5
     */
    public Horaire (int parHeure, int parMinutes) {
        chHeure = parHeure;
        chMinutes = parMinutes;
    }

    /**
     * Ce constructeur permet de rentrer un nombre de minutes pour définir un Horaire
     *
     * @param nbminutes Le nombre de minutes en integer, supérieure à 0 et divisible par 5
     */
    public Horaire (int nbminutes) {
        chHeure = nbminutes / 60;
        chMinutes = nbminutes % 60;
    }

    /**
     * Cette méthode permet de convertir un Horaire en nombre de minutes
     *
     * @return Integer, le nombre de minutes total
     */
    public int toMinutes() { return chHeure*60 + chMinutes; }

    /**
     * Méthode estValide, permet de tester la validité d'un Horaire
     *
     * @return Booléen, true si valide et false sinon
     */
    public boolean estValide() {
        if (0 <= chHeure && chHeure < 24) {
            return 0 <= chMinutes && chMinutes <= 55 && chMinutes % 5 == 0;
        }
        return false;
    }

    /**
     * Méthode toString, renvoie les informations d'un Horaire
     *
     * @return String, sous la forme HHhMM
     */
    public String toString() { return chHeure + "h" + chMinutes; }
}
