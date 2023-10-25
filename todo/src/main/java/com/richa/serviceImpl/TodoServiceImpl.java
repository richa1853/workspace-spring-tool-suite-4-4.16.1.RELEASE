package com.richa.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.richa.entity.Todo;
import com.richa.repository.TodoRepository;
import com.richa.service.TodoService;

import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }
    
    @Override
    public Optional<Todo> getTodoById(Long id) {
        return todoRepository.findById(id);
    }
    
    @Override
    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }
    
    @Override
    public Optional<Todo> updateTodo(Long id, Todo updatedTodo) {
        Optional<Todo> existingTodo = todoRepository.findById(id);

        if (existingTodo.isPresent()) {
            Todo todo = existingTodo.get();
            todo.setTodo(updatedTodo.getTodo());
            return Optional.of(todoRepository.save(todo));
        } else {
            return Optional.empty();
        }
    }
    
    @Override
    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }
}