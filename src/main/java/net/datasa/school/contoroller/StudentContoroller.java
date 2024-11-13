package net.datasa.school.contoroller;

import java.util.ArrayList;
import java.util.List;

import net.datasa.school.model.Student;

public class StudentContoroller {
    List<Student> students = new ArrayList<>();
    private Student student;
    private static Integer primaryKey = 100;

    public StudentContoroller() {
        this.students = new ArrayList<Student>();
    }

    // Create Student
    public void createStudent(Student student) {
        this.getStudents().add(student);
    }

    // Select one Student
    public Student selectStudent(Integer id) {
        for (Student s : this.getStudents()) {
            if (id == s.getId()) {
                return s;
            }
        }
        return null;
    }

    // Select all Students
    public List<Student> selectAll() {
        return this.getStudents();
    }

    // Update Student
    public void updateStudent(Integer id, String name, Integer[] scores) {
        for (Student student : this.getStudents()) {
            if (student.getId() == id) {
                student.setName(name);
                student.setScores(scores);
                student.setTotal(student.getScores());
                student.setAvg();
                this.getStudents().set(student.getId(), student);
            }
        }
    }

    // Delete Student
    public void deleteStudent(Student student) {
        this.getStudents().remove(student);
    }

    // Getters & Setters
    public List<Student> getStudents() {
        return this.students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Integer getPrimaryKey() {
        return ++primaryKey;
    }

    public void setPrimaryKey(int primaryKey) {
        StudentContoroller.primaryKey = primaryKey;
    }
}











