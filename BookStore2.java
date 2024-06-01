
import java.util.*;
import Assignment1.Book;

public class BookStore2 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int size;

        System.out.println("How many books to key in: ");
        size = sc.nextInt();
        sc.nextLine();  

        Queue bookQ = new Queue();
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
            System.out.print("Book Genre: ");
            String genre = sc.nextLine();
            System.out.print("Book price: RM ");
            double price = sc.nextDouble();
            System.out.print("Book quantity:");
            int qty = sc.nextInt();
            sc.nextLine();

            Book booklist = new Book(title, author, ISBN, genre, price, qty);
            bookQ.enqueue(booklist);
        }

        System.out.println("\nxxxxxxxxxxxxxxxxxxxxxxxxxxx Queue xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");

        //Total, average, min and max price of books sold
        double totalSoldPrice = 0.0;
        int totalSoldQuantity = 0;
        Book highestPricedBook = null;
        Book lowestPricedBook = null;
        Queue qTemp = new Queue();
        while(!bookQ.isEmpty()){
            Book b1 = (Book)bookQ.dequeue();
            totalSoldPrice += b1.calculateTotalPrice();
            totalSoldQuantity += b1.getQty();
            if (highestPricedBook == null || b1.calculateTotalPrice() > highestPricedBook.calculateTotalPrice()) {
                highestPricedBook = b1;
            }
            if (lowestPricedBook == null || b1.calculateTotalPrice() < lowestPricedBook.calculateTotalPrice()) {
                lowestPricedBook = b1;
            }
            qTemp.enqueue(b1);
        }
        // Restore the original queue
        while (!qTemp.isEmpty()) {
            bookQ.enqueue(qTemp.dequeue());
        }
        System.out.println("\nTotal price of books sold: RM " + totalSoldPrice);

        // Calculate average price 
        if (totalSoldQuantity > 0) {
            double avgSoldPrice = totalSoldPrice / totalSoldQuantity;;
            System.out.println("\nAverage price of books sold: RM " + avgSoldPrice);
        } else {
            System.out.println("\nNo books sold, average price is not applicable.");
        }

        // Display the book with the highest total price in one transaction
        System.out.println("\nBook with the highest total price in one transaction:" + highestPricedBook.toString());
        // Display the book with the lowest total price in one transaction
        System.out.println("\nBook with the lowest total price in one transaction:" + lowestPricedBook.toString());

        // Store the book published in language area English (0 & 1) in englishCountryBookQ book published in language area and Country in Malaysia (629) in malayPublishedStack
        Queue englishCountryBookQ = new Queue();
        Stack malayPublishedStack = new Stack();
        while(!bookQ.isEmpty()){
            Book b2 = (Book)bookQ.dequeue();
            String countryAndLanguage = b2.getISBN().split("-")[1];
            if(countryAndLanguage.equals("0") || countryAndLanguage.equals("1")){
                englishCountryBookQ.enqueue(b2);
            }else if(countryAndLanguage.equals("629")){
                malayPublishedStack.push(b2);
            }
            qTemp.enqueue(b2);
        }
        // Restore the original queue
        while (!qTemp.isEmpty()) {
            bookQ.enqueue(qTemp.dequeue());
        }
        Queue qTemp2 = new Queue();
        // Display the book published in language area English (0 & 1) in englishCountryBookQ
        System.out.println("\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx QUEUE TO QUEUE xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        System.out.println("\nBook published in language area English:");
        while(!englishCountryBookQ.isEmpty()){
            Book b3 = (Book)englishCountryBookQ.dequeue();
            System.out.print(b3.toString());
            qTemp2.enqueue(b3);
        }
        Stack stTemp = new Stack();
        System.out.println("\n");
        System.out.println("\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx QUEUE TO STACK xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        // Display the book published book published in language area and Country in Malaysia
        System.out.println("\nBook published in language area and Country in Malaysia:");
        while(!malayPublishedStack.isEmpty()){
            Book b4 = (Book)malayPublishedStack.pop();
            System.out.print(b4.toString());
            stTemp.push(b4);
        }
        // Restore the original queue
        while (!stTemp.isEmpty()) {
            malayPublishedStack.push(stTemp.pop());
        }

        Stack bookSt = new Stack();
        // Move Queue to Stack
        while(!bookQ.isEmpty()){
            bookSt.push(bookQ.dequeue());
        }

        Queue nonFic = new Queue();
        Stack fic = new Stack();
        while(!bookSt.isEmpty()){
            Book b5 = (Book)bookSt.pop();
            if(b5.getGenre().equalsIgnoreCase("fiction")){
                fic.push(b5);
            } else {
                nonFic.enqueue(b5);
            }
        }
        System.out.println("\n");
        System.out.println("\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx STACK TO STACK xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        // Display the fiction books
        System.out.println("\nFiction books:");
        while(!fic.isEmpty()){
            System.out.println(fic.pop());
        }

        System.out.println("\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx STACK TO QUEUE xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        // Display the non-fiction books
        System.out.println("\nNon-Fiction books:");
        while(!nonFic.isEmpty()){
            System.out.println(nonFic.dequeue());
        }

        // Expression conversion using stack
        System.out.println("\nxxxxxxxxxxxxxxxxxxxxxxxxxxxx Expression conversion using stack xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        System.out.print("Enter an infix expression: ");
        String infixExpression = sc.nextLine();
        String postfixExpression = infixToPostfix(infixExpression);
        System.out.println("Postfix expression: " + postfixExpression);

    }
    // Method to convert infix expression to postfix
    public static String infixToPostfix(String exp) {
        StringBuilder result = new StringBuilder();
        Stack stackC = new Stack(); // Using raw stack

        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            if (Character.isLetterOrDigit(c)) {
                result.append(c);
            } else if (c == '(') {
                stackC.push(c);
            } else if (c == ')') {
                while (!stackC.isEmpty() && (char) stackC.peek() != '(') {
                    result.append((char) stackC.pop()); // Cast to char
                }
                if (!stackC.isEmpty() && (char) stackC.peek() != '(')
                    return "Invalid Expression";
                else
                    stackC.pop();
            } else {
                while (!stackC.isEmpty() && precedence(c) <= precedence((char) stackC.peek())) {
                    if ((char) stackC.peek() == '(')
                        return "Invalid Expression";
                    result.append((char) stackC.pop()); // Cast to char
                }
                stackC.push(c);
            }
        }
        // pop all the operators from the stack
        while (!stackC.isEmpty()) {
            if ((char) stackC.peek() == '(')
                return "Invalid Expression";
            result.append((char) stackC.pop()); // Cast to char
        }
        return result.toString();
    }

     // Method to return precedence of operators
     public static int precedence(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }
}
