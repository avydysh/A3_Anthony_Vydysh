DROP TABLE IF EXISTS emp_dept;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS department;

CREATE TABLE employee (
                          emp_id BIGINT PRIMARY KEY,
                          first_name VARCHAR(45),
                          last_name VARCHAR(45),
                          email VARCHAR(255) UNIQUE,
                          gender ENUM('M','F'),
                          hire_date DATE
);

CREATE TABLE department (
                            dept_id BIGINT PRIMARY KEY,
                            dept_name VARCHAR(45),
                            email VARCHAR(45) UNIQUE,
                            phone VARCHAR(45)
);

CREATE TABLE emp_dept (
                          emp_id BIGINT,
                          dept_id BIGINT,
                          from_date DATE,
                          to_date DATE,
                          PRIMARY KEY (emp_id, dept_id),
                          FOREIGN KEY (emp_id) REFERENCES employee(emp_id),
                          FOREIGN KEY (dept_id) REFERENCES department(dept_id)
);