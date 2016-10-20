--DROP TABLE users IF EXISTS;

CREATE TABLE users (
  mobileNo        VARCHAR(10) PRIMARY KEY,
  name VARCHAR(30),
  password VARCHAR(30),
  email  VARCHAR(50)
);
