package com.SewingCompany.SewingCompany.controllers;

import com.SewingCompany.SewingCompany.services.FundsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FundsController {

    @Autowired
    private FundsService fundsService;

    @GetMapping("/funds")
    public String getEmployees(Model model) {
        fundsService.deleteFunds();
        fundsService.saveFunds();
        model.addAttribute("FundsList", fundsService.getFunds());
        return "funds";
    }
}
