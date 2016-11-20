package com.krypton.app;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.swing.ButtonGroup;

public class FloodfillFacade extends Application {

    private static final int DEFAULT_HBOX_SPACING = 5;
    private static final int DEFAULT_WINDOW_WIDTH = 400;
    private static final int DEFAULT_WINDOW_HEIGHT = 400;


    private ButtonGroup modeGroup = new ButtonGroup();
    private ButtonGroup maskGroup = new ButtonGroup();
    private ButtonGroup rangeGroup = new ButtonGroup();
    private ButtonGroup connectivityGroup = new ButtonGroup();

    private RadioButton modeColorRbtn = new RadioButton("Color");
    private RadioButton modeGrayscaleRbtn = new RadioButton("Grayscale");
    private RadioButton maskOnRbtn = new RadioButton("On");
    private RadioButton maskOffRbtn = new RadioButton("Off");
    private RadioButton rangeNullRbtn = new RadioButton("Null");
    private RadioButton rangeFixedRbtn = new RadioButton("Fixed (seed)");
    private RadioButton rangeFloatingRbtn = new RadioButton("Floating (relative)");
    private RadioButton connectivity4Rbtn = new RadioButton("4-Connectivity");
    private RadioButton connectivity8Rbtn = new RadioButton("8-Connectivity");

    private Slider lowerThSlider = new Slider();
    private Slider upperThSlider = new Slider();

    private Button resetBtn = new Button("Reset");

    @Override
    public void start(Stage primaryStage) {

        StackPane root = new StackPane();

        this.init(root);

        Scene scene = new Scene(root, DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT);
        primaryStage.setTitle("Floodfill Window");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void init(StackPane panel) {
        HBox modeBox = new HBox(DEFAULT_HBOX_SPACING);
        modeBox.setAlignment(Pos.TOP_RIGHT);

    }

    public static void main(String[] args) {
        launch(args);
    }
}









