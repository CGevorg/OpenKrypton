package com.krypton.app;

import org.junit.Test;
import org.opencv.core.Core;

import java.awt.Dimension;


public class MorphologyTest {
    private static final int SLEEP_DURATION = 7000000;

    @Test
    public void UI() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Morphology m = new Morphology();
        m.setSize(new Dimension(400, 300));
        m.setMinimumSize(new Dimension(400, 300));
        m.setVisible(true);
        m.openPicture(Morphology.IMAGE_PATH);
        try {
            Thread.sleep(SLEEP_DURATION);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
