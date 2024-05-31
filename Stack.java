import Assignment1.LinkedList;
public class Stack extends LinkedList{
    public Stack(){
        super();
    }

    public void push(Object obj){
        insertAtFront(obj);
    }

    public Object pop(){
        return removeFromFront();
    }

    public Object peek(){
        return getFirst();
    }
    
    public boolean isEmpty(){
        return super.isEmpty();
    }
    public void print(){
        super.print();
    }
}
