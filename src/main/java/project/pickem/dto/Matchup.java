package project.pickem.dto;

import project.pickem.entity.WeekEntity;

public class Matchup {

    private Long id;

    private String matchup;
    private WeekEntity weekNumber;
    private String winner;
    private String games;

    public Matchup(String matchup, WeekEntity weekNumber, String winner, String games) {
        this.matchup = matchup;
        this.weekNumber = weekNumber;
        this.winner = winner;
        this.games = games;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatchup() {
        return matchup;
    }

    public void setMatchup(String matchup) {
        this.matchup = matchup;
    }

    public WeekEntity getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(WeekEntity weekNumber) {
        this.weekNumber = weekNumber;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getGames() {
        return games;
    }

    public void setGames(String games) {
        this.games = games;
    }
}
