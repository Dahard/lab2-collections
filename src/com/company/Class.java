package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Class {

    private String groupName;
    private int maxStudentCount;
    private List<Student> studentList;

    public String getGroupName() {
        return groupName;
    }

    public int getMaxStudentCount() {
        return maxStudentCount;
    }

    public Class(){
    }

    public Class(String groupName, int maxStudentCount) {
        this.groupName = groupName;
        this.maxStudentCount = maxStudentCount;
        this.studentList = new ArrayList<>();
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setMaxStudentCount(int maxStudentCount) {
        this.maxStudentCount = maxStudentCount;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void addStudent(Student student) {
        if (studentList.stream().anyMatch(s -> s.getName().equals(student.getName()))) {
            System.out.println("Student o imieniu " + student.getName() + " juz istnieje.");
        } else if (studentList.size() >= maxStudentCount) {
            System.out.println(System.err);
        } else {
            studentList.add(student);
        }
    }

    public void addPoints(Student student, double points){
        studentList.stream()
                .filter(s -> s.getName().equals(student.getName()))
                .forEach(s -> s.addPoints(points));
    }

    public void getStudent(Student student) {
        studentList.removeIf(s -> s.getName().equals(student.getName()) && student.getPointsCount() == 0);
}

    public void changeCondition(Student student, StudentCondition studentCondition) {
        studentList.stream()
                .filter(s -> s.getName().equals(student.getName()))
                .forEach(s -> s.setStudentState(studentCondition));
    }

    public void removePoints(Student student, double points) {
        studentList.stream()
                .filter(s -> s.getName().equals(student.getName()))
                .forEach(s -> s.removePoints(points));
    }

    public Optional<Student> search(String lastName) {
        return studentList.stream()
                .filter(s -> lastName.equals(s.getLastName()))
                .findFirst();
    }

    public ArrayList<Student> searchPartial(String prefix) {
        return (ArrayList<Student>) studentList.stream()
                .filter(s -> s.getLastName().startsWith(prefix) || s.getName().startsWith(prefix))
                .collect(Collectors.toList());
    }

    public long countByCondition(StudentCondition studentCondition) {
        return studentList.stream()
                .filter(s -> s.getStudentState().equals(studentCondition))
                .count();
    }

    public void summary() {
        System.out.println("Podsumowanie klasy: ");
        studentList.forEach(System.out::println);
        System.out.println("");
    }

    public ArrayList<Student> sortByName() {
        return (ArrayList<Student>) studentList.stream()
                .sorted(Comparator.comparing(Student::getLastName))
                .collect(Collectors.toList());
    }

    public ArrayList<Student> sortByPoints() {
        return (ArrayList<Student>) studentList.stream()
                .sorted((new StudentComparator()).reversed())
                .collect(Collectors.toList());
    }

    public Student max() {
        return Collections.max(studentList);
    }

}
