package net.datasa.school.view;

import net.datasa.school.contoroller.StudentContoroller;
import net.datasa.school.model.Student;

import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentView {
    private Scanner sc;
    private StudentContoroller contoroller;

    public StudentView() {
        sc = new Scanner(System.in);
        contoroller = new StudentContoroller();
    }
    // View of Main
    public void viewMenu() {
        System.out.println("< 학생 성적 관리 >");
        System.out.println("1. 학생 등록");
        System.out.println("2. 학생 정보 조회(1명)");
        System.out.println("3. 학생 전체 조회");
        System.out.println("4. 학생 성적 수정");
        System.out.println("5. 학생 전학");
        System.out.println("0. 프로그램 종료");
        System.out.println("--------------------");
        System.out.print("선택 > ");
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
        this.contoroller.setStudent(new Student());
        System.out.println("< 학생 등록 >");
        System.out.print("이름 : ");
        this.contoroller.getStudent().setName(this.sc.next());
        try {
            System.out.print("국어 : ");
            this.contoroller.getStudent().setKor(this.sc.nextInt());
            System.out.print("영어 : ");
            this.contoroller.getStudent().setEng(this.sc.nextInt());
            System.out.print("수학 : ");
            this.contoroller.getStudent().setMath(this.sc.nextInt());
        } catch (InputMismatchException e) {
            System.err.println("점수에 숫자를 입력하세요");
            sc.nextLine();
            return;
        }
        this.contoroller.createStudent(new Student(StudentContoroller.getPrimaryKey(), this.contoroller.getStudent().getName(), this.contoroller.getStudent().getKor(), this.contoroller.getStudent().getEng(), this.contoroller.getStudent().getMath()));
        ;
        System.out.println("학생 등록 성공");
    }

    // View of information for one student
    private void viewSelectStudent() {
        this.contoroller.setStudent(new Student());
        System.out.println("< 학생 정보 조회(1명) >");
        try {
            System.out.print("검색 할 학생의 ID :");
            this.contoroller.getStudent().setId(sc.nextInt());
        } catch (InputMismatchException e) {
            System.err.println("ID에 숫자를 입력하세요");
            sc.nextLine();
            return;
        }
        if (this.contoroller.selectStudent(this.contoroller.getStudent().getId()) == null) {
            System.out.println("해당 ID의 회원이 존재 하지 않습니다");
            return;
        }
        System.out.println(this.contoroller.selectStudent(this.contoroller.getStudent().getId()));
    }

    // View of all student information
    private void viewSelectAll() {
        System.out.println("< 학생 전체 조회 >");
        if (contoroller.selectAll().isEmpty()) {
            System.out.println("학생이 존재하지 않습니다.");
            return;
        }

        for (Student s : contoroller.selectAll()) {
            System.out.println(s);
        }
    }

    // View of update student information
    private void viewUpdateStudent() {
        this.contoroller.setStudent(new Student());
        System.out.println("< 학생 성적 수정 >");
        try {
            System.out.print("> 수정할 학생의 ID:");
            this.contoroller.getStudent().setId(sc.nextInt());
        } catch (InputMismatchException e) {
            System.err.println("ID에 숫자를 입력하세요");
            sc.nextLine();
            return;
        }
        if (contoroller.selectStudent(this.contoroller.getStudent().getId()) == null) {
            System.out.println("해당 ID의 학생이 존재하지 않습니다");
            return;
        }
        System.out.println(contoroller.selectStudent(this.contoroller.getStudent().getId()));
        try {
            System.out.print("수정할 국어 :");
            this.contoroller.getStudent().setKor(this.sc.nextInt());
            System.out.print("수정할 영어 :");
            this.contoroller.getStudent().setEng(this.sc.nextInt());
            System.out.print("수정할 수학 :");
            this.contoroller.getStudent().setMath(this.sc.nextInt());
        } catch (InputMismatchException e) {
            System.err.println("점수에 숫자를 입력하세요");
            sc.nextLine();
            return;
        }
        System.out.println("회원 정보 수정 완료");
    }

    // View of student transfers
    private void viewDeleteStudent() {
        this.contoroller.setStudent(new Student());
        System.out.println("< 학생 전학 >");
        System.out.print("> 전학 시킬 회원의 ID : ");
        try {
            this.contoroller.getStudent().setId(sc.nextInt());
        } catch (InputMismatchException e) {
            System.err.println("ID에 숫자를 입력하세요");
            sc.nextLine();
            return;
        }
        // TODO : 탈퇴 시킬 회원을 찾기
        // TODO : 못 찾은 경우
        // TODO : 찾은 경우  :Y로 입력하면 탈퇴처리, 그외 탈퇴 처리 취소
        if (contoroller.selectStudent(this.contoroller.getStudent().getId()) == null) {
            System.out.println("학생 존재하지 않습니다");
            return;
        }
        System.out.println(contoroller.selectStudent(this.contoroller.getStudent().getId()));
        System.out.print("정말 전학 시킬까요?(Y/y): ");
        String answer = sc.next();
        if (answer.equals("Y") || answer.equals("y")) {
            contoroller.deleteStudent(contoroller.selectStudent(this.contoroller.getStudent().getId()));
            System.out.println("학생 전학 처리 완료");
        } else {
            System.out.println("전학 처리가 취소 되었습니다.");
        }
    }
    // Getters & Setters
    public Scanner getSc() {
        return sc;
    }

    public void setSc(Scanner sc) {
        this.sc = sc;
    }

    public StudentContoroller getContoroller() {
        return contoroller;
    }

    public void setContoroller(StudentContoroller contoroller) {
        this.contoroller = contoroller;
    }

}
