package domain;

import Objetos.Place;
import domain.list.ListException;
import domain.queue.QueueException;
import domain.stack.StackException;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class AdjacencyListGraphTest {
    @Test
    void test() {
        try {
            AdjacencyListGraph graph = new AdjacencyListGraph(20);
            Place place1 = new Place("Cartago");
            Place place2 = new Place("Heredia");
            Place place3 = new Place("San Jose");
            Place place4 = new Place("Guanacaste");
            Place place5 = new Place("Puntarenas");
            Place place6 = new Place("Limon");
            Place place7 = new Place("Alajuela");
            Place place8 = new Place("Puerto Viejo");
            Place place9 = new Place("Palmares");
            Place place10 = new Place("Orotina");
            Place place11 = new Place("Nicoya");
            Place place12 = new Place("El coco");
            Place place13 = new Place("Jacó");
            Place place14 = new Place("Sixaola");
            Place place15 = new Place("Quepos");
            Place place16 = new Place("Turrialba");
            Place place17 = new Place("Liberia");
            Place place18 = new Place("Paraiso");
            Place place19 = new Place("Orosi");
            Place place20 = new Place("El Roble");
            graph.addVertex(place1);
            graph.addVertex(place2);
            graph.addVertex(place3);
            graph.addVertex(place4);
            graph.addVertex(place5);
            graph.addVertex(place6);
            graph.addVertex(place7);
            graph.addVertex(place8);
            graph.addVertex(place9);
            graph.addVertex(place10);
            graph.addVertex(place11);
            graph.addVertex(place12);
            graph.addVertex(place13);
            graph.addVertex(place14);
            graph.addVertex(place15);
            graph.addVertex(place16);
            graph.addVertex(place17);
            graph.addVertex(place18);
            graph.addVertex(place19);
            graph.addVertex(place20);
            int random = util.Utility.random(50,100);
            graph.addEdgeAndWeight(place1, place2, random);
            graph.addEdgeAndWeight(place1, place3, random);
            graph.addEdgeAndWeight(place2, place4, random);
            graph.addEdgeAndWeight(place2, place5, random);
            graph.addEdgeAndWeight(place3, place6, random);
            graph.addEdgeAndWeight(place3, place7, random);
            graph.addEdgeAndWeight(place4, place8, random);
            graph.addEdgeAndWeight(place4, place9, random);
            graph.addEdgeAndWeight(place5, place10, random);
            graph.addEdgeAndWeight(place5, place11, random);
            graph.addEdgeAndWeight(place6, place12, random);
            graph.addEdgeAndWeight(place6, place13, random);
            graph.addEdgeAndWeight(place7, place14, random);
            graph.addEdgeAndWeight(place7, place15, random);
            graph.addEdgeAndWeight(place8, place16, random);
            graph.addEdgeAndWeight(place9, place17, random);
            graph.addEdgeAndWeight(place12, place18, random);
            graph.addEdgeAndWeight(place14, place19, random);
            graph.addEdgeAndWeight(place15, place20, random);

            //d. Muestre el contenido del grafo por consola (vértices, aristas, pesos)
            System.out.println(graph.toString());

            //e. Pruebe los recorridos dfs(), bfs() y muestre los resultados por consola
            System.out.println("\n\nRecorrido bfs:\n" + graph.bfs());
            System.out.println("\n\nRecorrido dfs:\n" + graph.dfs());

            //f. Suprima 5 vértices al azar (también deberá suprimir aristas y pesos)
            for (int i = 0; i < 5; i++) {
                Place remove = new Place(util.Utility.getPlace());
                graph.removeVertex(remove);
            }
            //g. Muestre el contenido del grafo por consola (vértices, aristas, pesos)
            System.out.println("\nGrafo post eliminaciones...\n" + graph);


        } catch (GraphException | ListException | QueueException | StackException e) {
            throw new RuntimeException(e);
        }
    }

}