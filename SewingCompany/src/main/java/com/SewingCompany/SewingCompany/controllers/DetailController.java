package com.SewingCompany.SewingCompany.controllers;

import com.SewingCompany.SewingCompany.data.entities.Detail;
import com.SewingCompany.SewingCompany.exceptions.CustomErrorException;
import com.SewingCompany.SewingCompany.services.DetailService;
import com.SewingCompany.SewingCompany.services.EmployeeService;
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
public class DetailController {

    @Autowired
    private DetailService detailService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value="/detail")
    public String getDetails(Model model) {
        model.addAttribute("DetailsList", detailService.getDetails());
        return "detail";
    }

    @RequestMapping("/detail/add-new-detail")
    public String addNewDetail(Model model) {
        model.addAttribute("detail", new Detail());
        model.addAttribute("EmployeesList", employeeService.getEmployees());

        return "Add-New-Detail";
    }

    @PostMapping("/detail/add-new-detail")
    public String addNewDetail(@ModelAttribute Detail detail, BindingResult bindingResult, RedirectAttributes redirAttrs) {
        if (bindingResult.hasErrors()) {
            return "redirect:/detail";
        }
        try {
            detailService.addDetail(detail);
            redirAttrs.addFlashAttribute("success", "Detail added!");
        } catch (CustomErrorException e) {
            redirAttrs.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/detail";
    }
}
