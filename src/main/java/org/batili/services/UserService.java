package org.batili.services;

import java.util.List;

import org.batili.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {//pour povoir utiliser userdetailsservice dans websecurity 
	UserDto createUser(UserDto userDto);
	UserDto getUserByUserId(String userId);
	UserDto updateUser(String id, UserDto userDto);
	void deleteUser(String id);
	List<UserDto> getUsers();
	UserDto getUserByEmail(String email);


}
