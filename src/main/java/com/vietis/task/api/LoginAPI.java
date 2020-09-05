package com.vietis.task.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vietis.task.dto.JwtRequest;
import com.vietis.task.service.impl.CustomMyUserDetailsService;
import com.vietis.task.util.JwtTokenUtil;

@RestController
@CrossOrigin
public class LoginAPI {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private CustomMyUserDetailsService userDetailsService;
	
	@PostMapping(value = "/api/login")
	public ResponseEntity<?> login(@RequestBody JwtRequest loginRequest) throws Exception {
		
		authenticate(loginRequest.getEmail(), loginRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return new ResponseEntity<>(token, HttpStatus.OK);
	}


	private void authenticate(String email, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

}
