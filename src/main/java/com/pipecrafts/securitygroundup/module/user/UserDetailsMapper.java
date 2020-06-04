package com.pipecrafts.securitygroundup.module.user;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
interface UserDetailsMapper {

  UserDetails selectByUserName(@Param("username") String userName);

}
