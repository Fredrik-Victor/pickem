package project.pickem.entity;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;

import javax.persistence.*;

@Entity
public class GuessEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private MatchupEntity matchupEntity;

    private String games;
    private String winner;

    public GuessEntity(){}

    public GuessEntity(String games, String winner) {
        this.games = games;
        this.winner = winner;
    }

    public Long getId() {
        return id;
    }

    public MatchupEntity getMatchupEntity() {
        return matchupEntity;
    }

    public void setMatchupEntity(MatchupEntity matchupEntity) {
        this.matchupEntity = matchupEntity;
    }

    public void setId(Long id) {
        this.id = id;
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
