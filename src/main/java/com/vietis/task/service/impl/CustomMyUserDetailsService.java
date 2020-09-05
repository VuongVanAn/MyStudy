package com.vietis.task.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vietis.task.model.User;
import com.vietis.task.repository.UserRepository;

@Service
public class CustomMyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("User Not Found");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getGrantedAuthority(user));
	}
	
	private Collection<GrantedAuthority> getGrantedAuthority(User user) {
		Collection<GrantedAuthority> grantedAuthority = new ArrayList<GrantedAuthority>();
		if (user.getRoles().getName().equals("ADMIN")) {
			grantedAuthority.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		} else if (user.getRoles().getName().equals("TEACHER")) {
			grantedAuthority.add(new SimpleGrantedAuthority("ROLE_TEACHER"));
		} else {
			grantedAuthority.add(new SimpleGrantedAuthority("ROLE_USER"));
		}
		return grantedAuthority;
	}

}
