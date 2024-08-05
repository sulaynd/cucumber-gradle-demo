package com.docker.restful.demo.db;

import com.docker.restful.demo.entities.User;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public interface SimpleDB {

	Map<String, User> getInstance();

}