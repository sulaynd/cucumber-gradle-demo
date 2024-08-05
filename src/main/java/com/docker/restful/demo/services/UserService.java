package com.docker.restful.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.docker.restful.demo.db.SimpleDB;
import com.docker.restful.demo.entities.User;

@Service
public class UserService {
@Autowired
	private SimpleDB simpleDB;
	
	public List<User> findByPattern() {
		return  new ArrayList<User>(simpleDB.getInstance().values());
	}

	public User findById(final String userId) {
		User user = simpleDB.getInstance().get(userId);
		if(user == null) {
			throw new ResponseStatusException(
					  HttpStatus.NOT_FOUND, "User does not exist in the DB"
					);
		}
		return user;
	}

	public void save(final User user) {
		String id = UUID.randomUUID().toString();;
		user.setId(id);
		simpleDB.getInstance().put(id, user);
		
	}

	public void update(final User user) {
		if(simpleDB.getInstance().containsKey(user.getId())) {
			simpleDB.getInstance().put(user.getId(), user);
		}else {
			throw new ResponseStatusException(
					  HttpStatus.NOT_FOUND, "User does not exist in the DB"
					);
		}
	}

	public void delete(final String userId) {

		if(simpleDB.getInstance().containsKey(userId)) {
			simpleDB.getInstance().remove(userId);
		}else {
			throw new ResponseStatusException(
					  HttpStatus.NOT_FOUND, "User does not exist in the DB"
					);
		}
	}
}

