package domain;

import domain.list.ListException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdjacencyMatrixGraphTest {

    @Test
    void test() {
        //a
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(8);
        try {
            //b
            graph.addVertex("A");
            graph.addVertex("B");
            graph.addVertex("C");
            graph.addVertex("D");
            graph.addVertex("E");
            graph.addVertex("F");
            graph.addVertex("G");
            graph.addVertex("H");
            graph.addEdgeAndWeight("A", "B", "blanco");
            graph.addEdgeAndWeight("B", "C", "verde");
            graph.addEdgeAndWeight("C", "D", "negro");
            graph.addEdgeAndWeight("A", "E", "azul");
            graph.addEdgeAndWeight("E", "F", "café");
            graph.addEdgeAndWeight("F", "G", "rojo");
            graph.addEdgeAndWeight("G", "H", "morado");
            //c
            System.out.println(graph.toString());

            //d
            //e
            //f

        } catch (GraphException e) {
            throw new RuntimeException(e);
        } catch (ListException e) {
            throw new RuntimeException(e);
        }


    }
}