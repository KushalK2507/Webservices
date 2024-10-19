package com.kushal.hateoas.resource;

import java.awt.PageAttributes.MediaType;
import java.util.Arrays;
import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kushal.hateoas.model.Users;

@RestController
@RequestMapping("/rest/users")
public class UsersResource {

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Users> getAll() {

		Users user1 = new Users("Kushal", 200L);
		Users user2 = new Users("Hello", 100L);
		return Arrays.asList(user1, user2);
	}

	@RequestMapping(value = "hateoas/all", method = RequestMethod.GET, produces = MediaTypes.HAL_JSON_VALUE)
	public List<Users> getHateoasAll() {

		Users user1 = new Users("Kushal", 200L);
		Link link = ControllerLinkBuilder.linkTo(UsersResource.class).slash(user1.getName()).withSelfRel();
		user1.add(link);
		Users user2 = new Users("Hello", 100L);
		user2.add(link);

		return Arrays.asList(user1, user2);
	}

	@RequestMapping(value = "/{name}", method = RequestMethod.GET, produces = MediaTypes.HAL_JSON_VALUE)
	public long getUser(String name) {

		Users user1 = new Users("Kushal", 200L);
		if (user1.getName().equals(name)) {
			return user1.getSalary();
		}

		return 0L;
	}

}
