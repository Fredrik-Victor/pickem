package project.pickem.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import project.pickem.dto.User;
import project.pickem.exception.EntityNotFoundException;
//import project.pickem.messaging.MessagePublisher;
import project.pickem.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;
//    private final MessagePublisher messagePublisher;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/test")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        return Collections.singletonMap("name", principal.getAttribute("name"));
    }

    @PostMapping("signup")
    public ResponseEntity<User> createUser(@Validated @RequestBody User user, BindingResult errors) {

//        if (errors.hasErrors())
//            throw new BadRequestException("Invalid input", errors);
        User createdUser = userService.createUser(user);
//        messagePublisher.sendMessage(createdUser.getUsername());

        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Validated @RequestBody User user, BindingResult errors) {

        if (errors.hasErrors())
            throw new EntityNotFoundException(notFound(id));

        userService.updateUser(id, user);

        return new ResponseEntity<>(HttpStatus.OK);
    }



    @GetMapping("{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id) {

        User user = userService.findUserById(id);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @GetMapping("")
    public ResponseEntity<List<User>> findAllUsers() {
        List<User> users = userService.findAllUsers();

        if(users.isEmpty())
            throw new EntityNotFoundException("Failed to find any users");

        return new ResponseEntity<>(users, HttpStatus.OK);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private String notFound(Long id) {
        return "User with ID: " + id + " was not found.";
    }

}
