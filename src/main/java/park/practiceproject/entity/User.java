package park.practiceproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Data
@Builder
@Table(name = "TB_USER",
		indexes = {@Index(name = "idx_user", columnList = "ID, PASSWORD")},
		uniqueConstraints = {@UniqueConstraint(columnNames = "ID")}
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

	public enum Authority {
		ROLE_USER, ROLE_ADMIN
	}

	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="USER_ID_GENERATOR")
	@SequenceGenerator(name = "USER_ID_GENERATOR", sequenceName = "USER_ID_GENERATOR", initialValue = 1, allocationSize = 1)
	@Column(name = "SEQ", nullable = false)
	@JsonIgnore
	private Long seq;

	@Column(name = "ID", nullable = false)
	private String id;

	@Column(name = "PASSWORD", nullable = false)
	@JsonIgnore
	private String password;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "STATE", nullable = false)
	@ColumnDefault("0")
	@JsonIgnore
	private Integer state;

	@Column(name = "AUTHORITY", nullable = false)
	@ColumnDefault("0")
	@JsonIgnore
	private Authority authority;

	public User(Long seq, String id, String password, String name, Integer state, Authority authority) {
		this.seq = seq;
		this.id = id;
		this.password = password;
		this.name = name;
		this.state = state;
		this.authority = authority;
	}
}
