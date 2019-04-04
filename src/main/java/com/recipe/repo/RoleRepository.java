package com.recipe.repo;
import org.springframework.data.repository.CrudRepository;

import com.recipe.entity.Role;

/**
 * RoleRepository interface.
 */
public interface RoleRepository extends CrudRepository<Role, Long> {
	/**
	 * Find by role.
	 */
	Role findByRole(String role);
}