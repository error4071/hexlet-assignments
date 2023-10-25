DROP TABLE IF EXISTS goods;

CREATE TABLE goods (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       title VARCHAR(255) NOT NULL,
                       price int
);
