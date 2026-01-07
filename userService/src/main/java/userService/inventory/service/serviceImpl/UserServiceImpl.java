package userService.inventory.service.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import userService.inventory.dtos.UserDTO;
import userService.inventory.dtos.UserRequest;
import userService.inventory.mapper.UserMapper;
import userService.inventory.model.Role;
import userService.inventory.model.User;
import userService.inventory.repository.RoleRepo;
import userService.inventory.repository.UserRepository;
import userService.inventory.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	private final RoleRepo roleRepository;
	private final UserMapper userMapper;
	
	public UserServiceImpl(UserRepository userRepo,RoleRepo roleRepository,UserMapper userMapper) {
		this.userRepository = userRepo;
		this.roleRepository = roleRepository;
		this.userMapper = userMapper;
	}

	@Override
	public UserDTO addNewUser(UserRequest request) {
		User newUser = userMapper.toEntity(request);
	    User savedUser = userRepository.save(newUser); // Save the user to DB
	    return userMapper.userToDto(savedUser); 
	}
	
	@Override
	public List<UserDTO> getAllUser() {
		System.out.println("in service function");
		List<User> users = userRepository.findAll();
		System.out.println("getting users from db:"+users);
	    return users.stream()
	                .map(userMapper::userToDto)
	                .collect(Collectors.toList());
	}

	@Override
	public Optional<UserDTO> findUserById(Long id) {
		Optional<User> users = userRepository.findById(id);
		return userRepository.findById(id).map(userMapper::userToDto);
	}

	@Override
	public Optional<UserDTO> findUserByName(String username) {
		
		return userRepository.findByUsername(username).map(userMapper::userToDto);
	}

	

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
		
	}
	
	@Transactional
	public void updateUserEmail(String username, String newEmail) {
	    User user = userRepository.findByUsername(username).orElseThrow();
	    user.setEmail(newEmail);
	    userRepository.save(user); // JPA tracks changes and only updates email
	}
 
}
