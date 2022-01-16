/**
 *
 * @author Hoang-Tien | fb/htmsyunh/
 * Version1 17-01-2022
 * Learn from teacher Nguyen-The-Hoang and references from many sources.
 */
package data;

public class Student {
    private String sID;
    private String sName;

    public Student(String sID, String sName) {
        this.sID = sID;
        this.sName = sName;
    }

    public String getsID() {
        return sID;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }
    
    public void display() {
            System.out.printf("%-10s|%-20s", sID, sName);
    }

    @Override
    public String toString() {
        return sID + ";" + sName + "\n";
    }
    
    public String toString2() {
        return String.format("%-10s|%-20s", sID, sName);
    }
    
    
    
}
