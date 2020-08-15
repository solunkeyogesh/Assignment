package com.task.Assignment.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.task.Assignment.model.Manager;

@Service
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private ManagerService managerService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	Manager manager=managerService.getManagerByEmail(email);
		return new User(manager.getEmail(), manager.getPassword(), new ArrayList<>());
	}

	
	
}
