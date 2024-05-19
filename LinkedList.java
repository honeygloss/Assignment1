package Assignment1;
public class LinkedList {
     Node head, tail, current;

    public LinkedList(){
        head = tail = current = null;
    }

    public boolean isEmpty(){
        return head==null;
    }

    public String print(){
        if(isEmpty())
            return "( empty list )";
        else 
            return "(" + head + ")";
    }

    public void insertAtFront(Object data){
        if(isEmpty())
            head = tail = new Node(data);
        else
            head = new Node(data, head);
    }

    public int size(){
        int count=0;
        if(isEmpty())
            return count;
        
        current = head;
        while(current!=null){
            ++count;
            current = current.next;
        }
        return count;
    }

    public void insertAtBack(Object data){
        if(isEmpty())
            head = tail = new Node(data);
        else 
            tail = tail.next = new Node(data);
    }

    public Object getFirst() throws EmptyListException{
        if(isEmpty())
            throw new EmptyListException();
        
        current = head;
        return current.data;
    }

    public Object getNext(){
        if(current != tail){
            current = current.next;
            return current.data;
        }
        else 
            return null;
    }

    public Object getLast() throws EmptyListException{
        if(isEmpty())
            throw new EmptyListException();
        
        return tail.data;
    }

    public Object removeFromFront() throws EmptyListException{
        if(isEmpty())
            throw new EmptyListException();
        
        Object d = head.data;

        if(head==tail)
            head=tail=null;
        else{
            Node curr = head;
            head = head.next;
            curr.next = null;
        }

        return d;
    }

    public Object removeFromBack() throws EmptyListException{
        if(isEmpty())
            throw new EmptyListException();
        
        Object d = tail.data;

        if(head==tail)
            head=tail=null;
        else{
            Node curr = head;
            while(curr.next != tail)
                curr = curr.next;
            
            tail = curr;
            curr.next = null;
        }

        return d;
    }

    public class EmptyListException extends RuntimeException{
        public EmptyListException(){
            super("The list is Empty");
        }
    }
}
