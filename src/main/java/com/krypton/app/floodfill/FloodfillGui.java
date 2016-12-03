package com.krypton.app.floodfill;


import com.krypton.app.FileManager;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.File;

public class FloodfillGui extends Application {

    private static final int DEFAULT_HBOX_SPACING = 5;
    private static final int DEFAULT_WINDOW_WIDTH = 900;
    private static final int DEFAULT_WINDOW_HEIGHT = 900;
    private static final Insets DEFAULT_SPACING_MAJOR = new Insets(0, 100, 0, 0);

    private ToggleGroup modeGroup = new ToggleGroup();
    private ToggleGroup maskGroup = new ToggleGroup();
    private ToggleGroup rangeGroup = new ToggleGroup();
    private ToggleGroup connGroup = new ToggleGroup();


    /*need add to function*/
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

    private Button openFileBtn = new Button("Open file");


    private Button resetBtn = new Button("Reset");

    private ImageView imageLbl = new ImageView();
    private ImageView maskImgLbl = new ImageView();

    private Mat originalImageMat;
    private Mat imageMat;
    private Mat grayImageMat = new Mat();
    private Mat maskMat = new Mat();


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

    private void init(VBox panel) {
        this.initLayouts(panel);
        this.initComponents();
        this.initListeners();
        this.initStyleSheet();
    }


    private void initLayouts(VBox panel) {

        HBox modeBox = new HBox(DEFAULT_HBOX_SPACING);
        modeBox.setAlignment(Pos.CENTER_RIGHT);
        modeBox.setPadding(new Insets(0, 100, 0, 50));
        Label modeLbl = new Label("Mode:");
        modeLbl.setFont(Font.font("Verdana", FontWeight.BOLD, modeLbl.getFont().getSize()));
        modeBox.getChildren().addAll(modeLbl, modeColorRbtn, modeGrayscaleRbtn);

        HBox maskBox = new HBox(DEFAULT_HBOX_SPACING);
        maskBox.setAlignment(Pos.CENTER_RIGHT);
        maskBox.setPadding(new Insets(0, 100, 0, 50));
        Label maskLbl = new Label("Mask:");
        maskLbl.setFont(Font.font("Verdana", FontWeight.BOLD, maskLbl.getFont().getSize()));
        maskBox.getChildren().addAll(maskLbl, maskOnRbtn, maskOffRbtn);


        HBox rangeBox = new HBox(DEFAULT_HBOX_SPACING);
        rangeBox.setAlignment(Pos.CENTER_RIGHT);
        rangeBox.setPadding(new Insets(0, 100, 0, 50));
        Label rangeLbl = new Label("Range:");
        rangeLbl.setFont(Font.font("Verdana", FontWeight.BOLD, rangeLbl.getFont().getSize()));
        rangeBox.getChildren().addAll(rangeLbl, rangeNullRbtn, rangeFixedRbtn, rangeFloatingRbtn);

        HBox connectivityBox = new HBox(DEFAULT_HBOX_SPACING);
        connectivityBox.setAlignment(Pos.CENTER_RIGHT);
        connectivityBox.setPadding(new Insets(0, 100, 0, 50));
        Label connectivityLbl = new Label("Connectivity:");
        connectivityLbl.setFont(Font.font("Verdana", FontWeight.BOLD, connectivityLbl.getFont().getSize()));
        connectivityBox.getChildren().addAll(connectivityLbl, connectivity4Rbtn, connectivity8Rbtn);

        HBox lowerThBox = new HBox(DEFAULT_HBOX_SPACING);
        lowerThBox.setAlignment(Pos.CENTER_RIGHT);
        lowerThBox.setPadding(new Insets(0, 100, 0, 50));
        Label lowerThLbl = new Label("Lower threshold:");
        lowerThLbl.setFont(Font.font("Verdana", FontWeight.BOLD, lowerThLbl.getFont().getSize()));
        lowerThBox.getChildren().addAll(lowerThLbl, lowerThSlider);

        HBox upperThBox = new HBox(DEFAULT_HBOX_SPACING);
        upperThBox.setAlignment(Pos.CENTER_RIGHT);
        upperThBox.setPadding(new Insets(0, 100, 0, 50));
        Label upperThLbl = new Label("Upper threshold:");
        upperThLbl.setFont(Font.font("Verdana", FontWeight.BOLD, upperThLbl.getFont().getSize()));
        upperThBox.getChildren().addAll(upperThLbl, upperThSlider);


        HBox btnBox = new HBox(DEFAULT_HBOX_SPACING);
        btnBox.setAlignment(Pos.CENTER);
        btnBox.getChildren().addAll(openFileBtn, resetBtn);

        HBox imageBox = new HBox();
        imageBox.setAlignment(Pos.BOTTOM_CENTER);
        imageBox.getChildren().addAll(imageLbl, maskImgLbl);

        panel.getChildren().addAll(modeBox, maskBox, rangeBox, connectivityBox, lowerThBox, upperThBox, btnBox, imageBox);

    }

