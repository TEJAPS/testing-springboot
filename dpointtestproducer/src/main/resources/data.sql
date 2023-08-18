CREATE TABLE employees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL
);
INSERT INTO employees (first_name, last_name, email) VALUES ('John', 'Doe', 'john.doe@example.com');
INSERT INTO employees (first_name, last_name, email) VALUES ('Jane', 'Smith', 'jane.smith@example.com');
INSERT INTO employees (first_name, last_name, email) VALUES ('Michael', 'Johnson', 'michael.johnson@example.com');
