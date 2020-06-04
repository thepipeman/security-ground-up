package com.pipecrafts.securitygroundup.module.user;

import lombok.*;
import org.apache.ibatis.type.Alias;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;

@Data
@Alias("UserDetails")
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {
  private Long id;
  private String username;
  private String password;
  private String firstName;
  private String middleName;
  private String lastName;
  private boolean enabled = true;
  private Collection<GrantedAuthority> authorities = new ArrayList<>();
  private boolean accountNonExpired = true;
  private boolean accountNonLocked = true;
  private boolean credentialsNonExpired = true;
}
