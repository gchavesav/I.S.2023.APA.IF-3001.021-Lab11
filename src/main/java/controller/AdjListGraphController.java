package controller;

import domain.AdjacencyListGraph;
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
        fill(counter);
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

            if (1==0){}
            for (int j = 0; j < noVertexs; j++) {
                if(j <= i){
                    if (adj.containsEdge(adj.getVertex(i).data, adj.getVertex(j).data)){
                        double angleTo = j * angleStep;
                        double xTo = centerX + radius * Math.cos(angleTo);
                        double yTo = centerY + radius * Math.sin(angleTo);

                        Line line = new Line(x, y, xTo+10, yTo+10);
                        graphPane.getChildren().add(line);
                    }
                }
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

    private void fill(int n) throws GraphException, ListException {
        adj = new AdjacencyListGraph(n);
        for (int i = 0; i < n; i++) {
            char a = util.Utility.getAlphabet();
            if (i==0){
                adj.addVertex(a);
            }else{
                if (!adj.containsVertex(a)){
                    adj.addVertex(a);
                }else{
                    i--;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            int vertexIndex = util.Utility.random(9);
            int vertexIndex2 = util.Utility.random(9);
            int peso = util.Utility.random(99);
            if (!adj.containsEdge(adj.getVertex(vertexIndex2).data,adj.getVertex(vertexIndex).data)) {
                adj.addEdgeAndWeight(adj.getVertex(vertexIndex2).data, adj.getVertex(vertexIndex).data, peso);
                //System.out.println(vertexIndex+", "+vertexIndex2); //prueba
            }
            if (!adj.containsEdge(adj.getVertex(i).data,adj.getVertex(vertexIndex).data)) {
                adj.addEdgeAndWeight(adj.getVertex(i).data, adj.getVertex(vertexIndex).data, peso);
                //System.out.println(vertexIndex+", "+vertexIndex2); //prueba
            }
        }
    }

    @FXML
    void bfsTourOnAction(ActionEvent event) {
        try {
            String result = "ADJACENCY LIST GRAPH...\n"+
                    "Recorrido BFS: \n"+adj.bfs();
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
        interactive1 = util.FXUtility.dialog("Edge","Contains: ");
        interactive1.showAndWait();
        String s1 = String.valueOf(interactive1.getResult());
        Character c1= s1.charAt(0);
        this.alert=util.FXUtility.alert("Edge","Contains: ");
        alert.setAlertType(Alert.AlertType.INFORMATION);

        interactive2 = util.FXUtility.dialog("Edge ","Contains: ");
        interactive2.showAndWait();
        String s2= String.valueOf(interactive1.getResult());
        Character c2= s2.charAt(0);
        this.alert=util.FXUtility.alert("Edge","Is Edge?: ");
        alert.setAlertType(Alert.AlertType.INFORMATION);

        try {
            alert.setContentText(String.valueOf(adj.containsEdge(c1, c2)));
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
        String s1 = String.valueOf(interactive1.getResult());
        Character c1= s1.charAt(0);
        this.alert=util.FXUtility.alert("Vertex ","Contains: ");
        alert.setAlertType(Alert.AlertType.INFORMATION);
        try {
            alert.setContentText(String.valueOf(adj.containsVertex(c1)));
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
            String result = "ADJACENCY LIST GRAPH...\n"+
                    "Recorrido DFS: \n"+adj.dfs();
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
            fill(counter);
            drawAdjListGraph();
        } catch (GraphException e) {
            throw new RuntimeException(e);
        } catch (ListException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void toStringOnAction(ActionEvent event) {
        listGraphTextArea.setText(adj.toString());
    }
}
