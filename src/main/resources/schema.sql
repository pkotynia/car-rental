DROP TABLE IF EXISTS company_branch;
DROP TABLE IF EXISTS car_rental;
CREATE TABLE car_rental (
    id              BIGINT AUTO_INCREMENT UNIQUE PRIMARY KEY,
    name            VARCHAR(255) NOT NULL,
    internet_domain VARCHAR(255) NOT NULL,
    address         VARCHAR(255) NOT NULL,
    owner           VARCHAR(255) NOT NULL
);

CREATE TABLE company_branch (
    id            BIGINT AUTO_INCREMENT UNIQUE PRIMARY KEY,
    name          VARCHAR(255) NOT NULL,
    car_rental_id BIGINT,
    FOREIGN KEY (car_rental_id) REFERENCES car_rental (id)
);