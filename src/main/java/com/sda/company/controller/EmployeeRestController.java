package com.sda.company.controller;

import com.sda.company.entities.Employee;
import com.sda.company.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rest/employee")
public class EmployeeRestController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getEmployees() {
        List<Employee> allEmployees = employeeService.findAll();

        if(allEmployees.size() == 0 || allEmployees == null)
            return (ResponseEntity) ResponseEntity.status(HttpStatus.NO_CONTENT);

        return ResponseEntity.ok(allEmployees);
    }

    @PostMapping("/create")
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.save(employee));
    }

    @RequestMapping("/update")
    public ResponseEntity<Employee> updateEmployee(@Valid @RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.update(employee));
    }

    @RequestMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        employeeService.delete(id);

        return ResponseEntity.ok("Employee was deleted.");
    }

    @RequestMapping("/searchByEmployeeName")
    public ResponseEntity<List<Employee>> searchByName(@RequestParam(value = "employeeName") String name) {
        List<Employee> employeeList = employeeService.findAllEmployeesByName(name);

        return ResponseEntity.ok(employeeList);
    }

}










