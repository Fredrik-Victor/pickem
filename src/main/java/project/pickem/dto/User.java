package project.pickem.dto;

import project.pickem.validator.UniqueEmail;
import javax.validation.constraints.Email;

public class User {

    private Long id;
    private String fullName;
    @UniqueEmail
    @Email
    private String email;
    private String points;
    private Role role;

    public User(String fullName, String email, Role role, String points) {
        this.fullName = fullName;
        this.email = email;
        this.role = role;
        this.points = points;
    }

    public User(){
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPoints(){return points;}

    public User setPoints(String points){
        this.points = points;
        return this;
    }

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }


    public String getUsername() {
        return fullName;
    }

    public User setUsername(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }
}
