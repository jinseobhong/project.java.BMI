package net.datasa.school.view;

import net.datasa.school.contoroller.StudentContoroller;
import net.datasa.school.model.Student;

import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentView {
    private Scanner scanner;
    private StudentContoroller controller;

    public StudentView() {
        this.scanner = new Scanner(System.in);
        this.controller = new StudentContoroller();
    }

    // View of Main
    public void viewMenu() {
        System.out.println("1. 학생 등록\n" +
                "2. 학생 정보 조회(1명)\n" +
                "3. 학생 전체 조회\n" +
                "4. 학생 성적 수정\n" +
                "5. 학생 전학\n" +
                "0. 종료");
        System.out.print("선택 : ");
    }

    // Selection of menu
    public void selectMenu(String selectedMenu) {
        switch (selectedMenu) {
            case "1":
                viewCreateStudent();
                break;
            case "2":
                viewSelectStudent();
                break;
            case "3":
                viewSelectAll();
                break;
            case "4":
                viewUpdateStudent();
                break;
            case "5":
                viewDeleteStudent();
                break;
            case "0":
                System.out.println("프로그램 종료");
                System.exit(0);
            default:
                System.out.println("메뉴에 있는 번호를 고르세요.");
        }
    }

    // View of the student registration menu
    private void viewCreateStudent() {
        this.getController().setStudent(new Student(this.getController().getPrimaryKey()));
        System.out.println("학생 등록");
        System.out.print("이름 : ");
        this.getController().getStudent().setName(this.getScanner().next());
        try {
            System.out.print("국어 : ");
            this.getController().getStudent().setScore("Korean", this.getScanner().nextInt());
            System.out.print("영어 : ");
            this.getController().getStudent().setScore("English", this.getScanner().nextInt());
            System.out.print("수학 : ");
            this.getController().getStudent().setScore("Math", this.getScanner().nextInt());
            this.getController().getStudent().setTotal(this.getController().getStudent().getScores());
            this.getController().getStudent().setAvg();
        } catch (InputMismatchException e) {
            System.err.println("점수에 숫자를 입력하세요");
            this.getScanner().nextLine();
            return;
        }
        this.getController().createStudent(this.getController().getStudent());
        System.out.println(this.getController().getStudent().toString());
        System.out.println("학생 등록 성공");
    }

    // View of information for one student
    private void viewSelectStudent() {
        this.getController().setStudent(new Student());
        System.out.println("학생 정보 조회(1명)");
        try {
            System.out.print("검색 할 학생의 ID :");
            this.getController().getStudent().setId(this.getScanner().nextInt());
            System.out.println(this.getController().getStudent().getId());
        } catch (InputMismatchException e) {
            System.err.println("ID에 숫자를 입력하세요");
            this.getScanner().nextLine();
            return;
        }
        if (this.getController().selectStudent(this.getController().getStudent().getId()) == null) {
            System.out.println("해당 ID의 회원이 존재 하지 않습니다");
            return;
        }
        System.out.println(this.getController().selectStudent(this.getController().getStudent().getId()).toString());
    }

    // View of all student information
    private void viewSelectAll() {
        System.out.println("학생 전체 조회");
        if (this.getController().selectAll().isEmpty()) {
            System.out.println("학생이 존재하지 않습니다.");
            return;
        }

        for (Student s : this.getController().selectAll()) {
            System.out.println(s);
        }
    }

    // View of update student information
    private void viewUpdateStudent() {
        this.getController().setStudent(new Student());
        System.out.println("학생 성적 수정");
        try {
            System.out.print("> 수정할 학생의 ID:");
            this.getController().getStudent().setId(this.getScanner().nextInt());
        } catch (InputMismatchException e) {
            System.err.println("ID에 숫자를 입력하세요");
            this.getScanner().nextLine();
            return;
        }
        if (this.getController().selectStudent(this.getController().getStudent().getId()) == null) {
            System.out.println("해당 ID의 학생이 존재하지 않습니다");
            return;
        }
        System.out.println(this.getController().selectStudent(this.getController().getStudent().getId()));
        try {
            System.out.print("수정할 국어 : ");
            this.getController().getStudent().setScore("Korean", this.getScanner().nextInt());
            System.out.print("수정할 영어 : ");
            this.getController().getStudent().setScore("English", this.getScanner().nextInt());
            System.out.print("수정할 수학 : ");
            this.getController().getStudent().setScore("Math", this.getScanner().nextInt());
            this.getController().getStudent().setTotal(this.getController().getStudent().getScores());
            this.getController().getStudent().setAvg();
        } catch (InputMismatchException e) {
            System.err.println("점수에 숫자를 입력하세요");
            this.getScanner().nextLine();
            return;
        }
        System.out.println("회원 정보 수정 완료");
    }

    // View of student transfers
    private void viewDeleteStudent() {
        this.getController().setStudent(new Student());
        System.out.println("학생 전학");
        System.out.print("> 전학 시킬 회원의 ID : ");
        try {
            this.getController().getStudent().setId(this.getScanner().nextInt());
        } catch (InputMismatchException e) {
            System.err.println("ID에 숫자를 입력하세요");
            this.getScanner().nextLine();
            return;
        }
        if (this.getController().selectStudent(this.getController().getStudent().getId()) == null) {
            System.out.println("학생 존재하지 않습니다");
            return;
        }
        System.out.println(this.getController().selectStudent(this.getController().getStudent().getId()));
        System.out.print("정말 전학 시킬까요?(Y/y): ");
        String answer = this.getScanner().next();
        if (answer.equals("Y") || answer.equals("y")) {
            this.getController().deleteStudent(this.getController().selectStudent(this.getController().getStudent().getId()));
            System.out.println("학생 전학 처리 완료");
        } else {
            System.out.println("전학 처리가 취소 되었습니다.");
        }
    }

    // Getters & Setters
    public Scanner getScanner() {
        return this.scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public StudentContoroller getController() {
        return this.controller;
    }

    public void setController(StudentContoroller controller) {
        this.controller = controller;
    }

}
