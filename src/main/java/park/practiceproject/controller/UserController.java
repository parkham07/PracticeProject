package park.practiceproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import park.practiceproject.entity.User;
import park.practiceproject.service.UserService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/User")
public class UserController {
	private final UserService userService;

	@GetMapping("/{seq}")
	public User getUser(@PathVariable Long seq) {
		return userService.findById(seq);
	}

	@GetMapping("/login/{id}/{password}")
	public User login(@PathVariable String id, @PathVariable String password) {
		return userService.getUser(id, password);
	}

	@GetMapping("/search/id/equals/{id}")
	public User searchIdEquals(@PathVariable String id) {
		return userService.searchIdEquals(id);
	}

	@GetMapping("/search/id/like/{id}")
	public List<User> searchIdLike(@PathVariable String id) {
		return userService.searchIdLike(id);
	}

	@GetMapping("/addRandomUser")
	public User addRandomUser() {
		return userService.addRandomUser();
	}
}
