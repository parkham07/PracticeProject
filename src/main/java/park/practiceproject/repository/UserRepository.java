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
	default Specification<User> login(Map<String, String> filter) {
		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();

			for(Map.Entry<String, String> entry:filter.entrySet()) {
				if(entry.getKey().equals("id") || entry.getKey().equals("password")) {
					predicates.add(criteriaBuilder.equal(root.get(entry.getKey()).as(String.class), entry.getValue()));
				}
			}

			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}

	default Specification<User> searchId(Map<String, String> filter, boolean isLike) {
		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();

			for(Map.Entry<String, String> entry:filter.entrySet()) {
				if(entry.getKey().equals("id")) {
					if(isLike) {
						predicates.add(criteriaBuilder.like(root.get(entry.getKey()).as(String.class), "%" + entry.getValue() + "%"));
					} else {
						predicates.add(criteriaBuilder.equal(root.get(entry.getKey()).as(String.class), entry.getValue()));
					}
				}
			}

			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}
}
