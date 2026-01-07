package authService.inventory.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import authService.inventory.dtos.RequestDTO;
import authService.inventory.dtos.ResponseDTO;
import inventory.common_security.autoconfigure.JWTConfig;
import system.inventory.commonutilities.commonDto.UserDTO;



@Service
public class AuthService {
	
	private final PasswordEncoder passwordEncoder;
	private final ClientService client;
	private final JWTConfig jwtConfig;
	
	public AuthService(PasswordEncoder passwordEncoder,ClientService client,JWTConfig jwtConfig) {
		this.client = client;
		this.jwtConfig = jwtConfig;
		this.passwordEncoder = passwordEncoder;
	}
	public ResponseDTO login(RequestDTO request) {
        UserDTO user = client.getUserByUsername(request.getUsername());
       if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword().trim())) {
            throw new RuntimeException("Invalid username or password");
        }

        String token = jwtConfig.generateToken(user.getUsername(),user.getRole());
        return new ResponseDTO(token, user.getRole());
    }

}
