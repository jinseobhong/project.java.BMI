package net.datasa.school.model;

public class Student {
    // 멤버 변수(필드)
    private int id;
    private String name;
    private int total;
    private double avg;

    private enum Subjects {
        Korean, English, Math;

        private Integer score;

        public Integer getScore() {
            return score;
        }

        public void setScore(Integer score) {
            this.score = score;
        }
    }

    public Student() {
        super();
    }

    public Student(int id, String name, int kor, int eng, int math) {
        this.id = id;
        this.name = name;
        Subjects.Korean.setScore(kor);
        Subjects.English.setScore(eng);
        Subjects.Math.setScore(math);
        this.setTotal(kor, eng, math);
        this.setAvg();
    }

    // 3과목의 점수 합계
    public void setTotal(int kor, int eng, int math) {
        this.total = kor + eng + math;
    }

    // 3과목의 평균 계산
    public void setAvg() {
        this.avg = (double) this.getTotal() / Subjects.values().length;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter, Setter
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKor() {
        return Subjects.Korean.getScore();
    }

    public void setKor(int kor) {
        Subjects.Korean.setScore(kor);
    }

    public int getEng() {
        return Subjects.English.getScore();
    }

    public void setEng(int eng) {
        Subjects.English.setScore(eng);
    }

    public int getMath() {
        return Subjects.Math.getScore();
    }

    public void setMath(int math) {
        Subjects.Math.setScore(math);
    }

    public int getTotal() {
        return total;
    }

    public double getAvg() {
        return avg;
    }

    //toString() 재정의
    @Override
    public String toString() {
        return String.format("학번 : %d 이름 : %s 국어 : %d 영어 : %d 총점: %d 평균 : %.2f", this.getId(), this.getName(), this.getKor(), this.getEng(), this.getTotal(), this.getAvg());
    }
}

















