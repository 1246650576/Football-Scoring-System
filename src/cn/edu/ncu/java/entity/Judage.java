package cn.edu.ncu.java.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class Judage {
    private String teamName;
    private String numbers;
    private String redCard;
    private String yellowCard;
    private String turn;
    private String judgeTime;
    private boolean selected;
    public boolean getSelected() {
        return this.selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Judage(String teamName, String numbers, String redCard, String yellowCard, String turn, String judgeTime) {
        this.teamName = teamName;
        this.numbers = numbers;
        this.redCard = redCard;
        this.yellowCard = yellowCard;
        this.turn = turn;
        this.judgeTime = judgeTime;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public String getRedCard() {
        return redCard;
    }

    public void setRedCard(String redCard) {
        this.redCard = redCard;
    }

    public String getYellowCard() {
        return yellowCard;
    }

    public void setYellowCard(String yellowCard) {
        this.yellowCard = yellowCard;
    }

    public String getTurn() {
        return turn;
    }

    public void setTurn(String turn) {
        this.turn = turn;
    }

    public String getJudgeTime() {
        return judgeTime;
    }

    public void setJudgeTime(String judgeTime) {
        this.judgeTime = judgeTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Judage judage = (Judage) o;
        return redCard == judage.redCard &&
                yellowCard == judage.yellowCard &&
                turn == judage.turn &&
                Objects.equals(teamName, judage.teamName) &&
                Objects.equals(numbers, judage.numbers) &&
                Objects.equals(judgeTime, judage.judgeTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamName, numbers, redCard, yellowCard, turn, judgeTime);
    }

    @Override
    public String toString() {
        return "Judage{" +
                "队名='" + teamName + '\'' +
                ", 球号='" + numbers + '\'' +
                ", 红牌=" + redCard +
                ", 黄牌=" + yellowCard +
                ", 轮数=" + turn +
                ", 处罚时间=" + judgeTime +
                '}';
    }
}
