package project.pickem.service;

import org.modelmapper.ModelMapper;
import project.pickem.dto.User;
import project.pickem.entity.RoleEntity;
import project.pickem.entity.UserEntity;
import org.springframework.stereotype.Service;
import project.pickem.repository.RoleRepository;
import project.pickem.repository.UserRepository;
import project.pickem.exception.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private static final String NOUSERID = "No user with id ";
    private static final String WASFOUND = " was found";

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       ModelMapper modelMapper) {

        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    public User createUser(User user) {

        UserEntity userEntity = modelMapper.map(user, UserEntity.class);

//        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        RoleEntity roleToAdd = roleRepository.findByRole("USER");
        userEntity.setRole(roleToAdd);

        return modelMapper.map(userRepository.save(userEntity), User.class);
    }

    public void updateUser(Long id, User user) {

        Optional<UserEntity> foundUser = userRepository.findById(id);
        if (foundUser.isEmpty())
            throw new EntityNotFoundException(NOUSERID+ id + WASFOUND);

        UserEntity userEntity = modelMapper.map(user, UserEntity.class);

        userEntity.setId(id);
        userEntity.setPassword(userEntity.getPassword());

        userRepository.save(userEntity);
    }

    public User findUserById(Long id) {

        Optional<UserEntity> foundUser = userRepository.findById(id);
        if (foundUser.isEmpty())
            throw new EntityNotFoundException(NOUSERID+ id +WASFOUND);

        return modelMapper.map(foundUser, User.class);
    }

    public List<User> findAllUsers() {
        Iterable<UserEntity> allUserEntities = userRepository.findAll();

        List<User> allUsers = new ArrayList<>();
        allUserEntities.forEach(user -> allUsers.add(modelMapper.map(user, User.class)));
        return allUsers;
    }

    public void deleteUser(Long id) {

        Optional<UserEntity> foundUser = userRepository.findById(id);
        if (foundUser.isEmpty())
            throw new EntityNotFoundException(NOUSERID+ id +WASFOUND);

        userRepository.deleteById(id);
    }
}
