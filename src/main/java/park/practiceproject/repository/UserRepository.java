package park.practiceproject.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import park.practiceproject.entity.User;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
	public default Specification<User> login(Map<String, String> filter) {
		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();

			for(Map.Entry<String, String> entry:filter.entrySet()) {
				switch (entry.getKey()) {
					case "id":
					case "password":
						predicates.add(criteriaBuilder.equal(root.get(entry.getKey()).as(String.class), entry.getValue()));

						break;
					default:
						break;
				}
			}

			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}

	public default Specification<User> searchId(Map<String, String> filter, boolean isLike) {
		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();

			for(Map.Entry<String, String> entry:filter.entrySet()) {
				switch (entry.getKey()) {
					case "id":
						if(isLike == true) {
							predicates.add(criteriaBuilder.like(root.get(entry.getKey()).as(String.class), "%" + entry.getValue() + "%"));
						} else {
							predicates.add(criteriaBuilder.equal(root.get(entry.getKey()).as(String.class), entry.getValue()));
						}

						break;
					default:
						break;
				}
			}

			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}
}
