package com.example.calendrier_graphique.controleur;

import javafx.event.*;
import javafx.scene.control.*;
import com.example.calendrier_graphique.modele.*;
import com.example.calendrier_graphique.vue.*;

/**
 * La classe Controleur sert à faire le lien entre les vues et les modèles
 * dans le cadre de l'architecture graphique MVC. Elle s'occupe,
 * à l'utilisation des boutons des dates du calendrier et
 * du bouton d'enregistrement du formulaire, de transmettre
 * respectivement la date ou les informations du formulaire aux modèles
 * pour qu'ils les utilisent.
 */
public class Controleur implements EventHandler {
    /**
     * La méthode handle de cette classe est le principal traitement
     * d'un évènement survenant sur les éléments graphiques.
     * Elle s'occupe des actions des ToggleButton représentant les dates
     * et du Button d'enregistrement du formulaire, car ces fonctions
     * envoient des informations entre vues et modèles.
     *
     * @param event : Récupère l'évènement à l'origine du lancement de la méthode
     */
    @Override
    public void handle(Event event) {

        // Récupération des éléments instanciés dans HBoxRoot et concernés par les évènements
        PlanningCollections planning = HBoxRoot.getPlanning();
        GridPaneFormulaireReservation reservationPane = HBoxRoot.getReservationPane();
        VBoxAffichagePlanning affichagePlanning = HBoxRoot.getAffichagePlanning();

        // La source d'event est l'ensemble des ToggleButton du calendrier,
        // ils sont visibles car "armés" avec setOnAction(controleur)
        if (event.getSource() instanceof ToggleButton) {
            ToggleButton selectedButton = (ToggleButton) event.getSource();
            DateCalendrier date = (DateCalendrier) selectedButton.getUserData();
            reservationPane.setDate(date);
            affichagePlanning.actualiserSemaine(date.getWeekOfYear());
        }
        // La source de event est le bouton "Enregistrer" du formulaire de réservation,
        // il est visible car "armé" avec setOnAction(controleur).
        if (event.getSource() instanceof Button) {
            Reservation reservationFormulaire = reservationPane.getReservation();
            planning.ajout(reservationFormulaire);
            affichagePlanning.actualiserSemaine(reservationFormulaire.getChDate().getWeekOfYear());
            System.out.println(planning);
        }
    }
}
