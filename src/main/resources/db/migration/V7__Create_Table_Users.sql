CREATE TABLE users (
  id SERIAL PRIMARY KEY,
  user_name varchar(10) DEFAULT NULL UNIQUE,
  full_name varchar(30) DEFAULT NULL,
  password varchar(255) DEFAULT NULL,
  account_non_expired bit(1) DEFAULT NULL,
  account_non_locked bit(1) DEFAULT NULL,
  credentials_non_expired bit(1) DEFAULT NULL,
  enabled bit(1) DEFAULT NULL
);