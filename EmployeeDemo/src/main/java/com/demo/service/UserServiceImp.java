package com.demo.service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.dao.RoleRepository;
import com.demo.dao.UserRepository;
import com.demo.dto.UserRegistrationDTO;
import com.demo.entities.Role;
import com.demo.entities.User;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private RoleRepository roleRepo;

	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		User user = userRepo.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				mapRolesToAuthorities(user.getRole()));

	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
	}

	@Override
	public User save(UserRegistrationDTO userRegistationDTO) {
		Role role = new Role();
		if (userRegistationDTO.getRole().equals("USER"))
			role = roleRepo.findByRole("USER");
		else if (userRegistationDTO.getRole().equals("ADMIN"))
			role = roleRepo.findByRole("ADMIN");
		User user = new User();
		user.setEmail(userRegistationDTO.getEmail());
		user.setName(userRegistationDTO.getUsername());
		user.setPassword(passwordEncoder.encode(userRegistationDTO.getPassword()));
		user.setRole(role);

		return userRepo.save(user);
	}
}
