package com.monisha.springSecurityJpa;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.monisha.springSecurityJpa.model.User;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user= userRepository.findByUsername(username);
		user.orElseThrow(() -> new UsernameNotFoundException("Not Found:"+username));
		return user.map(MyUserDetails::new).get();
	}

}
