package domain.queue;

public class LinkedQueue implements Queue{
    private Node front; //apuntador al anterior
    private Node rear; //apuntador al posterior
    private int  count; //control de elementos encolados

    public LinkedQueue() {
        this.front=this.rear=null;
        count = 0;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public void clear() {
        this.front=this.rear=null;
        count = 0;
    }

    @Override
    public boolean isEmpty() {
        return front == null;
    }

    @Override
    public int indexOf(Object element) throws QueueException {
        if(isEmpty()){
            throw  new QueueException("Linked Queue is empty");
        }
        LinkedQueue aux = new LinkedQueue();
        int i = 1; //iniciamos en el indice 1
        int j = -1; //si es -1 no existe el elemento en la cola
        while(!isEmpty()){
            if(util.Utility.compare(front(), element)==0){
                j = i;
            }
            aux.enQueue(deQueue());
            i++;
        }
        //al final dejamos la cola en su estado original
        while(!aux.isEmpty()){
            enQueue(aux.deQueue());
        }
        return j;
    }

    @Override
    public void enQueue(Object element) throws QueueException {
        Node newNode = new Node(element);
        if(isEmpty()){ //si la cola no existe
            rear = newNode;
            //garantizo q anterior quede apuntando al 1er nodo
            front = rear;
        }else{ //quiere decir q la cola existe
            rear.next = newNode;
            rear = newNode;
        }
        count++; //actualizamos el contador de elementos
    }

    @Override
    public Object deQueue() throws QueueException {
        if(isEmpty()){
            throw  new QueueException("Linked Queue is empty");
        }
        Object element = front.data;
        //caso 1. Cuando solo hay un elemento
        if(front==rear){
            clear(); //elimino la cola
        }else{ //caso 2. Cuando hay varios elementos
            front = front.next;
            count--; //actualizo el contador de elementos
        }
        return element;
    }

    @Override
    public boolean contains(Object element) throws QueueException {
        if(isEmpty()){
            throw  new QueueException("Linked Queue is empty");
        }
        LinkedQueue aux = new LinkedQueue();
        boolean finded = false;
        while(!isEmpty()){
            if(util.Utility.compare(front(), element)==0){
                finded = true;
            }
            aux.enQueue(deQueue());
        }
        //al final dejamos la cola en su estado original
        while(!aux.isEmpty()){
            enQueue(aux.deQueue());
        }
        return finded;
    }

    @Override
    public Object peek() throws QueueException {
        if(isEmpty()){
            throw  new QueueException("Linked Queue is empty");
        }
        return front.data;
    }

    @Override
    public Object front() throws QueueException {
        if(isEmpty()){
            throw  new QueueException("Linked Queue is empty");
        }
        return front.data;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "Linked Queue is empty";
        String result = "Linked Queue Content\n";
        try{
            LinkedQueue aux = new LinkedQueue();
            while(!isEmpty()){
                result+=front()+", ";
                aux.enQueue(deQueue());
            }
            //al final dejamos la cola en su estado original
            while(!aux.isEmpty()){
                enQueue(aux.deQueue());
            }

        }catch(QueueException ex){
            throw new RuntimeException(ex);
        }
        return result;
    }
}
