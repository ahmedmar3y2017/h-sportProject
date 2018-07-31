package com.sport.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name = "employee", catalog = "sport")
public class Employee implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;

	private String firstName;
	private String lastName;
	private String degree;
	private String title;
	private String Description;
	private String photo;

	public Employee(String firstName, String lastName, String degree, String title, String description, String photo) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.degree = degree;
		this.title = title;
		Description = description;
		this.photo = photo;
	}

	public Employee() {
		super();
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
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

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String fullName() {

		String fullName = firstName + "  " + lastName;
		if (!degree.isEmpty()) {
			fullName += degree;

		}

		return fullName;

	}

}
