package domain;
import domain.list.SinglyLinkedList;

public class Vertex {
    public Object data;
    public SinglyLinkedList edgesList; //lista de aristas
    private boolean visited; //para los recorridos DFS, BFS

    public Vertex(Object data) {
        this.data = data;
        this.edgesList = new SinglyLinkedList();
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Object getData() {
        return data;
    }
}
