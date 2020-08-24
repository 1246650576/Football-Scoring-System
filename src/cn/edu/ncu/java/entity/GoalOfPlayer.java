package cn.edu.ncu.java.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class GoalOfPlayer {//
    private String TeamName;
    private String numbers;
    private String turn;
    private String GoalTime;
    private boolean selected;
    public boolean getSelected() {
        return this.selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public GoalOfPlayer(String teamName, String numbers, String turn, String goalTime) {
        TeamName = teamName;
        this.numbers = numbers;
        this.turn = turn;
        GoalTime = goalTime;
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

    public String getTurn() {
        return turn;
    }

    public void setTurn(String turn) {
        this.turn = turn;
    }

    public String getGoalTime() {
        return GoalTime;
    }

    public void setGoalTime(String goalTime) {
        GoalTime = goalTime;
    }

    @Override
    public String toString() {
        return "GoalOfPlayer{" +
                "队名='" + TeamName + '\'' +
                ", 编号='" + numbers + '\'' +
                ", 轮数=" + turn +
                ", 进球时间=" + GoalTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoalOfPlayer that = (GoalOfPlayer) o;
        return turn == that.turn &&
                Objects.equals(TeamName, that.TeamName) &&
                Objects.equals(numbers, that.numbers) &&
                Objects.equals(GoalTime, that.GoalTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(TeamName, numbers, turn, GoalTime);
    }
}
