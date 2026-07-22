package demo.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserDto {

	@NotBlank(message = "name should not be empty")
	@Size(min = 2, max = 50, message = "Name must between 2 and 50 characters")
	private String name;

	@NotBlank(message = "Email should not be empty")
	private String email;

	@NotBlank(message = "Password should not be empty")
	@Size(min = 6, message = "Password must be at least 6 characters")
	private String password;

	@Valid
	@NotEmpty(message = "Orders can not be empty")
	private List<OrderDto> orderDtos;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<OrderDto> getOrderDtos() {
		return orderDtos;
	}

	public void setOrderDtos(List<OrderDto> orderDtos) {
		this.orderDtos = orderDtos;
	}

}
