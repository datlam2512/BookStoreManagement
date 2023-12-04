/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookprj;

/**
 *
 *
 */
import core.*;
import java.util.Collections;
import java.util.Comparator;
import tools.*;

public class BookPrj {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        // create MENU
        Menu mnu = new Menu();        
        mnu.add("|Publisher: Add|");
        mnu.add("|Publisher: Search|");
        mnu.add("|Publisher: Remove|");
        mnu.add("|Publisher: Update|");
        mnu.add("|Publisher: Print|");
        mnu.add("|Publisher: Save to file|");
        mnu.add("|Book: Add|");
        mnu.add("|Book: Search|");
        mnu.add("|Book: Remove|");
        mnu.add("|Book: Update|");
        mnu.add("|Book: Print|");
        mnu.add("|Book: Save to file|");
        int nChoice = mnu.size();
        // create list publisher
        PubList pList = new PubList();
        pList.readFromFile();
        PubList sortList = new PubList();
        
        // create book list base pList
        BookList bList = new BookList(pList);
        bList.readFromFile();

        BookList sList = new BookList(pList);
        BookList bsortList = new BookList(pList);
        

        int userChoice;
        // bien ghi nhan co su thay doi
        boolean pChange = false;
        boolean bChange = false;
        // RUN
        do {
            System.out.println("\n++++++++ BOOK STORE ++++++++");
            userChoice = mnu.getUserChoice();
            switch (userChoice) {
                case 1:
                    pList.addPublisher();
                    pChange = true;
                    break;
                case 2:
                    pList.search();
                    break;
                case 3:
                    pList.removePublisher();
                    break;
                case 4:
                    pList.updatePublisher();
                    pChange = true;
                    break;
                case 5:
                    sortList.addAll(pList);
                    sortList.sortByName();
                    sortList.print();
                    sortList.removeAll(sortList);
                    break;
                case 6:
                    pList.writeToFile();
                    pChange = false;
                    break;
                case 7:
                    bList.addBook();
                    bChange = true;
                    break;
                case 8:
                    System.out.println("1. Search by book name \n2. Search by Publisher ID");
                    System.out.print("Your choice: ");
                    int sChoice = Integer.parseInt(MyUtil.sc.nextLine().trim());
                    switch (sChoice) {
                        case 1:
                            System.out.print("Enter book name: ");
                            String bookName = MyUtil.sc.nextLine().trim().toUpperCase();
                            for (Book b : bList) {
                                if (b.getBookName().contains(bookName)) {
                                    sList.add(b);
                                }
                            }
                            if (sList.isEmpty()) System.out.println("Not found.");
                            else {
                                sList.sortByBookName();
                                sList.printBook();
                                sList.removeAll(sList);
                            }
                            break;
                        case 2:
                            String publisherID = MyUtil.readPattern("Enter publisher ID", "P[\\d]{5}");
                            for (Book b : bList) {
                                if (b.getPublisherID().equals(publisherID)) {
                                    sList.add(b);
                                } 
                            }
                            if (sList.isEmpty()) System.out.println("Not found.");
                            else {
                                sList.sortByBookName();
                                sList.printBook();
                                sList.removeAll(sList);
                            }
                            break;
                        }
                    break;
                case 9: 
                    bList.removeBook();
                    bChange = true;
                    break;
                case 10: 
                    bList.updateBook();
                    bChange = true;
                    break;
                case 11: 
                    bsortList.addAll(bList);
                    bsortList.sortQuantity();
                    bsortList.total();
                    bsortList.removeAll(bsortList);
                    break;
                case 12: 
                    bList.writeToFile();
                    bChange = false;
                    break;
            }
        
    }
    while (userChoice > 0 && userChoice <= nChoice);
        String response;
    if (pChange == true || bChange == true) {
        System.out.print("Data changed. Save file? - Y/N: ");
        response = MyUtil.sc.nextLine().trim().toUpperCase();
        if (response.startsWith("Y")) {
            if (pChange) {
                pList.writeToFile();
            }
            if (bChange) {
                bList.writeToFile();
            }
        }
    }
    System.out.println ("Bye!");
    } 
}
