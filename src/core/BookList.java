/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

/**
 *
 * 
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import tools.MyUtil;

public class BookList extends ArrayList<Book> {
    
    List<Book> list = new ArrayList<>();

    //PubList pL = new PubList();

    String fName = "src\\data\\book.dat";

    PubList pL;
    // contructor
    public BookList(PubList pL) {
        super();
        this.pL = pL;
    }
    
    public void readFromFile() {
        File f = new File(fName);
        if (!f.exists()) {
            System.out.println("File is not existed!");
            System.exit(0);
        }
        try {
            FileReader fr = new FileReader(f);
            BufferedReader bf = new BufferedReader(fr);
            String line;
            // Format: B00001, B01, 3.4, 20, P00001, Avaiable
            while ((line = bf.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(line, ",");
                String bookID = stk.nextToken().trim().toUpperCase();
                String bookName = stk.nextToken().trim().toUpperCase();
                double price = Double.parseDouble(stk.nextToken().trim());
                int quantity = Integer.parseInt(stk.nextToken().trim());
                String publisherID = stk.nextToken().trim().toUpperCase();
                Book b = new Book(bookID, bookName, price, quantity, publisherID);
                this.add(b);
            }
            bf.close();
            fr.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void writeToFile() throws Exception {
        if (this.isEmpty()) {
            System.out.println("Empty list!");
        } else {
            PrintWriter pw = new PrintWriter(fName);
            for (Book b : this) {
                pw.println(b);
            }
            pw.close();
            System.out.println("Writing file : DONE.");
        }
    }

    public int checkBook(String bookID) {
        int pos = -1;
        for (Book b : this) {
            if (bookID.equals(b.bookID)) {
                return pos;
            }
        }
        return -1;
    }

    public void addBook() {
        String publisherID, bookID, bookName;
        Publisher p = new Publisher();
        double price;
        int quantity;
        System.out.println("Data of new book: ");

        int pos;
        do {
            bookID = MyUtil.readPattern("Book ID", "B[\\d]{5}");
            //pos = this.indexOf(new Book(bookID));
            pos = searchBookID(bookID);
            //System.out.println(pos);
            if (pos >= 0) System.out.println("ID is duplicate!");
        } while (pos >= 0);
        bookName = MyUtil.readString("Name", 5, 30).toUpperCase();
        price = MyUtil.readDouble("Price", 0);
        quantity = MyUtil.readInt("Quantity", 0);
        do {
            publisherID = MyUtil.readString("Publisher ID", 5, 30);
        } while (pL.indexOf(new Publisher(publisherID)) < 0);
        Book newBook = new Book(bookID, bookName, price, quantity, publisherID);
        this.add(newBook);
        System.out.println("Add book successfully!");
    }
    
    public int searchBookID(String bookID) {
        for (Book b : this) {
            if (bookID.equals(b.bookID)) return this.indexOf(b);
        }
        return -1;
    }

    public void removeBook() {
        String bookID;
        System.out.println("ID of removed book: ");
        bookID = MyUtil.sc.nextLine().trim().toUpperCase();
        //int pos = this.indexOf(new Book(bookID));
        int pos = searchBookID(bookID);
        if (pos < 0) System.out.println("Not found!");
        else {
            this.remove(pos);
            System.out.println("Removed book ID " + bookID);
        }
    }
    
    public void updateBook() {
        String bookID;
        String input;
        System.out.println("ID of updated book:");
        bookID = MyUtil.sc.nextLine().trim().toUpperCase();
        //int pos = this.indexOf(new Book(bookID));
        int pos = searchBookID(bookID);
        if (pos < 0) System.out.println("Not found!");
        else {
            Book b = this.get(pos);
            System.out.println("New book attributes - Enter for skipping.");
            boolean OK;
            int L;
            do {
                System.out.print("New name (5..30 chars): ");
                input = MyUtil.sc.nextLine().trim().toUpperCase();
                L = input.length();
                OK = L == 0 || (L >= 5 && L <= 30);
            } while (!OK);
            if (L > 0) b.setBookName(input);
            //update price
            double newPrice = -1.0;
            do {
                System.out.print("New price (Enter of number >= 0): ");
                input = MyUtil.sc.nextLine().trim();
                if (!input.isEmpty())
                    newPrice = Double.parseDouble(input);
                OK = input.isEmpty() || newPrice >= 0.0;
            } while (!OK);
            if (newPrice >= 0) b.setBookPrice(newPrice);
            //update quantity
            int newQuantity = -1;
            do {
                System.out.print("New quantity (Enter of number >= 0): ");
                input = MyUtil.sc.nextLine().trim();
                if (!input.isEmpty())
                    newQuantity = Integer.parseInt(input);
                OK = input.isEmpty() || newQuantity >= 0;
            } while (!OK);
            if (newQuantity >= 0) {
                b.setQuantity(newQuantity);
                b.setStatus(newQuantity > 0 ? "Available" : "Not availble");
            }
            //update publisherID
            do {
                System.out.print("Enter publisher ID: ");
                OK = true;
                input = MyUtil.sc.nextLine().trim().toUpperCase();
                if (input.isEmpty()) break;
                else OK = pL.searchID(input) >= 0;
                //else OK = pL.indexOf(input) >= 0;
            } while (!OK);
            if (!input.isEmpty()) b.setPublisherID(input);
            System.out.println("Updated.");
        }
        
    }
    
    public void sortByBookName() {
        Collections.sort(this, new Comparator<Book>() {
        @Override
        public int compare(Book o1, Book o2) {
            return o1.getBookName().compareToIgnoreCase(o2.getBookName());
        }
        });
    }
    
    public void sortQuantity() {
        for (int i = 0; i < this.size(); i++) {
            for (int j = i+1; j < this.size(); j++) {
                if (this.get(i).getQuantity() < this.get(j).getQuantity()) {
                    Book tmp = this.get(i);
                    this.set(i, this.get(j));
                    this.set(j, tmp);
                }
                else if (this.get(i).getQuantity() == this.get(j).getQuantity()) {
                    if (this.get(i).getBookPrice() > this.get(j).getBookPrice()) {
                        Book tmp = this.get(i);
                        this.set(i, this.get(j));
                        this.set(j, tmp);
                    }
                }
            }
            
        }
    }
    
    public void printBook() {
        //sortByBookName();
        System.out.printf("%-7s|%-30s|%-5s|%-9s|%-14s|%-14s\n",
                "Book ID", "Book Name", "Price", "Quantity", "Publisher ID", "Status");
        for (Book b : this) {
            System.out.println(b.printAll());
        }
    }
    
    public void total() {
        System.out.printf("%-7s|%-30s|%-5s|%-9s|%-9s|%-14s|%-14s\n",
                "Book ID", "Book Name", "Price", "Quantity", "Subtotal", "Publisher ID", "Status");
        for (Book b : this) {
            System.out.println(b.stringTotal());
        }
    }
}
