/**
 *
 * @author Hoang-Tien | fb/htmsyunh/
 * Version1 17-01-2022
 * Learn from teacher Nguyen-The-Hoang and references from many sources.
 */
package util;

import data.Injection;
import data.Student;
import data.Vaccine;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class FileRead {
    public static ArrayList<Student> readFileStudent(String fileName) {
        ArrayList<Student> st = new ArrayList();
        FileReader fr = null;
        BufferedReader br = null;
        String line = "";
        try {
            File file = new File(fileName);
            if(!file.exists())
                return null;
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            while (true) {                
                line = br.readLine();
                if(line == null)
                    break;
                String txt[] = line.split(";");
                st.add(new Student(txt[0], txt[1]));
            }
            fr.close();
            br.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return st;
    }
    
    public static ArrayList<Vaccine> readFileVaccine(String fileName) {
        ArrayList<Vaccine> vc = new ArrayList();
        FileReader fr = null;
        BufferedReader br = null;
        String line = "";
        try {
            File file = new File(fileName);
            if(!file.exists())
                return null;
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            while (true) {                
                line = br.readLine();
                if(line == null)
                    break;
                String txt[] = line.split(";");
                vc.add(new Vaccine(txt[0], txt[1]));
            }
            fr.close();
            br.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return vc;
    }
    
    public static ArrayList<Injection> readFileInjection(String fileName) {
        ArrayList<Injection> ij = new ArrayList();
        FileReader fr = null;
        BufferedReader br = null;
        String line = "";
        try {
            File file = new File(fileName);
            if(!file.exists())
                return null;
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            while (true) {                
                line = br.readLine();
                if(line == null)
                    break;
                String txt[] = line.split("[|]");
                ij.add(new Injection(txt[0].trim(), txt[1].trim(), txt[2].trim(), txt[3].trim(), txt[4].trim(), txt[5].trim(), txt[6].trim()));
                fr.close();
                br.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return ij;
    }
    
    public static ArrayList<String> readFilePlace(String fileName) {
        ArrayList<String> place = new ArrayList();
        FileReader fr = null;
        BufferedReader br = null;
        String line = "";
        try {
            File file = new File(fileName);
            if(!file.exists())
                return null;
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            while (true) {                
                line = br.readLine();
                if(line == null)
                    break;
                place.add(line);
            }
            fr.close();
            br.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return place;
    }
}
