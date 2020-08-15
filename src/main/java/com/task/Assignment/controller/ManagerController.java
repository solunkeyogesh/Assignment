package com.task.Assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.Assignment.jwtUtil.JwtUtil;
import com.task.Assignment.model.AuthRequest;
import com.task.Assignment.model.Employee;
import com.task.Assignment.model.Manager;
import com.task.Assignment.service.ManagerService;

@CrossOrigin
@RestController
@RequestMapping("/manager")
public class ManagerController {

	@Autowired
	private ManagerService managerService;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping("/")
	public String welcome() {
		return "welcome in assignment";
	}

	@GetMapping(value = "/employee/{employeeId}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("employeeId") int employeeId) {
		System.out.println("id" + employeeId);

		Employee employee = managerService.getEmployeeById(employeeId);
		 
		if (employee != null) {
			return new ResponseEntity<Employee>(employee, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<Employee>(employee, HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping(value = "/signup-manager", headers = "Accept=application/json")
	public ResponseEntity<Integer> signupManager(@RequestBody Manager manager) {

		Integer result = managerService.signupManager(manager);
		if (result > 0) {
			return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Integer>(result, HttpStatus.NOT_MODIFIED);

		}

	}

	@PostMapping(value = "/login-manager", headers = "Accept=application/json")
	public ResponseEntity<Integer> login(@RequestBody Manager manager) {

		Integer result = managerService.login(manager);
		if (result > 0) {
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<Integer>(result, HttpStatus.UNAUTHORIZED);

		}

	}

	@PostMapping(value = "/create-employee", headers = "Accept=application/json")
	public ResponseEntity<Integer> createEmployee(@RequestBody Employee employee) {
		Integer result = managerService.createEmployee(employee);
		if (result > 0) {
			return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Integer>(result, HttpStatus.NOT_MODIFIED);

		}

	}

	@GetMapping(value = "/retrive-all-employee")
	public ResponseEntity<List<Employee>> retriveAllEmployee() {
		List<Employee> employeeList = managerService.retriveAllEmployee();

		if (!employeeList.isEmpty()) {
			return new ResponseEntity<List<Employee>>(employeeList, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<List<Employee>>(employeeList, HttpStatus.NOT_FOUND);

		}

	}

	@PutMapping(value = "/update-employee", headers = "Accept=application/json")
	public ResponseEntity<Integer> updateEmployee(@RequestBody Employee employee) {

		Integer result = managerService.updateEmployee(employee);
		if (result > 0) {
			return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Integer>(result, HttpStatus.NOT_MODIFIED);

		}

	}

	@DeleteMapping(value = "/delete-employee/{employeeId}", headers = "Accept=application/json")
	public ResponseEntity<Integer> deleteEmployee(@PathVariable("employeeId") Integer employeeId) {

		Integer result = managerService.deleteEmployee(employeeId);
		if (result > 0) {
			return new ResponseEntity<Integer>(result, HttpStatus.MOVED_PERMANENTLY);
		} else {
			return new ResponseEntity<Integer>(result, HttpStatus.NOT_MODIFIED);

		}

	}

	@PostMapping("/authenticate")
	public String generateToken(@RequestBody AuthRequest authRequest) throws Exception{
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

		} catch (Exception e) {
			throw new Exception("valid usrname and password");
		}
		String token=jwtUtil.generateToken(authRequest.getEmail());
		System.out.println("token............."+token);
		return token;

	}

}
