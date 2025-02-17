package com.example.calendrier_graphique.vue;

import com.example.calendrier_graphique.controleur.Controleur;
import javafx.scene.layout.*;
import com.example.calendrier_graphique.modele.*;

/**
 * La classe HBoxRoot gère les liens entre modèle, vue et contrôleur dans le cadre de l'architecture MVC.
 * C'est ici qu'est instanciée l'intégralité des vues, le Controleur et le PlanningCollections contenant les Reservation.
 * Le Controleur peut ainsi récupérer par exemple les informations du formulaire pour ajouter une Reservation
 * sur le PlanningCollections et l'afficher sur le VBoxAffichagePlanning
 */
public class HBoxRoot extends HBox{
    private static PlanningCollections planning = new PlanningCollections();
    private static Controleur controleur = new Controleur();
    private static GridPaneFormulaireReservation reservationPane = new GridPaneFormulaireReservation();
    private static VBoxCalendrier calendrierPane = new VBoxCalendrier();
    private static VBoxAffichagePlanning affichagePlanning = new VBoxAffichagePlanning();

    /**
     * Le constructeur ajoute à HBoxRoot les trois vues afin qu'elles soient visibles sur la fenêtre
     */
    public HBoxRoot() {
        super(40);
        this.getChildren().addAll(calendrierPane, reservationPane, affichagePlanning);
    }

    public static PlanningCollections getPlanning() {
        return planning;
    }

    public static Controleur getControleur() {
        return controleur;
    }

    public static GridPaneFormulaireReservation getReservationPane() {
        return reservationPane;
    }

    public static VBoxAffichagePlanning getAffichagePlanning() {
        return affichagePlanning;
    }
}
