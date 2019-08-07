package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import static java.lang.System.out;

public class App {
    private static ArrayList<Student> students;
    private static String fileName = "students.csv";
    private static Scanner scanner;

    private static void initialize() {
        scanner = new Scanner(System.in);
        readFile();
    }

    public static void run() {
        initialize();

        int choice;
        boolean isExit = false;

        while (!isExit) {
            out.println("HIGHEST SCORE******");
            if (students.isEmpty()) {
                out.println("No data in file");
            } else {
                ArrayList<Student> ordered = (ArrayList<Student>) students.clone();
                Collections.sort(ordered);
                Student highest = ordered.get(0);
                out.println(highest.getName() + " (" + highest.getAverage() + ")");
            }
            out.println("*******************");
            out.println("MENU **************");
            out.println("[1] Input student");
            out.println("[2] Show student list");
            out.println("[3] Show student list - Order by average");
            out.println("[0] Quit");
            out.println("*******************");
            out.print("Your choice? ");
            choice = scanner.nextInt();
            switch (choice) {
            case 1:
                /**
                 * REQ 3: Cho phép user input thông tin của student từ console và ghi vào file
                 */
                out.println("Input Student");
                try {
                    Student student = inputStudent();
                    if (student != null) {
                        writeFile(student);
                        /**
                         * REQ 2: Tạo obj student và in ra màn hình
                         */
                        out.println(getHeader());
                        out.println(student);
                        out.println("Student is saved successfully");
                    }
                } catch (IOException e) {
                    out.println("Saving failed!!");
                    out.println(e.getMessage());
                }
                break;
            case 2:
                out.println("Show Student List");
                showStudentList();
                break;
            case 3:
                out.println("Show Student List - Order by Average");
                showStudentListOrderByAverage();
                break;
            case 0:
                out.println("Exit");
                isExit = true;
                break;
            default:
                out.println("Invalid option!!");
            }
        }
        scanner.close();
    }

    public static Student inputStudent() {
        Student student = new Student();

        out.print("Id: ");
        String id = scanner.nextLine();
        student.setId(id);
        if (students.contains(student)) {
            out.println("Student Id must be unique!!");
            return null;
        }

        out.print("Name: ");
        String name = scanner.nextLine();
        student.setName(name);

        try {
            out.print("Score 1: ");
            float score1 = scanner.nextFloat();
            student.setScore1(score1);
    
            out.print("Score 2: ");
            float score2 = scanner.nextFloat();
            student.setScore2(score2);
    
            out.print("Score 3: ");
            float score3 = scanner.nextFloat();
            student.setScore3(score3);
        } catch (IllegalArgumentException e) {
            out.println("Invalid score!!");
            return null;
        }
        student.updateStatus();

        return student;
    }

    public static void showStudentList() {
        out.println(getHeader());
        students.forEach(out::println);
    }

    public static void showStudentListOrderByAverage() {
        out.println(getHeader());
        ArrayList<Student> ordered = (ArrayList<Student>) students.clone();
        Collections.sort(ordered);
        ordered.forEach(out::println);
    }

    public static void writeFile(Student student) throws IOException {
        File file = new File(fileName);
        FileWriter fr = new FileWriter(file, true);
        BufferedWriter br = new BufferedWriter(fr);

        br.newLine();
        br.write(student.toCsv());

        br.close();
        fr.close();
    }

    public static void readFile() {
        students = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            bufferedReader
                .lines()
                .map(Student::fromString)
                .forEach(students::add);

            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            out.println("Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
            out.println("Error reading file '" + fileName + "'");
        }
    }

    private static String getHeader() {
        return String.format("%-10s%-20s%-10s%-10s%-10s%-10s%-10s", Student.getFields());
    }
}