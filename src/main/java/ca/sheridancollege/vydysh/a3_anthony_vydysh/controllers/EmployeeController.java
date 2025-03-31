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

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Value("${EMPLOYEE_API_URL}")
    private String employeeApiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping
    public String showEmployeeForm(Model model) {
        ResponseEntity<Employee[]> response =
                restTemplate.getForEntity(employeeApiUrl, Employee[].class);
        List<Employee> employees = Arrays.asList(response.getBody());
        model.addAttribute("employees", employees);
        return "employeeForm";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute Employee employee) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Employee> request = new HttpEntity<>(employee, headers);

        if (employee.getEmpId() == null) {
            restTemplate.postForObject(employeeApiUrl, request, Employee.class);
        } else {
            restTemplate.exchange(employeeApiUrl + "/" + employee.getEmpId(),
                    HttpMethod.PUT, request, Void.class);
        }

        return "redirect:/employee";
    }
}
