package project.pickem.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.pickem.entity.RoleEntity;
import project.pickem.entity.UserEntity;

import java.util.Optional;

@Repository
public interface TeamRepository extends CrudRepository<UserEntity, Long> {
    @Override
    @NotNull
    Optional<UserEntity> findById(@NotNull Long id);
}
