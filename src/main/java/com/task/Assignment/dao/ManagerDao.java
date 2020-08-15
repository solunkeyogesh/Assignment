package com.task.Assignment.dao;

import java.util.List;

import com.task.Assignment.model.Employee;
import com.task.Assignment.model.Manager;

public interface ManagerDao {

	public Employee getEmployeeById(int employeeId);

	public Manager getManagerById(int managerId);

	public Manager getManagerByEmail(String email);

	public Employee getEmployeeByEmail(String email);

	public Integer signupManager(Manager manager);

	public Integer login(Manager manager);

	public Integer createEmployee(Employee employee);

	public List<Employee> retriveAllEmployee();

	public Integer updateEmployee(Employee employee);

	public Integer deleteEmployee(Integer employeeId);
}
