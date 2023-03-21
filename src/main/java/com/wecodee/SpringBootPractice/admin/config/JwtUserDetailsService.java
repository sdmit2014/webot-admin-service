package com.wecodee.SpringBootPractice.admin.config;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wecodee.SpringBootPractice.admin.constant.ResponseMessage;
import com.wecodee.SpringBootPractice.usermanagement.model.User;
import com.wecodee.SpringBootPractice.usermanagement.repository.UsersRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UsersRepository bmUsersRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("username->" + username);
		com.wecodee.SpringBootPractice.usermanagement.model.User users = bmUsersRepository.getByUserId(username);
		if (users != null) {

			return new org.springframework.security.core.userdetails.User(users.getUserId(), users.getPassword(),
					new ArrayList<>());

		} else {
			throw new UsernameNotFoundException(ResponseMessage.USER_NOT_FOUND.getMessage() + username);
		}
	}

}
