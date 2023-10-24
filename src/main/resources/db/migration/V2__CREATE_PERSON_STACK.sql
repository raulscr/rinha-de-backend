DROP TABLE IF EXISTS person_stack;
CREATE TABLE person_stack (
    id INT PRIMARY KEY AUTO_INCREMENT,
    person_id INT NOT NULL,
    stack VARCHAR(32)
);