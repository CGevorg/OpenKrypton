package com.krypton.app;


import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;

public class FileManager {

    public static Image openFile() {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.DECORATED);
        FileChooser fileChooser = new FileChooser();
        File defaultDirectory = new File(System.getProperty("user.dir") + "/resources");
        fileChooser.setInitialDirectory(defaultDirectory);
        fileChooser.setTitle("Open Resource File");
        File file = fileChooser.showOpenDialog(stage);
        Image image = null;
        if(null != file) {
            image = new Image(file.toURI().toString());
        }
        return image;
    }
}
