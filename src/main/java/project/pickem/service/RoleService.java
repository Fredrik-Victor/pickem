package project.pickem.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import project.pickem.dto.Role;
import project.pickem.entity.RoleEntity;
import project.pickem.repository.RoleRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    public RoleService(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    public Role createRole(Role role) {
        RoleEntity roleEntity = modelMapper.map(role, RoleEntity.class);

        roleRepository.save(roleEntity);

        return modelMapper.map(roleEntity, Role.class);
    }

    public Role findRoleById(Long id) {
        Optional<RoleEntity> foundRole = roleRepository.findById(id);
        if(foundRole.isEmpty())
            throw new project.pickem.exception.EntityNotFoundException("Role with ID: " + id + " was not found");

        return modelMapper.map(foundRole, Role.class);
    }

    public List<Role> findAllRoles() {
        Iterable<RoleEntity> allRoles = roleRepository.findAll();

        List<Role> roles = new ArrayList<>();
        allRoles.forEach(role -> roles.add(modelMapper.map(role, Role.class)));
        return roles;
    }

    public void updateRole(Long id, Role role) {
        role.setId(id);
        RoleEntity roleEntity = modelMapper.map(role, RoleEntity.class);
        roleRepository.save(roleEntity);
    }

    public void deleteRole(Long id) {
        RoleEntity foundRole = roleRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        roleRepository.deleteById(foundRole.getId());
    }
}
