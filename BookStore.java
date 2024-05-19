package Assignment1;
import java.util.*;

public class BookStore{
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        int size;

        System.out.println("How many books to key in: ");
        size = sc.nextInt();
        sc.nextLine();
        CustomArrayList arlist = new CustomArrayList(size);
        LinkedList lList = new LinkedList();

        //input books
        System.out.println("                         BookStore                                      ");
        for(int i=0; i<size; i++){
            System.out.println("------------------------------------------------------------------------");
            System.out.print("Book title: ");
            String title = sc.nextLine();
            System.out.print("Book author: ");
            String author = sc.nextLine();
            System.out.print("Book ISBN: ");
            String ISBN = sc.nextLine();
            System.out.print("Book price: RM ");
            double price = sc.nextDouble();
            System.out.print("Book quantity:");
            int qty = sc.nextInt();
            sc.nextLine();

            Book booklist = new Book(title, author, ISBN, price, qty);
            arlist.add(i, booklist);
        }

        System.out.println("\nxxxxxxxxxxxxxxxxxxxxxxxx Array list xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");

        //display info of books
        System.out.println("\n Books entered : \n");
        for(int i=0; i < size; i++){
            System.out.println(arlist.getData(i).toString());
            System.out.println("------------------------------------------------------------------");
        }

        //Total and average price of books sold 
        double totalSoldPrice = 0.0;
        int totalSoldQuantity = 0;

        for (int i = 0; i < arlist.getMaxSize(); i++) {
            Book bookTotal = (Book)arlist.getData(i);
            totalSoldPrice += bookTotal.calculateTotalPrice();
            totalSoldQuantity += bookTotal.getQty();
        }

        System.out.println("\nTotal price of books sold: RM " + totalSoldPrice);

        // Calculate average price 
        if (totalSoldQuantity > 0) {
            double avgSoldPrice = totalSoldPrice / totalSoldQuantity;
            System.out.println("\nAverage price of books sold: RM " + avgSoldPrice);
        } else {
            System.out.println("\nNo books sold, average price is not applicable.");
        }
        
        //get the last element and insert at front and removes the last element
        int lastIndex = arlist.getNum() - 1;
        if (lastIndex >= 0) {
            //get the last element 
            Object lastElement = arlist.getData(lastIndex);
            arlist.removeAt(lastIndex);
            //insert the last element at the front 
            arlist.insertFront(lastElement);

            //display the updated list
            System.out.println("\nCurrent Books after moving last element to front:");
            System.out.println(arlist.print()); 
        }

        //display the book with the highest total price in one transaction
        Object objHighest = arlist.getData(0);
        Book bookHighest = (Book)objHighest;
        double totPriceHigh = bookHighest.calculateTotalPrice();
        int highestInd = 0 ;
        for(int i = 0; i < arlist.getMaxSize(); i++){
            Object obj = arlist.getData(i);
            Book booktemp = (Book)obj;
            if(booktemp.calculateTotalPrice()>totPriceHigh){
                totPriceHigh = booktemp.calculateTotalPrice();
                highestInd = i;
            }
        }
        System.out.println("\nBook with highest total price in one transaction: " + arlist.getData(highestInd) + ", index :" + highestInd);

        // Display the book with the lowest total price in one transaction
        Object objLowest = arlist.getData(0); // Get the first book as the initial lowest
        Book bookLowest = (Book)objLowest;
        double totPriceLowest = bookLowest.calculateTotalPrice();
        int lowestIndex = 0;
        for (int i = 1; i < arlist.getMaxSize(); i++) { // Start from index 1 since we initialized with index 0
            Object obj = arlist.getData(i);
            Book currentBook = (Book)obj;
            if (currentBook.calculateTotalPrice()< totPriceLowest) {
                totPriceLowest = currentBook.calculateTotalPrice();
                lowestIndex = i;
            }
        }
        // Print the book with the lowest total price in one transaction
        System.out.println("\nBook with lowest total price in one transaction: " + arlist.getData(lowestIndex) + ", index :" + lowestIndex);


