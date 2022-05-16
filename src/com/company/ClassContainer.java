package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassContainer {

    private final Map<String, Class> classrooms;

    public Map<String, Class> getClassrooms() {
        return classrooms;
    }

    public ClassContainer() {
        this.classrooms = new HashMap<>();
    }

    public void addClass(String groupName, int maxStudentsCount) {
        classrooms.put(groupName, new Class(groupName, maxStudentsCount));
    }

    public void removeClass(String groupName) {
        classrooms.remove(groupName);
    }

    public List<Class> findEmpty() {
        List<Class> emptyClasses = new ArrayList<>();
        this.classrooms.forEach((gn, c) -> {
            if (c.getStudentList().isEmpty()) {
                emptyClasses.add(c);
            }
        });
        return emptyClasses;
    }

    public void summary() {
        System.out.println("Podsumowanie Kontenera Klasy: ");
        classrooms.forEach((gn, c) -> System.out.println());
        System.out.println("");
    }
}
