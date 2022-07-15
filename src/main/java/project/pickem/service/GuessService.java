package project.pickem.service;

import org.modelmapper.ModelMapper;
import project.pickem.dto.Guess;
import project.pickem.dto.Matchup;
import project.pickem.entity.GuessEntity;
import project.pickem.entity.MatchupEntity;
import org.springframework.stereotype.Service;
import project.pickem.repository.GuessRepository;
import project.pickem.repository.MatchupRepository;
import project.pickem.exception.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GuessService {

    private final GuessRepository guessRepository;
    private final ModelMapper modelMapper;
    private static final String NOUSERID = "No user with id ";
    private static final String WASFOUND = " was found";

    public GuessService(GuessRepository guessRepository,
                          ModelMapper modelMapper) {
        this.guessRepository = guessRepository;
        this.modelMapper = modelMapper;
    }

    public Guess createGuess(Guess guess) {

        GuessEntity guessEntity = modelMapper.map(guess, GuessEntity.class);

        return modelMapper.map(guessRepository.save(guessEntity), Guess.class);
    }

    public void updateGuess(Long id, Guess guess) {

        Optional<GuessEntity> foundGuess = guessRepository.findById(id);
        if (foundGuess.isEmpty())
            throw new EntityNotFoundException(NOUSERID + id + WASFOUND);

        GuessEntity guessEntity = modelMapper.map(guess, GuessEntity.class);

        guessEntity.setId(id);
        guessEntity.setId(guessEntity.getId());

        guessRepository.save(guessEntity);
    }

    public Guess findGuessById(Long id) {

        Optional<GuessEntity> foundGuess = guessRepository.findById(id);
        if (foundGuess.isEmpty())
            throw new EntityNotFoundException(NOUSERID + id + WASFOUND);

        return modelMapper.map(foundGuess, Guess.class);
    }

    public List<Guess> findAllGuesses() {
        Iterable<GuessEntity> allGuessEntities = guessRepository.findAll();

        List<Guess> allGuesses = new ArrayList<>();
        allGuessEntities.forEach(guess -> allGuesses.add(modelMapper.map(guess, Guess.class)));
        return allGuesses;
    }

    public void deleteGuess(Long id) {

        Optional<GuessEntity> foundGuess = guessRepository.findById(id);
        if (foundGuess.isEmpty())
            throw new EntityNotFoundException(NOUSERID + id + WASFOUND);

        guessRepository.deleteById(id);
    }
}
