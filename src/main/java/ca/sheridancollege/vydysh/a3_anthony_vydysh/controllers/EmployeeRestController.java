package ca.sheridancollege.vydysh.a3_anthony_vydysh.controllers;

import ca.sheridancollege.vydysh.a3_anthony_vydysh.beans.Employee;
import ca.sheridancollege.vydysh.a3_anthony_vydysh.repositories.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeRestController {

    private final EmployeeRepository employeeRepository;

    // Constructor injection of the employee repository.
    public EmployeeRestController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Retrieves all employees from the database
    @GetMapping("/api/employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Retrieves a single employee by their ID
    @GetMapping("/api/employees/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    // Retrieves an employee by their unique email address
    @GetMapping("/api/employees/email/{email}")
    public Employee getEmployeeByEmail(@PathVariable String email) {
        return employeeRepository.findByEmail(email).orElse(null);
    }

    // If empId is null a new 11-digit ID is generated
    @PostMapping("/api/employees")
    public Employee addEmployee(@RequestBody Employee emp) {
        if (emp.getEmpId() == null) {
            emp.setEmpId(generateRandom11DigitId());
        }
        return employeeRepository.save(emp);
    }

    //Updates an existing employee record by ID.
    @PutMapping("/api/employees/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee emp) {
        emp.setEmpId(id); // ensure the employee ID is used for update
        return employeeRepository.save(emp);
    }

    // Generates a random 11-digit ID for new employees.
    private Long generateRandom11DigitId() {
        return (long)(Math.random() * 9_000_000_0000L + 1_000_000_0000L);
    }
}
