package com.recipe.service;

import com.recipe.entity.Role;
import com.recipe.entity.User;
import com.recipe.repo.RoleRepository;
import com.recipe.repo.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private UserRepository userRepository;
	private RoleRepository roleRepository;

	private final String USER_ROLE = "USER";

	@Autowired
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}

		return new UserDetailsImpl(user);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public String registerUser(User userToRegister) {
		User userCheck = userRepository.findByEmail(userToRegister.getEmail());

		if (userCheck != null)
			return "alreadyExists";

		Role userRole = roleRepository.findByRole(USER_ROLE);
		if (userRole != null) {
			userToRegister.getRoles().add(userRole);
		} else {
			userToRegister.addRoles(USER_ROLE);
		}

		userRepository.save(userToRegister);

		return "ok";
	}
}
