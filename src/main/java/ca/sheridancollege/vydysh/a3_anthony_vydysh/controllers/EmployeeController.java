package ca.sheridancollege.vydysh.a3_anthony_vydysh.controllers;

import ca.sheridancollege.vydysh.a3_anthony_vydysh.beans.Employee;

import org.springframework.http.*;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

// Controller that handles all front-end requests related to Employee operations.
@Controller
@RequestMapping("/employee")
public class EmployeeController {

    // Injects the employee API URL from application.properties
    @Value("${EMPLOYEE_API_URL}")
    private String employeeApiUrl;

    // Used to perform HTTP calls to the RESTful employee API
    private final RestTemplate restTemplate = new RestTemplate();

    // Fetches all employees from the REST API and displays them on the employeeForm.html
    @GetMapping
    public String showEmployeeForm(Model model) {
        // Send GET request to retrieve all employees from the backend API
        ResponseEntity<Employee[]> response =
                restTemplate.getForEntity(employeeApiUrl, Employee[].class);

        // Convert the response body to a list
        List<Employee> employees = Arrays.asList(response.getBody());
        model.addAttribute("employees", employees);
        return "employeeForm";
    }

    //Either creates a new employee or updates an existing one depending on whether empId is null.
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute Employee employee) {
        // Set request headers to indicate JSON payload
        HttpHeaders headers = new HttpHeaders();

        // Wrap the employee in an HttpEntity to include headers
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Employee> request = new HttpEntity<>(employee, headers);

        if (employee.getEmpId() == null) {
            // If empId is null this is a new employee send POST request
            restTemplate.postForObject(employeeApiUrl, request, Employee.class);
        } else {
            // If empId exists update existing employee send PUT request
            restTemplate.exchange(employeeApiUrl + "/" + employee.getEmpId(),
                    HttpMethod.PUT, request, Void.class);
        }

        return "redirect:/employee";
    }
}
