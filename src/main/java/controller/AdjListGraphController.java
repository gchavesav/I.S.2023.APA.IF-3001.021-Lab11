package controller;

import domain.AdjacencyListGraph;
import domain.AdjacencyMatrixGraph;
import domain.GraphException;
import domain.Vertex;
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

public class AdjListGraphController {

    @FXML
    private Pane graphPane;

    @FXML
    private Group graph;
    @FXML
    private TextArea listGraphTextArea;
    private Alert alert;
    private AdjacencyListGraph adj;
    private TextInputDialog interactive1, interactive2;
    int counter= 10; //cantidad de vertices que hay

    @FXML
    public void initialize() throws GraphException, ListException {
        this.alert = util.FXUtility.alert("", "");
        randomize(counter);
        drawAdjListGraph();
    }

    private void drawAdjListGraph() throws ListException, GraphException {
        double centerX = graphPane.getWidth() / 2;
        double centerY = graphPane.getHeight()/2;
        double radius = Math.min(centerX, centerY)-100;

        int noVertexs = adj.size();
        double angleStep = 2 * Math.PI / noVertexs;

        graphPane.getChildren().clear();

        // Dibujar las conexiones entre los vértices
        for (int i = 0; i < noVertexs; i++) {
            double angle = i * angleStep;
            double x = centerX + radius * Math.cos(angle);
            double y = centerY + radius * Math.sin(angle);

            for (int j = 0; j < noVertexs; j++) {

                double angleTo = j * angleStep;
                double xTo = centerX + radius * Math.cos(angleTo);
                double yTo = centerY + radius * Math.sin(angleTo);

                Line line = new Line(x, y, xTo+10, yTo+10);
                graphPane.getChildren().add(line);

            }

            Circle vertex = new Circle(x, y, 25);
            vertex.setFill(Color.CYAN);
            graphPane.getChildren().add(vertex);

            Text vertexLabel = new Text(String.valueOf(adj.getVertex(i).data));
            vertexLabel.setFill(Color.BLACK);
            vertexLabel.setX(x - vertexLabel.getLayoutBounds().getWidth() / 2);
            vertexLabel.setY(y + vertexLabel.getLayoutBounds().getHeight() / 4);

            graphPane.getChildren().add(vertexLabel);
        }
    }

    private void randomize(int n) throws GraphException, ListException {
        adj = new AdjacencyListGraph(counter);
        for (int i = 0; i < 10; i++) {
            adj.addVertex(util.Utility.getAlphabet());
            // weight.setWeight(util.Utility.random(0,50));
        }
        for (int i = 0; i < 10; i++) {
            int vertexIndex = util.Utility.random(0,9);
            int vertexIndex2 = util.Utility.random(0,9);
            if (vertexIndex != vertexIndex2) {
                adj.addEdge(adj.getVertex(vertexIndex).data, adj.getVertex(vertexIndex2).data);
                //adj.addWeight(adj.vertexList[vertexIndex].data, adj.vertexList[vertexIndex2].data,weight);
            }
            if (!adj.containsEdge(adj.getVertex(i).data,adj.getVertex(vertexIndex).data)) {
                adj.addEdge(adj.getVertex(i).data, adj.getVertex(i).data);

            }
        }
    }

    private void drawAdjList(int k, double x, double y, double levelWidth) throws GraphException, ListException {
        Vertex vertex = adj.getVertex(k);
        //Gabriel y Blanca
        if (vertex != null) {
            // Dibujar las conexiones con los nodos hijos primero
            if (vertex != null) {
                double childX = x - levelWidth / 2;
                double childY = y + 60;
                drawAdjList(k+1, childX, childY, levelWidth / 2);

                // Dibujar una línea desde el nodo actual al nodo hijo izquierdo
                Line line = new Line(x, y, childX+10, childY-10);
                graphPane.getChildren().add(line);
            }

            // Dibujar el nodo actual como un círculo después de las líneas
            Circle circle = new Circle(x, y, 15);
            circle.setFill(Color.LIGHTGREEN); // Cambiar el color a verde claro
            graphPane.getChildren().add(circle);

            // Mostrar el valor del nodo
            Text text = new Text(String.valueOf(vertex.data));
            text.setX(x - 5);
            text.setY(y + 5);
            graphPane.getChildren().add(text);
        }
    }

    @FXML
    void bfsTourOnAction(ActionEvent event) {
        try {
            String result = "ADJACENCY LIST GRAPH..."+
                    "\n Recorrido BFS: \n"+adj.bfs();
            listGraphTextArea.setText(result);
        } catch (GraphException e) {
            throw new RuntimeException(e);
        } catch (QueueException e) {
            throw new RuntimeException(e);
        } catch (ListException e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    void containsEdgeOnAction(ActionEvent event) {
        interactive1 = util.FXUtility.dialog("Edge Contains","Contains: ");
        interactive1.showAndWait();
        int value1 = Integer.parseInt(interactive1.getResult());
        this.alert=util.FXUtility.alert("Edge Contains","Contains: ");
        alert.setAlertType(Alert.AlertType.INFORMATION);

        interactive2 = util.FXUtility.dialog("Edge Contains","Contains: ");
        interactive2.showAndWait();
        int value2 = Integer.parseInt(interactive2.getResult());
        this.alert=util.FXUtility.alert("Edge Contains","Contains: ");
        alert.setAlertType(Alert.AlertType.INFORMATION);

        try {
            alert.setContentText(String.valueOf(adj.containsEdge(value1, value2)));
            alert.showAndWait();
        } catch (GraphException e) {
            throw new RuntimeException(e);
        } catch (ListException e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    void containsVertexOnAction(ActionEvent event) throws GraphException, ListException {
        interactive1 = util.FXUtility.dialog("Vertex","Contains: ");
        interactive1.showAndWait();
        int value = Integer.parseInt(interactive1.getResult());
        this.alert=util.FXUtility.alert("Vertex ","Contains: ");
        alert.setAlertType(Alert.AlertType.INFORMATION);
        try {
            alert.setContentText(String.valueOf(adj.containsVertex(value)));
            alert.showAndWait();
        } catch (GraphException e) {
            throw new RuntimeException(e);
        } catch (ListException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void dfsTourOnAction(ActionEvent event) {
        try {
            String result = "ADJACENCY LIST GRAPH..."+
                    "\n Recorrido DFS: \n"+adj.dfs();
            listGraphTextArea.setText(result);
        } catch (GraphException e) {
            throw new RuntimeException(e);
        } catch (StackException e) {
            throw new RuntimeException(e);
        } catch (ListException e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    void randomizeOnAction(ActionEvent event) {
        try {
            randomize(counter);
            drawAdjListGraph();
        } catch (GraphException e) {
            throw new RuntimeException(e);
        } catch (ListException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void toStringOnAction(ActionEvent event) {
        String result = "ADJACENCY LIST GRAPH CONTENT..."+
        "\n "+adj.toString();
        listGraphTextArea.setText(result);
    }
}
