package com.example.calendrier_graphique.modele;


import java.util.Calendar;

/**
 * La classe DateCalendreir est héritée de la classe Date, elle ajoute
 * le numéro du jour de la semaine et le numéro de semaine de la date
 */
public class DateCalendrier extends Date implements ConstantesCalendrier, Comparable <Date>  {

    private int jourSemaine;
    private int weekOfYear ;

    /**
     * Ce constructeur donne la date d'aujourd'hui et définit automatiquement
     * le jour, le mois, l'année, la semaine de l'année et le jour de la semaine
     */
    public DateCalendrier () {
        Calendar dateAuj = Calendar.getInstance();
        chAnnee = dateAuj.get (Calendar.YEAR);
        chMois = dateAuj.get (Calendar.MONTH) + 1;
        chJour = dateAuj.get (Calendar.DAY_OF_MONTH);
        jourSemaine = dateAuj.get (Calendar.DAY_OF_WEEK);
        if (jourSemaine == 1)
            jourSemaine = 7;
        else jourSemaine -= 1;
        weekOfYear = dateAuj.get (Calendar.WEEK_OF_YEAR);
    }

    /**
     * Ce constructeur permet de définir une DateCalendrier
     * en entrant les caractéristiques d'une Date, c'est-à-dire
     * le jour, le mois et l'année.
     * Le jour de la semaine et la semaine de l'année sont calculés automatiquement
     *
     * @param parJour Le jour de la date, en integer et compris entre 1 et 31 (variable selon la combinaison du mois et de l'année)
     * @param parMois Le mois de la date, en integer et compris entre 1 et 12
     * @param parAnnee L'année de la date, en integer et supérieure ou égale à 1583
     */
    public DateCalendrier (int parJour, int parMois, int parAnnee) {
        super(parJour, parMois, parAnnee);
        Calendar date = Calendar.getInstance();
        date.set(chAnnee,chMois-1,chJour);
        jourSemaine = date.get (Calendar.DAY_OF_WEEK);
        if (jourSemaine == 1)
            jourSemaine = 7;
        else jourSemaine -= 1;
        weekOfYear = date.get (Calendar.WEEK_OF_YEAR);
    }

    /**
     * Méthode toString qui renvoie le jour de la semaine, le jour et le mois de la date
     *
     * @return String, renvoie les informations de la DateCalendrier
     */
    public String toString () {
        return  JOURS_SEMAINE [jourSemaine -1] + " " + chJour + " " + MOIS [chMois-1];
    }

    public int getJourSemaine () {
        return jourSemaine;
    }

    /**
     * Cette méthode permet de vérifier si une DateCalendrier correspond à la date d'aujourd'hui
     *
     * @return Booléen, true si c'est aujourd'hui et false sinon
     */
    public boolean isToday() {
        return this.compareTo(new DateCalendrier()) == 0;
    }

    public int getWeekOfYear() {
        return weekOfYear;
    }

    /**
     * Cette méthode permet d'obtenir le lendemain d'une DateCalendrier
     *
     * @return DateCalendrier, 1 jour plus tard que celle rentrée
     */
    public DateCalendrier dateDuLendemain () {
        Date dateLendemain =  super.dateDuLendemain();
        return new DateCalendrier (dateLendemain.chJour,dateLendemain.chMois,dateLendemain.chAnnee);
    }

    /**
     * Cette méthode permet d'obtenir la veille d'une DateCalendrier
     *
     * @return DateCalendrier, 1 jour plus tôt que celle rentrée
     */
    public DateCalendrier dateDeLaVeille () {
        Date dateVeille =  super.dateDeLaVeille();
        return new DateCalendrier (dateVeille.chJour,dateVeille.chMois,dateVeille.chAnnee);
    }
}