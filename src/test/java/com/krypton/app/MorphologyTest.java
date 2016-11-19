package com.krypton.app;

import java.awt.Dimension;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by employee on 10/22/16.
 */
public class MorphologyTest {
    static final int SLEEP_DURATION= 7000000;
    @Test
    public void UI() {
        Morphology m = new Morphology();
        m.setSize(new Dimension(400,300));
        m.setMinimumSize(new Dimension(400,300));
        m.setVisible(true);
        m.openPicture(Morphology.IMAGE_PATH);
        try {
            Thread.sleep(SLEEP_DURATION);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
