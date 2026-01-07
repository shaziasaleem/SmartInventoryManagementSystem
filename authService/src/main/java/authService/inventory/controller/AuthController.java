package authService.inventory.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import authService.inventory.dtos.RequestDTO;
import authService.inventory.dtos.ResponseDTO;
import authService.inventory.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final AuthService authService;
	
	public  AuthController(AuthService authService) {
		this.authService = authService;
	}
	
	@PostMapping("/login")
	public ResponseEntity<ResponseDTO> login(@RequestBody RequestDTO request){
		return ResponseEntity.ok(authService.login(request));
		
	}
}
