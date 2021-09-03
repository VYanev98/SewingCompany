package com.SewingCompany.SewingCompany.data.repositories;

import com.SewingCompany.SewingCompany.data.entities.Funds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FundsRepository extends JpaRepository<Funds, Long> {}
