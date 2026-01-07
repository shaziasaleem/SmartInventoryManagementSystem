package authService.inventory.dtos;

import lombok.Data;


@Data
public class ResponseDTO {

	private String token;
	private String role;
	public ResponseDTO(String token, String role) {
		super();
		this.token = token;
		this.role = role;
	}
	
	
}
