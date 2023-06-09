package controller;

import domain.AdjacencyMatrixGraph;
import domain.GraphException;
import domain.list.ListException;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.shape.Line;
public class AdjacencyMatrixGraphController
{
    @javafx.fxml.FXML
    private TextArea textareaInfo;
    @javafx.fxml.FXML
    private Pane lienzo;
    @javafx.fxml.FXML
    private Group graf;

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
        double centerX = lienzo.getWidth() / 25;
        double centerY = lienzo.getHeight() /25;
        double radius = Math.min(centerX, centerY) - 200;

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


    public void bfsTourOnAction(ActionEvent actionEvent) {
    }

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


    public void containsVertexOnAction(ActionEvent actionEvent) {
    }

    public void dfsTourOnAction(ActionEvent actionEvent) {
    }

    public void toStringOnAction(ActionEvent actionEvent) {
        textareaInfo.setText(matrixGraph.toString());
    }

    public void containsEdgeOnAction(ActionEvent actionEvent) {

    }
}