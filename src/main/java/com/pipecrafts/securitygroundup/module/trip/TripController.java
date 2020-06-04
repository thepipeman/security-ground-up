package com.pipecrafts.securitygroundup.module.trip;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/trips")
@RequiredArgsConstructor
public class TripController {

  private final TripService tripService;

  @GetMapping
  public ResponseEntity<Collection<Trip>> readTripsByUserId(@RequestParam("userId") Long userId) {
    Collection<Trip> trips = tripService.readByUserId(userId);
    return ResponseEntity.ok(trips);
  }

}
