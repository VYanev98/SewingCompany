package com.SewingCompany.SewingCompany.services;

import com.SewingCompany.SewingCompany.data.entities.Company;
import com.SewingCompany.SewingCompany.data.entities.Employee;
import com.SewingCompany.SewingCompany.data.entities.Detail;
import com.SewingCompany.SewingCompany.exceptions.CustomErrorException;
import com.SewingCompany.SewingCompany.data.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    public void addCompany(Company company) throws CustomErrorException {
        this.verifyCompany(company);

        companyRepository.save(company);
    }

    public void verifyCompany(Company company) throws CustomErrorException {
        if (company.getName().isBlank() || company.getOwner().isBlank() ||
                company.getBulstat().isBlank() ||
                company.getAddress().isBlank()) {
            throw new CustomErrorException("Please enter all fields!");
        }
        if (!company.getBulstat().matches("[0-9]+")) {
            throw new CustomErrorException("Field Bulstat must contains only numbers!");
        }
    }

    public List<Company> getCompanies() {
        return companyRepository.findAll();
    }

    public double getProfit(Company company) {
        double res = 0;
        for (Employee employee : company.getEmployees()) {
            for (Detail detail : employee.getDetails()) {
                res += detail.getDetailSellPrice();
            }
        }
        return res;
    }

    public double getLoss(Company company) {
        double res = 0;
        for (Employee employee : company.getEmployees()) {
            for (Detail detail : employee.getDetails()) {
                res += (detail.getMaterialCosts() + (detail.getHoursToMake() * employee.getType().getSalaryPerHour()));
            }
        }
        return res;
    }
}
