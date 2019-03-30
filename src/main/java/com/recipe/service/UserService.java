package com.recipe.service;

import com.recipe.entity.User;

public interface UserService {
	
	public String registerUser(User user);

	public User findByEmail(String email);
}
