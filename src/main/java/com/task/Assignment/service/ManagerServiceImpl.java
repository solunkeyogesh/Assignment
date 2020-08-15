package com.task.Assignment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.task.Assignment.dao.ManagerDao;
import com.task.Assignment.model.Employee;
import com.task.Assignment.model.Manager;

@Service
@Transactional
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	private ManagerDao managerDao;

	@Override
	public Employee getEmployeeById(int employeeId) {
		// TODO Auto-generated method stub
		return managerDao.getEmployeeById(employeeId);
	}
	
	@Override
	public Manager getManagerById(int managerId) {
		// TODO Auto-generated method stub
		return managerDao.getManagerById(managerId);
	}
	
	@Override
	public Manager getManagerByEmail(String email) {
		// TODO Auto-generated method stub
		return managerDao.getManagerByEmail(email);
	}
	
	@Override
	public Employee getEmployeeByEmail(String email) {
		// TODO Auto-generated method stub
		return managerDao.getEmployeeByEmail(email);
	}

	@Override
	public Integer signupManager(Manager manager) {
		// TODO Auto-generated method stub
		return managerDao.signupManager(manager);
	}

	@Override
	public Integer login(Manager manager) {
		// TODO Auto-generated method stub
		return managerDao.login(manager);
	}

	@Override
	public Integer createEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return managerDao.createEmployee(employee);
	}

	@Override
	public List<Employee> retriveAllEmployee() {
		// TODO Auto-generated method stub
		return managerDao.retriveAllEmployee();
	}

	@Override
	public Integer updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return managerDao.updateEmployee(employee);
	}

	@Override
	public Integer deleteEmployee(Integer employeeId) {
		// TODO Auto-generated method stub
		return managerDao.deleteEmployee(employeeId);
	}

}
