package com.company;

import java.util.ArrayList;
import java.util.List;

public class Class {

    String groupName;
    List<Student> studentList = new ArrayList<Student>();
    int maxStudentCount;

    public void addStudent(Student student) {
        studentList.add(student);
    }

    public void addPoints(Student student, double points){
    }

    public void getStudent(Student student) {

    }

    public void changeCondition(Student student, StudentCondition studentCondition) {

    }

    public void removePoints(Student student, double points) {

    }

}
