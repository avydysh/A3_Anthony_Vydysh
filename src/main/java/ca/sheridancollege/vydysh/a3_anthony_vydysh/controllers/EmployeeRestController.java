package ca.sheridancollege.vydysh.a3_anthony_vydysh.controllers;

import ca.sheridancollege.vydysh.a3_anthony_vydysh.beans.Employee;
import ca.sheridancollege.vydysh.a3_anthony_vydysh.repositories.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeRestController {

    private final EmployeeRepository employeeRepository;

    public EmployeeRestController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/api/employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/api/employees/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @GetMapping("/api/employees/email/{email}")
    public Employee getEmployeeByEmail(@PathVariable String email) {
        return employeeRepository.findByEmail(email).orElse(null);
    }

    @PostMapping("/api/employees")
    public Employee addEmployee(@RequestBody Employee emp) {
        if (emp.getEmpId() == null) {
            emp.setEmpId(generateRandom11DigitId());
        }
        return employeeRepository.save(emp);
    }

    @PutMapping("/api/employees/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee emp) {
        emp.setEmpId(id); // ensure ID is set for update
        return employeeRepository.save(emp);
    }

    private Long generateRandom11DigitId() {
        return (long)(Math.random() * 9_000_000_0000L + 1_000_000_0000L);
    }
}
