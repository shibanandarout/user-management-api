package demo.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import demo.dto.OrderDto;
import demo.dto.UserDto;
import demo.entity.Order;
import demo.entity.User;
import demo.exception.UserNotFoundException;
import demo.repository.UserRepository;
import demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public UserDto saveUser(UserDto userDto) {

		User user = convertToEntity(userDto);

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		User savedUser = userRepository.save(user);

		return convertToDto(savedUser);
	}

	@Override
	public List<UserDto> getAllUsersDto() {

		return userRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
	}

	@Override
	public UserDto getUserDtoById(int id) {

		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User not found with id : " + id));

		return convertToDto(user);
	}

	@Override
	public UserDto updateUserDto(int id, UserDto userDto) {

		User existingUser = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User not found with id : " + id));

		existingUser.setName(userDto.getName());
		existingUser.setEmail(userDto.getEmail());
		existingUser.setPassword(passwordEncoder.encode(userDto.getPassword()));

		User updatedUser = userRepository.save(existingUser);

		return convertToDto(updatedUser);
	}

	@Override
	public UserDto patchUserDto(int id, UserDto userDto) {

		User existingUser = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User not found with id : " + id));

		if (userDto.getName() != null) {
			existingUser.setName(userDto.getName());
		}

		if (userDto.getEmail() != null) {
			existingUser.setEmail(userDto.getEmail());
		}

		if (userDto.getPassword() != null) {
			existingUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
		}

		User updatedUser = userRepository.save(existingUser);

		return convertToDto(updatedUser);
	}

	@Override
	public void deleteUserById(int id) {

		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User not found with id : " + id));

		userRepository.delete(user);
	}

	@Override
	public void deleteAllUsers() {

		userRepository.deleteAll();
	}

	// ===========================
	// Mapping Methods
	// ===========================

	private User convertToEntity(UserDto dto) {

		User user = new User();

		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());

		List<Order> orders = new ArrayList<>();

		for (OrderDto orderDto : dto.getOrderDtos()) {

			Order order = new Order();

			order.setProduct(orderDto.getProduct());
			order.setPrice(orderDto.getPrice());

			order.setUser(user);

			orders.add(order);
		}

		user.setOrders(orders);

		return user;
	}

	private UserDto convertToDto(User user) {

		UserDto dto = new UserDto();

		dto.setName(user.getName());
		dto.setEmail(user.getEmail());

		List<OrderDto> orderDtos = new ArrayList<>();

		for (Order order : user.getOrders()) {

			OrderDto orderDto = new OrderDto();

			orderDto.setProduct(order.getProduct());
			orderDto.setPrice(order.getPrice());

			orderDtos.add(orderDto);
		}

		dto.setOrderDtos(orderDtos);

		// Password is intentionally not returned

		return dto;
	}
}