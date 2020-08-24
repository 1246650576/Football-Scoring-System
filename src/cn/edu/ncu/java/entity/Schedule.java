package cn.edu.ncu.java.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Schedule {
    private String Time;
    private String HomeTeam;
    private String visitingTeam;
    private String HomeScore;
    private String VisitingScore;
    private String turnone;
    private String turntwo;

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getHomeTeam() {
        return HomeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        HomeTeam = homeTeam;
    }

    public String getVisitingTeam() {
        return visitingTeam;
    }

    public void setVisitingTeam(String visitingTeam) {
        this.visitingTeam = visitingTeam;
    }

    public String getHomeScore() {
        return HomeScore;
    }

    public void setHomeScore(String homeScore) {
        HomeScore = homeScore;
    }

    public String getVisitingScore() {
        return VisitingScore;
    }

    public void setVisitingScore(String visitingScore) {
        VisitingScore = visitingScore;
    }

    public String getTurnone() {
        return turnone;
    }

    public void setTurnone(String turnone) {
        this.turnone = turnone;
    }

    public String getTurntwo() {
        return turntwo;
    }

    public void setTurntwo(String turntwo) {
        this.turntwo = turntwo;
    }

    public Schedule(String time, String homeTeam, String visitingTeam, String homeScore, String visitingScore, String turnone, String turntwo) {
        this.Time = time;
        this.HomeTeam = homeTeam;
        this.visitingTeam = visitingTeam;
        this.HomeScore = homeScore;
        this.VisitingScore = visitingScore;
        this.turnone = turnone;
        this.turntwo = turntwo;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "比赛时间=" + Time +
                ", 主队='" + HomeTeam + '\'' +
                ", 客队='" + visitingTeam + '\'' +
                ", 主队进球数='" + HomeScore + '\'' +
                ", 客队进球数='" + VisitingScore + '\'' +
                ", 轮数=" + turnone +
                ", 组别='" + turntwo + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return turnone == schedule.turnone &&
                Objects.equals(Time, schedule.Time) &&
                Objects.equals(HomeTeam, schedule.HomeTeam) &&
                Objects.equals(visitingTeam, schedule.visitingTeam) &&
                Objects.equals(HomeScore, schedule.HomeScore) &&
                Objects.equals(VisitingScore, schedule.VisitingScore) &&
                Objects.equals(turntwo, schedule.turntwo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Time, HomeTeam, visitingTeam, HomeScore, VisitingScore, turnone, turntwo);
    }
}
