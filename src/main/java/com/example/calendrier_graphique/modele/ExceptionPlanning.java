package com.example.calendrier_graphique.modele;

/**
 * Cette exception s'occupe de toute les erreurs liées à la classe Planning.
 */
public class ExceptionPlanning extends Exception{
    private int chCodeErreur;

    public ExceptionPlanning (int parNbErreur) {
        chCodeErreur = parNbErreur;
    }

    public int getChCodeErreur() {
        return chCodeErreur;
    }
}
