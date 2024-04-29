package com.pragya.oimspro.company.service.impl;

import com.pragya.oimspro.company.entity.Company;
import com.pragya.oimspro.company.repository.CompanyRepository;
import com.pragya.oimspro.company.service.CompanyService;
import com.pragya.oimspro.registration.dto.CompanyRegistrationRequest;
import com.pragya.oimspro.user.entity.User;
import com.pragya.oimspro.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserService userService;

    @Override
    public Company registerCompany(CompanyRegistrationRequest companyRegistrationRequest) {
        Company company = Company.builder()
                .name(companyRegistrationRequest.getName())
                .address(companyRegistrationRequest.getAddress())
                .website(companyRegistrationRequest.getWebsite())
                .contactMailId(companyRegistrationRequest.getContactMailId())
                .contactNumber(companyRegistrationRequest.getContactNumber())
                .build();
        User user = userService.getUserByEmailId(companyRegistrationRequest.getUserEmailId());
        company.setUser(user);
        company = createCompany(company);
        return company;
    }

    @Transactional
    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }
}
