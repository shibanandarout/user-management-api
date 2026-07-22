package demo.service;

import java.util.List;

import demo.dto.UserDto;

public interface UserService {
	UserDto saveUser(UserDto userDto);

	List<UserDto> getAllUsersDto();

	UserDto getUserDtoById(int id);

	UserDto updateUserDto(int id, UserDto userDto);

	UserDto patchUserDto(int id, UserDto userDto);

	void deleteUserById(int id);

	void deleteAllUsers();
}
