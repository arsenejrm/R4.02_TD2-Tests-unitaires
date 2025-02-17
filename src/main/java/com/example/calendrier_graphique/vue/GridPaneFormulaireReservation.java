package com.example.calendrier_graphique.vue;
import com.example.calendrier_graphique.controleur.Controleur;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import com.example.calendrier_graphique.modele.*;

/**
 * La classe GridPaneFormulaireReservation est l'une des trois vues de la fenêtre.
 * Située au milieu des deux autres, elle permet de créer une Reservation sur interface graphique.
 */
public class GridPaneFormulaireReservation extends GridPane{

    DateCalendrier dateReservation = new DateCalendrier();
    Label labelDate = new Label(dateReservation.toString());                                   // Label de l'affichage de la date d'aujourd'hui
    Label labelCours = new Label("Cours");
    TextField textCours = new TextField();
    Label labelHoraire = new Label("Horaire");
    Label labelDe = new Label("de");
    Label labelH1 = new Label("h");
    Label labelH2 = new Label("h");
    Label labelA = new Label("à");
    ChoiceBox<Integer> choixHeure1 = new ChoiceBox<>();
    ChoiceBox<Integer> choixHeure2 = new ChoiceBox<>();
    ChoiceBox<Integer> choixMin1 = new ChoiceBox<>();
    ChoiceBox<Integer> choixMin2 = new ChoiceBox<>();
    Button annuler = new Button("Annuler");
    Button enregistrer = new Button("Enregistrer");

    /**
     * Le constructeur de GridPaneFormulaireReservation construit toute l'interface de la vue
     * et met en action le bouton d'annulation. Elle est composée d'un Label indiquant la date sélectionnée
     * dans le VBoxCalendrier et d'un GridPane contenant les boutons des paramètres de la Reservation
     */
    public GridPaneFormulaireReservation() {
        GridPane formulaire = new GridPane();
        GridPane.setMargin(formulaire, new Insets(10, 10, 10, 10));    // Marge du contour du GridPane
        GridPane.setConstraints(labelDate, 0, 0, 10, 1);                      // Le label date va s'étendre de la case (0, 0) à la case (10, 1)
        formulaire.getChildren().add(labelDate);                                         // Ajout au formulaire du label date
        labelCours.setLabelFor(textCours);
        labelCours.setMnemonicParsing(true);
        GridPane.setConstraints(labelCours, 0, 1);
        GridPane.setMargin(labelCours, new Insets(12, 10, 1, 1));      // Ajout d'une marge de 12px en haut, 10px à gauche, 1px en bas et 1px à gauche
        formulaire.getChildren().add(labelCours);
        GridPane.setConstraints(textCours, 1, 1, 10, 2);
        GridPane.setMargin(textCours, new Insets(9, 1, 35, 1));
        formulaire.getChildren().add(textCours);
        for (int i = 0; i < 24; i++) {
            choixHeure1.getItems().add(i);
            choixHeure2.getItems().add(i);
        }
        for (int i = 0; i < 60; i += 5) {
            choixMin1.getItems().add(i);
            choixMin2.getItems().add(i);
        }
        GridPane.setConstraints(labelHoraire, 0, 3);
        GridPane.setMargin(labelHoraire, new Insets(0, 0, 1, 1));
        GridPane.setConstraints(labelDe, 1, 3);
        GridPane.setMargin(labelDe, new Insets(0, 10, 1, 0));
        GridPane.setConstraints(choixHeure1, 2, 3);
        GridPane.setMargin(choixHeure1, new Insets(0, 5, 1, 1));
        GridPane.setConstraints(labelH1, 3, 3);
        GridPane.setMargin(labelH1, new Insets(0, 5, 1, 5));
        GridPane.setConstraints(choixMin1, 4, 3);
        GridPane.setMargin(choixMin1, new Insets(0, 5, 1, 5));
        GridPane.setConstraints(labelA, 1, 4);
        GridPane.setMargin(labelA, new Insets(10, 0, 1, 6));
        GridPane.setConstraints(choixHeure2, 2, 4);
        GridPane.setMargin(choixHeure2, new Insets(10, 1, 1, 1));
        GridPane.setConstraints(labelH2, 3, 4);
        GridPane.setMargin(labelH2, new Insets(10, 5, 1, 5));
        GridPane.setConstraints(choixMin2, 4, 4);
        GridPane.setMargin(choixMin2, new Insets(10, 1, 1, 5));
        GridPane.setConstraints(annuler, 5, 5);
        annuler.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                reset();
            }
        });
        GridPane.setConstraints(enregistrer, 6, 5);
        enregistrer.setOnAction(HBoxRoot.getControleur());
        formulaire.getChildren().addAll(labelHoraire, labelDe, choixHeure1, labelH1, choixMin1, labelA, choixHeure2, labelH2, choixMin2, annuler, enregistrer);
        this.getChildren().add(formulaire);
        this.setGridLinesVisible(true);
        reset();
    }

    /**
     * Cette méthode permet de créer une Reservation avec les informations entrées dans les options du GridPane.
     * Elle est déclenchée par le bouton enregistrer à partir du Controleur et
     * traite les exceptions dues à une mauvaise utilisation du formulaire
     *
     * @return Reservation, à partir des options du GridPane
     */
    public Reservation getReservation() {
        try {
            return new Reservation(textCours.getText(), dateReservation, new PlageHoraire(new Horaire(choixHeure1.getValue(), choixMin1.getValue()), new Horaire(choixHeure2.getValue(), choixMin2.getValue())));
        }
        catch (ExceptionPlageHoraire parExc) {
            System.out.println("Cette plage horaire n'est pas valide");
        }
        catch (ExceptionReservation parExc) {
            System.out.println("Cette réservation n'est pas valide");
        }
        return null;
    }

    /**
     * Cette méthode permet d'effacer les valeurs entrées dans le GridPane.
     * Elle est utilisée par l'action du bouton d'annulation et
     * remet les valeurs par défaut sur les ChoiceBox
     */
    public void reset() {
        textCours.clear();
        choixHeure1.setValue(8);
        choixHeure2.setValue(9);
        choixMin1.setValue(0);
        choixMin2.setValue(0);
    }

    /**
     * Cette méthode permet de changer la DateCalendrier qui s'affiche au dessus du GridPane.
     * Elle change également la variable dateReservation, de sorte que la Reservation crée utilise cette DateCalendrier.
     * setDate est utilisée quand un clic est fait sur une date de VBoxCalendrier et est appelée par le COntroleur.
     *
     * @param parDate la DateCalendrier récupérée du ToggleButton cliquée sur le VBoxCalendrier
     */
    public void setDate(DateCalendrier parDate) {
        dateReservation = parDate;
        labelDate.setText(dateReservation.toString());
    }
}
