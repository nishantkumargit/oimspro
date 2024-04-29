package com.pragya.oimspro.company.service;

import com.pragya.oimspro.company.entity.Company;
import com.pragya.oimspro.registration.dto.CompanyRegistrationRequest;
import org.springframework.stereotype.Service;

@Service
public interface CompanyService {
    Company createCompany(Company company);

    Company registerCompany(CompanyRegistrationRequest companyRegistrationRequest);
}
