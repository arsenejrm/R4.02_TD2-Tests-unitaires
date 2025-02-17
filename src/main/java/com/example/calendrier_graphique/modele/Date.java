package com.example.calendrier_graphique.modele;

import java.util.Calendar;

/**
 * La classe Date définit une date à partir d'un jour, d'un mois et d'une année
 * Elle sont validables et comparables entre elles chronologiquement
 * On peut obtenir la date courante, le lendemain, la veille , la validité et le dernier jour du mois d'une date,
 * ainsi que savoir si elle est tombée sur une année bissextile
 */
public class Date {
    protected int chJour;
    protected int chMois;
    protected int chAnnee;

    /**
     * Ce constructeur permet de définir tous les paramètres d'une date (jour, mois, année)
     *
     * @param parJour Le jour de la date, en integer et compris entre 1 et 31 (variable selon le mois et l'année)
     * @param parMois Le mois de la date, en integer et compris entre 1 et 12
     * @param parAnnee L'année de la date, en integer et supérieure ou égale à 1583
     */
    public Date(int parJour, int parMois, int parAnnee)  {
        chJour = parJour ;
        chMois = parMois ;
        chAnnee = parAnnee ;
    }

    /**
     * Ce constructeur permet de définir le premier jour d'une année
     *
     * @param parAnnee L'année de la date, en integer et supérieure ou égale à 1583
     */
    public Date(int parAnnee)  {
        chJour = 1 ;
        chMois = 1 ;
        chAnnee = parAnnee ;
    }

    /**
     * Ce constructeur renvoie le jour d'aujourd'hui
     */
    public Date() {
        Calendar dateAuj = Calendar.getInstance();
        chAnnee = dateAuj.get (Calendar.YEAR);
        chMois = dateAuj.get (Calendar.MONTH) + 1;
        chJour = dateAuj.get (Calendar.DAY_OF_MONTH);
    }

    /**
     * Cette méthode permet de tester la validité d'une date.
     * Elle est considérée valide lorsque l'année est supérieure ou égale à 1583,
     * lorsque le mois est compris entre 1 et 12 et
     * lorsque le jour est compris entre 1 et le nombre de jours du mois désigné,
     * ce qui varie entre 28 et 31 selon la combinaison de mois et d'année.
     *
     * @return Booléen, true si valide et false sinon
     */
    public boolean estValide() {
        return chAnnee > 1582 &&
                chMois >= 1 && chMois <= 12 &&
                chJour >= 1 && chJour <= Date.dernierJourDuMois (chMois, chAnnee) ;
    }

    /**
     * Cette méthode permet de remvoyer le dernier jour du mois d'une date,
     * c'est à dire le nombre de jours qu'il y a dans le mois de l'année donné
     *
     * @param parMois Le mois, en integer et compris entre 1 et 12
     * @param parAnnee L'année, en integer et supérieure ou égale à 1583
     * @return Le dernier jour du mois ou le nombre de jours qu'il comporte,
     * en integer et compris entre 28 et 31
     */
    protected static int dernierJourDuMois (int parMois, int parAnnee) {
        switch (parMois) {
            case 2 : if (Date.estBissextile (parAnnee))
                return 29 ;
                return 28 ;
            case 4 :
            case 6 :
            case 9 :
            case 11 : return 30 ;
            default : return 31 ;
        }
    }

    /**
     * Cette méthode permet de vérifier si une année est bissextile ou non
     *
     * @param parAnnee L'année qu'on souhaite vérifier, en integer et supérieure ou égale à 1583
     * @return Booléen, true si l'année est bissextile et false sinon
     */
    private static boolean estBissextile(int parAnnee) {
        return (parAnnee % 4 == 0 && parAnnee % 100 != 0) || parAnnee % 400 == 0;
    }


    /**
     * Cette méthode compare compare les deux Date this et parDate
     *
     * @return Integer, 0 si les deux Date sont égales,
     * positive si this est postérieure à parDate et
     * négative si this est antérieure à parDate
     */
    public int compareTo (Date parDate) {
        if (chAnnee < parDate.chAnnee)
            return -8;
        if (chAnnee > parDate.chAnnee)
            return 19;
        // les années sont =
        if (chMois < parDate.chMois)
            return -1;
        if (chMois > parDate.chMois)
            return 18;
        // les mois sont =
        if (chJour < parDate.chJour)
            return -7;
        if (chJour > parDate.chJour)
            return 12;
        return 0;
    }

    /**
     * Cette méthode donne le lendemain de cette Date selon le calendrier grégorien
     *
     * @return Date, celle suivant la Date rentrée
     */
    public Date dateDuLendemain () {
        if (chJour < Date.dernierJourDuMois(chMois,chAnnee))
            return new Date(chJour+1,chMois,chAnnee);
        if (chMois < 12)
            return  new Date(1,chMois+1,chAnnee);
        return  new Date(1,1,chAnnee+1);
    }

    /**
     * Cette méthode donne la veille de cette Date selon le calendrier grégorien
     *
     * @return Date, celle précédant la date rentrée
     */
    public Date dateDeLaVeille ()  {
        if (chJour > 1)
            return  new Date(chJour-1,chMois,chAnnee);
        if (chMois > 1)
            return new Date(Date.dernierJourDuMois(chMois-1, chAnnee),chMois-1,chAnnee);
        return new Date(31,12,chAnnee-1);
    }

    public int getAnnee() {
        return chAnnee;
    }

    public int getJour() {
        return chJour;
    }

    public int getMois() {
        return chMois;
    }

    /**
     * Méthode toString donnant le jour, le mois et l'année de la Date
     *
     * @return String, sous la forme JJ-MM-AAAA
     */
    public String toString () {
        return  chJour + "-" + chMois + "-" + chAnnee;
    }
}