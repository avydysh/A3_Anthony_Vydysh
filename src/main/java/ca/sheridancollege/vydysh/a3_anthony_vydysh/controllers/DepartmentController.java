package ca.sheridancollege.vydysh.a3_anthony_vydysh.controllers;

import ca.sheridancollege.vydysh.a3_anthony_vydysh.beans.Department;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

//Controller responsible for handling web requests related to Department management.
@Controller
@RequestMapping("/department")
public class DepartmentController {

    // Injects the base URL of the Department REST API from application.properties
    @Value("${DEPARTMENT_API_URL}")
    private String deptApiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    //Fetches all departments from the REST API and adds them to the model to be displayed.
    @GetMapping
    public String showDepartmentForm(Model model) {
        // Send GET request to fetch all departments
        ResponseEntity<Department[]> response =
                restTemplate.getForEntity(deptApiUrl, Department[].class);

        // Convert the array response to a List
        List<Department> departments = Arrays.asList(response.getBody());

        // Add data to the model to be used in the view
        model.addAttribute("departments", departments);
        model.addAttribute("pageTitle", "Manage Departments");

        return "departmentForm";
    }

    // Sends the department data to the REST API to either create or update a department.
    @PostMapping("/save")
    public String saveDepartment(@ModelAttribute Department dept) {
        // Set headers to indicate JSON content
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Wrap the department in an HttpEntity
        HttpEntity<Department> request = new HttpEntity<>(dept, headers);

        // If deptId is null, its a new department uses POST
        if (dept.getDeptId() == null) {
            restTemplate.postForObject(deptApiUrl, request, Department.class);
        } else {
            // Otherwise update existing department uses PUT
            restTemplate.exchange(
                    deptApiUrl + "/" + dept.getDeptId(),
                    HttpMethod.PUT,
                    request,
                    Void.class
            );
        }

        return "redirect:/department";
    }
}
