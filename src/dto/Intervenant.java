package dto;

import java.util.ArrayList;
import java.util.List;

import enums.Role;

public class Intervenant {
	
	private int id;
	private String firstName;
	private String lastName;
	private Role role;
	
	public Intervenant() {
		
	}
	
	public Intervenant(int id, String firstName, String lastName, Role role) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
	}
	
	public Intervenant(int id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "Intervenant [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
	public void addRole(Role role) {
		this.role = role;
	}
	
	

}
