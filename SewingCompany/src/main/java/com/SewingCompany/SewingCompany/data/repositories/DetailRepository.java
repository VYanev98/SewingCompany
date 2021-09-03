package com.SewingCompany.SewingCompany.data.repositories;

import com.SewingCompany.SewingCompany.data.entities.Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailRepository extends JpaRepository<Detail, Long> {}
