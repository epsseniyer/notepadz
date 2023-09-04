package dev.vox.notebook;

import dev.vox.notebook.controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class app extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view.fxml"));
        VBox root = loader.load();
        Scene scene = new Scene(root, 800, 600);

        primaryStage.setTitle("Not Defteri");
        primaryStage.setScene(scene);
        primaryStage.show();


        setupShortcuts(scene, loader.getController());
    }

    private void setupShortcuts(Scene scene, controller controller) {
        scene.getAccelerators().put(
                new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN),
                controller::newFile
        );

        scene.getAccelerators().put(
                new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN),
                controller::openFile
        );

        scene.getAccelerators().put(
                new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN),
                controller::saveFile
        );

        scene.getAccelerators().put(
                new KeyCodeCombination(KeyCode.F4, KeyCombination.CONTROL_DOWN),
                controller::exitApp
        );

        scene.getAccelerators().put(
                new KeyCodeCombination(KeyCode.ADD, KeyCombination.CONTROL_DOWN),
                controller::zoomIn
        );

        scene.getAccelerators().put(
                new KeyCodeCombination(KeyCode.SUBTRACT, KeyCombination.CONTROL_DOWN),
                controller::zoomOut
        );
    }
}
