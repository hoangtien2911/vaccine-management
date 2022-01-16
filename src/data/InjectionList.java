/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import util.FileRead;
import util.MyToys;

public class InjectionList implements List{
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";
    StudentList studentList = new StudentList();
    VaccineList vaccineList = new VaccineList();
    ArrayList<Injection> injectionList = new ArrayList();

    public InjectionList() {
        this.readFile();
    }
    
    //ADD
    public void addInjection() {
        String injectionID, fPlace, fDate, sPlace = null, sDate = null, vID, sID;
        int pos1, pos2, check1 = 0, check2;
        do {
            //injectionID
            do {
                injectionID = MyToys.getString("Enter the injection id: ", "Input invalid!");
                pos1 = searchIDInjection(injectionID);
                if(pos1 != -1)
                    System.out.println(ANSI_RED+ "This injection id already exist!" + ANSI_RESET);
                
            } while (pos1 != -1);
            
            //sID
            do {
                sID = studentList.menuStudent();
                pos2 = searchIDStudent(sID);
                if(pos2 != -1)
                    System.out.println(ANSI_RED + "This student already exist!" + ANSI_RESET);
            } while (pos2 != -1);
            
            //fPlace
            fPlace = MyToys.getPlace("Enter the place of the first injection: ", "Input invalid!");
            
            //fDate
            fDate = MyToys.getDate("Enter the date of the first injection(dd/MM/yyyy): ", "Input invalid!");
            if(checkDay(fDate) == true) {
                check2 = MyToys.getYesNo("This student was eligible for a 2nd injection (4 weeks to 12 weeks). Do you want to enter the information a second time? ", "Please enter Yes/No or Y/N!");
                if(check2 == 1) {
                    sPlace = MyToys.getPlace("Enter the place of the second injection: ", "Input invalid!");
                    sDate = MyToys.getDate2(fDate);
                }
            }
            
            //vID
            vID = vaccineList.menuVaccine();
            
            //injectionList
            injectionList.add(new Injection(injectionID, sID, fPlace, fDate, sPlace, sDate, vID));
            System.out.println(ANSI_GREEN + "Add successfully!" + ANSI_RESET);
            check1 = MyToys.getYesNo("Do you want to continue adding another injection? (Yes/No) ", "Please enter Yes/No or Y/N!");
            
        } while (check1 == 1);
    }
    
    //SEARCH Injection
    public void searchInjectionByStudentID() {
        String id = studentList.menuStudent();
        for (Injection injection : injectionList) {
            if(injection.getStudentID().equalsIgnoreCase(id))
                infoAInjection(injection.getInjectionID());
            return;
        }
        System.out.println(ANSI_RED + "Students have not been vaccinated. Not found in the list." + ANSI_RESET);
    }
    
    //UPDATE
    public void updateInjectionByID() {
        String id;
        int pos;
        if(injectionList.isEmpty()) {
            System.out.println(ANSI_RED + "The injection list is empty. Nothing to update!" + ANSI_RESET);
            return;
        }
        id = MyToys.getString("Enter the injection id you want to update: ", "Input invalid!");
        pos = searchIDInjection(id);
        if(pos == -1){
            System.out.println(ANSI_RED + "Injection does not exist!" + ANSI_RESET);
            return;
        }
        if(searchInjection(id).getSecondDate().equals("null") && searchInjection(id).getSecondPlace().equals("null")) {
            System.out.println(ANSI_BLUE + "The injection you want to update: " + ANSI_RESET);
            infoAInjection(id);
            searchInjection(id).setSecondPlace(MyToys.getPlace("Enter the place of the second injection: ", "Input invalid!"));
            searchInjection(id).setSecondDate(MyToys.getDate2(searchInjection(id).getFirstDate()));
            System.out.println(ANSI_GREEN + "Update successfully!" + ANSI_RESET);  
        }
        else{
            System.out.println(ANSI_RED + "Student has completed 2 injections!" + ANSI_RESET);
        }
    }
    
