package com.cgev.openkrypton.imagePyramid;

import javafx.util.Pair;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import javax.swing.*;

public class App {
    public static Pair<String, JPanel> create() throws Exception {
        String filePath = "src/main/resources/images/cathedral.jpg";
        Mat newImage = Imgcodecs.imread(filePath);

        GUI gui = null;
        if (newImage.dataAddr() == 0) {
            System.out.println("Couldn't open file " + filePath);
        } else {

            gui = new GUI("Image Pyramid", newImage);
            gui.init();
        }
        return new Pair<>("ImagePyramid", gui.getPanel());
    }
}