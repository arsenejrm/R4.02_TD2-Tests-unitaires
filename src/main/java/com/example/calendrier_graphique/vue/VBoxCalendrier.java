package com.example.calendrier_graphique.vue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import com.example.calendrier_graphique.modele.CalendrierDuMois;
import com.example.calendrier_graphique.modele.ConstantesCalendrier;
import com.example.calendrier_graphique.modele.DateCalendrier;

import java.util.*;

/**
 * La classe VboxCalendrier est l'une des trois vues de la fenêtre.
 * Située à gauche des deux autres, elle affiche graphiquement un calendrier graphique sous forme de mois.
 * Elle peut afficher les 12 mois de l'année avec leurs dates et chaque clic sur l'une des dates
 * transmet le jour au formulaire et la semaine à l'affichage des Reservation
 */
public class VBoxCalendrier extends VBox implements ConstantesCalendrier {
    DateCalendrier selectedDate = new DateCalendrier();
    /**
     * Le constructeur de VBoxCalendrier construit l'interface de la vue
     * qui est coposée d'un Label indiquant le mois et l'année,
     * des Button servant à changer de mois et
     * d'un StackPane contenant les 12 mois de l'année sous forme graphique.
     * Chaque objet du StackPane est un TilePane contenant les ToggleButton représentant les dates du mois.
     * Chaque ligne du TilePane est une semaine, chaque colonne est un jour de la semaine.
     * Les dates faisant partie de la semaine mais pas du mois ont une couleur plus foncée que les autres,
     * et la date du jour a un texte rouge au lieu de noir.
     * Parmi les boutons servant à changer de mois, les deux du centre servent à passer au mois suivant ou précédent,
     * et les deux aux extrêmes servent à aller au premier ou au dernier mois. Leur mise en action est faite dans le constructeur.
     */
    public VBoxCalendrier() {
        String [] JOURS_SEMAINE_ABR = {"lun", "mar", "mer", "jeu", "ven", "sam", "dim"};
        DateCalendrier today = new DateCalendrier();
        StackPane stackPaneMois = new StackPane();
        ToggleGroup buttonGroup = new ToggleGroup();
        for(int numMois = 1; numMois <= 12; numMois++) {
            CalendrierDuMois monthCalendar = new CalendrierDuMois(numMois, today.getAnnee());
            TilePane tilePane = new TilePane();
            tilePane.setPrefColumns(7);
            tilePane.setPrefRows(monthCalendar.getDates().size() % 7 + 1);
            tilePane.setId("opaque");
            if (numMois == 1) {
                tilePane.setId("first");
            } else if (numMois == 12) {
                tilePane.setId("last");
            }
            for (String jourAB : JOURS_SEMAINE_ABR) {
                Label labelJour = new Label(jourAB);
                tilePane.getChildren().add(labelJour);
            }
            for (DateCalendrier date : monthCalendar.getDates()) {
                ToggleButton boutonDate = new ToggleButton(Integer.toString(date.getJour()));
                boutonDate.setToggleGroup(buttonGroup);
                tilePane.getChildren().add(boutonDate);
                boutonDate.setUserData(date);
                boutonDate.setOnAction(HBoxRoot.getControleur());
                if (date.getMois() != numMois) {
                    boutonDate.setId("dateHorsMois");
                }
                if (date.isToday()) {
                    boutonDate.setId("today");
                }
            }
            tilePane.setAccessibleText(MOIS[numMois - 1]);
            stackPaneMois.getChildren().add(tilePane);
        }
        List <Node> listeStackPane = stackPaneMois.getChildren();
        Label labelTitle = new Label();
        labelTitle.setPrefSize(100, 10);
        Button boutonGauche = new Button("<");
        Button boutonDroit = new Button(">");
        Button boutonFirst = new Button("<<");
        Button boutonLast = new Button(">>");
        HBox boutons = new HBox();
        HBox hBoxHaut = new HBox();
        boutons.getChildren().addAll(boutonFirst, boutonGauche, boutonDroit, boutonLast);
        hBoxHaut.getChildren().addAll(labelTitle, boutons);
        this.getChildren().addAll(hBoxHaut, stackPaneMois);
        boutonLast.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                List<Node> childs = stackPaneMois.getChildren();
                while (!childs.get(childs.size() - 1).getId().equals("last")) {
                    Node lastNode = childs.get(0);
                    lastNode.toFront();
                    System.out.println(lastNode.getId());
                }
                String mois = childs.get(childs.size() - 1).getAccessibleText();
                labelTitle.setText(mois + today.getAnnee());
            }
        });
        boutonDroit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                List <Node> childs = stackPaneMois.getChildren();
                if (childs.size() > 1) {
                    Node lastNode = childs.get(0);
                    lastNode.toFront();
                    String mois = childs.get(childs.size() - 1).getAccessibleText();
                    labelTitle.setText(mois + today.getAnnee());
                }
            }
        });
        boutonGauche.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                List <Node> childs = stackPaneMois.getChildren();
                if (childs.size() > 1) {
                    Node topNode = childs.get(childs.size() - 1);
                    topNode.toBack();
                    String mois = childs.get(childs.size() - 1).getAccessibleText();
                    labelTitle.setText(mois + today.getAnnee());
                }
            }
        });
        boutonFirst.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                List<Node> childs = stackPaneMois.getChildren();
                while (!childs.get(childs.size() - 1).getId().equals("first")) {
                    Node lastNode = childs.get(childs.size() - 1);
                    lastNode.toBack();
                    System.out.println(lastNode.getId());
                    String mois = childs.get(childs.size() - 1).getAccessibleText();
                    labelTitle.setText(mois + today.getAnnee());
                }
            }
        });

    }
}
