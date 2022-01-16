/**
 *
 * @author Hoang-Tien | fb/htmsyunh/
 * Version1 17-01-2022
 * Learn from teacher Nguyen-The-Hoang and references from many sources.
 */
package com.fu.vaccinemanagement;

import data.InjectionList;
import ui.Menu;

public class VaccineManagement {
    public static void main(String[] args) {
        InjectionList list = new InjectionList();
        int choice;
        Menu menu = new Menu("Welcome to Vaccine Management - ©2022 by Phạm Hoàng Tiến (StudentID: SE160239)");
        menu.addNewOption("1. Show information all students have been injected.");
        menu.addNewOption("2. Add student's vaccine injection information.");
        menu.addNewOption("3. Updating information of students' vaccine injection.");
        menu.addNewOption("4. Delete student vaccine injection information.");
        menu.addNewOption("5. Search for injection information by studentID.");
        menu.addNewOption("6. Quit");
        list.addData();
        do {            
            menu.printMenu();
            choice = menu.getChoice();
            switch(choice){
                case 1:
                    list.display();
                    list.saveFile();
                    break;
                case 2:
                    list.addInjection();
                    list.saveFile();
                    break;
                case 3:
                    list.updateInjectionByID();
                    list.saveFile();
                    break;
                case 4:
                    list.removeInjectionByID();
                    list.saveFile();
                    break;
                case 5:
                    list.searchInjectionByStudentID();
                    break;
                case 6:
                    list.saveFile(); 
                    System.out.println("You have exited the program!");
                    break;
            }
        } while (choice <= 5);   
    }
}
