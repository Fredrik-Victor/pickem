package project.pickem.dto;

import project.pickem.entity.MatchupEntity;

public class Guess {

    private Long id;

    private MatchupEntity matchupEntity;
    private String games;
    private String winner;

    public Guess(MatchupEntity matchupEntity, String games, String winner) {
        this.matchupEntity = matchupEntity;
        this.games = games;
        this.winner = winner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MatchupEntity getMatchupEntity() {
        return matchupEntity;
    }

    public void setMatchupEntity(MatchupEntity matchupEntity) {
        this.matchupEntity = matchupEntity;
    }

    public String getGames() {
        return games;
    }

    public void setGames(String games) {
        this.games = games;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}
