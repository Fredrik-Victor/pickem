package project.pickem.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import project.pickem.entity.RoleEntity;
import project.pickem.entity.UserEntity;
import project.pickem.repository.RoleRepository;
import project.pickem.repository.UserRepository;
import project.pickem.entity.*;
import project.pickem.repository.*;

import javax.transaction.Transactional;
import java.util.List;
@Component
public class SampleDataInitializer implements ApplicationRunner {


    private final UserRepository userRepository;
    //    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public SampleDataInitializer(UserRepository userRepository,
                                 RoleRepository roleRepository) {
        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public void run(ApplicationArguments args) {
        RoleEntity adminRole = new RoleEntity("ADMIN");
        RoleEntity userRole = new RoleEntity("USER");

        UserEntity user = new UserEntity(
                "UserTest",
                "Test",
                "User",
                "password",
                "user@corkdork.se",
                userRole);

        UserEntity admin = new UserEntity(
                "Test",
                "Test",
                "Admin",
                "password",
                "admin@corkdork.se",
                adminRole);

        this.roleRepository.saveAll(List.of(adminRole, userRole));
        this.userRepository.saveAll(List.of(admin, user));
    }
}