        //Search book ISBN
        System.out.println("------------------------------------------------------------------");
        System.out.println("\nEnter book ISBN to search: ");
        String searchISBN = sc.nextLine();
        boolean found = false;
        int searchIndex = -1;
        for(int i=0; i< arlist.getMaxSize(); i++){
            Book searchtemp = (Book)arlist.getData(i);
            if(searchtemp.getISBN().equalsIgnoreCase(searchISBN)){
                searchIndex = i;
                found = true;
                break;
            }
        }
        if(found)
            System.out.println("\nSearch item found. \n" + arlist.getData(searchIndex).toString());
        else
            System.out.println("\nSearch item not found.");

        //remove books that is quantity=0
        System.out.println("\nBooks with quantity 0 that is to be removed :");
        int currentSize = arlist.getMaxSize(); // Store the current size
        for (int i = 0; i < currentSize; i++) {
            Book qtyTemp = (Book) arlist.getData(i);
            if (qtyTemp.getQty() == 0) {
                Object objR = arlist.removeAt(i);
                System.out.println(objR.toString());
                currentSize--; 
                i--; 
            }
        }
        System.out.println("------------------------------------------------------------------");

        //add books to index entered and display the new list after removing and adding using setData
        System.out.print("\nEnter index to update: ");
        int indAdd = sc.nextInt();
        sc.nextLine();
        System.out.print("Book title: ");
        String title = sc.nextLine();
        System.out.print("Book author: ");
        String author = sc.nextLine();
        System.out.print("Book ISBN: ");
        String ISBN = sc.nextLine();
        System.out.print("Book price: RM ");
        double price = sc.nextDouble();
        System.out.print("Book quantity: ");
        int qty = sc.nextInt();
        sc.nextLine(); 

        Book bookToAdd = new Book(title, author, ISBN, price, qty);
        arlist.setData(indAdd, bookToAdd);

        //display current list
        System.out.println("\nBooks : \n");
        for(int i=0; i < arlist.getNum(); i++){
            System.out.println(arlist.getData(i).toString());
            System.out.println("------------------------------------------------------------------");
        }

        //copy new books data from CustomArrayList to LinkedList
        for (int i = 0; i < arlist.getMaxSize(); i++) {
            lList.insertAtBack(arlist.getData(i));
        }

        System.out.println("\nxxxxxxxxxxxxxxxxxxxxxxxx Linked list xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");

        System.out.println("Inserting at the front and back...");

        // Inserting at the front
        System.out.println("Enter details for the book to insert at the front:");
        System.out.print("Book title: ");
        String frontTitle = sc.nextLine();
        System.out.print("Book author: ");
        String frontAuthor = sc.nextLine();
        System.out.print("Book ISBN: ");
        String frontISBN = sc.nextLine();
        System.out.print("Book price: RM ");
        double frontPrice = sc.nextDouble();
        System.out.print("Book quantity: ");
        int frontQty = sc.nextInt();
        sc.nextLine(); 
        
        Book bookAtFront = new Book(frontTitle, frontAuthor, frontISBN, frontPrice, frontQty);
        lList.insertAtFront(bookAtFront);
        
        // Inserting at the back
        System.out.println("\nEnter details for the book to insert at the back:");
        System.out.print("Book title: ");
        String backTitle = sc.nextLine();
        System.out.print("Book author: ");
        String backAuthor = sc.nextLine();
        System.out.print("Book ISBN: ");
        String backISBN = sc.nextLine();
        System.out.print("Book price: RM ");
        double backPrice = sc.nextDouble();
        System.out.print("Book quantity: ");
        int backQty = sc.nextInt();
        sc.nextLine(); 
        
        Book bookAtBack = new Book(backTitle, backAuthor, backISBN, backPrice, backQty);
        lList.insertAtBack(bookAtBack);

        // Displaying the new list
        System.out.println("\nNew list after inserting at the front and back:");

        Object objnew = lList.getFirst();
        while (objnew != null) {
            Book book = (Book) objnew;
            System.out.println(book);
            objnew = lList.getNext();
        }

        // Removing a book from the front
        System.out.println("Removing a book from the front...");
        lList.removeFromFront();

        // Removing a book from the back
        System.out.println("Removing a book from the back...");
        lList.removeFromBack();

        // Displaying the new list after removing 
        System.out.println("New list after removing at the front and back:");

