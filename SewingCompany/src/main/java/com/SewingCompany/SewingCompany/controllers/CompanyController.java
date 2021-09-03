package com.SewingCompany.SewingCompany.controllers;

import com.SewingCompany.SewingCompany.data.entities.Company;
import com.SewingCompany.SewingCompany.exceptions.CustomErrorException;
import com.SewingCompany.SewingCompany.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping(value="/company")
    public String getCompanies(Model model) {
        model.addAttribute("CompaniesList", companyService.getCompanies());
        return "company";
    }

    @RequestMapping("/company/add-new-company")
    public String addNewCompany(Model model) {
        model.addAttribute("company", new Company());

        return "Add-New-Company";
    }

    @PostMapping("/company/add-new-company")
    public String addNewCompany(@ModelAttribute Company company, BindingResult bindingResult, RedirectAttributes redirAttrs) {
        if (bindingResult.hasErrors()) {
            return "redirect:/company";
        }
        try {
            companyService.addCompany(company);
            redirAttrs.addFlashAttribute("success", "Company added!");
        } catch (CustomErrorException e) {
            redirAttrs.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/company";
    }

}
