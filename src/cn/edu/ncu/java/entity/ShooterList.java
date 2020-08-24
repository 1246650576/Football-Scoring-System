package cn.edu.ncu.java.entity;

import java.util.Objects;

public class ShooterList {
    private String ranking;
    private String name;
    private String TeamName;
    private String numbers;
    private String goals;
    private String RedCard;
    private String YelllowCard;

    public ShooterList(String ranking, String name, String teamName, String numbers, String goals, String redCard, String yelllowCard) {
        this.ranking = ranking;
        this.name = name;
        TeamName = teamName;
        this.numbers = numbers;
        this.goals = goals;
        RedCard = redCard;
        YelllowCard = yelllowCard;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeamName() {
        return TeamName;
    }

    public void setTeamName(String teamName) {
        TeamName = teamName;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    public String getRedCard() {
        return RedCard;
    }

    public void setRedCard(String redCard) {
        RedCard = redCard;
    }

    public String getYelllowCard() {
        return YelllowCard;
    }

    public void setYelllowCard(String yelllowCard) {
        YelllowCard = yelllowCard;
    }

    @Override
    public String toString() {
        return "ShooterList{" +
                "排名=" + ranking +
                ", 姓名='" + name + '\'' +
                ", 队名='" + TeamName + '\'' +
                ", 编号='" + numbers + '\'' +
                ", 进球数=" + goals +
                ", 红牌=" + RedCard +
                ", 黄牌=" + YelllowCard +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShooterList that = (ShooterList) o;
        return ranking == that.ranking &&
                goals == that.goals &&
                RedCard == that.RedCard &&
                YelllowCard == that.YelllowCard &&
                Objects.equals(name, that.name) &&
                Objects.equals(TeamName, that.TeamName) &&
                Objects.equals(numbers, that.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ranking, name, TeamName, numbers, goals, RedCard, YelllowCard);
    }
}
