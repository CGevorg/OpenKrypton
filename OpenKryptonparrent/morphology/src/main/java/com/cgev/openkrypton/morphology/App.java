package com.cgev.openkrypton.morphology;

import javafx.util.Pair;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import javax.swing.*;

public class App {
    public static Pair<String, JPanel> create() throws Exception {
        String filePath = "src/main/resources/images/baboon.jpg";
        //String filePath = "src/main/resources/images/c.png";
        Mat newImage = Imgcodecs.imread(filePath);

        GUI gui = null;
        if (newImage.dataAddr() == 0) {
            System.out.println("Couldn't open file " + filePath);
        } else {

            gui = new GUI("Morphology Example", newImage);
            gui.init();
        }
        return new Pair<>("Morphology", gui.frame);
    }
}