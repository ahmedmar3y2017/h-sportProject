package com.sport.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.sport.model.Employee;
import com.sport.shared.HibernateUtil;

import java.util.List;

public class EmployeeDao {

	static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	public static Employee SaveEmployee(Employee Employee) {

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(Employee);

		session.getTransaction().commit();

		session.close();

		return Employee;
	}

	public static Employee UpdateEmployee(int id, Employee Employee) {

		Session session = sessionFactory.openSession();
		if (SelectEmployee(id) == null) {
			session.close();
			return null;

		} else {

			session.beginTransaction();
			session.update(String.valueOf(id), Employee);

			session.getTransaction().commit();
			Employee c = (Employee) session.get(Employee.class, id);
			session.close();

			return c;
		}

	}

	public static Employee DeleteEmployee(int id) {
		Session session = sessionFactory.openSession();

		Employee c = SelectEmployee(id);
		if (c == null) {
			session.close();

			return null;

		} else {
			session.beginTransaction();
			session.delete(c);

			session.getTransaction().commit();
			Employee s1 = (Employee) session.get(Employee.class, id);
			session.close();

			return s1;
		}
	}

	public static Employee SelectEmployee(int id) {

		Session session = sessionFactory.openSession();
		Employee Employee = (Employee) session.get(Employee.class, id);

		if (Employee == null) {
			session.close();

			return null;
		}
		session.close();

		return Employee;

	}

	public static List<Employee> SelectAllEmployee() {

		Session session = sessionFactory.openSession();
		List<Employee> Employeees = session.createCriteria(Employee.class).list();

		session.close();

		return Employeees;

	}

}
