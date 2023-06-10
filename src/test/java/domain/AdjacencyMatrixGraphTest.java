package domain;

import domain.list.ListException;
import domain.queue.QueueException;
import domain.stack.StackException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdjacencyMatrixGraphTest {

    @Test
    void test() {
        //4.a
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(8);
        try {
            //4.b
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
            //4.c
            System.out.println(graph.toString());

            //4.d Sebastián Monge C04973
            System.out.println("\n-- dfs--");
            System.out.println(graph.dfs());
            System.out.println("\n-- bfs--");
            System.out.println(graph.bfs());

            //4.e Sebastián Monge C04973
            System.out.println("\nRemoving Vertex: C,E,G with his edges and weights" );

            graph.removeEdge("C","B");
            graph.removeEdge("C","D");
            graph.removeEdge("E","A");
            graph.removeEdge("E","F");
            graph.removeEdge("G","F");
            graph.removeEdge("G","H");

            graph.removeVertex("C");
            graph.removeVertex("E");
            graph.removeVertex("G");

            //4.f Sebastián Monge C04973
            System.out.println(graph.toString());

        } catch (GraphException  | StackException |QueueException |ListException e ) {
            throw new RuntimeException(e);
        }


    }
}