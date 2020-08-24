package cn.edu.ncu.java.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Player {
    private String name;
    private String age;
    private String team;
    private String gender;
    private String position;
    private String number;
    private String height;
    private String weight;
    private String  birthdate;
    private boolean selected;

    public Player() { }

    public Player(String name, String age, String team, String gender, String position,
                  String number, String height, String weight, String birthdate) {
        this.name = name;
        this.age = age;
        this.team = team;
        this.gender = gender;
        this.position = position;
        this.number = number;
        this.height = height;
        this.weight = weight;
        this.birthdate = birthdate;
    }

    public boolean getSelected() {
        return this.selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return team.equals(player.team) &&
                number.equals(player.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(team, number);
    }

    @Override
    public String toString() {
        return "Player{" +
                "姓名='" + name + '\'' +
                ", 年龄=" + age +
                ", 所属球队='" + team + '\'' +
                ", 性别='" + gender + '\'' +
                ", 位置='" + position + '\'' +
                ", 编号='" + number + '\'' +
                ", 身高='" + height + '\'' +
                ", 体重='" + weight + '\'' +
                ", 出生日期=" + birthdate +
                '}';
    }
}
