package com.anhvu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users_roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Users_Roles {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "user_id")
	private int user_id;
	
	@Column(name = "role_id")
	private int role_id;

	public Users_Roles(int user_id, int role_id) {
		this.user_id = user_id;
		this.role_id = role_id;
	}
	
}
