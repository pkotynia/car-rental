DROP TABLE IF EXISTS reservation;
DROP TABLE IF EXISTS company_branch;
DROP TABLE IF EXISTS car_rental;

CREATE TABLE car_rental
(   id              BIGINT AUTO_INCREMENT UNIQUE PRIMARY KEY,
    name            VARCHAR(255) NOT NULL,
    internet_domain VARCHAR(255) NOT NULL,
    address         VARCHAR(255) NOT NULL,
    owner           VARCHAR(255) NOT NULL
);

CREATE TABLE company_branch
(   id            BIGINT AUTO_INCREMENT UNIQUE PRIMARY KEY,
    name          VARCHAR(255) NOT NULL,
    car_rental_id BIGINT,
    FOREIGN KEY (car_rental_id) REFERENCES car_rental (id)
);

CREATE TABLE reservation
(   id   BIGINT AUTO_INCREMENT UNIQUE PRIMARY KEY,
    customer VARCHAR(255) NOT NULL,
    car VARCHAR(255) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    price DECIMAL(7, 2) NOT NULL,
    start_branch_id BIGINT,
    end_branch_id BIGINT,
    FOREIGN KEY (start_branch_id) REFERENCES company_branch (id),
    FOREIGN KEY (end_branch_id) REFERENCES company_branch (id)
);