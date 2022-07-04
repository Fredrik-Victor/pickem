package project.pickem.repository;

import org.springframework.data.repository.CrudRepository;
import project.pickem.entity.WeekEntity;

import java.util.Optional;

public interface WeekRepository extends CrudRepository<WeekEntity, Long> {

    Optional<WeekEntity> findByWeekNumber(Long id);
}
