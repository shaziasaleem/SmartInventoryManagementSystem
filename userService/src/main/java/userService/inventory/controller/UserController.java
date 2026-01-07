package userService.inventory.controller;

import java.util.Enumeration;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import userService.inventory.dtos.UserDTO;
import userService.inventory.dtos.UserRequest;
import userService.inventory.model.User;
import userService.inventory.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	
	@GetMapping("/check")
	public ResponseEntity<String> checkHeaders(HttpServletRequest request) {
	    Enumeration<String> headerNames = request.getHeaderNames();
	    if (headerNames == null) return ResponseEntity.ok("No headers received");

	    StringBuilder sb = new StringBuilder("Headers:\n");
	    while (headerNames.hasMoreElements()) {
	        String name = headerNames.nextElement();
	        sb.append(name).append(": ").append(request.getHeader(name)).append("\n");
	    }
	    return ResponseEntity.ok(sb.toString());
	}
	
	@GetMapping("/test2")
	public ResponseEntity<String> testAdmin() {
		System.out.println("in test api");
	    return ResponseEntity.ok("Access granted for ADMIN!");
	}
	
	@GetMapping("/all")
	//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<UserDTO>> getAllUsers(){
		System.out.println("get all users api");
		List<UserDTO> result = userService.getAllUser();
		return ResponseEntity.ok(result);	
	}
	
	@GetMapping("/getuserbyname/{username}")
	public ResponseEntity<UserDTO> getUserByName(@PathVariable String username) {
	    return userService.findUserByName(username)
	            .map(ResponseEntity::ok)
	            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	
	@GetMapping("/getuserbyid/{id}")
	public ResponseEntity<Optional<UserDTO>> getUserById(@PathVariable Long id){
		Optional<UserDTO> result = userService.findUserById(id);
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("/saveuser")
	//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<UserDTO> saveUser(@Valid @RequestBody UserRequest user){
		return ResponseEntity.ok(userService.addNewUser(user));
		
	}
	
	@DeleteMapping("/deleteuser/{id}")
	//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteUser(@PathVariable Long id){
		userService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}

}
