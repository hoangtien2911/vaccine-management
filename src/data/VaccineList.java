/**
 *
 * @author Hoang-Tien | fb/htmsyunh/
 * Version1 17-01-2022
 * Learn from teacher Nguyen-The-Hoang and references from many sources.
 */
package data;

import java.io.FileWriter;
import java.util.ArrayList;
import ui.Menu;
import util.FileRead;
import util.MyToys;

public class VaccineList implements List{
    ArrayList<Vaccine> list = new ArrayList();

    public VaccineList() {
        this.readFile();
    }
    public boolean isEmpty(){
        return list.isEmpty();
    }
    public int getSize(){
        return list.size();
    }
    
    public void addVaccine() {
        String id, name;
        int n, pos;
        n = MyToys.getPositiveInteger("How many types of vaccines do you want to import: ", "Input Invalid!");
        for (int i = 0; i < n; i++) {
            System.out.println("Vaccine " + (i+1) +"/"+ n);
            do{
                id = MyToys.getString("Enter the vaccine ID: ", "Input Invalid!");
                pos = searchID(id);
                if(pos!=-1)
                    System.out.println("The vaccine already exits!");
            }while(pos != -1);
            name = MyToys.getString("Enter the vaccine name: ", "Input Invalid!"); 
            list.add(new Vaccine(id, name));
        }
        saveFile();
    }
    
    public int searchID(String id) {
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i).getvID().equalsIgnoreCase(id))
                return i;
        }
        return -1;
    }
    
    public String menuVaccine(){
        while (true) {
            Menu menu = new Menu("Choice the vaccine id:");
            for (int i = 0; i < list.size(); i++) {
                menu.addNewOption((i + 1) +". " + list.get(i).toString2());
            }
            menu.printMenu();
            int choice = menu.getChoice();
            for (int i = 0; i < list.size(); i++) {
                if((choice - 1) == i)
                    return list.get(i).getvID();
            }               
        }
    }
    
    public void displayAVaccine(String id) {
        for (Vaccine x : list) {
            if(x.getvID().equalsIgnoreCase(id))
                x.display();
        }
    }
    
    
    @Override
    public void display() {
        if(list.isEmpty()) {
            System.out.println("The Vaccines list is empty!");
            return;
        }
        for (int i = 0; i <= 30; i++) {
            System.out.print("-");
        }
        System.out.println();
        System.out.printf("|%-10s|%-20s|\n", "VACCINE ID", "VACCINE NAME");
        for (Vaccine x : list) {
            x.display();
            System.out.println();
        }
        for (int i = 0; i <= 30; i++) {
            System.out.println("-");
        }
        System.out.println();
    }

    @Override
    public void readFile() {
        list = FileRead.readFileVaccine("vaccine.txt");
    }

    @Override
    public void saveFile() {
        if (list.isEmpty()) {
            System.out.println("Save FAILED. Empty list.");
            return;
        }
        try {
            FileWriter fw = new FileWriter("vaccine.txt");
            for (Vaccine vc : list) {
                fw.write(vc.toString());
            }
            fw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}
