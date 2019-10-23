package demo.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserRepository extends JpaRepository<User, Long> {
	public List<User> findByNameIgnoreCaseContaining(String username);
	
	@Query(nativeQuery = true, value="call microdemo.user_list ( search => :search)")
	public List<User> listByProcedure(@Param("search") String search); 
	 
}
