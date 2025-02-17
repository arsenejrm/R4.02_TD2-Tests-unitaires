module com.example.calendrier_graphique {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.calendrier_graphique to javafx.fxml;
    exports com.example.calendrier_graphique.controleur;
    exports com.example.calendrier_graphique.modele;
    exports com.example.calendrier_graphique.vue;
}