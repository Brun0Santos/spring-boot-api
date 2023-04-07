CREATE TABLE books (
  id SERIAL NOT NULL,
  author TEXT NOT NULL,
  launch_date TIMESTAMP NOT NULL,
  price MONEY NOT NULL,
  title TEXT NOT NULL
);
