package com.cgev.openkrypton.kernels;

import javafx.util.Pair;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import javax.swing.*;

public class App {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static Pair<String, JPanel> create() throws Exception {
        String filePath = "src/main/resources/images/clouds.png";
        Mat newImage = Imgcodecs.imread(filePath, Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);

        GUI gui = null;
        if (newImage.dataAddr() == 0) {
            System.out.println("Couldn't open file " + filePath);
        } else {

            gui = new GUI("Kernel Example", newImage);
            gui.init();
        }
        return new Pair<>("Kernels", gui.getPanel());
    }
}