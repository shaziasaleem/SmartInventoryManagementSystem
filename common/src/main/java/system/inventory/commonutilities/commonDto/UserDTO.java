package system.inventory.commonutilities.commonDto;


import lombok.Data;


@Data
public class UserDTO {
	
	private long id;
	private String username;
	private String email;
	private String password;
	private String role; // or enum Role
	
}
