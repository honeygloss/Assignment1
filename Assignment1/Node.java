package Assignment1;
public class Node{
    Object data;
    Node next;

    public Node(){
        data = null;
        next = null;
    }

    public Node(Object data){
        this.data = data;
        next = null;
    }

    public Node(Object data, Node next){
        this.data = data;
        this.next = next;
    }

    public void setData(Object newData){
        data = newData;
    }

    public void setNext(Node newNext){
        next = newNext;
    }

    public Object getData(){
        return data;
    }

    public Node getNext(){
        return next;
    }

    public String toString(){
        if(next==null)
            return data.toString() + " ";
        else 
            return data.toString() + "," + next.toString();
    }
}
