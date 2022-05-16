package com.company;

import com.company.model.ClassTableModel;
import com.company.model.StudentTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {

    private JLabel label;
    private JFrame frame;
    private JPanel panel;
    private JButton newGroupButton;
    private JButton editGroupButton;
    private JButton deleteGroupButton;
    private JButton sortButton;
    private JButton newStudentButton;
    private JButton editStudentButton;
    private JButton deleteStudentButton;
    private GridBagLayout layout;
    private JTable table;
    private GridBagConstraints settings = new GridBagConstraints();
    private StudentTableModel studentTableModel;
    private ClassTableModel classTableModel;
    private JScrollPane studentScrollPane;
    private JScrollPane classScrollPane;

    public GUI() {

        Class studentClass = new Class("Amigos", 10);
        studentClass.addStudent(new Student("Daniel", "Cis", StudentCondition.ILL, 1995, 100));
        studentClass.addStudent(new Student("Maciej", "Lubik", StudentCondition.DETACHING, 2000, 200));
        studentClass.addStudent(new Student("Anie", "Drag", StudentCondition.ABSENT, 1995, 10));
        studentClass.addStudent(new Student("Marta", "Agra", StudentCondition.ILL, 1990, 60));

        ClassContainer classContainer = new ClassContainer();
        classContainer.addClass("Amigos", 2);
        classContainer.addClass("Pysie", 10);



        frame = new JFrame();
        panel = new JPanel();

        newGroupButton = new JButton("Create new Group");
        newGroupButton.addActionListener(this);
        editGroupButton = new JButton("Edit Selected Group");
        deleteGroupButton = new JButton("Delete Selected Group");
        sortButton = new JButton("Sort");
        newStudentButton = new JButton("Create New Student");
        editStudentButton = new JButton("Edit selected student");
        deleteGroupButton = new JButton("Delete selected Group");
        deleteStudentButton = new JButton("Delete selected Student");


        label = new JLabel("You clicked him");
        layout = new GridBagLayout();

        studentTableModel = new StudentTableModel(studentClass);
        table = new JTable(studentTableModel);
        studentScrollPane = new JScrollPane(table);

        classTableModel = new ClassTableModel(classContainer);
        table = new JTable(classTableModel);
        classScrollPane = new JScrollPane(table);

        settings.fill = GridBagConstraints.BOTH;
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(layout);
        settings.gridx = 0;
        settings.gridy = 0;
        panel.add(studentScrollPane, settings);
        settings.gridx = 1;
        settings.gridy = 0;
        panel.add(classScrollPane, settings);

        settings.anchor = GridBagConstraints.PAGE_END;
        settings.weighty = 0.01;
        settings.weightx = 0.1;
        settings.gridwidth = 1;
        settings.gridx = 0;
        settings.gridy = 2;
        panel.add(newGroupButton, settings);

        settings.gridx = 1;
        settings.gridy = 2;
        panel.add(editGroupButton, settings);

        settings.gridx = 3;
        settings.gridy = 2;
        panel.add(sortButton, settings);

        settings.gridx = 4;
        settings.gridy = 2;
        panel.add(newStudentButton, settings);

        settings.gridx = 5;
        settings.gridy = 2;
        panel.add(editStudentButton, settings);

        settings.gridx = 6;
        settings.gridy = 2;
        panel.add(deleteStudentButton, settings);


        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Student Class Manager");
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
