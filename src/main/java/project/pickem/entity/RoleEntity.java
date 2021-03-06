package project.pickem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String role;

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true, mappedBy = "role")
    private Set<UserEntity> users = new HashSet<>();

    public RoleEntity(String role) {
        this.role = role;
    }

    public RoleEntity() {
    }

    public void addUser(UserEntity user) {
        users.add(user);
    }

    public RoleEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getRole() {
        return role;
    }

    public RoleEntity setRole(String role) {
        this.role = role;
        return this;
    }

    @JsonIgnore
    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }

    public Long getId() {
        return id;
    }


}
