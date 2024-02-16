package tools;

public class Acces {
    int id;
    String surName;
    String user;
    String pw;
    String status;
    int age;

    public Acces(int id, String surName, String user, String pw, String status, int age) {
        this.id = id;
        this.surName = surName;
        this.user = user;
        this.pw = pw;
        this.status = status;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getSurName() {
        return surName;
    }

    public String getUser() {
        return user;
    }

    public String getPw() {
        return pw;
    }

    public String getStatus() {
        return status;
    }

    public int getAge() {
        return age;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAge(int age) {
        this.age = age;
    }

}