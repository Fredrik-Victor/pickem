package project.pickem.service;

import org.modelmapper.ModelMapper;
import project.pickem.dto.Week;
import org.springframework.stereotype.Service;
import project.pickem.entity.WeekEntity;
import project.pickem.exception.EntityNotFoundException;
import project.pickem.repository.WeekRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WeekService {

    private final WeekRepository weekRepository;
    private final ModelMapper modelMapper;
    private static final String NOUSERID = "No user with id ";
    private static final String WASFOUND = " was found";

    public WeekService(WeekRepository weekRepository,
                       ModelMapper modelMapper) {

        this.weekRepository = weekRepository;
        this.modelMapper = modelMapper;
    }

    public Week createWeek(Week week) {

        WeekEntity weekEntity = modelMapper.map(week, WeekEntity.class);

        return modelMapper.map(weekRepository.save(weekEntity), Week.class);
    }

    public void updateUser(Long id, Week week) {

        Optional<WeekEntity> foundWeek = weekRepository.findById(id);
        if (foundWeek.isEmpty())
            throw new EntityNotFoundException(NOUSERID+ id + WASFOUND);

        WeekEntity weekEntity = modelMapper.map(week, WeekEntity.class);

        weekEntity.setWeekNumber(id);

        weekRepository.save(weekEntity);
    }

    public Week findWeekById(Long id) {

        Optional<WeekEntity> foundWeek = weekRepository.findById(id);
        if (foundWeek.isEmpty())
            throw new EntityNotFoundException(NOUSERID+ id +WASFOUND);

        return modelMapper.map(foundWeek, Week.class);
    }

    public List<Week> findAllWeeks() {
        Iterable<WeekEntity> allWeekEntities = weekRepository.findAll();

        List<Week> allWeeks = new ArrayList<>();
        allWeekEntities.forEach(week -> allWeeks.add(modelMapper.map(week, Week.class)));
        return allWeeks;
    }

    public void deleteWeek(Long id) {

        Optional<WeekEntity> foundWeek = weekRepository.findByWeekNumber(id);
        if (foundWeek.isEmpty())
            throw new EntityNotFoundException(NOUSERID+ id +WASFOUND);

        weekRepository.deleteById(id);
    }
}
