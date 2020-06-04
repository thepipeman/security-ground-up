package com.pipecrafts.securitygroundup.module.trip;

import lombok.*;
import org.apache.ibatis.type.Alias;

@Data
@Alias("Trip")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Trip {
  private Long id;
  private String destination;
  private Long userId;
}
