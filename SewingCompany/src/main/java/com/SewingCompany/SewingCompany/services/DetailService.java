package com.SewingCompany.SewingCompany.services;

import com.SewingCompany.SewingCompany.data.entities.Detail;
import com.SewingCompany.SewingCompany.data.repositories.DetailRepository;
import com.SewingCompany.SewingCompany.exceptions.CustomErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailService {
    @Autowired
    private DetailRepository detailRepository;

    public void addDetail(Detail detail) throws CustomErrorException {
        this.verifyDetail(detail);

        detailRepository.save(detail);
    }

    public void verifyDetail(Detail detail) throws CustomErrorException {
        if (detail.getDetailType().isBlank() || detail.getDetailColor().isBlank() ||
                String.valueOf(detail.getMaterialCosts()).isBlank() ||
                String.valueOf(detail.getHoursToMake()).isBlank() ||
                detail.getEmployee().getFirstName().isBlank() ||
                String.valueOf(detail.getDetailSellPrice()).isBlank()) {
            throw new CustomErrorException("Please enter all fields!");
        }

        if (!Double.toString(detail.getMaterialCosts()).matches("^\\d+\\.\\d+")) {
            throw new CustomErrorException("Field Material costs must contains only numbers!");
        }

        if (!Double.toString(detail.getHoursToMake()).matches("^\\d+\\.\\d+")) {
            throw new CustomErrorException("Field Hours to make must contains only numbers!");
        }

        if (!Double.toString(detail.getDetailSellPrice()).matches("^\\d+\\.\\d+")) {
            throw new CustomErrorException("Field Detail sell price must contains only numbers!");
        }

        if (detail.getMaterialCosts() <= 0) {
            throw new CustomErrorException("Field Material costs must be > 0!");
        }

        if (detail.getHoursToMake() <= 0) {
            throw new CustomErrorException("Field Hours to make must be > 0!");
        }

        if (detail.getDetailSellPrice() <= 0) {
            throw new CustomErrorException("Field Detail sell price must be > 0!");
        }

        if (detail.getEmployee().getDetails().size() >= detail.getEmployee().getType().getMaxDetails()) {
            throw new CustomErrorException("This employee can not make more details!");
        }
    }

    public List<Detail> getDetails() {
        return detailRepository.findAll();
    }
}
