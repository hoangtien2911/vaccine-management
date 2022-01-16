/**
 *
 * @author Hoang-Tien | fb/htmsyunh/
 * Version1 17-01-2022
 * Learn from teacher Nguyen-The-Hoang and references from many sources.
 */
package ui;

import java.util.ArrayList;
import util.MyToys;

public class Menu {
    private String menuTitle;
    private ArrayList<String> optionList = new ArrayList();

    public Menu(String menuTitle) {
        this.menuTitle = menuTitle;
    }
   
    public void addNewOption(String newOption) {
        if(optionList.equals(newOption))
            return;
        else
            optionList.add(newOption);
    }
    
    public void printMenu() {
        if(optionList.isEmpty()) {
            System.out.println("There is no item in menu!!!");
            return;
        }
        for (int i = 0; i  <= 50; i++) {
            System.out.print("=");
        }
        System.out.println();
        System.out.println(menuTitle);
        System.out.println("Select the option belows");
        for (String x : optionList) {
            System.out.println(x);
        }
        for (int i = 0; i <= 50; i++) {
            System.out.print("=");
        }
        System.out.println();
    }
    
    public int getChoice() {
        int choice;
        int maxOption = optionList.size();
        String inputMsg = "Your choice 1..." + maxOption + ": ";
        String errorMsg = "Please choice 1..." + maxOption + "!!!";
        choice = MyToys.getInteger(inputMsg, errorMsg, 1, maxOption);
        return choice;
    }
}
