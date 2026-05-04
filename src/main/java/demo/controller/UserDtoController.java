package demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PatchExchange;

import demo.dto.UserDto;
import demo.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user-dto")
public class UserDtoController {

	@Autowired
	private UserService service;

	@PostMapping
	public ResponseEntity<UserDto> addUserDto(@Valid @RequestBody UserDto userDto) {

		UserDto saved = service.saveUser(userDto);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}

	@GetMapping
	public List<UserDto> getAllUserDtos() {
		return service.getAllUsersDto();
	}

	@GetMapping("/{id}")
	public UserDto getUserDtoById(@PathVariable int id) {
		return service.getUserDtoById(id);
	}

	@PutMapping("/{id}")
	public UserDto updateAllDto(@PathVariable int id, @RequestBody UserDto userDto) {
		return service.updateUserDto(id, userDto);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<UserDto> updateDtoById(@PathVariable Integer id, @Valid @RequestBody UserDto userDto) {

		UserDto updatedUser = service.patchUserDto(id, userDto);
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public String deleteDto(@PathVariable int id) {
		service.deleteUserById(id);
		return "Deleted";
	}

	@DeleteMapping
	public String deleteAllUserDto() {
		service.deleteAllUsers();
		return "All Data Deleted";

	}
}
