package com.krypton.app;

import java.awt.Dimension;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by employee on 10/22/16.
 */
public class MorphologyTest {
    @Test
    public void UI() {
        Morphology m = new Morphology();
        m.setSize(new Dimension(400,300));
        m.setMinimumSize(new Dimension(400,300));
        m.setVisible(true);

        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
