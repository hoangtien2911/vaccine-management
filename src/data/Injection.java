/**
 *
 * @author Hoang-Tien | fb/htmsyunh/
 * Version1 17-01-2022
 * Learn from teacher Nguyen-The-Hoang and references from many sources.
 */
package data;

public class Injection {
    private String injectionID;
    private String studentID;
    private String firstPlace, firstDate;
    private String secondPlace, secondDate;
    private String vaccineID;

    public Injection(String injectionID, String studentID, String firstPlace, String firstDate, String secondPlace, String secondDate, String vaccineID) {
        this.injectionID = injectionID;
        this.studentID = studentID;
        this.firstPlace = firstPlace;
        this.firstDate = firstDate;
        this.secondPlace = secondPlace;
        this.secondDate = secondDate;
        this.vaccineID = vaccineID;
    }

    public String getInjectionID() {
        return injectionID;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getFirstPlace() {
        return firstPlace;
    }

    public String getFirstDate() {
        return firstDate;
    }

    public String getSecondPlace() {
        return secondPlace;
    }

    public String getSecondDate() {
        return secondDate;
    }

    public String getVaccineID() {
        return vaccineID;
    }

    public void setFirstPlace(String firstPlace) {
        this.firstPlace = firstPlace;
    }

    public void setFirstDate(String firstDate) {
        this.firstDate = firstDate;
    }

    public void setSecondPlace(String secondPlace) {
        this.secondPlace = secondPlace;
    }

    public void setSecondDate(String secondDate) {
        this.secondDate = secondDate;
    }

    

    @Override
    public String toString() {
        return String.format("%-10s|%-10s|%-15s|%-10s|%-15s|%-10s|%-10s|\n",
                                injectionID, studentID, firstPlace, firstDate, secondPlace, secondDate, vaccineID);
    }
    
    public void display1(){
        System.out.printf("|%-10s|", injectionID);
    }
    public void display2(){
        System.out.printf("|%-15s|%-10s|%-15s|%-10s|", firstPlace, firstDate, secondPlace, secondDate);
    }
    
}
