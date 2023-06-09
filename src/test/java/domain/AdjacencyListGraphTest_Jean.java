package domain;

import domain.Objetos.Place;
import domain.list.ListException;
import domain.queue.QueueException;
import domain.stack.StackException;
import org.junit.jupiter.api.Test;

class AdjacencyListGraphTest_Jean {

    @Test
    void test() throws GraphException, ListException, QueueException, StackException {
        //a. Cree e instancie un objeto tipo AdjacencyListGraph llamado “graph”.
        AdjacencyListGraph graph = new AdjacencyListGraph(20);
        //b. Agregue 20 nuevos vértices de tipo Place (name) al grafo
        graph.addVertex(new Place("San José"));
        graph.addVertex(new Place("Alajuela"));
        graph.addVertex(new Place("Cartago"));
        graph.addVertex(new Place("Heredia"));
        graph.addVertex(new Place("Guanacaste"));
        graph.addVertex(new Place("Puntarenas"));
        graph.addVertex(new Place("Limón"));
        graph.addVertex(new Place("Desamparados"));
        graph.addVertex(new Place("Puriscal"));
        graph.addVertex(new Place("Turrialba"));
        graph.addVertex(new Place("Liberia"));
        graph.addVertex(new Place("Pérez Zeledón"));
        graph.addVertex(new Place("San Carlos"));
        graph.addVertex(new Place("Parrita"));
        graph.addVertex(new Place("Escazú"));
        graph.addVertex(new Place("Santa Cruz"));
        graph.addVertex(new Place("Tilarán"));
        graph.addVertex(new Place("Coto Brus"));
        graph.addVertex(new Place("Orotina"));
        graph.addVertex(new Place("Talamanca"));
        //c. Siempre en la clase unit testing, cree y compruebe el funcionamiento de un método llamado: public void connect (AdjacencyListGraph g) que
        //recorra todo el grafo y conecte los lugares que inicien con la misma letra. Los pesos de cada arista serán valores entre 50 y 100.
        connect(graph);
        //d. Muestre el contenido del grafo por consola (vértices, aristas, pesos)
        System.out.println(graph.toString());
    }

    public void connect(AdjacencyListGraph g) throws ListException, GraphException {
        int n = g.size();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //verifica si la primera letra del vertice posicion i es igual a la primera letras del vertice posicion j
                if (i != j && !g.containsEdge(g.getVertex(i).data, g.getVertex(j).data))
                    if (String.valueOf(g.getVertex(i).data).substring(0, 1).equalsIgnoreCase(String.valueOf(g.getVertex(j).data).substring(0, 1))) {
                        g.addEdgeAndWeight(g.getVertex(i).data, g.getVertex(j).data, util.Utility.random(50, 100));
                    }
            }
        }
    }
}