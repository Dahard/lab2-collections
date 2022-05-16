package com.company.model;

import com.company.ClassContainer;

import javax.swing.table.AbstractTableModel;

public class ClassTableModel extends AbstractTableModel {

    private ClassContainer classContainer;

    public ClassTableModel(ClassContainer classContainer) {
        this.classContainer = classContainer;
    }

    @Override
    public int getRowCount() {
        return classContainer.getClassrooms().size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Object key = classContainer.getClassrooms().keySet().toArray()[rowIndex];

        switch(columnIndex) {
            case 0:
                return classContainer.getClassrooms().get(key).getGroupName();
            case 1:
                return classContainer.getClassrooms().get(key).getStudentList().size();
            case 2:
                return classContainer.getClassrooms().get(key).getMaxStudentCount();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "Group Name";
            case 1:
                return "Number of Students";
            case 2:
                return "Maximum number of Students";
            default:
                return null;
        }
    }
}
