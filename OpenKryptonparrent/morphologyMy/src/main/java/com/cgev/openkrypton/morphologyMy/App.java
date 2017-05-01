package com.cgev.openkrypton.morphologyMy;

import javafx.util.Pair;

import javax.swing.*;

/**
 * Created by gharutyunyan on 3/14/17.
 */

public class App {
    public static Pair<String, JPanel> create() throws Exception {
        String filePath = "src/main/resources/images/baboon.jpg";
        Morphology m = new Morphology();
        m.openPicture(Morphology.IMAGE_PATH);
        return new Pair<>("MorphologyMy", m);
    }
}
