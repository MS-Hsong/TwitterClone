package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{
    Role findByRole(String role);
}