package com.example;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;


public class PrimaryController {

    @FXML
    private AnchorPane contentArea;

    @FXML
    public void initialize() throws IOException {
        // Load the database view into the content area
        FXMLLoader loader = new FXMLLoader(getClass().getResource("database_view.fxml"));
        AnchorPane databaseView = loader.load();
        contentArea.getChildren().setAll(databaseView);
    }

    @FXML
    private void switchToSecondary(ActionEvent event) throws IOException {
        App.setRoot("secondary");
    }
}
