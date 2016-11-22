package com.krypton.app;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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

        root.getChildren().add(vBox);
        Scene scene = new Scene(root, DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT);
        primaryStage.setTitle("Floodfill Window");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void init(VBox panel) {
        this.initLayouts(panel);
        this.initComponents();
        this.initStyleSheet();
    }

    public void initLayouts(VBox panel) {

        HBox modeBox = new HBox(DEFAULT_HBOX_SPACING);
        modeBox.setAlignment(Pos.CENTER_RIGHT);
        modeBox.setPadding(new Insets(0, 100, 0, 50));
        Label modeLbl = new Label("Mode:");
        modeLbl.setFont(Font.font("Verdana", FontWeight.BOLD, modeLbl.getFont().getSize()));
        modeBox.getChildren().addAll(modeLbl,modeColorRbtn,modeGrayscaleRbtn);

        HBox maskBox = new HBox(DEFAULT_HBOX_SPACING);
        maskBox.setAlignment(Pos.CENTER_RIGHT);
        maskBox.setPadding(new Insets(0, 100, 0, 50));
        Label maskLbl = new Label("Mask:");
        maskLbl.setFont(Font.font("Verdana", FontWeight.BOLD, maskLbl.getFont().getSize()));
        maskBox.getChildren().addAll(maskLbl,maskOnRbtn,maskOffRbtn);


        HBox rangeBox = new HBox(DEFAULT_HBOX_SPACING);
        rangeBox.setAlignment(Pos.CENTER_RIGHT);
        rangeBox.setPadding(new Insets(0, 100, 0, 50));
        Label rangeLbl = new Label("Range:");
        rangeLbl.setFont(Font.font("Verdana", FontWeight.BOLD, rangeLbl.getFont().getSize()));
        rangeBox.getChildren().addAll(rangeLbl,rangeNullRbtn,rangeFixedRbtn,rangeFloatingRbtn);

        HBox connectivityBox = new HBox(DEFAULT_HBOX_SPACING);
        connectivityBox.setAlignment(Pos.CENTER_RIGHT);
        connectivityBox.setPadding(new Insets(0, 100, 0, 50));
        Label connectivityLbl = new Label("Connectivity:");
        connectivityLbl.setFont(Font.font("Verdana", FontWeight.BOLD, connectivityLbl.getFont().getSize()));
        connectivityBox.getChildren().addAll(connectivityLbl,connectivity4Rbtn,connectivity8Rbtn);

        HBox lowerThBox = new HBox(DEFAULT_HBOX_SPACING);
        lowerThBox.setAlignment(Pos.CENTER_RIGHT);
        lowerThBox.setPadding(new Insets(0, 100, 0, 50));
        Label lowerThLbl = new Label("Lower threshold:");
        lowerThLbl.setFont(Font.font("Verdana", FontWeight.BOLD, lowerThLbl.getFont().getSize()));
        lowerThBox.getChildren().addAll(lowerThLbl,lowerThSlider);

        HBox upperThBox = new HBox(DEFAULT_HBOX_SPACING);
        upperThBox.setAlignment(Pos.CENTER_RIGHT);
        upperThBox.setPadding(new Insets(0, 100, 0, 50));
        Label upperThLbl = new Label("Upper threshold:");
        upperThLbl.setFont(Font.font("Verdana", FontWeight.BOLD, upperThLbl.getFont().getSize()));
        upperThBox.getChildren().addAll(upperThLbl,upperThSlider);


        panel.getChildren().addAll(modeBox,maskBox,rangeBox,connectivityBox,lowerThBox,upperThBox);

    }

    private void initComponents() {
        ToggleGroup modeGroup = new ToggleGroup();
        modeColorRbtn.setToggleGroup(modeGroup);
        modeGrayscaleRbtn.setToggleGroup(modeGroup);

        ToggleGroup maskGroup = new ToggleGroup();
        maskOnRbtn.setToggleGroup(maskGroup);
        maskOffRbtn.setToggleGroup(maskGroup);

        ToggleGroup rangeGroup = new ToggleGroup();
        rangeFixedRbtn.setToggleGroup(rangeGroup);
        rangeNullRbtn.setToggleGroup(rangeGroup);
        rangeFloatingRbtn.setToggleGroup(rangeGroup);

        ToggleGroup connGroup = new ToggleGroup();
        connectivity4Rbtn.setToggleGroup(connGroup);
        connectivity8Rbtn.setToggleGroup(connGroup);
    }

    public void initStyleSheet() {
        //@TODO
    }

    public static void main(String[] args) {
        launch(args);
    }
}