    //REMOVE
    public void removeInjectionByID() {
        String id;
        int check;
        if(injectionList.isEmpty()){
            System.out.println(ANSI_RED + "The injection list is empty. Nothing to remove!" +ANSI_RESET);
            return;
        }
        id = MyToys.getString("Enter the injection id you want to remove: ", "Input invalid!");
        if(searchIDInjection(id) == -1)
            System.out.println(ANSI_RED + "Injection does not exist!" + ANSI_RESET);
        else{
            System.out.println(ANSI_BLUE + "The injection you want to remove: " + ANSI_RESET);
            infoAInjection(id);
            check = MyToys.getYesNo("Are you sure you want to delete it?(Yes/No)", "Please enter Yes/No or Y/N!");
            if(check == 1){
                injectionList.remove(searchInjection(id));
                System.out.println(ANSI_GREEN + "Remove successfully!" +ANSI_RESET);
            }
            else
                System.out.println(ANSI_RED + "Remove failed!" + ANSI_RESET);
        }
    }
    
    //DISPLAY
    @Override
    public void display() {
        if(injectionList.isEmpty()){
            System.out.println(ANSI_RED + "The injection list is empty!" +ANSI_RESET);
        }
        
        int check = 0;
        for (Injection injection : injectionList) {
            if(studentList.search(injection.getStudentID())!=null){
                check++;
            }
        }
        if(check != 0){
            for (int i = 0; i <= 130; i++) {
                System.out.print("-");
            }
            System.out.println();
            String header = String.format(ANSI_BLUE + "|%-10s|%-10s|%-20s|%-15s|%-10s|%-15s|%-10s|%-10s|%-19s|\n", "ID","STUDENT_ID", "STUDENT_NAME", "1ST_PLACE", "1ST_DATE", "2ND_PLACE", "2ND_DATE", "VACCINE_ID", "VACCINE_NAME" + ANSI_RESET);
            System.out.println(header);
            for (int i = 0; i <= 130; i++) {
                System.out.print("-");
            }
            System.out.println();
        }
        for (Injection injection : injectionList) {
            if(studentList.search(injection.getStudentID()) != null) {
                injection.display1();
                studentList.displayAStudent(injection.getStudentID());
                injection.display2();
                vaccineList.displayAVaccine(injection.getVaccineID());
                System.out.println();
            }
        }
        if(check!=0){
            for (int i = 0; i <= 130; i++) {
                System.out.print("-");
            }
            System.out.println();
        }   
    }
    
    //ADD FILE
    public void addData(){
        if(studentList.isEmpty())
            studentList.addStudent();
        if(vaccineList.isEmpty())
            vaccineList.addVaccine();
    }
    
    //------------------------------------------------------------------------------
    //Support
    
        //infor a ID injection for SEARCH
    public void infoAInjection(String id){
        searchInjection(id).display1();//display: InjectionID
        studentList.displayAStudent(searchInjection(id).getStudentID()); //display student
        searchInjection(id).display2();//display: firstPlace, firstDate, secondPlace, secondDate
        vaccineList.displayAVaccine(searchInjection(id).getVaccineID());//display vaccine
        System.out.println();
    }
    
        //checkday for ADD
    public boolean checkDay(String date1) {
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        int n = MyToys.daysBetween(date1, sdf.format(date));
        if (n < 27 || n > 85)
            return false;
        else
            return true;
    }

        //search
    public int searchIDStudent(String id) {
        for (int i = 0; i < injectionList.size(); i++) {
            if(injectionList.get(i).getInjectionID().equalsIgnoreCase(id))
                return i;
        }
        return -1;
    }
    
    public int searchIDInjection(String id) {
        for (int i = 0; i < injectionList.size(); i++) {
            if(injectionList.get(i).getInjectionID().equalsIgnoreCase(id))
                return i;
        }
        return -1;
    }
    
    public Injection searchInjection(String id) {
        for (Injection x : injectionList) {
            if(x.getInjectionID().equalsIgnoreCase(id))
                return x;
        }
        return null;
    }
    
    
    //------------------------------
    
    

    @Override
    public void readFile() {
        injectionList = FileRead.readFileInjection("injection.txt");
    }

    @Override
    public void saveFile() {
        try {
            FileWriter fw = new FileWriter("injection.txt");
//            fw.write(String.format("%-10s|%-10s|%-15s|%-10s|%-15s|%-10s|%-10s|\n", "ID","STUDENT_ID","1ST_PLACE", "1ST_DATE", "2ND_PLACE", "2ND_DATE", "VACCINE_ID"));
            for (Injection ij : injectionList) {
                fw.write(ij.toString());
            }
            fw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}

