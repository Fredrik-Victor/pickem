package project.pickem.entity;



import javax.persistence.*;


@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fullName;
    private String email;
    private String points;

    @ManyToOne(fetch = FetchType.EAGER)
    RoleEntity role;

    public UserEntity() {
    }

    public UserEntity(String fullName, String email, RoleEntity role, String points) {
        this.fullName = fullName;
        this.email = email;
        this.role = role;
        this.points = points;
    }

    public Long getId() {
        return id;
    }

    public UserEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public int getPoints(int points) {
        return points;
    }

    public UserEntity setPoints(String points) {
        this.points = points;
        return this;
    }

    public String getPoints() {
        return points;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
        role.addUser(this);
    }
}
