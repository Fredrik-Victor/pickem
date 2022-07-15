package project.pickem.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.pickem.entity.GuessEntity;

import java.util.Optional;

@Repository
public interface GuessRepository extends CrudRepository<GuessEntity, Long> {

    @Override
    Optional<GuessEntity> findById(Long id);

}
