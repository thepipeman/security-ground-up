CREATE TABLE IF NOT EXISTS user_details (
  id          BIGSERIAL    NOT NULL,
  username    VARCHAR(64)  NOT NULL,
  password    VARCHAR(256) NOT NULL,
  first_name  VARCHAR(64)  NOT NULL,
  middle_name VARCHAR(64)  NOT NULL,
  last_name   VARCHAR(64)  NOT NULL,

  PRIMARY KEY (id)
);

CREATE UNIQUE INDEX user_uq_username
  ON user_details(username);


CREATE TABLE IF NOT EXISTS trip (
  id          BIGSERIAL   NOT NULL,
  destination VARCHAR(64) NOT NULL,
  user_id     BIGINT      NOT NULL,

  PRIMARY KEY (id),
  CONSTRAINT trip_fk_user_id
    FOREIGN KEY (user_id)
      REFERENCES user_details(id)
);

