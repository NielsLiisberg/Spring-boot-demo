package demo.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="users_full")
public class User {
	@Id
	@Column(name="id")
	long id;

	@Column(name="password")
	String passWord;

	@Column(name="user_id")
	String userId;

	@Column(name="last_access")
	String  lastAccess;
 	
	@Column(name="name")
	String name;

	@Column(name="email")
	String eMail;

	@Column(name="status")
	String status;

	@Column(name="storage_used")
	BigDecimal  storageUsed;

	@Column(name="home_dir")
	String  homeDir;

}
