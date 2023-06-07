package domain;

import domain.list.ListException;
import domain.queue.QueueException;
import domain.stack.StackException;

public class AdjacencyMatrixGraph implements Graph{
    private int n; //tam maximo de vertices para el grafo
    private Vertex vertexList[]; //lista de vertices
    private Object adjacencyMatrix[][];
    private int counter; //me dice el num de vertices agregados

    public AdjacencyMatrixGraph(int n) {
        if(n<=0) System.exit(-1); //sale con error
        this.n = n;
        this.counter = 0;
        this.vertexList = new Vertex[n];
        this.adjacencyMatrix = new Object[n][n];

    }

    @Override
    public int size() throws ListException {
        return counter;
    }

    @Override
    public void clear() {
        this.counter = 0;
        this.vertexList = new Vertex[n];
        this.adjacencyMatrix = new Object[n][n];
    }

    @Override
    public boolean isEmpty() {
        return counter==0;
    }

    @Override
    public boolean containsVertex(Object element) throws GraphException, ListException {
        return false;
    }

    @Override
    public boolean containsEdge(Object a, Object b) throws GraphException, ListException {
        return false;
    }

    @Override
    public void addVertex(Object element) throws GraphException, ListException {
        if(counter>=vertexList.length)
            throw new GraphException("Adjacency Matrix Graph is Full");
        vertexList[counter++] = new Vertex(element);
    }

    @Override
    public void addEdge(Object a, Object b) throws GraphException, ListException {
        if(!containsVertex(a)||!containsVertex(b))
            throw new GraphException("Cannot add edge between ["+a+"] and ["+b+"]");
        adjacencyMatrix[indexOf(a)][indexOf(b)] = 1;
        adjacencyMatrix[indexOf(b)][indexOf(a)] = 1;
    }

    private int indexOf(Object value) {
        for (int i = 0; i < counter; i++) {
            if(util.Utility.compare(vertexList[i].data, value)==0) return i;
        }
        return -1; //significa que vertice no existe
    }

    @Override
    public void addWeight(Object a, Object b, Object weight) throws GraphException, ListException {

    }

    @Override
    public void removeVertex(Object element) throws GraphException, ListException {

    }

    @Override
    public void removeEdge(Object a, Object b) throws GraphException, ListException {

    }

    @Override
    public String dfs() throws GraphException, StackException, ListException {
        return null;
    }

    @Override
    public String bfs() throws GraphException, QueueException, ListException {
        return null;
    }
}
