package com.example.calendrier_graphique.modele;

/**
 * La classe PlageHoraire est basées sur deux objets de la classe Horaire pour créer une durée dans le temps
 */
public class PlageHoraire implements Comparable<PlageHoraire> {
    private final static int chMinimum = 5;
    private Horaire chDebut;
    private Horaire chFin;

    /**
     * Ce constructeur permet de définir tous les paramètres d'une PlageHoraire,
     * c'est-à-dire l'horaire de début et celui de fin
     *
     * @param parDebut L'horaire de début, de classe Horaire
     * @param parFin L'horaire de fin, de classe Horaire et plus tardive que celle de début
     * @throws ExceptionPlageHoraire Exception déclenchée si la PlageHoraire n'est pas valide
     */
    public PlageHoraire (Horaire parDebut, Horaire parFin) throws ExceptionPlageHoraire{
        if (!parDebut.estValide() || !parFin.estValide()) {
            throw new ExceptionPlageHoraire();
        }
        if (parDebut.toMinutes() > parFin.toMinutes()) {
            throw new ExceptionPlageHoraire();
        }
        chDebut = parDebut;
        chFin = parFin;
    }

    /**
     * Cette méthode permet de vérifier la validité d'une PlageHoraire, c'est à dire
     * si l'Horaire de début se situe chronologiquement avant l'horaire de fin et si les deux Horaires sont valides.
     *
     * @return Booléen, true si la PlageHoraire est valide, false sinon
     */
    public boolean estValide() {
        if (chFin.toMinutes() >= chDebut.toMinutes() + chMinimum && chDebut.estValide() && chFin.estValide()){
            return true;
        }
        return false;
    }

    /**
     * Cette méthode permet d'obtenir la durée d'une PlageHoraire, c'est-à-dire
     * le temps qui sépare l'Horaire de début de l'Horaire de fin
     *
     * @return Horaire qui représente la durée de la PlageHoraire
     */
    public Horaire duree() {
        if (estValide()) {
            int parDuree = chFin.toMinutes() - chDebut.toMinutes();
            return new Horaire(parDuree);
        }
        else {
            return null;
        }
    }

    /**
     * Méthode de comparaison des PlageHoraire, renvoie 0 si elles se chevauchent,
     * 1 si la fin de l'une est égale au début de l'autre et inversement, ou le nombre de minutes qui les sépare le cas échéant.
     *
     * @param parPlage La PlageHoraire qui sera oparée à this
     * @return Integer, 0 ou 1 si les PlageHoraires sont en contact, ou le nombre de minutes qui les séparent.
     */
    public int compareTo(PlageHoraire parPlage) {
        if (this.chFin.toMinutes() <= parPlage.chDebut.toMinutes()) {
            if (this.chFin.toMinutes() == parPlage.chDebut.toMinutes()) {
                return 1;
            }
            return parPlage.chDebut.toMinutes() - this.chFin.toMinutes();
        }
        if (this.chDebut.toMinutes() >= parPlage.chFin.toMinutes()) {

            if (this.chDebut.toMinutes() == parPlage.chFin.toMinutes()) {
                return 1;
            }
            return parPlage.chFin.toMinutes() - this.chDebut.toMinutes();
        }
        return 0;
    }

    /**
     * Méthode toString de PlageHoraire, renvoie l'Horaire de début, de fin et la durée
     *
     * @return String, les informations de la PlageHoraire
     */
    public String toString() {
        return chDebut.toString() + " - " + chFin.toString() + ", duree : " + duree();
    }
}
