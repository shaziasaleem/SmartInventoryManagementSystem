package system.inventory.commonutilities.commonDto;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class LoggedInUserDTO {
	
	private long id;
	private String userName;
	private String email;
	private String role; // or enum Role
	private List<String> permissions;
	public LoggedInUserDTO(String username, String role) {
		this.userName = username;
		this.role =role;
	}
}
