package cn.edu.ncu.java.entity;

import java.util.Objects;

public class ScoreOfTeam {
    private String ranking;
    private String changes;
    private String teamname;
    private String matchs;
    private String win;
    private String even;
    private String beaten;
    private String goal;
    private String lost;
    private String net;
    private String avergoal;
    private String averlost;
    private String avernet;
    private String averpoint;

    public ScoreOfTeam(String ranking, String changes, String teamname, String matchs,
                       String win, String even, String beaten, String goal, String lost,
                       String net, String avergoal, String averlost, String avernet, String averpoint) {
        this.ranking = ranking;
        this.changes = changes;
        this.teamname = teamname;
        this.matchs = matchs;
        this.win = win;
        this.even = even;
        this.beaten = beaten;
        this.goal = goal;
        this.lost = lost;
        this.net = net;
        this.avergoal = avergoal;
        this.averlost = averlost;
        this.avernet = avernet;
        this.averpoint = averpoint;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public String getChanges() {
        return changes;
    }

    public void setChanges(String changes) {
        this.changes = changes;
    }

    public String getTeamname() {
        return teamname;
    }

    public void setTeamname(String teamname) {
        this.teamname = teamname;
    }

    public String getMatchs() {
        return matchs;
    }

    public void setMatchs(String matchs) {
        this.matchs = matchs;
    }

    public String getWin() {
        return win;
    }

    public void setWin(String win) {
        this.win = win;
    }

    public String getEven() {
        return even;
    }

    public void setEven(String even) {
        this.even = even;
    }

    public String getBeaten() {
        return beaten;
    }

    public void setBeaten(String beaten) {
        this.beaten = beaten;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getLost() {
        return lost;
    }

    public void setLost(String lost) {
        this.lost = lost;
    }

    public String getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = net;
    }

    public String getAvergoal() {
        return avergoal;
    }

    public void setAvergoal(String avergoal) {
        this.avergoal = avergoal;
    }

    public String getAverlost() {
        return averlost;
    }

    public void setAverlost(String averlost) {
        this.averlost = averlost;
    }

    public String getAvernet() {
        return avernet;
    }

    public void setAvernet(String avernet) {
        this.avernet = avernet;
    }

    public String getAverpoint() {
        return averpoint;
    }

    public void setAverpoint(String averpoint) {
        this.averpoint = averpoint;
    }

    @Override
    public String toString() {
        return "ScoreOfTeam{" +
                "排名=" + ranking +
                ", 升降=" + changes +
                ", 队名='" + teamname + '\'' +
                ", 场数=" + matchs +
                ", 赢=" + win +
                ", 平=" + even +
                ", 负=" + beaten +
                ", 进球数=" + goal +
                ", 失球数=" + lost +
                ", 净球数=" + net +
                ", 场均进球=" + avergoal +
                ", 场均失球=" + averlost +
                ", 场均净球=" + avernet +
                ", 场均积分=" + averpoint +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScoreOfTeam that = (ScoreOfTeam) o;
        return Objects.equals(ranking, that.ranking) &&
                Objects.equals(changes, that.changes) &&
                Objects.equals(teamname, that.teamname) &&
                Objects.equals(matchs, that.matchs) &&
                Objects.equals(win, that.win) &&
                Objects.equals(even, that.even) &&
                Objects.equals(beaten, that.beaten) &&
                Objects.equals(goal, that.goal) &&
                Objects.equals(lost, that.lost) &&
                Objects.equals(net, that.net) &&
                Objects.equals(avergoal, that.avergoal) &&
                Objects.equals(averlost, that.averlost) &&
                Objects.equals(avernet, that.avernet) &&
                Objects.equals(averpoint, that.averpoint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ranking, changes, teamname, matchs, win, even, beaten, goal, lost, net, avergoal, averlost, avernet, averpoint);
    }
}
