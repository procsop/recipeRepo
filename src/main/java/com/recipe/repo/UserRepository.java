package com.recipe.repo;
import org.springframework.data.repository.CrudRepository;

import com.recipe.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByEmail(String email);
	
}