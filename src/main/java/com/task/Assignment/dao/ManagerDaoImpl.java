package com.task.Assignment.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.task.Assignment.model.Employee;
import com.task.Assignment.model.Manager;

@Repository
public class ManagerDaoImpl implements ManagerDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Employee getEmployeeById(int employeeId) {
		Session session = null;
		Employee employee = null;
		try {
			session = sessionFactory.getCurrentSession();
			employee = session.get(Employee.class, employeeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employee;
	}

	@Override
	public Manager getManagerById(int managerId) {
		Session session = null;
		Manager manager = null;
		try {
			session = sessionFactory.getCurrentSession();
			manager = session.get(Manager.class, managerId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return manager;
	}
	
	@Override
	public Manager getManagerByEmail(String email) {
		Session session = null;
		Manager manager = null;
		try {
			session = sessionFactory.getCurrentSession();
			Query query=session.createQuery("FROM Manager WHERE email=:email");
			query.setParameter("email", email);
			manager=(Manager) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return manager;
	}
	
	@Override
	public Employee getEmployeeByEmail(String email) {
		Session session = null;
		Employee employee = null;
		try {
			session = sessionFactory.getCurrentSession();
			Query query=session.createQuery("FROM Employee WHERE email=:email");
			query.setParameter("email", email);
			employee=(Employee) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employee;
	}
	
	@Override
	public Integer signupManager(Manager manager) {
		Session session = null;
		Integer id = 0;
		try {
			session = sessionFactory.getCurrentSession();
			id = (Integer) session.save(manager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public Integer login(Manager manager) {
		Session session = null;
		Integer id = 0;

		try {
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("FROM Manager where email=:email AND password=:password");
			query.setParameter("email", manager.getEmail());
			query.setParameter("password", manager.getPassword());
			Manager manager2 = (Manager) query.uniqueResult();
			if (manager2 != null) {
				id = 1;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public Integer createEmployee(Employee employee) {
		Session session = null;
		Integer id = 0;
		try {
			session = sessionFactory.getCurrentSession();
			id = (Integer) session.save(employee);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public List<Employee> retriveAllEmployee() {
		Session session = null;
		List<Employee> employeeList = null;
		try {
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("FROM Employee");
			employeeList = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeList;
	}

	@Override
	public Integer updateEmployee(Employee employee) {
		Session session = null;
		Integer result = 0;
		try {
			session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(employee);
			result = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Integer deleteEmployee(Integer employeeId) {
		Session session = null;
		Integer result = 0;

		try {
			session = sessionFactory.getCurrentSession();
			Employee employee=session.get(Employee.class, employeeId);
			session.delete(employee);
			result = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
