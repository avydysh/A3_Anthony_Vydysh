package ca.sheridancollege.vydysh.a3_anthony_vydysh.repositories;

import ca.sheridancollege.vydysh.a3_anthony_vydysh.beans.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findByEmail(String email);
}
