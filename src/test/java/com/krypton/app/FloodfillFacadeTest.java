package com.krypton.app;

import javafx.stage.Stage;
import org.junit.Test;

public class FloodfillFacadeTest {
    @Test
    public void runUi() {
        FloodfillFacade floodfillFacade = new FloodfillFacade();
        try {
            floodfillFacade.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}