package com.company;

import com.company.exception.IncorrectNumberException;
import com.company.model.ClassTableModel;
import com.company.model.StudentTableModel;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GUI implements ActionListener {

    private final JFrame frame;
    private JPanel panel;
    private JButton newGroupButton;
    private JButton editGroupButton;
    private JButton deleteGroupButton;
    private JButton sortButton;
    private JButton newStudentButton;
    private JButton editStudentButton;
    private JButton deleteStudentButton;
    private GridBagLayout layout;
    private JTable classTable;
    private JTable studentTable;
    private GridBagConstraints settings = new GridBagConstraints();
    private StudentTableModel studentTableModel;
    private ClassTableModel classTableModel;
    private JScrollPane studentScrollPane;
    private JScrollPane classScrollPane;
    private final JTextField textField;
    private final JComboBox jComboBox;
    private final ClassContainer classContainer;
    private final Class studentClass;
    private final TableRowSorter tableRowSorter;
    int id = 0;

    public GUI() {

        studentClass = new Class("Amigos", 10);

        classContainer = new ClassContainer();
        classContainer.addClass(id,"Amigos", 2);
        classContainer.addClass(++id, "Pysie", 10);

        frame = new JFrame();
        panel = new JPanel();

        layout = new GridBagLayout();

        studentTableModel = new StudentTableModel(studentClass);
        studentTable = new JTable(studentTableModel);
        studentScrollPane = new JScrollPane(studentTable);

        classTableModel = new ClassTableModel(classContainer);
        classTable = new JTable(classTableModel);
        classTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JTable target = (JTable) e.getSource();
                int row = target.getSelectedRow();
                studentTableModel.setStudentClass(classContainer.getClassrooms().get(row));
                studentTableModel.fireTableDataChanged();
            }
        });
        classScrollPane = new JScrollPane(classTable);

        tableRowSorter = new TableRowSorter<>(studentTableModel);
        studentTable.setRowSorter(tableRowSorter);

        newGroupButton = new JButton("Create new Group");
        newGroupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String groupName = JOptionPane.showInputDialog(frame, "Enter Group Name:");
                    String maxStudents = JOptionPane.showInputDialog(frame, "Enter max number of students in group:");
                    int maxStudentsCount = Integer.parseInt(maxStudents);

                    try {
                        if (maxStudentsCount <= 0) {
                            throw new IncorrectNumberException("Incorrect number!");
                        }
                    } catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(null, "Input must be a number!");
                    } catch (IncorrectNumberException ine) {
                        JOptionPane.showMessageDialog(null, "Input must be a number higher than 0!");
                    }
                    classContainer.addClass(++id, groupName, maxStudentsCount);
                }
                catch(IndexOutOfBoundsException indexOutOfBoundsException){
                    JOptionPane.showMessageDialog(null, "Something went wrong ;)");
                }
                classTableModel.fireTableDataChanged();
            }
        });

        editGroupButton = new JButton("Edit Selected Group");
        editGroupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = classTable.getSelectedRow();
                if(selectedRow >= 0) {
                    String groupName = JOptionPane.showInputDialog(frame, "Enter Group Name:");
                    int groupMaxStudents = inputMaxStudents();
                    classContainer.getClassrooms().get(selectedRow).setGroupName(groupName);
                    classContainer.getClassrooms().get(selectedRow).setMaxStudentCount(groupMaxStudents);
                }else{
                    JOptionPane.showMessageDialog(null, "You have to select group to edit it");
                }
                classTableModel.fireTableDataChanged();
            }
        });

        deleteGroupButton = new JButton("Delete Selected Group");
        deleteGroupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = classTable.getSelectedRow();
                if (selectedRow >= 0) {
                    classContainer.getClassrooms().remove(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(null, "You have to select group to delete it");
                }
                classTableModel.fireTableDataChanged();
            }
        });

        sortButton = new JButton("Sort");
        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = classTable.getSelectedRow();
                List<Student> students = classContainer.getClassrooms().get(selectedRow).sortByPoints();
                classContainer.getClassrooms().get(selectedRow).setStudentList(students);
                studentTableModel.fireTableDataChanged();
            }
        });

        newStudentButton = new JButton("Create New Student");
        newStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int actualSize = classContainer.getClassrooms().get(classTable.getSelectedRow()).getStudentList().size();
                    int maxStudentCount = classContainer.getClassrooms().get(classTable.getSelectedRow()).getMaxStudentCount();
                    if (actualSize < maxStudentCount) {
                        String studentName = JOptionPane.showInputDialog(frame, "Enter Student Name:");
                        String studentSurname = JOptionPane.showInputDialog(frame, "Enter Student Surname:");
                        double studentPoints = inputPoints();
                        int birthYear = inputBirthYear();
                        StudentCondition[] studentConditions = StudentCondition.values();
                        String studentCondition = JOptionPane.showInputDialog(frame, "Choose Condition", null, JOptionPane.QUESTION_MESSAGE, null, studentConditions, studentConditions[1]).toString();
                        classContainer.getClassrooms().get(classTable.getSelectedRow()).getStudentList().add(new Student(studentName, studentSurname, StudentCondition.valueOf(studentCondition), birthYear, studentPoints));
                    }
                } catch(IndexOutOfBoundsException ioobe){
                    JOptionPane.showMessageDialog(null, "You must select group first!");
                }
                studentTableModel.fireTableDataChanged();
            }
        });

        editStudentButton = new JButton("Edit selected student");
        editStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = studentTable.getSelectedRow();
                Student student = studentTableModel.getStudentClass().getStudentList().get(selectedRow);
                if(selectedRow >= 0) {
                    String studentName = JOptionPane.showInputDialog(frame, "Enter Student Name:");
                    String studentLastName = JOptionPane.showInputDialog(frame, "Enter Student Surname:");
                    double studentPoints = inputPoints();
                    int birthYear = inputBirthYear();
                    StudentCondition[] choices = StudentCondition.values();
                    String studentCondition = JOptionPane.showInputDialog(frame, "Choose Condition", null, JOptionPane.QUESTION_MESSAGE, null, choices, choices[1]).toString();

                    student.setName(studentName);
                    student.setLastName(studentLastName);
                    student.setPointsCount(studentPoints);
                    student.setBirthYear(birthYear);
                    student.setStudentState(StudentCondition.valueOf(studentCondition));

                }else{
                    JOptionPane.showMessageDialog(null, "You have to select student to edit him");
                }
                studentTableModel.fireTableDataChanged();
            }
        });

        deleteStudentButton = new JButton("Delete selected Student");
        deleteStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = studentTable.getSelectedRow();
                if(selectedRow >= 0) {
                    studentTableModel.getStudentClass().getStudentList().remove(selectedRow);
                }else{
                    JOptionPane.showMessageDialog(null, "You have to select student to delete him");
                }
                studentTableModel.fireTableDataChanged();
            }
        });

        textField = new JTextField();
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    String query = textField.getText();
                    filter(query);
                }
            }
        });

        jComboBox = new JComboBox<>(StudentCondition.values());
        jComboBox.insertItemAt("", 0);
        jComboBox.setSelectedIndex(0);
        jComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getItem() != "") {
                    filter(String.valueOf(e.getItem()));
                } else {
                    filter("");
                }
            }
        });

        settings.fill = GridBagConstraints.BOTH;
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(layout);
        settings.weightx = 0.5;
        settings.weighty = 0.9;
        settings.gridwidth = 3;
        settings.gridx = 0;
        settings.gridy = 0;
        panel.add(studentScrollPane, settings);
        settings.gridx = 4;
        settings.gridy = 0;
        panel.add(classScrollPane, settings);

        settings.fill = GridBagConstraints.HORIZONTAL;
        settings.weightx = 0.1;
        settings.weighty = 0.01;
        settings.gridwidth = 1;
        settings.gridx = 6;
        settings.gridy = 1;
        panel.add(jComboBox, settings);

        settings.fill = GridBagConstraints.HORIZONTAL;
        settings.weightx = 0.9;
        settings.weighty = 0.01;
        settings.gridwidth = 6;
        settings.gridx = 0;
        settings.gridy = 1;
        panel.add(textField, settings);

        settings.anchor = GridBagConstraints.PAGE_END;
        settings.weighty = 0.01;
        settings.weightx = 0.1;
        settings.gridwidth = 1;
        settings.gridx = 0;
        settings.gridy = 2;
        panel.add(newStudentButton, settings);

        settings.gridx = 1;
        settings.gridy = 2;
        panel.add(editStudentButton, settings);

        settings.gridx = 2;
        settings.gridy = 2;
        panel.add(deleteStudentButton, settings);

        settings.gridx = 3;
        settings.gridy = 2;
        panel.add(sortButton, settings);

        settings.gridx = 4;
        settings.gridy = 2;
        panel.add(newGroupButton, settings);

        settings.gridx = 5;
        settings.gridy = 2;
        panel.add(editGroupButton, settings);

        settings.gridx = 6;
        settings.gridy = 2;
        panel.add(deleteGroupButton, settings);


        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Student Class Manager");

        frame.setVisible(true);
        frame.pack();
    }

    private void filter(String query) {
        tableRowSorter.setRowFilter(RowFilter.regexFilter(query));
    }

    private double inputPoints() {
        while (true) {
            try {
                String input = JOptionPane.showInputDialog(frame, "Enter Student Points:");
                double a = Double.parseDouble(input);
                return a;
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Input must be a number");
            }
        }
    }

    private int inputBirthYear() {
        while (true) {
            try {
                String input = JOptionPane.showInputDialog(frame, "Enter Student Birth Year:");
                return Integer.parseInt(input);
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Input must be a number");
            }
        }
    }

    private int inputMaxStudents() {

        while (true) {
            try {
                String input = JOptionPane.showInputDialog(frame, "Enter max number of students in group:");
                int a = Integer.parseInt(input);
                if (a <= 0) {
                    throw new IncorrectNumberException("Incorrect number!");
                }
                return a;
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Input must be a number!");
            } catch (IncorrectNumberException ine) {
                JOptionPane.showMessageDialog(null, "Input must be a number higher than 0!");
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