    private void initComponents() {
        modeColorRbtn.setToggleGroup(modeGroup);
        modeGrayscaleRbtn.setToggleGroup(modeGroup);

        maskOnRbtn.setToggleGroup(maskGroup);
        maskOffRbtn.setToggleGroup(maskGroup);

        rangeFixedRbtn.setToggleGroup(rangeGroup);
        rangeNullRbtn.setToggleGroup(rangeGroup);
        rangeFloatingRbtn.setToggleGroup(rangeGroup);

        connectivity4Rbtn.setToggleGroup(connGroup);
        connectivity8Rbtn.setToggleGroup(connGroup);

        modeColorRbtn.setSelected(true);
        maskOnRbtn.setSelected(true);
        rangeFixedRbtn.setSelected(true);
        connectivity4Rbtn.setSelected(true);

        lowerThSlider.setValue(20);
        upperThSlider.setValue(20);
    }

    private void initListeners() {
        openFileBtn.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        File imageFile = FileManager.openFile();
                        Image image = FileManager.getImagefromFile(imageFile);
                        if (null != image) {
                            imageLbl.setImage(image);
                            imageMat = Imgcodecs.imread(imageFile.getPath());
                            originalImageMat = imageMat.clone();
                            maskMat.create(new Size(imageMat.cols()+2, imageMat.rows()+2), CvType.CV_8UC1);
                            maskMat.setTo(new Scalar(0));
                            maskImgLbl.setImage(image);
                        }
                    }
                });
        resetBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                imageMat = originalImageMat.clone();
                maskMat.setTo(new Scalar(0));
                imageLbl.setImage(FloodFillFacade.mat2Image(imageMat));
                maskImgLbl.setImage(FloodFillFacade.mat2Image(maskMat));
            }
        });

        imageLbl.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FloodFillFacade facade = new FloodFillFacade();
                facade.setColored(modeColorRbtn.isSelected());
                facade.setMasked(maskOnRbtn.isSelected());

                if(rangeNullRbtn.isSelected()) {
                    facade.setRange(FloodFillFacade.NULL_RANGE);
                }
                else if(rangeFixedRbtn.isSelected()) {
                    facade.setRange(FloodFillFacade.FIXED_RANGE);
                }
                else if(rangeFloatingRbtn.isSelected()) {
                    facade.setRange(FloodFillFacade.FLOATING_RANGE);
                }
                facade.setConnectivity(connectivity4Rbtn.isSelected() ? 4 : 8 );
                facade.setLowerDiff((int)lowerThSlider.getValue());
                facade.setUpperDiff((int)upperThSlider.getValue());

                facade.fill(imageMat,maskMat,(int)event.getX(),(int)event.getY());
                imageLbl.setImage(FloodFillFacade.mat2Image(imageMat));
                maskImgLbl.setImage(FloodFillFacade.mat2Image(maskMat));
                maskMat.setTo(new Scalar(0));
            }
        });
    }


    private void initStyleSheet() {
        lowerThSlider.setMin(0);
        lowerThSlider.setMax(250);
        lowerThSlider.setPrefWidth(400);
        lowerThSlider.setShowTickMarks(true);
        lowerThSlider.setShowTickLabels(true);


        upperThSlider.setMin(0);
        upperThSlider.setMax(250);
        upperThSlider.setPrefWidth(400);
        upperThSlider.setShowTickMarks(true);
        upperThSlider.setShowTickLabels(true);
    }

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        launch(args);
    }
}









