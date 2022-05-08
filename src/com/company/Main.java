package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        ClassContainer classContainer = new ClassContainer();

        Student daniek = new Student("Pawel", "Cos", StudentCondition.ILL, 1995, 0);
        Student lubik = new Student("Daniek", "Lubik", StudentCondition.ILL, 1980, 0);

        Class studentClass = new Class("Amigos", 10);
        studentClass.addStudent(new Student("Daniel", "Cis", StudentCondition.ILL, 1995, 100));
        studentClass.addStudent(new Student("Maciej", "Lubik", StudentCondition.DETACHING, 2000, 200));
        studentClass.addStudent(new Student("Anie", "Drag", StudentCondition.ABSENT, 1995, 10));
        studentClass.addStudent(new Student("Marta", "Agra", StudentCondition.ILL, 1990, 60));

        //summary()
        studentClass.summary();

        //addStudent()
        studentClass.addStudent(daniek);
        studentClass.summary();

        //addPoints()
        studentClass.addPoints(daniek, 100);
        studentClass.summary();

        //removePoints()
        studentClass.removePoints(daniek, 100);
        studentClass.getStudent(daniek);
        studentClass.summary();

        //changeCondition()
        studentClass.addStudent(lubik);
        studentClass.changeCondition(lubik, StudentCondition.DETACHING);
        studentClass.summary();

        //search()
        System.out.println(studentClass.search("Lubik"));
        System.out.println("");

        //searchPartial()
        System.out.println(studentClass.searchPartial("Dan"));
        System.out.println("");

        //countByCondition()
        System.out.println(studentClass.countByCondition(StudentCondition.DETACHING));
        studentClass.summary();

        //sortByName()
        System.out.println(studentClass.sortByName());
        System.out.println("");

        //sortByPoints()
        System.out.println(studentClass.sortByPoints());
        System.out.println("");

        //addClass()
        classContainer.addClass("boje", 6);
        classContainer.addClass("dżesiki", 3);
        classContainer.summary();

        //removeClass()
        classContainer.removeClass("boje");
        classContainer.summary();

        //findEmpty
        System.out.println(classContainer.findEmpty());
    }
}
