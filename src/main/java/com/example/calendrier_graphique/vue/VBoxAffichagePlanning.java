package com.example.calendrier_graphique.vue;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import com.example.calendrier_graphique.modele.*;

import java.util.TreeSet;

/**
 * La classe VBoxAffichagePlanning est l'une des trois vues de la fenêtre.
 * Située à droite des deux autres, elle affiche le contenu du PlanningCollections sur une semaine,
 * en fonction de la date sélectionnée dans VBoxCalendrier
 */
public class VBoxAffichagePlanning extends VBox {
    public Label labelSemaine;
    public int valeurSemaine;
    public TreeSet<Reservation> reservationsSemaine;
    public TableView <Reservation> tableDesReservations;
    public TableColumn <Reservation, String> titreColumn = new TableColumn<>("Reservation");
    public TableColumn <Reservation, DateCalendrier> dateColumn = new TableColumn<>("Date");
    public TableColumn <Reservation, PlageHoraire> plageColumn = new TableColumn<>("Horaire");

    /**
     * Le constructeur de VBoxAffichagePlanning construit l'interface graphique
     * qui est composée d'un Label indiquant le numéro de semaine sélectionnée
     * et une TableView affichant les Reservation du PlanningCollections de la semaine
     */
    public VBoxAffichagePlanning() {
        valeurSemaine = 1;
        labelSemaine = new Label("Semaine " + valeurSemaine);
        this.getChildren().add(labelSemaine);
        tableDesReservations = new TableView<>();
        titreColumn.setCellValueFactory(new PropertyValueFactory<>("chTitre"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("chDate"));
        plageColumn.setCellValueFactory(new PropertyValueFactory<>("chPlage"));
        tableDesReservations.getColumns().addAll(titreColumn, dateColumn, plageColumn);
        actualiserReservations(valeurSemaine);
        this.getChildren().add(tableDesReservations);
    }

    /**
     * Cette méthode permet d'actualiser ce qu'affiche le Label et le TableView
     * avec le numéro de semaine indiquée
     *
     * @param valeurSemaine Le numéro de semaine souhaité
     */
    public void actualiserSemaine(int valeurSemaine) {
        this.valeurSemaine = valeurSemaine;
        labelSemaine.setText("Semaine " + valeurSemaine);
        actualiserReservations(valeurSemaine);
    }

    /**
     * Cette méthode permet d'actualiser la liste des Reservation de la semaine avec le numéro de semaine souhaitée.
     * Elle est appelée au changement du numéro de semaine provoquée par actualiserSemaine
     *
     * @param noSemaine Le numéro de semaine souhaité
     */
    public void actualiserReservations(int noSemaine) {
        tableDesReservations.getItems().clear();
        reservationsSemaine = new TreeSet<>();
        for (Reservation reservation : HBoxRoot.getPlanning().getReservations()) {
            if (reservation.getChDate().getWeekOfYear() == noSemaine) {
                tableDesReservations.getItems().add(reservation);
            }
        }
    }
}
