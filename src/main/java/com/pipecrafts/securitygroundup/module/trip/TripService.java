package com.pipecrafts.securitygroundup.module.trip;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
@RequiredArgsConstructor
public class TripService {

  private final TripMapper tripMapper;

  @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
  public Collection<Trip> readByUserId(Long userId) {
    return tripMapper.selectByUserId(userId);
  }

}
