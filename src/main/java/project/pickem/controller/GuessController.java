package project.pickem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import project.pickem.dto.Guess;
import project.pickem.dto.Matchup;
import project.pickem.exception.BadRequestException;
import project.pickem.exception.EntityNotFoundException;
import project.pickem.service.GuessService;
import project.pickem.service.MatchupService;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("guess")
public class GuessController {

    private final GuessService guessService;

    public GuessController(GuessService guessService) {
        this.guessService = guessService;
    }

    @PostMapping("add")
    public ResponseEntity<Guess> createGuess(@Validated @RequestBody Guess guess, BindingResult errors) {
        if (errors.hasErrors())
            throw new BadRequestException("Invalid input", errors);

        Guess createdGuess = guessService.createGuess(guess);

        return new ResponseEntity<>(createdGuess, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Guess> findGuessById(@PathVariable Long id) {

        Guess foundGuess = guessService.findGuessById(id);

        return new ResponseEntity<>(foundGuess, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Guess>> findAllGuesses() {

        List<Guess> guesses = guessService.findAllGuesses();
        if(guesses.isEmpty())
            throw new EntityNotFoundException("Failed to find any roles");

        return new ResponseEntity<>(guesses, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Guess> updateGuess(@Validated @PathVariable Long id, @RequestBody Guess guess, BindingResult errors) {
        if (errors.hasErrors())
            throw new EntityNotFoundException(notFound(id));
        guessService.updateGuess(id, guess);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteGuess(@PathVariable Long id) {
        guessService.deleteGuess(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private String notFound(Long id) {
        return "Role with ID: " + id + " was not found.";
    }
}
