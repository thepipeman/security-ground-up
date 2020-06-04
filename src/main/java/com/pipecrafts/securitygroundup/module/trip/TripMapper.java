package com.pipecrafts.securitygroundup.module.trip;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;

@Mapper
interface TripMapper {

  Collection<Trip> selectByUserId(@Param("userId") Long userId);

}
