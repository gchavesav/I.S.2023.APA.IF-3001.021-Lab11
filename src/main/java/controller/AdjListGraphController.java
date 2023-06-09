package controller;

import domain.AdjacencyListGraph;
import domain.AdjacencyMatrixGraph;
import domain.GraphException;
import domain.list.ListException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

public class AdjListGraphController {

    @FXML
    private Pane graphPane;

    @FXML
    private TextArea listGraphTextArea;
    private Alert alert;
    private AdjacencyListGraph adj;
    int counter= 10; //cantidad de vertices que hay

    @FXML
    public void initialize() {
        this.alert = util.FXUtility.alert("", "");
        randomize(counter);
    }

    private void randomize(int n) {
        adj = new AdjacencyListGraph(n); //n tam maximo de vertices para el grafo
        for (int i = 0; i < n; i++) {
            try {
                adj.addVertex(util.Utility.random(99));
            } catch (GraphException e) {
                throw new RuntimeException(e);
            } catch (ListException e) {
                throw new RuntimeException(e);
            }
        }
        int j =0;
        while (j<= n*3){ //cantidad de aristas que desea
            int a = util.Utility.random(99);
            int b = util.Utility.random(99);
            try {
                if (adj.containsVertex(a) && adj.containsVertex(b) && !adj.containsEdge(a, b)){
                    adj.addEdge(a, b); // Agrega la arista entre los vértices
                    //System.out.println(a +" "+ b); //prueba
                    j++;
                }
            } catch (GraphException e) {
                throw new RuntimeException(e);
            } catch (ListException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void bfsTourOnAction(ActionEvent event) {

    }

    @javafx.fxml.FXML
    void containsEdgeOnAction(ActionEvent event) {

    }

    @javafx.fxml.FXML
    void containsVextexOnAction(ActionEvent event) {

    }

    @FXML
    void dfsTourOnAction(ActionEvent event) {

    }

    @javafx.fxml.FXML
    void randomizeOnAction(ActionEvent event) {

    }

    @FXML
    void toStringOnAction(ActionEvent event) {

    }
}
