package demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PatchExchange;

import demo.entity.User;
import demo.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	public User addUser(@RequestBody User user) {
		return userService.saveuser(user);
	}

	@GetMapping
	public List<User> getUsers(@RequestParam(required = false) String name) {
		if (name != null)
			return userService.getUserByName(name);

		return userService.getAllUsers();
	}

	@GetMapping("/{id}")
	public User getUserById(@PathVariable int id) {
		return userService.getUserById(id);
	}

	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable int id) {
		userService.deleteUserById(id);
		return "Deleted successfully";
	}

	@DeleteMapping
	public String deleteAllUsers() {
		userService.deleteAllUsers();
		return "All Users deleted";
	}

	@PatchMapping("/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User user) {
		return userService.patchUser(id, user);
	}
	@PutMapping("/{id}")
	public User putUser(@PathVariable int id, @RequestBody User user) {
	    return userService.putUser(id, user);
	}
}
