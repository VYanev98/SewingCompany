package com.SewingCompany.SewingCompany.controllers;

import com.SewingCompany.SewingCompany.data.entities.Employee;
import com.SewingCompany.SewingCompany.data.entities.Type;
import com.SewingCompany.SewingCompany.exceptions.CustomErrorException;
import com.SewingCompany.SewingCompany.services.CompanyService;
import com.SewingCompany.SewingCompany.services.EmployeeService;
import com.SewingCompany.SewingCompany.services.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private TypeService typeService;

    //Employee
    @GetMapping("/employee")
    public String getEmployees(Model model) {
        model.addAttribute("EmployeesList", employeeService.getEmployees());
        return "employee";
    }

    @RequestMapping("/employee/add-new-employee")
    public String addNewEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("CompaniesList", companyService.getCompanies());
        model.addAttribute("TypesList", typeService.getEmployeeTypes());

        return "Add-New-Employee";
    }

    @PostMapping("/employee/add-new-employee")
    public String addNewEmployee(@ModelAttribute Employee employee, BindingResult bindingResult,  RedirectAttributes redirAttrs) {
        if (bindingResult.hasErrors()) {
            return "redirect:/employee";
        }
        try {
            employeeService.addEmployee(employee);
            redirAttrs.addFlashAttribute("success", "Employee added!");
        } catch (CustomErrorException e) {
            redirAttrs.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/employee";
    }

    //EmployeeTypes
    @GetMapping("/employeeType")
    public String getEmployeeTypes(Model model) {
        model.addAttribute("EmployeeTypesList", typeService.getEmployeeTypes());
        return "employeeType";
    }

    @RequestMapping("/employee/add-new-employeeType")
    public String addNewEmployeeType(Model model) {
        model.addAttribute("typeEmployee", new Type());

        return "Add-New-EmployeeType";
    }

    @PostMapping("/employee/add-new-employeeType")
    public String addNewEmployeeType(@ModelAttribute Type type, BindingResult bindingResult, RedirectAttributes redirAttrs) {
        if (bindingResult.hasErrors()) {
            return "redirect:/employeeType";
        }
        try {
            typeService.addEmployeeType(type);
            redirAttrs.addFlashAttribute("success", "Employee type added!");
        } catch (CustomErrorException e) {
            redirAttrs.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/employeeType";
    }
}
