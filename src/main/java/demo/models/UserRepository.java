package demo.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
	public List<User> findByNameIgnoreCaseContaining(String username);
	
	@Query(nativeQuery = true, value="call qgpl.userlist")
	public List<User> procedureList();
}
