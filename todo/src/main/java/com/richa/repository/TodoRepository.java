package com.richa.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.richa.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
