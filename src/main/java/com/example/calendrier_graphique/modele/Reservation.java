package com.example.calendrier_graphique.modele;

/**
 * La classe Reservation permet de définir un évènement sur un laps de temps.
 * Elle a comme caractéristiques un titre sous forme de String, une DateCalendrier et une PlageHoraire
 */
public class Reservation implements Comparable<Reservation> {
    public String chTitre;
    public DateCalendrier chDate;
    public PlageHoraire chPlage;

    /**
     * Ce constructeur permet de configorer tous les paramètres d'une Reservation
     *
     * @param parTitre Le titre de la Reservation sous forme de String
     * @param parDate La DateCalendrier de la Reservation
     * @param parPlage  La PlageHoraire de la Reservation
     * @throws ExceptionReservation Permet d'éviter de créer des Reservation sans titre
     */
    public Reservation (String parTitre, DateCalendrier parDate, PlageHoraire parPlage) throws ExceptionReservation{
        if (parTitre.isEmpty()) {
            throw new ExceptionReservation();
        }
        chTitre = parTitre;
        chDate = parDate;
        chPlage = parPlage;
    }

    public DateCalendrier getChDate() {
        return chDate;
    }

    public String getChTitre() {return chTitre;}

    /**
     * Méthode compareTo qui renvoie la comparaison entre this et la Reservation en paramètre
     *
     * @param parResa La Reservation comparée à this
     * @return Integer, 0 si chevauchement, positif si this est postérieur à parResa
     */
    public int compareTo(Reservation parResa) {
        if (parResa.chDate.compareTo(this.chDate) == 0) {
            return parResa.chPlage.compareTo(this.chPlage);
        }
        else {
            return parResa.chPlage.compareTo(chPlage) + parResa.chDate.compareTo(chDate) * 1440;
        }
    }

    /**
     * Méthode estValide qui vérifie la validité de la DateCalendrier et de la PlageHoraire
     *
     * @return Booléen, true si valide et false sinon
     */
    public boolean estValide() {
        return (chDate.estValide() && chPlage.estValide());
    }

    /**
     * Méthode toString qui renvoie, le titre, la DateCalendrier et la PlageHoraire
     * de la Reservation
     *
     * @return String, les informations de la Reservation
     */
    public String toString() {
        return chTitre + ", " + chDate + ", " + chPlage;
    }
}
