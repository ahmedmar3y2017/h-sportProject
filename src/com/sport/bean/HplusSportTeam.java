package com.sport.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.servlet.http.Part;

import com.sport.model.Employee;
import com.sport.services.EmployeeDao;

@ManagedBean(name = "team", eager = true)
@SessionScoped
public class HplusSportTeam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Employee employee = new Employee();
	List<Employee> employees = new ArrayList<Employee>();

	private final String EMPLOYEES_FILE = "employees.txt";
	private final String SEPARATOR = ";";
	private FileUtils fileUtils = new FileUtils();

	private String breadCrumb;

	Part uploadedFile;

	@PostConstruct
	public void inititlize() {

		System.out.println("Done Init ");
		// save into database
		// EmployeeDao.SaveEmployee(new
		// Employee("ahmed","mar3y","65","title","desc","HenryTwill.jpg "));

		init();
	}

	public String getBreadCrumb() {
		return breadCrumb;
	}

	public void setBreadCrumb(String breadCrumb) {
		this.breadCrumb = breadCrumb;
	}

	public Part getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(Part uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public void init() {

		// select all employees from database

		employees.addAll(EmployeeDao.SelectAllEmployee());

		employee = new Employee();

		// fileUtils=new FileUtils(EMPLOYEES_FILE , SEPARATOR);
		// setUpEmployeeList();

	}

	private void setUpEmployeeList() {
		// TODO Auto-generated method stub

		List<String> line = fileUtils.getDataList();

		line.stream().forEach(ee -> {

			String[] data = ee.split(SEPARATOR);
			int ID = Integer.parseInt(data[0]);
			String firstName = data[1];
			String lastName = data[2];
			String degree = data[3];
			String title = data[4];
			String photo = data[5];
			String description = data[6];

			employee = new Employee();

			employee.setID(ID);
			employee.setFirstName(firstName);
			employee.setLastName(lastName);
			employee.setTitle(title);
			employee.setPhoto(photo);
			employee.setDegree(degree);
			employee.setDescription(description);

			// added to employee List

			employees.add(employee);

		});

		employee = new Employee();

	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public String DeleteEmployee(Employee employee) {

		// remove from database
		Employee employee2 = EmployeeDao.DeleteEmployee(employee.getID());
		if (employee2 == null) {
			// remove from list
			this.employees.remove(employee);

		}

		return "dashboard.xhtml?faces-redirect=true";

	}

	public String EditEmployee(Employee employee) {

		this.employee = employee;

		return "edit.xhtml?faces-redirect=true";

	}

	public String add() {

		// upload image
		if (getUploadedFile() != null) {

			fileUtils.saveFile(getUploadedFile());
			employee.setPhoto(fileUtils.getUploadedFileName());

			// add into database

			Employee employeeSaved = EmployeeDao.SaveEmployee(employee);

			if (employeeSaved != null) {

				employees.add(employeeSaved);

				employee = new Employee();

			}

		}

		return "dashboard.xhtml?faces-redirect=true";

	}

	public String update() {

		// upload image
		if (getUploadedFile() != null) {

			fileUtils.saveFile(getUploadedFile());
			employee.setPhoto(fileUtils.getUploadedFileName());

			// update into database

			System.out.println("Id id : " + employee.getID());

			Employee employeeSaved = EmployeeDao.UpdateEmployee(employee.getID(), employee);

			employee = new Employee();

		}

		return "dashboard.xhtml?faces-redirect=true";
	}

	public void login() {

		System.out.println("Login ");

	}

	public String response() {
		return breadCrumb + "?faces-redirect=true";
	}

	public void action(ActionEvent e) {
		breadCrumb = (String) e.getComponent().getAttributes().get("breadCrumb");
	}

}
