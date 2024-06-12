package com.crocobet.customer.notification.system.service;

import com.crocobet.customer.notification.system.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
  Optional<User> findByUsername(String username);

  @Override
  UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

  List<User> getAllUsers();

  User getUserById(Long id);

  void addUser(User user);

  void updateUser(Long id, User user);

  void deleteUser(Long id);
}
