package com.bigdata.demo.bean;

public class Student_score {

    //[学号,姓名,班级,课程名称,分数]
    private String id;
    private String name;
    private String clazz;
    private String cname;
    private int score;

    @Override
    public String toString() {
        return "Student_score{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", clazz='" + clazz + '\'' +
                ", cname='" + cname + '\'' +
                ", score=" + score +
                '}';
    }

    public Student_score() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Student_score(String id, String name, String clazz, String cname, int score) {

        this.id = id;
        this.name = name;
        this.clazz = clazz;
        this.cname = cname;
        this.score = score;
    }
}
