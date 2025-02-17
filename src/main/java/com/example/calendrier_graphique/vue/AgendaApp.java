package com.example.calendrier_graphique.vue;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;

/**
 * La classe AgendaApp est la classe principale de l'application graphique.
 * Elle hérite de la classe Application, met en place la fenêtre et applique les modifications du fichier CSS.
 */
public class AgendaApp extends Application {

    /**
     * Cette méthode initialise la fenêtre et les compsants principaux de l'interface graphique.
     * On peut y paramétrer les dimensions de la fenêtre et son titre.
     *
     * @param stage
     */
    public void start(Stage stage) {
        HBoxRoot root = new HBoxRoot();
        Scene scene = new Scene(root, 1100, 500);
        File [] fichiersCss = new File("css").listFiles();
        for (File fichier : fichiersCss) {
            scene.getStylesheets().add(fichier.toURI().toString());
        }
        stage.setScene(scene);
        stage.setTitle("CalendrierDuMois");
        stage.show();
    }

    /**
     * Cette méthode permet de démarrer le projet
     *
     * @param args
     */
    public static void main(String [] args) {
        Application.launch(args);
    }
}
