package net.datasa.school.model;

import java.util.ArrayList;

public class Student {
    // Field member
    private Integer id;
    private String name;
    private Integer total;
    private Double avg;

    public enum Subject {
        Korean, English, Math;

        private Integer score;

        public Integer getScore() {
            return this.score;
        }

        public void setScore(Integer score) {
            this.score = score;
        }
    }

    public Student() {
        this.total = 0;
    }

    public Student(Integer id) {
        this.id = id;
        this.total = 0;
    }

    public Student(Integer id, String name, Integer[] scores) {
        this.id = id;
        this.name = name;
        this.setScores(scores);
        this.setTotal(this.getScores());
        this.setAvg();
    }

    public void setScores(Integer[] scores) {
        for (Subject s : Student.Subject.values()) {
            Student.Subject.valueOf(s.name()).setScore(scores[s.ordinal()]);
        }
    }

    // Total of all subjects
    public void setTotal(Integer[] scores) {
        for (Subject s : Student.Subject.values()) {
            this.total = this.total + Student.Subject.valueOf(s.name()).getScore();
        }
    }

    // Calculate the average of all subjects
    public void setAvg() {
        this.avg = (double) this.getTotal() / Subject.values().length;
    }

    // Getter, Setter
    public void setId(int id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScore(String subject) {
        return Subject.valueOf(subject).getScore();
    }

    public void setScore(String subject, Integer score) {
        Subject.valueOf(subject).setScore(score);
    }

    public Integer[] getScores() {
        ArrayList<Integer> scores = new ArrayList<>();
        for (Subject s : Student.Subject.values()) {
            scores.add(Subject.valueOf(s.name()).getScore());
        }
        return scores.toArray(new Integer[Subject.values().length]);
    }

    public Integer getTotal() {
        return this.total;
    }

    public Double getAvg() {
        return this.avg;
    }

    // Redefinition of Object.toString()
    @Override
    public String toString() {
        return String.format("학번 : %d 이름 : %s 국어 : %d 영어 : %d 수학 : %d 총점: %d 평균 : %.2f", this.getId(), this.getName(), this.getScore("Korean"), this.getScore("English"), this.getScore("Math"), this.getTotal(), this.getAvg());
    }
}
