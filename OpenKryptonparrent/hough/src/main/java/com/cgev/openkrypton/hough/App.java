package com.cgev.openkrypton.hough;

import javafx.util.Pair;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import javax.swing.*;

public class App {
    public static Pair<String, JPanel> create() throws Exception {
        //String filePath = "src/main/resources/images/building.jpg";
        String filePath = "src/main/resources/images/circle.png";
        Mat newImage = Imgcodecs.imread(filePath, Imgcodecs.CV_LOAD_IMAGE_ANYCOLOR);

        GUI gui = null;
        if (newImage.dataAddr() == 0) {
            System.out.println("Couldn't open file " + filePath);
        } else {

            gui = new GUI("Hough Example", newImage);
            gui.init();
        }
        return new Pair<>("Hough", gui.getFrame());
    }
}