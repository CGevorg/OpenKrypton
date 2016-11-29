package com.krypton.app;

import com.krypton.app.floodfill.FloodfillGui;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.junit.Test;

public class FloodfillFacadeTest {
    @Test
    public void runUi() {
        FloodfillGui floodfillFacade = new FloodfillGui();
        try {
            Stage stage = new Stage();
            stage.initStyle(StageStyle.DECORATED);

            floodfillFacade.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}