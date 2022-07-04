package project.pickem.controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import project.pickem.dto.Week;
import project.pickem.exception.BadRequestException;
import project.pickem.exception.EntityNotFoundException;
import project.pickem.service.WeekService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("weeks")
public class WeekController {

    private final WeekService weekService;

    public WeekController(WeekService weekService) {
        this.weekService = weekService;
    }

    @PostMapping("signup")
    public ResponseEntity<Week> createWeek(@Validated @RequestBody Week week, BindingResult errors) {

        if (errors.hasErrors())
            throw new BadRequestException("Invalid input", errors);
        Week createdWeek = weekService.createWeek(week);

        return new ResponseEntity<>(createdWeek, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Week> updateWeek(@PathVariable Long id, @Validated @RequestBody Week week, BindingResult errors) {

        if (errors.hasErrors())
            throw new EntityNotFoundException(notFound(id));

        weekService.updateUser(id, week);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Week> findUserById(@PathVariable Long id) {

        Week week = weekService.findWeekById(id);

        return new ResponseEntity<>(week, HttpStatus.OK);
    }


    @GetMapping("")
    public ResponseEntity<List<Week>> findAllWeeks() {
        List<Week> weeks = weekService.findAllWeeks();

        if(weeks.isEmpty())
            throw new EntityNotFoundException("Failed to find any users");

        return new ResponseEntity<>(weeks, HttpStatus.OK);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteWeek(@PathVariable Long id) {

        weekService.deleteWeek(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private String notFound(Long id) {
        return "User with ID: " + id + " was not found.";
    }

}
