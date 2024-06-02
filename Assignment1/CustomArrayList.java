package Assignment1;

public class CustomArrayList {
    private Object[] data;
    private int length, maxSize;

    public CustomArrayList(){
        maxSize = 0;
        data = null;
        length = 0;
    }

    public CustomArrayList(int k){
        maxSize = k;
        data = new Object[maxSize];
        length = 0;
    }

    public int getMaxSize(){
        return maxSize;
    }

    public int getNum(){
        return length;
    }

    public void insertEnd(Object k) throws ArrayListException{
        if(!isFull()){
            data[length] = k;
            length = length + 1;
        }else
            throw new ArrayListException("The Array List is full");
    }

    public void insertFront(Object k) throws ArrayListException{
        add(0,k);
    }
 
    public void add(int i, Object k) throws ArrayListException{
        if(isFull())
            throw new ArrayListException("The Array List is full");
        else if ((i<0) || (i>length))
            throw new ArrayListException("Index is out of bounds");
        else{
            for(int j = length-1; j >= i; --j)
                data[j+1] = data[j];
            length = length + 1;
            data[i] = k;
        }
    }

    public Object getData(int i) throws ArrayListException{
        if(isEmpty())
            throw new ArrayListException("The Array List is Empty");
        else if((i<0) || (i>length-1))
            throw new ArrayListException("Index is out of bounds");

        return data[i];
    }

    public void setData(int i, Object k) throws ArrayListException{
        if(isEmpty())
            throw new ArrayListException("Index is out of bounds");
        else if((i<0) || (i>length-1))
            throw new ArrayListException("Index is out of bounds");

        data[i] = k;
    }

    public Object removeAt(int i) throws ArrayListException{
        if(isEmpty())
            throw new ArrayListException("Index is out of bounds");
        else if((i<0) || (i>length-1))
            throw new ArrayListException("Index is out of bounds");
        
        if( i == (length-1)){
            length = length - 1;
            return data[i];
        }

        Object obj = data[i];
        for(int j = i; j<length-1; ++j){
            data[j] = data[j+1];
        }

        length = length - 1;
        return obj;
    }

    public Object removeFirst() throws ArrayListException{
        return removeAt(0);
    }

    public Object removeLast() throws ArrayListException{
        return removeAt(length-1);
    }

    public void clearList(){
        length = 0;
    }

    public boolean isFull(){
        return length == maxSize;
    }

    public boolean isEmpty(){
        return length == 0;
    }

    public String print(){
        String str = "(";
        if(length == 0)
            return "()";
        else{
            for(int i=0; i<length; ++i)
                str += data[i]+" , \n";
            return str+")";
        }
    }
}
