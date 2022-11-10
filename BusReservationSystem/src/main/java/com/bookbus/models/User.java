package com.bookbus.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
	@SequenceGenerator(name = "user_seq", sequenceName = "User_Id", allocationSize=1, initialValue=101)
	private Integer userId;
	
//	@NotNull(message = "UserName field is mandatory.")
//	@Size(min = 5, max = 15, message = "UserName should not be less than size 5 and more than 15.")
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private Long mobile;
	private String emailId;
	
	
	
	public User(String userName, String password, String firstName, String lastName, Long mobile, String emailId) {
		super();
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobile = mobile;
		this.emailId = emailId;
	}
	
	
	
}
