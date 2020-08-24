package cn.edu.ncu.java.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Team {
    private String name;
    private String coach;
    private String college;
    private String setTime;
    private boolean selected;
    public Team() {
    }
    public boolean getSelected() {
        return this.selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Team(String name, String coach, String college, String setTime) {
        this.name = name;
        this.coach = coach;
        this.college = college;
        this.setTime = setTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getSetTime() {
        return setTime;
    }

    public void setSetTime(String setTime) {
        this.setTime = setTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return name.equals(team.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Team{" +
                "队名='" + name + '\'' +
                ", 教练='" + coach + '\'' +
                ", 所属大学或学院='" + college + '\'' +
                ", 成立时间=" + setTime +
                '}';
    }

}
