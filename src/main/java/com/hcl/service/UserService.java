package com.hcl.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.entity.User;
import com.hcl.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository urepo;

	public boolean validateByID(Long id) {
		return urepo.existsById(id);
		
	}

	public User findByID(Long id) {
		Optional<User> u = Optional.of(new User());
		if (validateByID(id)) 
		{
			u = urepo.findById(id);
		}
		if (u.isPresent()) {
			User o = new User();
			return o = (User) u.get();
		}
		return null;
	}

	public List<User> getAllUsers(){
		return urepo.findAll();
	}
	public User save(User u) {
		urepo.save(u);
		return null;
	}
}
