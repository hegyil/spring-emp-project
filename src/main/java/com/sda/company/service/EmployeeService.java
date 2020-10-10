package com.sda.company.service;

import com.sda.company.entities.Employee;
import com.sda.company.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EmployeeService {

    EmployeeRepository employeeRepository;

    public EmployeeService(@Autowired EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }


    @Transactional
    public Employee save(Employee employee) {
//        if (employee.getAddress() != null && employee.getAddress().size() > 0){
            return employeeRepository.save(employee);
//        }
//        throw new  Exception("Cannot create an Employee without an Address");
    }

    public List<Employee> findAllEmployeesByName(String name) {
        return employeeRepository.findAllByName(name);
    }


    public Employee update(Employee employee) {
        return employeeRepository.save(employee);
    }


    public Employee findById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);

        if(!employee.isPresent()){
            throw new NoSuchElementException("Invalid ID");
        }

        return employee.get();
    }


    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }

}
