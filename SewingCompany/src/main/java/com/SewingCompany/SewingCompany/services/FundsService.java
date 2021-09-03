package com.SewingCompany.SewingCompany.services;

import com.SewingCompany.SewingCompany.data.entities.Funds;
import com.SewingCompany.SewingCompany.data.entities.Company;
import com.SewingCompany.SewingCompany.data.repositories.FundsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FundsService {

    @Autowired
    private FundsRepository fundsRepository;

    @Autowired
    private CompanyService companyService;

    public List<Funds> getFunds() {
        return fundsRepository.findAll();
    }
    public void deleteFunds() {fundsRepository.deleteAll();}

    public void saveFunds(){
        for(Company company: companyService.getCompanies()){
            Funds funds = new Funds();

            funds.setCompany(company);
            funds.setProfit(companyService.getProfit(company));
            funds.setLoss(companyService.getLoss(company));
            funds.setIncome(funds.getProfit() - funds.getLoss());
            funds.setNetIncome(funds.getIncome() - (funds.getIncome() * 0.2));

            fundsRepository.save(funds);
        }
    }
}
