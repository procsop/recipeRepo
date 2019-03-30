package com.recipe.repo;
import org.springframework.data.repository.CrudRepository;

import com.recipe.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

	Role findByRole(String role);
}