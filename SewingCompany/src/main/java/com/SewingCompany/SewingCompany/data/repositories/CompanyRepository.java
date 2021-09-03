package com.SewingCompany.SewingCompany.data.repositories;

import com.SewingCompany.SewingCompany.data.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {}
