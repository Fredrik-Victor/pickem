package project.pickem.service;

import org.modelmapper.ModelMapper;
import project.pickem.dto.Matchup;
import project.pickem.entity.MatchupEntity;
import org.springframework.stereotype.Service;
import project.pickem.repository.MatchupRepository;
import project.pickem.exception.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MatchupService {

    private final MatchupRepository matchupRepository;
    private final ModelMapper modelMapper;
    private static final String NOUSERID = "No user with id ";
    private static final String WASFOUND = " was found";

    public MatchupService(MatchupRepository matchupRepository,
                       ModelMapper modelMapper) {
        this.matchupRepository = matchupRepository;
        this.modelMapper = modelMapper;
    }

    public Matchup createMatchup(Matchup matchup) {

        MatchupEntity matchupEntity = modelMapper.map(matchup, MatchupEntity.class);

        return modelMapper.map(matchupRepository.save(matchupEntity), Matchup.class);
    }

    public void updateMatchup(Long id, Matchup matchup) {

        Optional<MatchupEntity> foundMatchup = matchupRepository.findById(id);
        if (foundMatchup.isEmpty())
            throw new EntityNotFoundException(NOUSERID + id + WASFOUND);

        MatchupEntity matchupEntity = modelMapper.map(matchup, MatchupEntity.class);

        matchupEntity.setId(id);
        matchupEntity.setMatchup(matchupEntity.getMatchup());

        matchupRepository.save(matchupEntity);
    }

    public Matchup findMatchupById(Long id) {

        Optional<MatchupEntity> foundMatchup = matchupRepository.findById(id);
        if (foundMatchup.isEmpty())
            throw new EntityNotFoundException(NOUSERID + id + WASFOUND);

        return modelMapper.map(foundMatchup, Matchup.class);
    }

    public List<Matchup> findAllMatchups() {
        Iterable<MatchupEntity> allMatchupEntities = matchupRepository.findAll();

        List<Matchup> allMatchups = new ArrayList<>();
        allMatchupEntities.forEach(matchup -> allMatchups.add(modelMapper.map(matchup, Matchup.class)));
        return allMatchups;
    }

    public void deleteMatchup(Long id) {

        Optional<MatchupEntity> foundMatchup = matchupRepository.findById(id);
        if (foundMatchup.isEmpty())
            throw new EntityNotFoundException(NOUSERID + id + WASFOUND);

        matchupRepository.deleteById(id);
    }
}
