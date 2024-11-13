package net.datasa.school.main;

import net.datasa.school.view.StudentView;

public class App {

    public static void main(String[] args) {
        StudentView view = new StudentView();
        try {
            while (true) {
                view.viewMenu();
                view.selectMenu(view.getScanner().next());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}