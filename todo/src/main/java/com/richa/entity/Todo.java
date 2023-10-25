package com.richa.entity;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Todo {
	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String todo;
	

	public Todo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Todo(String todo) {
		super();
		this.todo = todo;
	}

	public String getTodo() {
		return todo;
	}

	public void setTodo(String todo) {
		this.todo = todo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(todo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Todo other = (Todo) obj;
		return Objects.equals(todo, other.todo);
	}

	@Override
	public String toString() {
		return "Todo [todo=" + todo + "]";
	}
	
	

}
