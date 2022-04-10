package com.gl.employee.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gl.employee.config.MyUserDetails;
import com.gl.employee.entity.User;
import com.gl.employee.repository.UserRepository;
import com.gl.employee.service.UserService;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	BCryptPasswordEncoder nCoder;

	/* Saving new user to DB
	 * User name should be unique.
	 * It encodes password before saving to db.
	 */
	@Override
	public String save(User user) {
		User isUserExist = userRepo.findByUserName(user.getUserName());
		if (isUserExist != null) {
			return "User already exists, please use a unique username!";
		}
		else {
			user.setPassword(nCoder.encode(user.getPassword()));
			userRepo.saveAndFlush(user);
			return "User saved to DB";
		}
	}
	
	@Override
	public String update(User user) {
		boolean isUserExist = userRepo.existsById(user.getId());
		if (isUserExist) {
			user.setPassword(nCoder.encode(user.getPassword()));
			userRepo.saveAndFlush(user);
			return "User data updated with DB";
		}
		else {
			return "User doesn't exist!";
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("Could not find user!");
		}
		else {
			return new MyUserDetails(user);
		}
	}
}
