package Assignment1;

public class Book extends Object{
            private String title;
            private String author;
            private String ISBN;
            private double price;
            private int qty;

            public Book(){}

            public Book(String title, String author, String ISBN, double price, int qty) {
                this.title = title;
                this.author = author;
                this.ISBN = ISBN;
                this.price = price;
                this.qty = qty;
            }
        
            public String getTitle() {
                return title;
            }
            
            public String getAuthor() {
                return author;
            }

            public String getISBN() {
                return ISBN;
            }

            public double getPrice() {
                return price;
            }

            public double getQty() {
                return qty;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        
            public void setAuthor(String author) {
                this.author = author;
            }
        
            public void setISBN(String ISBN) {
                this.ISBN = ISBN;
            }
        
            public void setPrice(double price) {
                this.price = price;
            }

            public void setQty(int qty) {
                this.qty = qty;
            }

            public double calculateTotalPrice() {
                double totalPrice = 0.0;
                totalPrice = price*qty;
                return totalPrice;
            }

            public String toString() {
                return "\nTitle: " + title + "\nAuthor: " + author + "\nISBN: " + ISBN + "\nPrice: RM " + price + "\nQuantity: " + qty + "\nTotal Price: " + calculateTotalPrice();
            }
}    
