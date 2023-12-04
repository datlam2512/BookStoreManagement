/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;
import tools.MyUtil;

/**
 *
 * 
 */
public class PubList extends ArrayList<Publisher> {
    
    String fName = "src\\data\\publisher.dat";
    //String fName = "data\\publisher.dat";
    public void readFromFile() {
        File f = new File(fName);
        if (!f.exists()) {
            System.out.println("File is not existed!");
            System.exit(0);
        }
        try {
            FileReader   fr = new FileReader(f);
            BufferedReader bf = new BufferedReader(fr);
            String line;
            while ((line = bf.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(line, ",");
                String ID = stk.nextToken().trim().toUpperCase();
                String name = stk.nextToken().trim().toUpperCase();
                String phone = stk.nextToken().trim().toUpperCase();
                Publisher p = new Publisher(ID, name, phone);
                this.add(p);
            }
            bf.close();
            fr.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    
    
    }
    
    public void writeToFile() throws Exception{
        if (this.isEmpty())
            System.out.println("Empty list!");
        else {
            PrintWriter pw = new PrintWriter(fName);
            for (Publisher p : this) pw.println(p);
            pw.close();
            System.out.println("Writing file publisher.dat: DONE.");
            
        }
    }
    //them 1 publisher voi data tu ban phim
    public void addPublisher() {
        String ID, name, phone;
        System.out.println("Data of new publisher: ");
        int pos;
        do {
            ID = MyUtil.readPattern("ID", "P[\\d]{5}");
            pos = this.indexOf(new Publisher(ID));
            if (pos>=0) System.out.println("ID is duplicate!");
        } while (pos >= 0);
        name = MyUtil.readString("Name", 5, 30).toUpperCase();
        phone = MyUtil.readPattern("Phone", "[\\d]{10,12}");
        Publisher p = new Publisher(ID, name, phone);
        this.add(p);
    }
    //tim 1 pblisher dua tren ID duoc nhap
    public void search() {
        String ID;
        System.out.println("ID of searched publisher: ");
        ID = MyUtil.sc.nextLine().trim().toUpperCase();
        int pos = this.indexOf(new Publisher(ID));
        if (pos<0) System.out.println("Not found!");
        else System.out.println("Found: " + this.get(pos));
    }
    public int searchID(String publisherID) {
        int pos = -1;
        for (Publisher p : this) {
            if (p.publisherID.equals(publisherID)) pos = indexOf(p);
        }
        //int pos = this.indexOf(new Publisher(publisherID));
        if (pos >= 0) return pos;
        return -1;
    }
    public void removePublisher() {
        String ID;
        System.out.println("ID of searched publisher: ");
        ID = MyUtil.sc.nextLine().trim().toUpperCase();
        int pos = this.indexOf(new Publisher(ID));
        if (pos<0) System.out.println("Publisher’s Id does not exist");
        else {
            this.remove(pos);
            System.out.println("Removed.");
        }
    }
    
    public void updatePublisher() {
        String ID;
        String name;
        String phone;
        System.out.println("ID of updated publisher: ");
        ID = MyUtil.sc.nextLine().trim().toUpperCase();
        int pos = this.indexOf(new Publisher(ID));
        if (pos<0) System.out.println("Not found!");
        else {
            Publisher p = this.get(pos);
            name = MyUtil.readString("Name", 5, 30);
            phone = MyUtil.readPattern("New phone", "[\\d]{10,12}");
            p.setPublisherName(name);
            p.setPhone(phone);
            System.out.println("Updated.");
        }
    }
    
    public void sortByName() {
        Collections.sort(this, new Comparator<Publisher>() {
        @Override
        public int compare(Publisher o1, Publisher o2) {
            return o1.getPublisherName().compareToIgnoreCase(o2.getPublisherName());
        }
        });
    }
    
    public void print() {
        sortByName();
        System.out.printf("|%-12s|%-30s|%-12s\n",
                "Publisher ID", "Publisher Name", "Phone");
        for (Publisher p : this) {
            System.out.println(p.printAll());
        }
    }
    
}
