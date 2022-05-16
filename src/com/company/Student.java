package com.company;

public class Student implements Comparable<Student> {

    private String name;
    private String lastName;
    private StudentCondition studentState;
    private int birthYear;
    private double pointsCount;

    public Student(String name, String lastName, StudentCondition studentState, int birthYear, double pointsCount) {
        this.name = name;
        this.lastName = lastName;
        this.studentState = studentState;
        this.birthYear = birthYear;
        this.pointsCount = pointsCount;
    }

    public void print() {
        System.out.println("Imie: " + this.name);
        System.out.println("Nazwisko: " + this.lastName);
        System.out.println("Rok urodzenia: " + this.birthYear);
        System.out.println("Ilosc punktow: " + this.pointsCount);
        System.out.println("Stan studenta: " + this.studentState);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public StudentCondition getStudentState() {
        return studentState;
    }

    public void setStudentState(StudentCondition studentState) {
        this.studentState = studentState;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public double getPointsCount() {
        return pointsCount;
    }

    public void addPoints(double points) {
        this.pointsCount += points;
    }

    public void removePoints(double points) {
        this.pointsCount -= points;
    }

    public void setPointsCount(double pointsCount) {
        this.pointsCount = pointsCount;
    }

    @Override
    public int compareTo(Student student) {
        return this.lastName.compareTo(student.lastName);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", studentState=" + studentState +
                ", birthYear=" + birthYear +
                ", pointsCount=" + pointsCount +
                '}';
    }
}