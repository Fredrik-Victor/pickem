package project.pickem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import project.pickem.dto.Matchup;
import project.pickem.exception.BadRequestException;
import project.pickem.exception.EntityNotFoundException;
import project.pickem.service.MatchupService;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("matchup")
public class MatchupController {

    private final MatchupService matchupService;

    public MatchupController(MatchupService matchupService) {
        this.matchupService = matchupService;
    }

    @PostMapping("add")
    public ResponseEntity<Matchup> createMatchup(@Validated @RequestBody Matchup matchup, BindingResult errors) {
        if (errors.hasErrors())
            throw new BadRequestException("Invalid input", errors);

        Matchup createdMatchup = matchupService.createMatchup(matchup);

        return new ResponseEntity<>(createdMatchup, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Matchup> findMatchupById(@PathVariable Long id) {

        Matchup foundMatchup = matchupService.findMatchupById(id);

        return new ResponseEntity<>(foundMatchup, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Matchup>> findAllMatchups() {

        List<Matchup> matchups = matchupService.findAllMatchups();
        if(matchups.isEmpty())
            throw new EntityNotFoundException("Failed to find any roles");

        return new ResponseEntity<>(matchups, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Matchup> updateMatchup(@Validated @PathVariable Long id, @RequestBody Matchup matchup, BindingResult errors) {
        if (errors.hasErrors())
            throw new EntityNotFoundException(notFound(id));
        matchupService.updateMatchup(id, matchup);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteMatchup(@PathVariable Long id) {
        matchupService.deleteMatchup(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private String notFound(Long id) {
        return "Role with ID: " + id + " was not found.";
    }
}
