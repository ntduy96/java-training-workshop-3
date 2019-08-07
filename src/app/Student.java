package app;

import java.lang.IllegalArgumentException;
import java.util.Arrays;

/**
 * Student REQ 1: Tạo class Student với những thuộc tính sau
 */
public class Student implements Comparable<Student> {
    private String id;
    private String name;
    private float score1;
    private float score2;
    private float score3;
    private String status;
    private static String college = "My college";

    private static String[] fields = { "Id", "Name", "Score 1", "Score 2", "Score 3", "Average", "Status", "College" };

    public Student() {
    }

    public static String[] getFields() {
        return fields;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getScore1() {
        return score1;
    }

    /**
     * REQ 4: validate thông tin Set score 1
     * 
     * @param score1
     * @throws IllegalArgumentException
     */
    public void setScore1(float score1) throws IllegalArgumentException {
        if (score1 < 1 || score1 > 10) {
            throw new IllegalArgumentException("Invalid score!!");
        }
        this.score1 = score1;
    }

    public float getScore2() {
        return score2;
    }

    public void setScore2(float score2) throws IllegalArgumentException {
        if (score2 < 1 || score2 > 10) {
            throw new IllegalArgumentException("Invalid score!!");
        }
        this.score2 = score2;
    }

    public float getScore3() {
        return score3;
    }

    public void setScore3(float score3) throws IllegalArgumentException {
        if (score3 < 1 || score3 > 10) {
            throw new IllegalArgumentException("Invalid score!!");
        }
        this.score3 = score3;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static String getCollege() {
        return college;
    }

    public static void setSchool(String college) {
        Student.college = college;
    }

    @Override
    public String toString() {
        return String.format("%-10s%-20s%-10.1f%-10.1f%-10.1f%-10.1f%-10s%-10s", id, name, score1, score2, score3, getAverage(), status,
                college);
    }

    public String toCsv() {
        return String.format("%s,%s,%.1f,%.1f,%.1f,%s", id, name, score1, score2, score3, status);
    }

    public static Student fromString(String line) {
        String[] parts = line.split(",");
        Student student = new Student();
        student.setId(parts[0]);
        student.setName(parts[1]);
        student.setScore1(new Float(parts[2]));
        student.setScore2(new Float(parts[3]));
        student.setScore3(new Float(parts[4]));
        student.setStatus(parts[5]);

        return student;
    }

    public float getAverage() {
        return (score1 + score2 + score3) / 3;
    }

    public void updateStatus() {
        if (getAverage() >= 5) {
            this.status = "PASS";
        }
        this.status = "FAILED";
    }

    @Override
    public boolean equals(Object obj) {
        Student student = (Student) obj;
        return student.getId().equals(this.id);
    }

    @Override
    public int compareTo(Student student) {
        return (int)(student.getAverage() - this.getAverage());
    }
}