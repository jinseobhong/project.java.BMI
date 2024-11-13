package net.datasa.school.contoroller;

import java.util.ArrayList;
import java.util.List;

import net.datasa.school.model.Student;

public class StudentContoroller {
    List<Student> students = new ArrayList<>();
    private Student student;
    private static int primaryKey = 100;

    public StudentContoroller() {
        this.students = new ArrayList<Student>();
    }

    // TODO : 학생 등록
    public void createStudent(Student student) {
        students.add(student);
    }

    // TODO : 학생 정보 조회(1명)
    public Student selectStudent(int id) {
        for (Student s : this.getStudents()) {
            if (id == s.getId()) {
                return s;
            }
        }
        return null;
    }

    // TODO : 학생 전체 조회
    public List<Student> selectAll() {
        return this.getStudents();
    }

    // TODO : 학생 정보 수정
    public void updateStudent(Integer id, String name, int kor, int eng, int math) {
        for (Student student : this.students) {
            if (student.getId() == id) {
                student.setName(name);
                student.setKor(kor);
                student.setEng(eng);
                student.setTotal(kor, eng, math);
                student.setAvg();
                this.students.set(student.getId(), student);
            }
        }
    }

    // TODO : 학생 전학
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
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public static int getPrimaryKey() {
        return ++primaryKey;
    }

    public static void setPrimaryKey(int primaryKey) {
        StudentContoroller.primaryKey = primaryKey;
    }
}











