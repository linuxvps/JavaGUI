package org.desktop.knn;


import com.formdev.flatlaf.FlatLightLaf; // Import FlatLaf classes
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class StudentApp extends JFrame {
    private static final String BASE_URL = "http://localhost:8080/api/students"; // Change this to your backend URL
    private final JTextField nameField;
    private final JTextField gradeField;
    private final JTextArea outputArea;

    public StudentApp() {
        // Set FlatLaf Look and Feel
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        setTitle("Student Management");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout()); // Use GridBagLayout for better alignment
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        nameField = new JTextField(20);
        gradeField = new JTextField(20);
        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);
        JButton addButton = new JButton("Add Student");
        JButton getButton = new JButton("Get Students");

        // Add components to the layout
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        add(nameField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Grade:"), gbc);
        gbc.gridx = 1;
        add(gradeField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(addButton, gbc);
        gbc.gridx = 1;
        add(getButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // Span both columns
        add(new JScrollPane(outputArea), gbc);

        // Add action listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                double grade = Double.parseDouble(gradeField.getText());
                try {
                    createStudent(name, grade);
                    JOptionPane.showMessageDialog(StudentApp.this, "Student added successfully!");
                    nameField.setText("");
                    gradeField.setText("");
                } catch (IOException ioException) {
                    JOptionPane.showMessageDialog(StudentApp.this, "Failed to add student: " + ioException.getMessage());
                }
            }
        });

        getButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String students = getAllStudents();
                    outputArea.setText(students);
                } catch (IOException ioException) {
                    JOptionPane.showMessageDialog(StudentApp.this, "Failed to retrieve students: " + ioException.getMessage());
                }
            }
        });

        setVisible(true);
    }

    public void createStudent(String name, double grade) throws IOException {
        // Implementation for creating a student
    }

    public String getAllStudents() throws IOException {
        return "Student List"; // Implementation for getting all students
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentApp::new);
    }
}
