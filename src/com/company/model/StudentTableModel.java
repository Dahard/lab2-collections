package com.company.model;

import com.company.Class;

import javax.swing.table.AbstractTableModel;

public class StudentTableModel extends AbstractTableModel {

    private Class studentClass;

    public StudentTableModel(Class studentClass) {
        this.studentClass =  studentClass;
    }

    public void setStudentClass(Class studentClass) {
        this.studentClass = studentClass;
    }

    public Class getStudentClass() {
        return studentClass;
    }

    @Override
    public int getRowCount() {
        return studentClass.getStudentList().size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0:
                return studentClass.getStudentList().get(rowIndex).getName();
            case 1:
                return studentClass.getStudentList().get(rowIndex).getLastName();
            case 2:
                return studentClass.getStudentList().get(rowIndex).getStudentState();
            case 3:
                return studentClass.getStudentList().get(rowIndex).getBirthYear();
            case 4:
                return studentClass.getStudentList().get(rowIndex).getPointsCount();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "Name";
            case 1:
                return "Last Name";
            case 2:
                return "Student State";
            case 3:
                return "Birth Year";
            case 4:
                return "Points";
            default:
                return null;
        }
    }
}
