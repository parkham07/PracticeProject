package park.practiceproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import park.practiceproject.common.Utils;
import park.practiceproject.entity.User;
import park.practiceproject.repository.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class UserService {
	private final UserRepository userRepository;

	public User findById(Long seq) {
		return userRepository.findById(seq).orElse(User.builder().build());
	}

	public User getUser(String id, String password) {
		Map<String, String> param = new HashMap<>();

		param.put("id", id);
		param.put("password", password);

		return userRepository.findOne(userRepository.login(param)).orElse(User.builder().build());
	}

	public User searchIdEquals(String id) {
		Map<String, String> param = new HashMap<>();

		param.put("id", id);

		return userRepository.findOne(userRepository.searchId(param, false)).orElse(User.builder().build());
	}

	public List<User> searchIdLike(String id) {
		Map<String, String> param = new HashMap<>();

		param.put("id", id);

		return userRepository.findAll(userRepository.searchId(param, true));
	}

	public User addRandomUser() {
		return userRepository.save(User.builder()
										.id("test" + Utils.getInstance().rnd().nextInt())
										.password(Utils.getInstance().makeMD5("pass" + Utils.getInstance().rnd().nextInt()))
										.name("name" + (userRepository.count() + 1))
										.state(0)
										.authority((Utils.getInstance().rnd().nextInt() % 2 == 0)? User.Authority.ROLE_ADMIN: User.Authority.ROLE_USER)
										.build());
	}
}
