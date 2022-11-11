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
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admin_seq")
	@SequenceGenerator(name = "admin_seq", sequenceName = "Admin_Id", allocationSize=1, initialValue=1)
	private Integer adminId;
	
//	@NotNull(message = "UserName field is mandatory.")
//	@Size(min = 5, max = 15, message = "UserName should not be less than size 5 and more than 15.")
	private String adminUserName;
	private String adminPassword;
	
	
	public Admin(String adminUserName, String adminPassword) {
		super();
		this.adminUserName = adminUserName;
		this.adminPassword = adminPassword;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
