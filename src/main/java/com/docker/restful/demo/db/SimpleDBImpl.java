package com.docker.restful.demo.db;

import com.docker.restful.demo.entities.User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@Service
public class SimpleDBImpl implements SimpleDB {
	Map<String, User> map = new ConcurrentHashMap<String, User>();

	public SimpleDBImpl() {

	}

	@Override
	public Map<String, User> getInstance() {
		return map;
	}
}
