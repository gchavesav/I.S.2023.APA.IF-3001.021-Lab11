package domain;

import org.junit.jupiter.api.Test;
import domain.AdjacencyListGraph;
import domain.GraphException;
import domain.list.ListException;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class AdjacencyListGraphTest {

    public void connect(AdjacencyListGraph g) throws GraphException, ListException {
        Random random = new Random();
        int maxVertices = getMaxVertices(g);
        if (maxVertices == -1) {
            throw new GraphException("The graph is full. Cannot connect additional vertices.");
        }
        for (int i = 0; i < maxVertices; i++) {
            String placeA = (String) g.getVertex(i).getData();
            for (int j = i + 1; j < maxVertices; j++) {
                String placeB = (String) g.getVertex(j).getData();
                if (placeA.charAt(0) == placeB.charAt(0)) {
                    int weight = random.nextInt(51) + 50; // Genera un número aleatorio entre 50 y 100
                    addEdgeAndWeight(g, placeA, placeB, weight);
                }
            }
        }
    }

    private int getMaxVertices(AdjacencyListGraph g) throws ListException {

        int maxVertices = g.getMaxVertices();
        int currentSize = g.size();
        return maxVertices - currentSize;
    }

    private void addEdgeAndWeight(AdjacencyListGraph g, Object a, Object b, Object weight) throws GraphException, ListException {
        if (!g.containsEdge(a, b)) {
            g.addEdge(a, b);
            g.addWeight(a, b, weight);
        }
    }
    @Test
    public void testDiego() {
        AdjacencyListGraph graph = new AdjacencyListGraph(20);

        try {
            // Agregar 20 nuevos vértices de tipo Place al grafo
            for (int i = 1; i <= 20; i++) {
                graph.addVertex("Place " + i);
            }

            // Conectar los lugares que inicien con la misma letra
            AdjacencyListGraphTest tester = new AdjacencyListGraphTest();
            tester.connect(graph);

            System.out.println(graph.toString());
        } catch (GraphException | ListException e) {
            e.printStackTrace();
        }
    }
}