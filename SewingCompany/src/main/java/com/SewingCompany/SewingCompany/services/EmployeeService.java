package com.SewingCompany.SewingCompany.services;

import com.SewingCompany.SewingCompany.data.entities.Employee;
import com.SewingCompany.SewingCompany.exceptions.CustomErrorException;
import com.SewingCompany.SewingCompany.data.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public void addEmployee(Employee employee) throws CustomErrorException {
        this.verifyEmployee(employee);

        employeeRepository.save(employee);
    }

    public void verifyEmployee(Employee employee) throws CustomErrorException {
        if (employee.getFirstName().isBlank() || employee.getLastName().isBlank() ||
                employee.getCompany().getName().isBlank() ||
                employee.getType().getEmployeeType().isBlank()) {
            throw new CustomErrorException("Please enter all fields!");
        }
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }
}
