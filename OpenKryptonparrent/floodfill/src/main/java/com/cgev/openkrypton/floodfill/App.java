package com.cgev.openkrypton.floodfill;

import javafx.util.Pair;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import javax.swing.*;
import java.util.Map;

public class App {
    public static Pair<String,JPanel> create() throws Exception {
        String filePath = "src/main/resources/images/cathedral-small.jpg";
        //String filePath = "images/cathedral-small.jpg";
        Mat newImage = Imgcodecs.imread(filePath);

        GUI gui = null;
        if (newImage.dataAddr() == 0) {
            System.out.println("Couldn't open file " + filePath);
        } else {

            gui = new GUI("Floodfill Example", newImage);
            gui.init();
        }

        return new Pair<>("FloodFill",gui.frame);
    }
}