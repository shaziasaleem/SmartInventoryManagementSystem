package system.inventory.commonutilities.permissions;

import system.inventory.commonutilities.commonDto.LoggedInUserDTO;

public class PermissionChecker {
	
	public static boolean roleCheck(LoggedInUserDTO user , String role) {
		return user != null && role != null && user.getRole().equals(role);
	}
	public static boolean permissionCheck(LoggedInUserDTO user, String permission) {
		return user!=null && permission !=null && user.getPermissions().contains(permission);
	}

}
