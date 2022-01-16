/**
 *
 * @author Hoang-Tien | fb/htmsyunh/
 * Version1 17-01-2022
 * Learn from teacher Nguyen-The-Hoang and references from many sources.
 */
package data;

public class Vaccine {
    private String vID;
    private String vName;

    public Vaccine(String vID, String vName) {
        this.vID = vID;
        this.vName = vName;
    }

    public String getvID() {
        return vID;
    }

    public String getvName() {
        return vName;
    }

    public void setvName(String vName) {
        this.vName = vName;
    }
    
    public void display() {
        System.out.printf("%-10s|%-15s|", vID, vName);
    }

    @Override
    public String toString() {
        return vID + ";" + vName + "\n";
    }
    
    public String toString2() {
        return String.format("%-10s|%-15s|", vID, vName);
    }
}
