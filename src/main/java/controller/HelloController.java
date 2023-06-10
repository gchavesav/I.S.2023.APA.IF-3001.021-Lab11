package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import ucr.lab.HelloApplication;

import java.io.IOException;

public class HelloController {

    @FXML
    private AnchorPane ap;

    @FXML
    private BorderPane bp;

    @FXML
    private Text txtMessage;

    private void loadPage(String page) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(page));
        try {
            this.bp.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void Exit(ActionEvent event) {System.exit(0);
    }

    @FXML
    void Home(ActionEvent event) {
        this.txtMessage.setText("Laboratory No. 11");
        this.bp.setCenter(ap);
    }

    @FXML
    void adjListGraphOnAction(ActionEvent event) {

    }

    @FXML
    void adjMatrixGraphOnAction(ActionEvent event) {

    }

    @FXML
    void listOperationsOnAction(ActionEvent event) {
        loadPage("adjListOperations.fxml");
    }

    @FXML
    void matrixOperationsOnAction(ActionEvent event) {

    }
    void adjacencyMatrixGraphOnAction(ActionEvent event) {loadPage("adjacencyMatrixGraph.fxml");

    }

}