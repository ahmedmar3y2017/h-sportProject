package com.sport.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.sport.model.Employee;
import com.sport.services.EmployeeDao;

@ManagedBean(name ="team" , eager=true)
@SessionScoped
public class HplusSportTeam implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Employee employee;
	List<Employee> employees=new ArrayList<Employee>();
	
	
	private final String  EMPLOYEES_FILE="employees.txt";
	private final String  SEPARATOR=";";
	private FileUtils fileUtils;

	@PostConstruct
	public void inititlize(){
		
		System.out.println("Done Init ");
	}
	
	
	public HplusSportTeam() {
		super();
		
		// save into database 
		EmployeeDao.SaveEmployee(new Employee("ahmed","mar3y","65","title","desc","HenryTwill.jpg "));
		
		
		
		init();
		
		
		
		// TODO Auto-generated constructor stub
	}
	
	public void init(){
		
		// select all employees from database 
	
		employees.addAll(EmployeeDao.SelectAllEmployee());
		
		
		
		
//		fileUtils=new FileUtils(EMPLOYEES_FILE , SEPARATOR);
//		setUpEmployeeList();
		
		
		
	}
	
	private void setUpEmployeeList() {
		// TODO Auto-generated method stub
		
		
	List<String> line=fileUtils.getDataList();
	
	line.stream().forEach( ee->{
		
		String[] data=ee.split(SEPARATOR);
		int ID =Integer.parseInt(data[0]);
		String firstName = data[1];
		String lastName = data[2];
		String degree = data[3];
		String  title= data[4];
		String photo = data[5];
		String description = data[6];

		 employee=new Employee();
		 
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
	
	
	employee =new Employee();
		
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
	
	
	
	public void DeleteEmployee(Employee employee){
		
		System.out.println("Edit Employee : "+employee.getFirstName());
		
	}
	public void EditEmployee(Employee employee){
		
		System.out.println("DElete Employee : "+employee.getFirstName());

		
	}
	
	

}
