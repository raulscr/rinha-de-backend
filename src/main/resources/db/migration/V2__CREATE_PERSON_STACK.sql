DROP TABLE IF EXISTS stack;
CREATE TABLE stack (
    id INT PRIMARY KEY AUTO_INCREMENT,
    stack VARCHAR(32)
);

DROP TABLE IF EXISTS person_stack;
CREATE TABLE person_stack (
    person_id INT,
    stack_id INT,
    CONSTRAINT PRIMARY KEY (person_id, stack_id)
);