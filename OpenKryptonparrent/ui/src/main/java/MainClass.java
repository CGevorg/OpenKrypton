import javafx.util.Pair;
import org.opencv.core.Core;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class MainClass {
    static Map<String, JPanel> panels = new HashMap<>();

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args) throws Exception {
        Pair<String, JPanel> pair = null;
        //pair = com.cgev.openkrypton.morphology.App.create();
        //panels.put(pair.getKey(), pair.getValue());
        pair = com.cgev.openkrypton.floodfill.App.create();
        panels.put(pair.getKey(), pair.getValue());
        pair = com.cgev.openkrypton.hough.App.create();
        panels.put(pair.getKey(), pair.getValue());
        pair = com.cgev.openkrypton.imagePyramid.App.create();
        panels.put(pair.getKey(), pair.getValue());
        pair = com.cgev.openkrypton.imagePyramid.App.create();
        panels.put(pair.getKey(), pair.getValue());
        pair = com.cgev.openkrypton.kernels.App.create();
        panels.put(pair.getKey(), pair.getValue());
        pair = com.cgev.openkrypton.threshold.App.create();
        panels.put(pair.getKey(), pair.getValue());
        pair = com.cgev.openkrypton.morphologyMy.App.create();
        panels.put(pair.getKey(), pair.getValue());


        GUI.create("OpenKrypton", panels);
    }
}
