package demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	List<User> findByName(String name);

	Optional<User> findByEmail(String email);
}
