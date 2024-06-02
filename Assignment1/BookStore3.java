package Assignment1;
import javax.swing.JOptionPane;
import java.util.Scanner;

public class BookStore3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size;

        size = Integer.parseInt(JOptionPane.showInputDialog(null, "How many books to key in:"));

        Queue bookQ = new Queue();

        // Input books
        JOptionPane.showMessageDialog(null, "BookStore");
        for (int i = 0; i < size; i++) {
            String title = JOptionPane.showInputDialog((i+1)+ ". Book title:");
            String author = JOptionPane.showInputDialog((i+1)+ ". Book author:");
            String ISBN = JOptionPane.showInputDialog((i+1)+ ". Book ISBN:");
            String genre = JOptionPane.showInputDialog((i+1)+ ". Book Genre:");
            double price = Double.parseDouble(JOptionPane.showInputDialog((i+1)+ ". Book price: \nRM"));
            int qty = Integer.parseInt(JOptionPane.showInputDialog((i+1)+ ". Book quantity:"));

            Book booklist = new Book(title, author, ISBN, genre, price, qty);
            bookQ.enqueue(booklist);
            JOptionPane.showMessageDialog(null, "Book " + (i + 1) + " added: " + booklist);
        }

        boolean exit = false;

        while (!exit) {
            // Switch case for different calculations
            String input = JOptionPane.showInputDialog("Choose calculation:\n1. Total and Average\n2. Highest and Lowest priced book\n3. English and Malaysian books\n4. Fiction and Non-fiction books\n5. Expression conversion using stack\n6. Exit");
            int choice = Integer.parseInt(input);

            switch (choice) {
                case 1:
                    calculateTotalAndAverage(bookQ);
                    break;
                case 2:
                    calculateHighestAndLowest(bookQ);
                    break;
                case 3:
                    displayEnglishAndMalaysianBooks(bookQ);
                    break;
                case 4:
                    displayFictionAndNonFictionBooks(bookQ);
                    break;
                case 5:
                    // Expression conversion using stack
                    String infixExpression = JOptionPane.showInputDialog("Enter an infix expression:");
                    String postfixExpression = infixToPostfix(infixExpression);
                    JOptionPane.showMessageDialog(null, "Postfix expression: " + postfixExpression);
                    break;
                case 6:
                    JOptionPane.showMessageDialog(null, "Exiting program.");
                    exit = true;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice.");
            }
        }
    }

    // Method to calculate total and average price of books sold
    public static void calculateTotalAndAverage(Queue bookQ) {
        double totalSoldPrice = 0.0;
        int totalSoldQuantity = 0;
        Queue qTemp = new Queue();

        while (!bookQ.isEmpty()) {
            Book b1 = (Book) bookQ.dequeue();
            totalSoldPrice += b1.calculateTotalPrice();
            totalSoldQuantity += b1.getQty();
            qTemp.enqueue(b1);
        }

        // Restore the original queue
        while (!qTemp.isEmpty()) {
            bookQ.enqueue(qTemp.dequeue());
        }

        StringBuilder result = new StringBuilder();
        result.append("Total price of books sold: RM ").append(totalSoldPrice).append("\n");

        // Calculate average price
        if (totalSoldQuantity > 0) {
            double avgSoldPrice = totalSoldPrice / totalSoldQuantity;
            result.append("Average price of books sold: RM ").append(avgSoldPrice).append("\n");
        } else {
            result.append("No books sold, average price is not applicable.\n");
        }

        JOptionPane.showMessageDialog(null, result.toString());
    }

    // Method to calculate highest and lowest priced book sold in one transaction
    public static void calculateHighestAndLowest(Queue bookQ) {
        Book highestPricedBook = null;
        Book lowestPricedBook = null;
        Queue qTemp = new Queue();

        while (!bookQ.isEmpty()) {
            Book b1 = (Book) bookQ.dequeue();
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
        StringBuilder result = new StringBuilder();
        result.append("Highest priced book sold in one transaction: RM ").append(highestPricedBook.calculateTotalPrice()).append("\n").append(highestPricedBook).append("\n\n");
        result.append("Lowest priced book sold in one transaction: RM ").append(lowestPricedBook.calculateTotalPrice()).append("\n").append(lowestPricedBook).append("\n");
        JOptionPane.showMessageDialog(null, result.toString());

    }

    // Method to display English and Malaysian books
    public static void displayEnglishAndMalaysianBooks(Queue bookQ) {
        Queue englishCountryBookQ = new Queue();
        Stack malayPublishedStack = new Stack();
        Queue qTemp = new Queue();

        while (!bookQ.isEmpty()) {
            Book b2 = (Book) bookQ.dequeue();
            String countryAndLanguage = b2.getISBN().split("-")[1];
            if (countryAndLanguage.equals("0") || countryAndLanguage.equals("1")) {
                englishCountryBookQ.enqueue(b2);
            } else if (countryAndLanguage.equals("629")) {
                malayPublishedStack.push(b2);
            }
            qTemp.enqueue(b2);
        }

        // Restore the original queue
        while (!qTemp.isEmpty()) {
            bookQ.enqueue(qTemp.dequeue());
        }

        StringBuilder result = new StringBuilder();
        result.append("Books published in language area English:\n");
        while (!englishCountryBookQ.isEmpty()) {
            Book b3 = (Book) englishCountryBookQ.dequeue();
            result.append(b3.toString()).append("\n");
            qTemp.enqueue(b3);
        }

        // Restore the original queue
        while (!qTemp.isEmpty()) {
            englishCountryBookQ.enqueue(qTemp.dequeue());
        }

        result.append("\nBooks published in language area and Country in Malaysia:\n");
        Stack stTemp = new Stack();
        while (!malayPublishedStack.isEmpty()) {
            Book b4 = (Book) malayPublishedStack.pop();
            result.append(b4.toString()).append("\n");
            stTemp.push(b4);
        }

        // Restore the original stack
        while (!stTemp.isEmpty()) {
            malayPublishedStack.push(stTemp.pop());
        }

        JOptionPane.showMessageDialog(null, result.toString());
    }

    // Method to display Fiction and Non-fiction books
    public static void displayFictionAndNonFictionBooks(Queue bookQ) {
        Stack bookSt = new Stack();
        Queue qTemp = new Queue();

        while (!bookQ.isEmpty()) {
            bookSt.push(bookQ.dequeue());
        }

        Queue nonFic = new Queue();
        Stack fic = new Stack();

        while (!bookSt.isEmpty()) {
            Book b5 = (Book) bookSt.pop();
            if (b5.getGenre().equalsIgnoreCase("fiction")) {
                fic.push(b5);
            } else {
                nonFic.enqueue(b5);
            }
            qTemp.enqueue(b5);
        }

        // Restore the original queue
        while (!qTemp.isEmpty()) {
            bookQ.enqueue(qTemp.dequeue());
        }

        StringBuilder result = new StringBuilder();
        result.append("Fiction books:\n");
        while (!fic.isEmpty()) {
            result.append(fic.pop().toString()).append("\n");
        }

        result.append("\nNon-Fiction books:\n");
        while (!nonFic.isEmpty()) {
            result.append(nonFic.dequeue().toString()).append("\n");
        }

        JOptionPane.showMessageDialog(null, result.toString());
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
