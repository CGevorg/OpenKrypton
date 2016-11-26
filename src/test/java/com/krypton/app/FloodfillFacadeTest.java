package com.krypton.app;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.junit.Test;
import org.opencv.core.Core;

public class FloodfillFacadeTest {
    @Test
    public void runUi() {
        FloodfillFacade floodfillFacade = new FloodfillFacade();
        try {
            Stage stage = new Stage();
            stage.initStyle(StageStyle.DECORATED);

            floodfillFacade.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}