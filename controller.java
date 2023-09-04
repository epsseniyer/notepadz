package dev.vox.notebook;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class controller {
    @FXML
    private TextArea textArea;

    @FXML
    private CheckMenuItem light;

    @FXML
    private CheckMenuItem dark;

    public void initialize() {

        light.setSelected(true);


        MenuItem newFileMenuItem = new MenuItem("Yeni (Ctrl+N)");
        newFileMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));
        newFileMenuItem.setOnAction(event -> newFile());

        MenuItem openFileMenuItem = new MenuItem("Aç (Ctrl+O)");
        openFileMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));
        openFileMenuItem.setOnAction(event -> openFile());

        MenuItem saveFileMenuItem = new MenuItem("Kaydet (Ctrl+S)");
        saveFileMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
        saveFileMenuItem.setOnAction(event -> saveFile());

        MenuItem exitAppMenuItem = new MenuItem("Çıkış  (Alt+F4)");
        exitAppMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.F4, KeyCombination.ALT_DOWN));
        exitAppMenuItem.setOnAction(event -> exitApp());


        Menu fileMenu = new Menu("Dosya");
        fileMenu.getItems().addAll(newFileMenuItem, openFileMenuItem, saveFileMenuItem, new SeparatorMenuItem(), exitAppMenuItem);


        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu);
    }

    public void lighter(){
        double fontSize = textArea.getFont().getSize();
        textArea.setStyle("-fx-border-width: 0; -fx-background-color: transparent; -fx-control-inner-background: white; -fx-font-size: " + (fontSize) + "px;");
        dark.setSelected(false);
    }
    public void darker(){
        double fontSize = textArea.getFont().getSize();
        textArea.setStyle("-fx-border-width: 0; -fx-background-color: transparent; -fx-control-inner-background: #202020; -fx-font-size: " + (fontSize) + "px;");

        light.setSelected(false);
    }
    public void newFile() {
        textArea.clear();
    }

    public void openFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Metin Dosyaları", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            try {
                String content = new String(Files.readAllBytes(selectedFile.toPath()));
                textArea.setText(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Metin Dosyaları", "*.txt"));
        File selectedFile = fileChooser.showSaveDialog(null);
        if (selectedFile != null) {
            try {
                Files.write(selectedFile.toPath(), textArea.getText().getBytes(), StandardOpenOption.CREATE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void exitApp() {
        System.exit(0);
    }

    public void zoomIn() {
        if (dark.isSelected()){
            double fontSize = textArea.getFont().getSize();
            textArea.setStyle("-fx-border-width: 0; -fx-background-color: transparent; -fx-control-inner-background: #202020; -fx-font-size: " + (fontSize + 2) + "px;");
        }else{
            double fontSize = textArea.getFont().getSize();
            textArea.setStyle("-fx-border-width: 0; -fx-background-color: transparent; -fx-control-inner-background: #fefefe; -fx-font-size: " + (fontSize + 2) + "px;");
        }
    }

    public void zoomOut() {
        if (dark.isSelected()){
            double fontSize = textArea.getFont().getSize();
            textArea.setStyle("-fx-border-width: 0; -fx-background-color: transparent; -fx-control-inner-background: #202020; -fx-font-size: " + (fontSize - 2) + "px;");
        }else{
            double fontSize = textArea.getFont().getSize();
            textArea.setStyle("-fx-border-width: 0; -fx-background-color: transparent; -fx-control-inner-background: #fefefe; -fx-font-size: " + (fontSize - 2) + "px;");
        }

    }
}
