package project.pickem.dto;

import javax.validation.constraints.NotBlank;

public class Team {

    private Long id;
    @NotBlank
    private String name;

    public Team(String firstName) {
        this.name = name;
    }

    public Team(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
