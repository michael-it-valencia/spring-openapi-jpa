package com.michael.valencia.prototypes.api.template.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "USERS")
@Getter
@Setter
@NoArgsConstructor
@Data
public class User {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private @Id Integer id;

	private String firstname;

	private String email;

	private String lastname;

}