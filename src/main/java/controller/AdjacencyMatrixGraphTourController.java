package controller;

import domain.AdjacencyMatrixGraph;
import domain.GraphException;
import domain.list.ListException;
import domain.queue.QueueException;
import domain.stack.StackException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class AdjacencyMatrixGraphTourController {

    @FXML
    private Group graf;
    private Alert alert;

    @FXML
    private Pane lienzo;

    @FXML
    private TextArea textareaInfo;
    private TextInputDialog dialog1, dialog2;

    private AdjacencyMatrixGraph matrixGraph;

    @javafx.fxml.FXML
    public void initialize() throws GraphException, ListException {
        matrixGraph = new AdjacencyMatrixGraph(10);
        for (int i = 0; i < 10; i++) {
            matrixGraph.addVertex(util.Utility.random(99));
        }
        for (int i = 0; i < 10; i++) {
            int vertexIndex = util.Utility.random(0,9);
            int vertexIndex2 = util.Utility.random(0,9);
            if (vertexIndex != vertexIndex2) {
                matrixGraph.addEdge(matrixGraph.vertexList[vertexIndex].data, matrixGraph.vertexList[vertexIndex2].data);
            }
            if (!matrixGraph.containsEdge(matrixGraph.vertexList[i].data,matrixGraph.vertexList[ vertexIndex].data)) {
                matrixGraph.addEdge(matrixGraph.vertexList[i].data, matrixGraph.vertexList[ vertexIndex].data);
            }
        }
        drawMatrixGraph();
    }

    private void drawMatrixGraph() throws ListException, GraphException {
        double centerX = lienzo.getWidth() / 2;
        double centerY = lienzo.getHeight() / 2;
        double radius = Math.min(centerX, centerY) - 250;

        int numVertices = matrixGraph.size();
        double angleStep = 2 * Math.PI / numVertices;

        graf.getChildren().clear();

        for (int i = 0; i < numVertices; i++) {
            double angle = i * angleStep;
            double x = centerX + radius * Math.cos(angle);
            double y = centerY + radius * Math.sin(angle);

            Circle vertex = new Circle(x, y, 25);
            vertex.setFill(Color.CYAN);

            Text vertexLabel = new Text(String.valueOf(matrixGraph.vertexList[i].data));
            vertexLabel.setFill(Color.BLACK);
            vertexLabel.setX(x - vertexLabel.getLayoutBounds().getWidth() / 2);
            vertexLabel.setY(y + vertexLabel.getLayoutBounds().getHeight() / 4);

            graf.getChildren().addAll(vertex, vertexLabel);
        }

        // Dibujar las conexiones entre los vértices
        for (int k = 0; k < numVertices; k++) {
            for (int j = 0; j < numVertices; j++) {
                if (matrixGraph.containsEdge(matrixGraph.vertexList[k].data, matrixGraph.vertexList[j].data)) {
                    double angleTo = j * angleStep;
                    double xTo = centerX + radius * Math.cos(angleTo);
                    double yTo = centerY + radius * Math.sin(angleTo);

                    Line connection = new Line(
                            centerX + radius * Math.cos(k * angleStep),
                            centerY + radius * Math.sin(k * angleStep),
                            xTo,
                            yTo
                    );

                    connection.setStroke(Color.BLACK);
                    connection.setStrokeWidth(1.5);

                    connection.setOnMouseEntered(event -> {
                        connection.setStroke(Color.RED);
                        connection.setStrokeWidth(6.0);
                    });

                    connection.setOnMouseExited(event -> {
                        connection.setStroke(Color.BLACK);
                        connection.setStrokeWidth(1.5);
                    });

                    graf.getChildren().add(connection);
                }
            }
        }
    }
    @FXML
    public void containsEdgeOnAction(ActionEvent actionEvent) {
        dialog1 = util.FXUtility.dialog("Edge Contains","Contains: ");
        dialog1.showAndWait();
        int value1 = Integer.parseInt(dialog1.getResult());
        this.alert=util.FXUtility.alert("Edge Contains","Contains: ");
        alert.setAlertType(Alert.AlertType.INFORMATION);

        dialog2 = util.FXUtility.dialog("Edge Contains","Contains: ");
        dialog2.showAndWait();
        int value2 = Integer.parseInt(dialog2.getResult());
        this.alert=util.FXUtility.alert("Edge Contains","Contains: ");
        alert.setAlertType(Alert.AlertType.INFORMATION);

        try {
            alert.setContentText(String.valueOf(matrixGraph.containsEdge(value1, value2)));
            alert.showAndWait();
        } catch (GraphException e) {
            throw new RuntimeException(e);
        } catch (ListException e) {
            throw new RuntimeException(e);
        }
    }

    public void containsVertexOnAction(ActionEvent actionEvent) {
        dialog1 = util.FXUtility.dialog("Vertex Contains","Contains: ");
        dialog1.showAndWait();
        int value = Integer.parseInt(dialog1.getResult());
        this.alert=util.FXUtility.alert("Vertex Contains","Contains: ");
        alert.setAlertType(Alert.AlertType.INFORMATION);
        try {
            alert.setContentText(String.valueOf(matrixGraph.containsVertex(value)));
            alert.showAndWait();
        } catch (GraphException e) {
            throw new RuntimeException(e);
        } catch (ListException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void dfsTourOnAction(ActionEvent event) throws GraphException, ListException, StackException {
        textareaInfo.setText(matrixGraph.dfs());
    }
    @FXML
    void bfsTourOnAction(ActionEvent event) throws GraphException, QueueException, ListException {
        textareaInfo.setText(matrixGraph.bfs());

    }

    @FXML
    public void ramdomizeOnAction(ActionEvent actionEvent) throws GraphException, ListException {
        matrixGraph = new AdjacencyMatrixGraph(10);
        for (int i = 0; i < 10; i++) {
            matrixGraph.addVertex(util.Utility.random(99));
        }
        for (int i = 0; i < 10; i++) {
            int vertexIndex = util.Utility.random(0,9);
            int vertexIndex2 = util.Utility.random(0,9);
            if (vertexIndex != vertexIndex2) {
                matrixGraph.addEdge(matrixGraph.vertexList[vertexIndex].data, matrixGraph.vertexList[vertexIndex2].data);
            }
            if (!matrixGraph.containsEdge(matrixGraph.vertexList[i].data,matrixGraph.vertexList[ vertexIndex].data)) {
                matrixGraph.addEdge(matrixGraph.vertexList[i].data, matrixGraph.vertexList[ vertexIndex].data);
            }
        }
        drawMatrixGraph();
    }


    @FXML
    public void toStringOnAction(ActionEvent actionEvent) {
        textareaInfo.setText(matrixGraph.toString());
    }
}