        Object objrem = lList.getFirst();
        while (objrem != null) {
            Book book = (Book) objrem;
            System.out.println(book);
            objrem = lList.getNext();
        }
        
        //Total of price of books sold
        double totalPrice = 0.0;
        Object obj = lList.getFirst();
        int totalQuantity = 0;
        while(obj != null){
            Book b1 = (Book)obj;
            totalPrice += b1.calculateTotalPrice();
            totalQuantity += b1.getQty(); // Increment totalQuantity
            obj = lList.getNext();
        }
        System.out.println("Total price of books sold: " + totalPrice);

        // Average of price of books sold
        double avg = 0.0;
        if (totalQuantity != 0) { // Check if totalQuantity is not 0
            avg = totalPrice / totalQuantity;
            System.out.println("Average price of books sold: " + avg);
        } else {
            System.out.println("No books sold, average price is not applicable.");
        }

        //Books sold above average price
        System.out.println("\nBooks sold above average price: ");
        Object obj2 = lList.getFirst();
        int index = 0;
        boolean found3 = false, foundAboveAvg = false;
        while (obj2 != null) {
            Book b2 = (Book)obj2;
            if (b2.calculateTotalPrice() >= avg) {
                System.out.println("Index: " + index + ", Book Title: " + b2.getTitle());
                found3 = true;
            }
            if (found3 && b2.calculateTotalPrice() > avg) {
                foundAboveAvg = true; 
            }
            obj2 = lList.getNext();
            index++;
        }
        if(!found3){
            System.out.println("None.");
        }

        //find highest totalPrice of books sold in one transaction
        Object objMax = lList.getFirst();
        Book bMax = (Book)objMax;
        double totalMax = bMax.calculateTotalPrice();
        Object obj3 = lList.getNext();
        while (obj3 != null) {
            Book b3 = (Book)obj3;

            if(b3.calculateTotalPrice()>totalMax){
                totalMax = b3.calculateTotalPrice();
            }
            obj3 = lList.getNext();    
        }
        System.out.println("\nHighest Total Price of books sold in one transaction: " + totalMax );

        //find Lowest totalPrice of books sold in one transaction
        Object objMin = lList.getFirst();
        Book bMin = (Book)objMin;
        double totalMin = bMin.calculateTotalPrice();
        Object obj4 = lList.getNext();
        while (obj4 != null) {
            Book b4 = (Book)obj4;

            if(b4.calculateTotalPrice()<totalMin){
                totalMin = b4.calculateTotalPrice();
            }
            obj4 = lList.getNext();    
        }
        System.out.println("\nLowest Total Price of books sold in one transaction: " + totalMin);

        //search for book title
        boolean found2 = false;
        Book searchBook = new Book();
        Object obj5 = lList.getFirst();
        System.out.println("\nEnter book title to search: ");
        String search = sc.nextLine();
        while (obj5 != null) {
            Book sbook = (Book)obj5;

            if( sbook.getTitle().equalsIgnoreCase(search)){
                found2 = true;
                searchBook = sbook;
                break;
            }
            obj5 = lList.getNext();
        }
        if(found2)
            System.out.println("\nSearch item found: \n" + searchBook.toString());
        else
            System.out.println("\nSearch item not found.");

        //Bubble sorting by title alphabetically
        if (lList.isEmpty() || lList.head == lList.tail) {
            return; // check if the list is empty or has only one element
        }

        boolean swapped;
        Node end = null;

        do {
            swapped = false;
            Node current = lList.head;

            while (current.next != end) {
                Book currentBook = (Book)current.getData();
                Book nextBook = (Book)current.next.getData();

                // Compare titles
                if (currentBook.getTitle().compareTo(nextBook.getTitle()) > 0) {
                    current.setData(nextBook);
                    current.next.setData(currentBook);
                    swapped = true;
                }

                current = current.next;
            }
            end = current; // Move the end pointer one step back
        } while (swapped);

        System.out.println("New list after bubble sorting by title:");

        Object objBubble = lList.getFirst();
        while (objBubble != null) {
            Book bookBubble = (Book) objBubble;
            System.out.println(bookBubble);
            objBubble = lList.getNext();
        }
    }
}