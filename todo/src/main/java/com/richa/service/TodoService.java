package com.richa.service;

import java.util.List;
import java.util.Optional;

import com.richa.entity.Todo;

public interface TodoService {

    List<Todo> getAllTodos();

    Optional<Todo> getTodoById(Long id);

    Todo createTodo(Todo todo);

    Optional<Todo> updateTodo(Long id, Todo updatedTodo);

    void deleteTodo(Long id);
}