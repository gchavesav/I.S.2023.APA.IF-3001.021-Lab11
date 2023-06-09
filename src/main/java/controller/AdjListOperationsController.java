package controller;

import domain.AdjacencyListGraph;
import domain.GraphException;
import domain.list.ListException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class AdjListOperationsController
{
    @FXML
    private Pane base;

    @FXML
    private TextArea dataTextArea;

    AdjacencyListGraph adjacencyListGraph;
    List<String> listOfEdges = new ArrayList<>();

    @javafx.fxml.FXML
    public void initialize() {
        adjacencyListGraph = new AdjacencyListGraph(26);
        randomizeGraph();
        drawGraph();
    }

    @FXML
    void addENWOnAction(ActionEvent event) {
        addEdge();
        drawGraph();
        dataTextArea.setText(adjacencyListGraph.toString());
    }

    @FXML
    void addVertexOnAction(ActionEvent event) {
        addVertex();
        drawGraph();
    }

    @FXML
    void clearOnAction(ActionEvent event) {

    }

    @FXML
    void randomizeOnAction(ActionEvent event) {
        randomizeGraph();
        drawGraph();
    }

    @FXML
    void removeENWOnAction(ActionEvent event) {

    }

    @FXML
    void removeVertexOnAction(ActionEvent event) {

    }

    private void addVertex(){
        char c = util.Utility.getAlphabet();
        try {
            if (!adjacencyListGraph.isEmpty()){
                adjacencyListGraph.addVertex(c);
                while (adjacencyListGraph.containsVertex(c)){
                    c = util.Utility.getAlphabet();
                }
            }
            adjacencyListGraph.addVertex(c);
        } catch (GraphException | ListException e) {
            throw new RuntimeException(e);
        }

        dataTextArea.setText(adjacencyListGraph.toString());
    }

    private void randomizeGraph(){
        listOfEdges.clear();
        adjacencyListGraph.clear();
        try {
            adjacencyListGraph.addVertex(util.Utility.getAlphabet());
            for (int i = 0; i < 10-1; i++) {
                    char letter = util.Utility.getAlphabet();
                    if (!adjacencyListGraph.containsVertex(letter)) adjacencyListGraph.addVertex(letter);
                    else i--;
            }

            for (int i = 0; i < 10; i++) {
                addEdge();
            }

        } catch (GraphException | ListException e) {
            e.printStackTrace();
        }

        dataTextArea.setText(adjacencyListGraph.toString());
    }

    private void addEdge(){
        int a = 0;
        int b = 0;
        char vertexA;
        char vertexB;
        String dataList;
        String dataListInverted;
        try {
            do {
                a = util.Utility.random(0, adjacencyListGraph.size());
                b = util.Utility.random(0, adjacencyListGraph.size());
                vertexA = (char) adjacencyListGraph.getVertex(a).data;
                vertexB = (char) adjacencyListGraph.getVertex(b).data;
                dataList = vertexA + Character.toString(vertexB);
                dataListInverted = dataList.charAt(1) + Character.toString(dataList.charAt(0));
            }while (adjacencyListGraph.containsEdge(vertexA, vertexB) || a == b || listOfEdges.contains(dataList) || listOfEdges.contains(dataListInverted));
            adjacencyListGraph.addEdgeAndWeight(vertexA, vertexB, util.Utility.random(1, 50));
            listOfEdges.add(dataList);
        } catch (ListException | GraphException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(String.valueOf(e));
            alert.showAndWait();
        }
    }

    private void drawGraph(){
        base.getChildren().clear();
        int numNodes = 0;
        double centerY = base.getPrefHeight()/2;
        double centerX = base.getPrefWidth()/2;
        double radius = base.getPrefHeight()/2 - 20;
        Circle[] circles;
        Text[] texts;

        try {
            numNodes = adjacencyListGraph.size();
            double angleStep = 2 * Math.PI / numNodes;
            circles = new Circle[numNodes];
            texts = new Text[numNodes];
            for (int i = 0; i < numNodes; i++) {
                double angle = i * angleStep;
                double x = centerX + radius * Math.cos(angle);
                double y = centerY + radius * Math.sin(angle);

                Circle node = new Circle(x, y, 25, Color.valueOf("#027a8e"));
                circles[i] = node;

                Text data = null;
                data = new Text(String.valueOf(adjacencyListGraph.getVertex(i).data));

                data.setFont(Font.font("Arial", FontWeight.BOLD, 20));
                data.setFill(Color.WHITE);
                double textWidth = data.getLayoutBounds().getWidth();
                double textHeight = data.getLayoutBounds().getHeight();
                data.setX(x - textWidth / 2);
                data.setY(y + textHeight / 4);
                texts[i] = data;

                base.getChildren().add(node);
            }
        } catch (ListException | GraphException e) {
            throw new RuntimeException(e);
        }

        drawEdges(circles);
        base.getChildren().addAll(texts);
    }

    private void drawEdges(Circle[] circles){
        int n = listOfEdges.size();
        double centerY = base.getPrefHeight()/2;
        double centerX = base.getPrefWidth()/2;

        for (int i = 0; i < n; i++) {
            String edges = listOfEdges.get(i);
            int a = adjacencyListGraph.indexOf(listOfEdges.get(i).charAt(0));
            int b = adjacencyListGraph.indexOf(listOfEdges.get(i).charAt(1));
            Circle nodoA = circles[a];
            Circle nodoB = circles[b];

            double endX = centerX + (nodoB.getCenterX() - centerX) * 0.9;
            double endY = centerY + (nodoB.getCenterY() - centerY) * 0.9;
            double startX = centerX + (nodoA.getCenterX() - centerX) * 0.9;
            double startY = centerY + (nodoA.getCenterY() - centerY) * 0.9;

            Line line = new Line(startX, startY, endX, endY);
            line.setStroke(Color.BLACK);
            line.setStrokeWidth(3);
            line.setOnMouseEntered(this::onMouseEntered);
            line.setOnMouseExited(this::onMouseExited);
            this.base.getChildren().add(line);
        }
    }

    private void onMouseExited(MouseEvent mouseEvent) {
        Line line = (Line) mouseEvent.getSource();
        line.setStroke(Color.BLACK);
        line.setStrokeWidth(3);
    }

    private void onMouseEntered(MouseEvent event) {
        Line line = (Line) event.getSource();
        line.setStroke(Color.RED);
        line.setStrokeWidth(5);
    }
}