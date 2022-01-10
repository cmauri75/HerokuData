package com.example.dataload;

import org.springframework.data.repository.CrudRepository;

public interface IUserDAO extends CrudRepository<User, Long> {
}
