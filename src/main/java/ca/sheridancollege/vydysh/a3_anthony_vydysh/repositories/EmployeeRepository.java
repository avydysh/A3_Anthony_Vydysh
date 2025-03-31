package ca.sheridancollege.vydysh.a3_anthony_vydysh.repositories;

import ca.sheridancollege.vydysh.a3_anthony_vydysh.beans.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    //A container object which may or may not contain a non-null value. If a value is present, isPresent() returns true. If no value is present, the object is considered empty and isPresent() returns false.
    Optional<Employee> findByEmail(String email);
}
