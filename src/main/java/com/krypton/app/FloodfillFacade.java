package com.krypton.app;


import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.ButtonGroup;

public class FloodfillFacade extends Application {

    private static final int DEFAULT_HBOX_SPACING = 5;
    private static final int DEFAULT_WINDOW_WIDTH = 700;
    private static final int DEFAULT_WINDOW_HEIGHT = 400;
    private static final Insets DEFAULT_SPACING_MAJOR = new Insets(0, 100, 0, 0);

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
        VBox vBox = new VBox();
        vBox.setSpacing(10);

        this.init(vBox);
        this.initStyleSheet();

        root.getChildren().add(vBox);
        Scene scene = new Scene(root, DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT);
        primaryStage.setTitle("Floodfill Window");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void init(VBox panel) {
        HBox modeBox = new HBox(DEFAULT_HBOX_SPACING);
        modeBox.setAlignment(Pos.CENTER_RIGHT);
        modeBox.setPadding(new Insets(0, 100, 0, 50));
        Label modeLbl = new Label("Mode:");
        modeBox.getChildren().addAll(modeLbl,modeColorRbtn,modeGrayscaleRbtn);

        HBox maskBox = new HBox(DEFAULT_HBOX_SPACING);
        maskBox.setAlignment(Pos.CENTER_RIGHT);
        maskBox.setPadding(new Insets(0, 100, 0, 50));
        Label maskLbl = new Label("Mask:");
        maskBox.getChildren().addAll(maskLbl,maskOnRbtn,maskOffRbtn);


        HBox rangeBox = new HBox(DEFAULT_HBOX_SPACING);
        rangeBox.setAlignment(Pos.CENTER_RIGHT);
        rangeBox.setPadding(new Insets(0, 100, 0, 50));
        Label rangeLbl = new Label("Range:");
        rangeBox.getChildren().addAll(rangeLbl,rangeNullRbtn,rangeFixedRbtn,rangeFloatingRbtn);

        HBox connectivityBox = new HBox(DEFAULT_HBOX_SPACING);
        connectivityBox.setAlignment(Pos.CENTER_RIGHT);
        connectivityBox.setPadding(new Insets(0, 100, 0, 50));
        Label connectivityLbl = new Label("Connectivity:");
        connectivityBox.getChildren().addAll(connectivityLbl,connectivity4Rbtn,connectivity8Rbtn);

        HBox lowerThBox = new HBox(DEFAULT_HBOX_SPACING);
        lowerThBox.setAlignment(Pos.CENTER_RIGHT);
        lowerThBox.setPadding(new Insets(0, 100, 0, 50));
        Label lowerThLbl = new Label("Lower threshold:");
        lowerThBox.getChildren().addAll(lowerThLbl,lowerThSlider);

        HBox upperThBox = new HBox(DEFAULT_HBOX_SPACING);
        upperThBox.setAlignment(Pos.CENTER_RIGHT);
        upperThBox.setPadding(new Insets(0, 100, 0, 50));
        Label upperThLbl = new Label("Upper threshold:");
        upperThBox.getChildren().addAll(upperThLbl,upperThSlider);


        panel.getChildren().addAll(modeBox,maskBox,rangeBox,connectivityBox,lowerThBox,upperThBox);

    }

    public void initStyleSheet() {
        //@TODO
    }

    public static void main(String[] args) {
        launch(args);
    }
}









