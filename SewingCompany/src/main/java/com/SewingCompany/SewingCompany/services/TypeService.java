package com.SewingCompany.SewingCompany.services;

import com.SewingCompany.SewingCompany.data.entities.Type;
import com.SewingCompany.SewingCompany.data.repositories.TypeRepository;
import com.SewingCompany.SewingCompany.exceptions.CustomErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TypeService {
    @Autowired
    private TypeRepository typeRepository;

    public void addEmployeeType(Type type) throws CustomErrorException {
        this.verifyEmployeeType(type);

        typeRepository.save(type);
    }

    public void verifyEmployeeType(Type type) throws CustomErrorException {
        if (type.getEmployeeType().isBlank() || String.valueOf(type.getSalaryPerHour()).isBlank() ||
                String.valueOf(type.getMaxDetails()).isBlank()) {
            throw new CustomErrorException("Please enter all fields!");
        }
        if (!Double.toString(type.getSalaryPerHour()).matches("^\\d+\\.\\d+")) {
            throw new CustomErrorException("Field Salary per hour must contains only numbers!");
        }
        if (!Integer.toString(type.getMaxDetails()).matches("[0-9]+")) {
            throw new CustomErrorException("Field Max details must contains only numbers!");
        }

        if (type.getSalaryPerHour() <= 0) {
            throw new CustomErrorException("Field Salary per hour must be > 0!");
        }
        if (type.getMaxDetails() <= 0) {
            throw new CustomErrorException("Field Max details must be > 0!");
        }
    }

    public List<Type> getEmployeeTypes() {
        return typeRepository.findAll();
    }
}

