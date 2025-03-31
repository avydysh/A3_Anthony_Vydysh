package ca.sheridancollege.vydysh.a3_anthony_vydysh.controllers;

import ca.sheridancollege.vydysh.a3_anthony_vydysh.beans.Department;
import ca.sheridancollege.vydysh.a3_anthony_vydysh.repositories.DepartmentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Allows external systems or frontend components to interact with the Department table
@RestController
public class DepartmentRestController {

    private final DepartmentRepository departmentRepository;

    // Constructor injection of the department repository
    public DepartmentRestController(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    //Returns a list of all departments
    @GetMapping("/api/departments")
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    //Finds a department by its email address
    @GetMapping("/api/departments/email/{email}")
    public Department getDepartmentByEmail(@PathVariable String email) {
        return departmentRepository.findByEmail(email).orElse(null);
    }

    // Adds a new department, automatically assigns a random 11-digit ID
    @PostMapping("/api/departments")
    public Department addDepartment(@RequestBody Department dept) {
        if (dept.getDeptId() == null) {
            dept.setDeptId(generateRandom11DigitId());
        }
        return departmentRepository.save(dept);
    }

    // Updates an existing department using the provided ID
    @PutMapping("/api/departments/{id}")
    public Department updateDepartment(@PathVariable Long id, @RequestBody Department dept) {
        dept.setDeptId(id);
        return departmentRepository.save(dept);
    }
    // Method to generate a random 11-digit department ID
    private Long generateRandom11DigitId() {
        return (long)(Math.random() * 9_000_000_0000L + 1_000_000_0000L);
    }
}
