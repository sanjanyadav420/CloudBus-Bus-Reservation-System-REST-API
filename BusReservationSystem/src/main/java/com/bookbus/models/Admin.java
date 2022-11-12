package com.bookbus.models;

import javax.persistence.Column;
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
public class Admin {

	@Id
	@Column(unique = true)
	private Integer adminId;
	private String adminUserName;
	private String adminPassword;
	
	
	public Admin(String adminUserName, String adminPassword) {
		super();
		this.adminUserName = adminUserName;
		this.adminPassword = adminPassword;
	}
	
	
	//insert into admin values(1,'SanjanYadav','sanjan1234');
	//insert into admin values(2,'YashRajput','yash1234');
	//insert into admin values(3,'BhaveshShahani','bhavesh1234');
	//insert into admin values(4,'MukundJha','mukund1234');
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
