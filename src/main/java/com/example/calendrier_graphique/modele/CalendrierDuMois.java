package com.example.calendrier_graphique.modele;

import java.util.Collection;
import java.util.TreeSet;

/**
 * La classe CalendrierDuMois définit les dates du mois de l'année souhaitée.
 * Elle contient trois champs : le mois et l'année sous forme d'integer, et
 * un TreeSet contenant des dates de classe DateCalendrier
 */
public class CalendrierDuMois {
    private int mois;
    private int annee;
    private Collection <DateCalendrier> treeSetDate;

    /**
     * Ce constructeur permet de définir toute les dates d'un mois d'une année donné en paramètre
     * et de les ajouter automatiquement au TreeSet de dates sous forme de DateCalendrier
     *
     * @param mois Le mois souhaité, sous forme d'integer et compris entre 1 et 12
     * @param annee L'année souhaitée, sous forme d'integer et supérieure ou égale à 1583
     */
    public CalendrierDuMois (int mois, int annee) {
        this.mois = mois;
        this.annee = annee;
        treeSetDate = new TreeSet <> ();
        DateCalendrier date = new DateCalendrier (1,mois,annee);
        int indiceJour = date.getJourSemaine() ;
        for (int x = indiceJour ; x!=0 ; x--) {
            treeSetDate.add(date);
            date = date.dateDeLaVeille();
        }
        date = new DateCalendrier (2,mois,annee);
        indiceJour = indiceJour % 7 ;
        while (date.getMois () == mois) {
            while(indiceJour<7) {
                treeSetDate.add(date);
                date = date.dateDuLendemain();
                indiceJour++ ;
            }
            indiceJour=0;
        }
    }

    /**
     * Accesseur sur l'ensemble des dates du mois
     *
     * @return TreeSet contenant les dates de ce mois
     */
    public Collection <DateCalendrier> getDates() {
        return treeSetDate;
    }

    /**
     * Méthode toString de CalendrierDuMois
     *
     * @return String donnant la taille puis les dates de ce mois
     */
    public String toString () {
        return treeSetDate.size() + " " +treeSetDate.toString();
    }
}
