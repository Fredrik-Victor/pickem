package project.pickem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project.pickem.dto.Role;

import project.pickem.exception.BadRequestException;
import project.pickem.exception.EntityNotFoundException;
import project.pickem.service.RoleService;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("")
    public ResponseEntity<Role> createRole(@Validated @RequestBody Role role, BindingResult errors) {
        if (errors.hasErrors())
            throw new BadRequestException("Invalid input", errors);

        Role createdRole = roleService.createRole(role);

        return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Role> findRoleById(@PathVariable Long id) {

        Role foundRole = roleService.findRoleById(id);

        return new ResponseEntity<>(foundRole, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Role>> findAllRoles() {

        List<Role> roles = roleService.findAllRoles();
        if(roles.isEmpty())
            throw new EntityNotFoundException("Failed to find any roles");

        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Role> updateRole(@Validated @PathVariable Long id, @RequestBody Role role, BindingResult errors) {
        if (errors.hasErrors())
            throw new EntityNotFoundException(notFound(id));
        roleService.updateRole(id, role);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private String notFound(Long id) {
        return "Role with ID: " + id + " was not found.";
    }
}
