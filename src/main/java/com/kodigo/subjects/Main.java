package com.kodigo.subjects;



import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class Main {

    private static Object Scanner;
    static int subject;

    public static void main(String[] args) throws IOException {
        //readFile();
        //createFile();
        askSubjects();
        //toPdf();
    }

    public static void askSubjects() throws IOException {

        Scanner sc = new Scanner(System.in);
        for (subject = 1; subject <= 3; subject++) {
            System.out.println("Collecting Data for Signature " + subject);
            askData();
        }
    }

    public static void askData() throws IOException {
        int option;
        Scanner sc = new Scanner(System.in);
        System.out.println("\t");
        System.out.println("load Subjects File? Type 1");
        System.out.println("Enter Students info Manually? Type 2");
        option = sc.nextInt();
        if (option == 1) {
            readFile();
        } else if (option == 2) {
            createFile();
        } else {
            System.out.println("Invalid Option");
        }
    }

    public static void readFile() throws IOException {
        int studentId = 0;
        String name = "";
        double grade = 0;

        //analisis for data

        double total = 0;
        double average = 0;
        double minGrade = Integer.MAX_VALUE;
        double maxGrade = 0;


        // Creating a File
        FileOutputStream fo = new FileOutputStream("src/main/resources/" + subject + ".txt");
        PrintWriter pw = new PrintWriter(fo);

        File subjects = new File("src/main/resources/subject_" + subject + ".txt");
        Scanner sc = new Scanner(subjects);

        ArrayList<Student> list = new ArrayList<Student>();

        while (sc.hasNext()) {
            studentId = sc.nextInt();
            name = sc.next();
            grade = sc.nextDouble();
            Student record = new Student(studentId, name, grade);
            list.add(record);
        }

        for (Student record : list) {
            pw.println(record);
            total = total = record.getGrade();
            // Finding MAX and MIN Value
            if (record.getGrade() > maxGrade) {
                maxGrade = record.getGrade();
            }
            if (record.getGrade() < minGrade) {
                minGrade = record.getGrade();
            }
        }
        average = total / list.size(); // Finding average of grades

        pw.println("\t\t");
        pw.println("total: " + total);
        pw.println("Average: " + average);
        pw.println("Max Grade: " + maxGrade);
        pw.println("Min Grade: " + minGrade);
        pw.close();
        System.out.println("Total: " + total);
        System.out.println("Average: " + average);
        System.out.println("MaxGrade: " + maxGrade);
        System.out.println("MinGrade: " + minGrade);

    }

    public static void createFile() throws IOException {
        int studentId = 0;
        String name = "";
        double grade = 0;
        boolean status = true;

        //analysis for data

        double total = 0;
        double average = 0;
        double minGrade = Integer.MAX_VALUE;
        double maxGrade = 0;

        System.out.println("Please introduce student's information");
        System.out.println("\t");

        ArrayList<Student> list = new ArrayList<Student>();
        FileWriter writer = new FileWriter("src/main/resources/manualdata.txt");

        FileOutputStream fo = new FileOutputStream("src/main/resources/" + subject + ".txt");
        PrintWriter pw = new PrintWriter(fo);
        int dataList = list.size();
        BufferedWriter bwr = new BufferedWriter(writer);
        Scanner sc = new Scanner(System.in);

        for (int i = 1; i <= 4; i++) {
            studentId = i;
            System.out.println("Introduce Students name");
            name = sc.next();
            System.out.println("Introduce Students grade");
            grade = sc.nextDouble();
            Student record = new Student(studentId, name, grade);
            list.add(record);
        }
        for (Student record : list) {
            pw.println(record);
            total = total = record.getGrade();
            // Finding MAX and MIN Value
            if (record.getGrade() > maxGrade) {
                maxGrade = record.getGrade();
            }
            if (record.getGrade() < minGrade) {
                minGrade = record.getGrade();
            }
        }
        average = total / list.size(); // Finding average of grades

        pw.println("\t\t");
        pw.println("total: " + total);
        pw.println("Average: " + average);
        pw.println("Max Grade: " + maxGrade);
        pw.println("Min Grade: " + minGrade);
        pw.close();
        System.out.println("Total: " + total);
        System.out.println("Average: " + average);
        System.out.println("MaxGrade: " + maxGrade);
        System.out.println("MinGrade: " + minGrade);
        pw.close();
        fo.close();
    }

}















