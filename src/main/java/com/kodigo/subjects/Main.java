package com.kodigo.subjects;


import com.aspose.words.Document;
import com.aspose.words.SaveFormat;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;


public class Main {

    static int subject = 1;
    public static void main(String[] args) throws Exception {
        //readFile();
        //createFile();
        askSubjects();
        toPDF();
        sendEmail();
    }

    public static void askSubjects() throws IOException {

        for (subject = 1; subject <= 3; subject++) {
            System.out.println("Collecting Data for Signature " + subject);
            askData();
        }
    }

    public static void askData() throws IOException {

        // Ask Data shows the user the option to either introduce
        // 1 for reading an existing file or
        // 2 for introducing the information manually


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
        int studentId ;
        String name ;
        double grade ;

        // variables for Statics

        double total = 0;
        double average ;
        double minGrade = Integer.MAX_VALUE;
        double maxGrade = 0;


        // Creating a .txt file with the array for the Object Student


        FileOutputStream fo = new FileOutputStream("src/main/resources/" + subject + ".txt");
        PrintWriter pw = new PrintWriter(fo);

        File subjects = new File("src/main/resources/subject_" + subject + ".txt");
        Scanner sc = new Scanner(subjects);

        ArrayList<Student> list = new ArrayList<>();

        while (sc.hasNext()) {
            studentId = sc.nextInt();
            name = sc.next();
            grade = sc.nextDouble();
            Student record = new Student(studentId, name, grade);
            list.add(record);
        }

        for (Student record : list) {
            pw.println(record);
            total += record.getGrade();
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
        int studentId ;
        String name  ;
        double grade  ;

        // Analysis for statics

        double total = 0;
        double average ;
        double minGrade = Integer.MAX_VALUE;
        double maxGrade = 0;

        System.out.println("Please introduce student's information");
        System.out.println("\t");

        ArrayList<Student> list = new ArrayList<>();
        FileOutputStream fo = new FileOutputStream("src/main/resources/" + subject + ".txt");
        PrintWriter pw = new PrintWriter(fo);

        Scanner sc = new Scanner(System.in);

        System.out.println("How many students would you like to add?");
        int j = sc.nextInt();

        for (int i = 1; i <= j; i++) {
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
            System.out.println("Document Created");

            // Total is the accumulative var for all the grades

            total += record.getGrade();

            // Finding MAX and MIN Value
            if (record.getGrade() > maxGrade) {
                maxGrade = record.getGrade();
            }
            if (record.getGrade() < minGrade) {
                minGrade = record.getGrade();
            }
        }

        // Finding average of grades

        average = total / list.size();

        // Output of the analysis and writing in the txt file the results

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

    // toPdf takes the txt file created and converts the file to PDF

    public static void toPDF() throws Exception {

        for (subject =1;subject<=3;subject++){
            Document document = new Document("src/main/resources/" + subject + ".txt");
            document.save("src/main/resources/" + subject + ".pdf", SaveFormat.PDF);
        }

    }

    // sendEmail used the java mail api to send the PDF file attached
    // For testing purposes we use the Mailtrap server

    public static void sendEmail() throws MessagingException, IOException {

        for (subject =1;subject<=3;subject++){


            Properties prop = new Properties();
            prop.put("mail.smtp.auth", true);
            prop.put("mail.smtp.starttls.enable", "true");
            prop.put("mail.smtp.host", "smtp.mailtrap.io");
            prop.put("mail.smtp.port", "465");
            prop.put("mail.smtp.ssl.trust", "smtp.mailtrap.io");

            Session session = Session.getInstance(prop, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("42a030f1461013", "6b5affd25a5539");
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("ernesto@mail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("ernesto1991diaz@gmail.com"));
            message.setSubject("Test Mail Subject");

            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Mail Body");

            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.attachFile(new File("src/main/resources/" + subject + ".pdf"));

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentPart);
            message.setContent(multipart);
            Transport.send(message);
            System.out.println("\t");
            System.out.println("Email sent to: ernesto@mail.com");
        }

    }
}

















