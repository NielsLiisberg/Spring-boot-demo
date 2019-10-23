package demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="NAVUSRET00")
public class User {
	@Id
	@Column(name="usrusrtkn")
	long id;
	
	@Column(name="usrnam")
	String name;
}
