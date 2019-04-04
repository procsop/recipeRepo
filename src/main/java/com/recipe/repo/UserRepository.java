package com.recipe.repo;
import org.springframework.data.repository.CrudRepository;

import com.recipe.entity.User;

/**
 * UserRepository interface.
 */
public interface UserRepository extends CrudRepository<User, Long> {
	/**
	 * Find user by email.
	 * @param email
	 * @return
	 */
	User findByEmail(String email);	
}