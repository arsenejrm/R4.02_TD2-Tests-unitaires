package com.example.calendrier_graphique.modele;

import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Iterator;
import java.util.TreeMap;

/**
 * La classe PlanningCollections permet de créer un planning contenant des Reservation.
 * Elle utilise un TreeSet pour stocker les Reservation, ce qui signifie qu'une Reservation
 * qui chevauche une Reservation déjà ajoutée au PlanningCollections ne pourra pas être ajoutée
 */
public class PlanningCollections {
    private ArrayList <Reservation> chArrayReservations;
    private TreeSet <Reservation> chTreeReservations;
    private TreeMap <Integer, TreeSet <Reservation>> chMap = new TreeMap <> ();

    /**
     * Ce constructeur instancie le TreeSet contenant les Reservation
     */
    public PlanningCollections() {
        chArrayReservations = new ArrayList <Reservation> ();
        chTreeReservations = new TreeSet <Reservation> ();
    }

    /**
     * Cette méthode permet d'ajouter une Reservation dans le PlanningCollections
     *
     * @param parReservation La Reservation à ajouter au TreeSet
     */
    public void ajout (Reservation parReservation) {
        int cle = parReservation.getChDate().getJourSemaine();
        if (chMap.containsKey(cle)) {
            TreeSet <Reservation> valeur = chMap.get(cle);
            valeur.add(parReservation);
        }
        else {
            TreeSet <Reservation> nouveau = new TreeSet <Reservation> ();
            nouveau.add(parReservation);
            chMap.put(cle, nouveau);
        }
        chArrayReservations.add(parReservation);
        chTreeReservations.add(parReservation);
    }

    /**
     * Cette méthode permet d'obtenir toute les Reservations contenues dans le PlanningCollections
     *
     * @return TreeSet contenant les Reservation du PlanningCollections
     */
    public TreeSet <Reservation> getReservations () {
        TreeSet <Reservation> dateReservations = new TreeSet <>();
        Iterator <Reservation> treeIterator = chTreeReservations.iterator();
        while (treeIterator.hasNext()) {
            Reservation parResa = treeIterator.next();
            dateReservations.add(parResa);
        }
        return dateReservations;
    }

    /**
     * Cette méthode permet d'obtenir les Reservation contenues dans le PLanningCollections
     * dont le titre contient une chaîne de caractères donnée en paramètre
     *
     * @param parString La chaîne de caractères recherchée dans les titres des Reservation
     * @return TreeSet contenant les Reservations correspondants à la recherche
     */
    public TreeSet <Reservation> getReservations (String parString) {
        TreeSet <Reservation> titreReservations = new TreeSet <>();
        for (Reservation Y:chTreeReservations) {
            String titre = Y.getChTitre();
            if (titre.contains(parString)) {
                titreReservations.add(Y);
            }
        }
        return titreReservations;
    }

    /**
     * Méthode toString qui renvoie les Reservation du PlanningCollections
     * ainsi que le nombre de Reservation enregistrées
     *
     * @return String, les informations du PlanningCollections
     */
    public String toString() {
        return "chMap : " + chMap.toString() + " - Taille : " + chMap.size() + "\n";
    }
}
