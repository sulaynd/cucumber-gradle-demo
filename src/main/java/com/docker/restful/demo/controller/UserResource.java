package com.docker.restful.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.docker.restful.demo.entities.User;
import com.docker.restful.demo.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController

// @Path("/users")
public class UserResource {
	Logger log = LoggerFactory.getLogger(SysInfoResource.class);
	@Autowired
	private UserService userService;

	@RequestMapping(path = "/users", method = RequestMethod.GET)
	// @Produces("application/json")
	@CrossOrigin
	public List<User> getUsers() {
		log.debug("getUsers start");
		final List<User> users = userService.findByPattern();
		log.debug("users size is {}",users.size());
		log.debug("getUsers end");
		return users;
	}

	@PostMapping(path = "/users")
	@CrossOrigin
	public void createUser(@RequestBody User user) {
		log.debug("createUser start");
		log.debug("createUser detail is {} ", user.toString());
		userService.save(user);
		log.debug("createUser end");
	}


	@RequestMapping(value = "/users/{id}",method = RequestMethod.DELETE)
	@CrossOrigin
	public void deleteUser(@PathVariable("id") String userId) {
		//	System.out.println("Change.");
		log.debug("deleteUser start");
		log.debug("delete User ID is {}",userId);
		userService.delete(userId);
		log.debug("deleteUser end");

	}

//	@GET
//	@Path("/{id}")
//	@Produces("application/json")
//	@CrossOrigin
//	public Response getUsers(@PathParam("id") final String userId) {
//		User user = userService.findById(userId);
//		if (user == null) {
//			return Response.status(404).build();
//		}
//		return Response.status(200).entity(user).build();
//	}

//	@PUT
//	@Path("/{id}")
//	@Consumes("application/json")
//	@Produces("application/json")
//	@CrossOrigin
//	public Response updateUser(@PathParam("id") final String userId, final User user) {
//		user.setId(userId);
//		userService.update(user);
//		return Response.status(200).entity(user).build();
//	}

}
