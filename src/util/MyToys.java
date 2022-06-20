/**
 *
 * @author Hoang-Tien | fb/htmsyunh/
 * Version1 17-01-2022
 * Learn from teacher Nguyen-The-Hoang and references from many sources.
 */
package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import ui.Menu;

public class MyToys {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final int MAX_ELEMENT = 500;
    private static Scanner sc = new Scanner(System.in);

    public static int getInteger(String inputMsg, String errorMsg, int lowerBound, int upperBound) {
        int n, tmp;
        if (lowerBound > upperBound) {
            tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }
        do {
            try {
                System.out.print(inputMsg);
                n = Integer.parseInt(sc.nextLine());
                if (n < lowerBound || n > upperBound) {
                    throw new Exception();
                }
                return n;
            } catch (Exception e) {
                System.out.println(ANSI_RED + errorMsg + ANSI_RESET);
            }
        } while (true);
    }
    
    public static int getPositiveInteger(String inputMsg, String errorMsg) {
        int n;
        do {
            try {
                System.out.print(inputMsg);
                n = Integer.parseInt(sc.nextLine());
                if(n < 0)
                    throw new Exception();
            } catch (Exception e) {
                System.out.println(ANSI_RED + errorMsg + ANSI_RESET);
            }
        } while (true);    
    }
   
    public static String getString(String inputMsg, String errorMsg) {
        String id;
        while (true) {            
            System.out.print(inputMsg);
            id = sc.nextLine().trim();
            if(id.length() == 0 || id.isEmpty())
                System.out.println(ANSI_RED + errorMsg + ANSI_RESET);
            else
                return id;
        }
    }
    
    public static String getString(String inputMsg, String errorMsg, int lowerBound, int upperBound) {
        int tmp;
        String id;
        if(lowerBound > upperBound){
            tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }
        while (true) {            
            System.out.print(inputMsg);
            id = sc.nextLine().trim();
            if(id.length() == 0 || id.isEmpty() || id.length() < lowerBound || id.length() > upperBound)
                System.out.println(ANSI_RED + errorMsg + ANSI_RESET);
            else
                return id;
        }
    }
    
    public static int getYesNo(String inputMsg, String errorMsg) {
        String answer;        
        do {     
            System.out.print(inputMsg);
            answer = sc.nextLine().trim();
            if (answer.equalsIgnoreCase("yes")) {
            return 1;
            } else {
                if (answer.equalsIgnoreCase("no")) {
                    return -1;
                } else {
                    System.out.println(ANSI_RED + errorMsg + ANSI_RESET);
                }
            }
        } while (true);
    }
    
    public static String getDate(String inputMsg, String errorMsg) {
        Calendar cal = Calendar.getInstance(); //use the default time zone and locale.
        Date date = cal.getTime();
        SimpleDateFormat sDF = new SimpleDateFormat("dd/MM/yyyy");
        sDF.setLenient(false);
        String dateString;
        while (true) {            
            try {
                dateString = getString(inputMsg, errorMsg);
                if(sDF.parse(dateString).compareTo(sDF.parse("08/03/2021")) >= 0 && sDF.parse(dateString).compareTo(sDF.parse(sDF.format(date))) <= 0)
                    return dateString;
                else
                    System.out.println(ANSI_RED + errorMsg + ANSI_RESET);
            } catch (ParseException e) {
                System.out.println(ANSI_RED + errorMsg + ANSI_RESET);
            }
        }
    }
    
    public static String getDateTmp(String inputMsg, String errorMsg) {
        SimpleDateFormat sDF = new SimpleDateFormat("dd/MM/yyyy");
        sDF.setLenient(false);
        String dateString;
        while (true) {            
            try {
                dateString = getString(inputMsg, errorMsg);
                if(sDF.parse(dateString).compareTo(sDF.parse("08/03/2021")) >= 0)
                    return dateString;
                else
                    System.out.println(ANSI_RED + errorMsg + ANSI_RESET);
            } catch (ParseException e) {
                System.out.println(ANSI_RED + errorMsg + ANSI_RESET);
            }
        }
    }
    
    public static String getDate2(String date1) {
        String date2;
        while (true) {            
            date2 = getDateTmp("Enter the date of the second injection: ", "Input invalid!");
            int n = daysBetween(date1, date2);
            if(n < (4 * 7) || n  > (12 * 7)) {
                System.out.println("The second dose of vaccine must be given 4 to 12 weeks after the first injection !");
                System.out.println("Your first dose of vaccine was given on "+ date1);
            }
            return date2;
        }
    }
    
    public static int daysBetween(String date1, String date2) {
        SimpleDateFormat sDF = new SimpleDateFormat("dd/MM/yyyy");
        sDF.setLenient(false);
        try {
            long diff = sDF.parse(date2).getTime() - sDF.parse(date1).getTime();
            return (int) (diff / 1000 / 60 / 60 /24);
        } catch (ParseException e) {
            e.getMessage();
            return 0;
        }
    }
    
    public static String getPlace(String inputMsg, String errorMsg){
        ArrayList<String> list1 = new ArrayList();
        ArrayList<String> list2 = new ArrayList();
        Menu menu = new Menu("These are provinces you want to mention. Please select a province: ");
        list1 = FileRead.readFilePlace("place.txt");
        do {            
            String place = MyToys.getString(inputMsg, errorMsg);
            for (String s : list1) {
                if(s.equalsIgnoreCase(place.replaceAll("\\s\\s", " ")))
                    return s;
            }
            String arr[] = place.split("\\s");
            for (String x : arr) {
                if(x.length() > 1) {
                    for (String string : list1) {
                        if(string.contains(x.toUpperCase()))
                            list2.add(string);
                    }
                }
            }
            if(list2.isEmpty())
                System.out.println(ANSI_RED + "Your input could not give a hint. Let's try again!" + ANSI_RESET);
        } while (list2.isEmpty());
        Set<String> dataSet = new HashSet<> (list2);
        for (int i = 0; i < dataSet.size(); i++) {
            menu.addNewOption((i + 1) + ". " + list2.get(i));
        }
        menu.printMenu();
        int choice = menu.getChoice();
        return list2.get(choice - 1);
    }
}
