package com.kushal.hateoas.model;

import org.springframework.hateoas.ResourceSupport;

public class Users extends ResourceSupport {
	private String name;
	private Long salary;

	public Users(String name, Long salary) {

		this.name = name;
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public Long getSalary() {
		return salary;
	}

}
