package project.pickem.repository;

import org.springframework.data.repository.CrudRepository;
import project.pickem.entity.MatchupEntity;
import project.pickem.entity.WeekEntity;

import java.util.Optional;

public interface MatchupRepository extends CrudRepository<MatchupEntity, Long> {

    @Override
    Optional<MatchupEntity> findById(Long id);
}