package project.pickem.service;

import org.modelmapper.ModelMapper;
import project.pickem.dto.Team;
import project.pickem.entity.TeamEntity;
import org.springframework.stereotype.Service;
import project.pickem.repository.TeamRepository;
import project.pickem.exception.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final ModelMapper modelMapper;
    private static final String NOUSERID = "No user with id ";
    private static final String WASFOUND = " was found";

    public TeamService(TeamRepository teamRepository,
                       ModelMapper modelMapper) {
        this.teamRepository = teamRepository;
        this.modelMapper = modelMapper;
    }

    public Team createTeam(Team team) {

        TeamEntity teamEntity = modelMapper.map(team, TeamEntity.class);

        return modelMapper.map(teamRepository.save(teamEntity), Team.class);
    }

    public void updateTeam(Long id, Team team) {

        Optional<TeamEntity> foundUser = teamRepository.findById(id);
        if (foundUser.isEmpty())
            throw new EntityNotFoundException(NOUSERID + id + WASFOUND);

        TeamEntity teamEntity = modelMapper.map(team, TeamEntity.class);

        teamEntity.setId(id);
        teamEntity.setName(teamEntity.getName());

        teamRepository.save(teamEntity);
    }

    public Team findTeamById(Long id) {

        Optional<TeamEntity> foundTeam = teamRepository.findById(id);
        if (foundTeam.isEmpty())
            throw new EntityNotFoundException(NOUSERID + id + WASFOUND);

        return modelMapper.map(foundTeam, Team.class);
    }

    public List<Team> findAllTeams() {
        Iterable<TeamEntity> allTeamEntities = teamRepository.findAll();

        List<Team> allUsers = new ArrayList<>();
        allTeamEntities.forEach(team -> allUsers.add(modelMapper.map(team, Team.class)));
        return allUsers;
    }

    public void deleteTeam(Long id) {

        Optional<TeamEntity> foundTeam = teamRepository.findById(id);
        if (foundTeam.isEmpty())
            throw new EntityNotFoundException(NOUSERID + id + WASFOUND);

        teamRepository.deleteById(id);
    }
}
