package domain.controller;

import domain.AdjacencyMatrixGraph;
import domain.GraphException;
import domain.list.ListException;
import domain.list.SinglyLinkedList;
import domain.queue.LinkedQueue;
import domain.queue.QueueException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import util.Utility;

public class AdjacencyMatrixGraphOperationsController {
    @FXML
    private BorderPane bp;
    private LinkedQueue edgesAdded;
    private LinkedQueue vertexAdded;
    private AdjacencyMatrixGraph adjacencyMatrixGraph;
    Alert alert;
    @FXML
    private TextArea textArea;

    private SinglyLinkedList list;
    private boolean isRandomized = false;
    private Object[][] matriz;
    @FXML
    private Pane lienzo;
    @FXML
    private Group graf;


    public void initialize() throws GraphException, ListException, QueueException {
        vertexAdded = new LinkedQueue();
        edgesAdded = new LinkedQueue();
        list = new SinglyLinkedList();
        adjacencyMatrixGraph = new AdjacencyMatrixGraph(30);
        btnOnActionRandomize(new ActionEvent());
    }

//JOSUE VERSION
    public void drawGraph() throws ListException, GraphException {
//        double centerX = lienzo.getWidth() / 1/5;
//        double centerY = lienzo.getHeight() / 1/5;

        double centerX = lienzo.getWidth() / 3;
        double centerY = lienzo.getHeight() / 3;
        double radius = Math.min(centerX, centerY) - 100;

        int numVertices = adjacencyMatrixGraph.size();
        double angleStep = 2 * Math.PI / numVertices;

        graf.getChildren().clear();

        for (int i = 0; i < numVertices; i++) {
            double angle = i * angleStep;
            double x = centerX + radius * Math.cos(angle);
            double y = centerY + radius * Math.sin(angle);

            Circle vertex = new Circle(x, y, 20);
            vertex.setFill(Color.CYAN);

            Text vertexLabel = new Text(String.valueOf(adjacencyMatrixGraph.vertexList[i].data));
            vertexLabel.setFill(Color.BLACK);
//            vertexLabel.setX(x - vertexLabel.getLayoutBounds().getWidth() / 1.5);-->original
            //vertexLabel.setY(y + vertexLabel.getLayoutBounds().getHeight() / 2);--> original

            vertexLabel.setX(x - vertexLabel.getLayoutBounds().getWidth() / 2);
            vertexLabel.setY(y + vertexLabel.getLayoutBounds().getHeight() / 4);

            graf.getChildren().addAll(vertex, vertexLabel);
        }

        // Dibujar las conexiones entre los vértices
        for (int k = 0; k < numVertices; k++) {
            for (int j = 0; j < numVertices; j++) {
                if (adjacencyMatrixGraph.containsEdge(adjacencyMatrixGraph.vertexList[k].data, adjacencyMatrixGraph.vertexList[j].data)) {
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
    void btnOnActionClear(ActionEvent event) throws GraphException, ListException {
        graf.getChildren().clear();
        adjacencyMatrixGraph.clear();
        vertexAdded.clear();
        edgesAdded.clear();
        textArea.setText("");
        list.clear();
    }

    @FXML
    void btnOnActionVertex(ActionEvent event) throws GraphException, ListException, QueueException {
        //limpiamos el panel de dibujo
        //lienzo.getChildren().clear();
        if (!adjacencyMatrixGraph.isEmpty()){

            if (adjacencyMatrixGraph.size() < 30){
                //agregamos los vertices aleatorios no repetidos
                int newVertex = randomNumbers();
                adjacencyMatrixGraph.addVertex(newVertex);
                vertexAdded.enQueue(newVertex);
                list.add(newVertex);
                textArea.setText(adjacencyMatrixGraph.toString());
                drawGraph();
                //limpiamos el panel, pero mantenemos el grafo
            }else{

                alert  = util.FXUtility.alert("Error", "Graph size reached");
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.showAndWait();

            }
        }
    }


    @FXML
    void btnOnActionEdges_Weights(ActionEvent event) throws GraphException, ListException, QueueException {
        //limpiamos el panel de dibujo
        //lienzo.getChildren().clear();

        if (!adjacencyMatrixGraph.isEmpty()){

            //Agregamos el nuevo peso y linea
            boolean added = false;
            int count = list.size();
            //obtener algun vertice de la lista
            int vertexRandom1 = -1;
            int vertexRandom2 = -1;
            while(added == false){
                if (vertexRandom1 != vertexRandom2){
                    adjacencyMatrixGraph.addEdgeAndWeight(vertexRandom1,vertexRandom2, Utility.random(1,50));
                    edgesAdded.enQueue(vertexRandom1+"-"+vertexRandom2);
                    textArea.setText(adjacencyMatrixGraph.toString());
                    added = true;
                }
                //Obtener dos vertices de los agregados a la lista
                vertexRandom1 = (int) list.getNode(Utility.random(1, count)).data;
                vertexRandom2 = (int) list.getNode(Utility.random(1, count)).data;
            }
            drawGraph();

        }
    }


    @FXML
    void btnOnActionRandomize(ActionEvent event) throws GraphException, ListException, QueueException {
        //lienzo.getChildren().clear();
        //randomizeGraph();
        //textArea.setText(adjacencyMatrixGraph.toString());

        vertexAdded.clear();
        edgesAdded.clear();
        list.clear();

        //indicamos el tam maximo del grafo
        adjacencyMatrixGraph = new AdjacencyMatrixGraph(30);

        //agregamos 10 vertices aleatorios no repetidos, pero uno manual para evitar el IOException isEmpty()
        int initialVertex = Utility.random(0,99);
        adjacencyMatrixGraph.addVertex(initialVertex);
        vertexAdded.enQueue(initialVertex);
        list.add(initialVertex);

        int count = 0;
        while(count <= 9){
            int vertex = Utility.random(0, 99);
            if (!adjacencyMatrixGraph.containsVertex(vertex)){
                count++;
                adjacencyMatrixGraph.addVertex(vertex);
                vertexAdded.enQueue(vertex);
                list.add(vertex);
            }
        }

        //Agregar 3 pesos aleaorios
        count = list.size();
        int i = 0;
        int vertexRandom1 = -1;
        int vertexRandom2 = -1;

        while(i <= 3){
            if (vertexRandom1 != vertexRandom2){
                adjacencyMatrixGraph.addEdgeAndWeight(vertexRandom1,vertexRandom2, Utility.random(1,50));
                edgesAdded.enQueue(vertexRandom1+"-"+vertexRandom2);
                i++;
            }
            vertexRandom1 = (int) list.getNode(Utility.random(1, count)).data;
            vertexRandom2 = (int) list.getNode(Utility.random(1, count)).data;
        }

        textArea.setText(adjacencyMatrixGraph.toString());
        drawGraph();
    }

    @FXML
    void btnOnActionRemoveVertex(ActionEvent event) throws QueueException, GraphException, ListException {

        if (!adjacencyMatrixGraph.isEmpty()) {
            list.remove(vertexAdded.front());//removemos de la lista
            adjacencyMatrixGraph.removeVertex(vertexAdded.deQueue());
            textArea.setText(adjacencyMatrixGraph.toString());
        }else{
            if(vertexAdded.isEmpty()){
                alert = util.FXUtility.alert("Error", "No more vertex found");
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.showAndWait();
            }
        }
        drawGraph();
    }

    @FXML
    public void btnOnActionRemoveEdgesWeights(ActionEvent actionEvent) throws QueueException, GraphException, ListException {
        if (!adjacencyMatrixGraph.isEmpty()){
            if(edgesAdded.isEmpty()){
                alert  = util.FXUtility.alert("Error", "No more edges found");
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.showAndWait();
            }else{
                String []remove = edgesAdded.deQueue().toString().split("-");
                adjacencyMatrixGraph.removeEdge(Integer.parseInt(String.valueOf(remove[0])), Integer.parseInt(String.valueOf(remove[1])));
                textArea.setText(adjacencyMatrixGraph.toString());
            }
        }
        drawGraph();
    }

    public int randomNumbers() throws GraphException, ListException, QueueException {
        //num aleatorio
        int number = Utility.random(0,99);
        //seguir generando numeros hasta que ninguna coincida con uno de los del grafo
        while(adjacencyMatrixGraph.containsVertex(number)){
            number = Utility.random(0,99);
        }
        return number;
    }
}