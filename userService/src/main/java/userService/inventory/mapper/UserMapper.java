package userService.inventory.mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import userService.inventory.dtos.UserDTO;
import userService.inventory.dtos.UserRequest;
import userService.inventory.model.Role;
import userService.inventory.model.User;
import userService.inventory.repository.RoleRepo;


@Component
public class UserMapper {

    private final RoleRepo roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserMapper(RoleRepo roleRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public  UserDTO userToDto(User user) {
        if (user == null) return null;

        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setRole(user.getRole() != null ? user.getRole().getName() : null);
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword()); // Consider omitting password in DTO for security
        return dto;
    }

    public User toEntity(UserRequest dto) {
        if (dto == null) return null;

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        Role role = roleRepository.findByName(dto.getRole().toUpperCase())
            .orElseThrow(() -> new RuntimeException("Invalid role"));

        user.setRole(role);
        return user;
    }
}