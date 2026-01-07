package userService.inventory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import userService.inventory.dtos.UserDTO;
import userService.inventory.dtos.UserRequest;
import userService.inventory.model.User;


public interface UserService {

	public List<UserDTO> getAllUser();
	public Optional<UserDTO> findUserById(Long id);
	public Optional<UserDTO> findUserByName(String username);
	public UserDTO addNewUser(UserRequest user);
	public void deleteUser(Long id);
}
