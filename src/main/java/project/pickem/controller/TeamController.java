package project.pickem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import project.pickem.dto.Team;
import project.pickem.exception.EntityNotFoundException;
import project.pickem.service.TeamService;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("teams")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping("add")
    public ResponseEntity<Team> createTeam(@Validated @RequestBody Team team, BindingResult errors) {
//        if (errors.hasErrors())
//            throw new BadRequestException("Invalid input", errors);

        Team createdTeam = teamService.createTeam(team);

        return new ResponseEntity<>(createdTeam, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Team> findTeamById(@PathVariable Long id) {

        Team foundTeam = teamService.findTeamById(id);

        return new ResponseEntity<>(foundTeam, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Team>> findAllTeams() {

        List<Team> teams = teamService.findAllTeams();
        if(teams.isEmpty())
            throw new EntityNotFoundException("Failed to find any roles");

        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Team> updateTeam(@Validated @PathVariable Long id, @RequestBody Team team, BindingResult errors) {
        if (errors.hasErrors())
            throw new EntityNotFoundException(notFound(id));
        teamService.updateTeam(id, team);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private String notFound(Long id) {
        return "Role with ID: " + id + " was not found.";
    }
}
