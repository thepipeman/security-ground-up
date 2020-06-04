package com.pipecrafts.securitygroundup.module.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

  private final UserDetailsMapper userDetailsMapper;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userDetailsMapper.selectByUserName(username);
  }
}
