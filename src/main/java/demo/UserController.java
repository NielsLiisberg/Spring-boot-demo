package demo;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.List;

import org.springframework.data.rest.webmvc.RepositoryLinksResource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import demo.models.User;
import demo.models.UserRepository;

@RestController
@RequestMapping("api/users")
public class UserController implements ResourceProcessor<RepositoryLinksResource> {
	
	private UserRepository userRepository;

	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@GetMapping("/{id}")
	User getUser(@PathVariable("id") Long id) {
		User u =  userRepository.findById(id).get();
		return u;
	}

	@GetMapping("")
	List<User> getUser(@RequestParam("search") String search) {
		return  userRepository.findByNameIgnoreCaseContaining(search);
	}

	@GetMapping("/proclist")
	List<User> getUser() {
		return userRepository.procedureList();
	}

	@Override
	public RepositoryLinksResource process(RepositoryLinksResource resource) {
		resource.add(linkTo(UserController.class).slash("/$id").withRel("users"));
		resource.add(linkTo(UserController.class).slash("?search=").withRel("users"));
		resource.add(linkTo(UserController.class).slash("/proclist?search=").withRel("users"));
		return resource;
	}

}
