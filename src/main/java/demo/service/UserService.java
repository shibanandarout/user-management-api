package demo.service;

import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import demo.dto.OrderDto;
import demo.dto.UserDto;
import demo.entity.Order;
import demo.entity.User;
import demo.exception.UserNotFoundException;
import demo.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);


	public User saveuser(User user) {

		// Set user inside each order
		if (user.getOrders() != null) {
			user.getOrders().forEach(i -> i.setUser(user));
		}

		return userRepository.save(user);
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User getUserById(int id) {
		return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Invalid Id"));
	}

	public List<User> getUserByName(String name) {
		return userRepository.findByName(name);
	}

	public void deleteUserById(int id) {
		userRepository.deleteById(id);
	}

	public void deleteAllUsers() {
		userRepository.deleteAll();
	}

	public User patchUser(int id, User newUser) {

		// 1. Check if user exists
		User existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

		// 2. Apply conditions
		if (newUser.getName() != null) {
			existingUser.setName(newUser.getName());
		}

		// 3. Update orders if needed
		if (newUser.getOrders() != null) {
			existingUser.setOrders(newUser.getOrders());
			// important fix
			newUser.getOrders().forEach(order -> order.setUser(existingUser));
		}

		// 4. Save updated data
		return userRepository.save(existingUser);
	}

	public User putUser(int id, User newUser) {

		User existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

		// Update name
		existingUser.setName(newUser.getName());

		// Get existing list (IMPORTANT)
		List<Order> existingOrders = existingUser.getOrders();

		// Clear old orders
		existingOrders.clear();

		// Add new orders into SAME list
		if (newUser.getOrders() != null) {
			for (Order order : newUser.getOrders()) {
				order.setUser(existingUser);
				existingOrders.add(order);
			}
		}

		return userRepository.save(existingUser);
	}

	/* All DTO Operations */

	// Save DTO
	public UserDto saveUser(UserDto dto) {

	    logger.info("Request received to save user: {}", dto.getName());

	    User user = new User();
	    user.setName(dto.getName());

	    List<Order> orders = dto.getOrderDtos().stream().map(o -> {
	        Order order = new Order();
	        order.setProduct(o.getProduct());
	        order.setPrice(o.getPrice());
	        order.setUser(user);
	        return order;
	    }).toList();

	    user.setOrders(orders);

	    User saved = userRepository.save(user);

	    logger.info("User saved successfully with id: {}", saved.getId());

	    UserDto response = new UserDto();
	    response.setName(saved.getName());

	    List<OrderDto> orderDtos = saved.getOrders().stream().map(o -> {
	        OrderDto od = new OrderDto();
	        od.setProduct(o.getProduct());
	        od.setPrice(o.getPrice());
	        return od;
	    }).toList();

	    response.setOrderDtos(orderDtos);

	    return response;
	}

	// GetAll DTO
	public List<UserDto> getAllUsersDto() {
		return userRepository.findAll().stream().map(user -> {
			UserDto dto = new UserDto();
			dto.setName(user.getName());

			List<OrderDto> orders = user.getOrders().stream().map(o -> {
				OrderDto od = new OrderDto();
				od.setProduct(o.getProduct());
				od.setPrice(o.getPrice());
				return od;
			}).toList();

			dto.setOrderDtos(orders);
			return dto;
		}).toList();
	}

	// GetUserDTOByID
	public UserDto getUserDtoById(int id) {
		User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

		UserDto dto = new UserDto();
		dto.setName(user.getName());

		List<OrderDto> orders = user.getOrders().stream().map(o -> {
			OrderDto od = new OrderDto();
			od.setProduct(o.getProduct());
			od.setPrice(o.getPrice());
			return od;
		}).toList();

		dto.setOrderDtos(orders);
		return dto;
	}

	// Put Full UpdateDTO
	public UserDto updateUserDto(int id, UserDto dto) {

		User existing = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

		// update basic field
		existing.setName(dto.getName());

		// ensure list is mutable + managed by Hibernate
		if (existing.getOrders() == null) {
			existing.setOrders(new ArrayList<>());
		} else {
			existing.getOrders().clear(); // safe now
		}

		// rebuild orders
		if (dto.getOrderDtos() != null) {

			for (OrderDto o : dto.getOrderDtos()) {
				Order order = new Order();
				order.setProduct(o.getProduct());
				order.setPrice(o.getPrice());
				order.setUser(existing);

				existing.getOrders().add(order); // IMPORTANT
			}
		}

		User saved = userRepository.save(existing);

		// DTO conversion
		UserDto response = new UserDto();
		response.setName(saved.getName());

		List<OrderDto> orderDtos = new ArrayList<>();
		for (Order o : saved.getOrders()) {
			OrderDto od = new OrderDto();
			od.setProduct(o.getProduct());
			od.setPrice(o.getPrice());
			orderDtos.add(od);
		}

		response.setOrderDtos(orderDtos);

		return response;
	}

	// Patch Dto
	public UserDto patchUserDto(Integer id, UserDto dto) {

		User existing = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

		// ✅ PATCH: update only if value is present
		if (dto.getName() != null) {
			existing.setName(dto.getName());
		}

		// ✅ PATCH orders (optional)
		if (dto.getOrderDtos() != null) {

			existing.getOrders().clear();

			for (OrderDto o : dto.getOrderDtos()) {
				Order order = new Order();
				order.setProduct(o.getProduct());
				order.setPrice(o.getPrice());
				order.setUser(existing);

				existing.getOrders().add(order);
			}
		}

		User saved = userRepository.save(existing);

		// convert back to DTO
		UserDto response = new UserDto();
		response.setName(saved.getName());

		List<OrderDto> orderDtos = new ArrayList<>();

		for (Order o : saved.getOrders()) {
			OrderDto od = new OrderDto();
			od.setProduct(o.getProduct());
			od.setPrice(o.getPrice());
			orderDtos.add(od);
		}

		response.setOrderDtos(orderDtos);

		return response;
	}

	public User getUserById(Integer id) {

		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
		return user;
	}
}
