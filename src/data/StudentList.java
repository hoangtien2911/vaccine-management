/**
 *
 * @author Hoang-Tien | fb/htmsyunh/
 * Version1 17-01-2022
 * Learn from teacher Nguyen-The-Hoang and references from many sources.
 */
package data;
import util.FileRead;
import java.io.FileWriter;
import java.util.ArrayList;
import ui.Menu;
import util.MyToys;

public class StudentList implements List{
    
    ArrayList<Student> list = new ArrayList();

    public StudentList() {
        this.readFile();
    }
    
    public boolean isEmpty() {
        return list.isEmpty();
    }
    
    public int getSize() {
        return list.size();
    }
    
    public void addStudent() {
        String id, name;
        int n, pos;
        n = MyToys.getPositiveInteger("Number of students you want to import: ", "Input Invalid!");
        for (int i = 0; i < n; i++) {
            System.out.println("Student " + (i + 1) + "/" + n );
            do {
                id = MyToys.getString("Input student ID: ", "Input Invalid!");
                pos = searchID(id);
                if(pos != -1) 
                    System.out.println("The student already exits!");
            } while (pos != -1);
            name = MyToys.getString("Input student name: ", "Input Invalid!");
            list.add(new Student(id, name));
        }
        saveFile();
    }
    
    public int searchID(String id) {
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getsID().equalsIgnoreCase(id))
                return i;
        }
        return -1;
    }
    
    public Student search(String id) {
        for (Student x : list) {
            if(x.getsID().equalsIgnoreCase(id))
                return x;
        }
        return null;
    }
    
    public String menuStudent() {
        while (true) {
            Menu menu = new Menu("Choice the student id to be injected:");
            for (int i = 0; i < list.size(); i++) {
                menu.addNewOption((i + 1) +". " + list.get(i).toString2());
            }
            menu.printMenu();
            int choice = menu.getChoice();
            for (int i = 0; i < list.size(); i++) {
                if((choice - 1) == i)
                    return list.get(i).getsID();
            }               
        }
    }
    
    public void displayAStudent(String id) {
        for (Student x : list) {
            if(x.getsID().equalsIgnoreCase(id))
                x.display();
        }
    }

    @Override
    public void saveFile() {
        if(list.isEmpty()) {
            System.out.println("Save FAILED. Empty list.");
            return;
        }
        try {
            FileWriter fw = new FileWriter("student.txt");
            for (Student st : list) {
                fw.write(st.toString());
            }
            fw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public void readFile() {
        list = FileRead.readFileStudent("student.txt");
    }
    
    @Override
    public void display() {
        if(list.isEmpty()) {
            System.out.println("The students list is empty!");
            return;
        }
        for (int i = 0; i <= 30; i++) {
            System.out.print("-");
        }
        System.out.println();
        System.out.printf("|%-10s|%-20s|\n", "STUDENT ID", "STUDENT NAME");
        for (Student x : list) {
            x.display();
            System.out.println();
        }
        for (int i = 0; i <= 30; i++) {
            System.out.println("-");
        }
        System.out.println();
    }
}
