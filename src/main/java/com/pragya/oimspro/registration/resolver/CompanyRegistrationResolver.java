package com.pragya.oimspro.registration.resolver;

import com.pragya.oimspro.company.entity.Company;
import com.pragya.oimspro.company.service.CompanyService;
import com.pragya.oimspro.registration.dto.CompanyRegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompanyRegistrationResolver {
    @Autowired
    private CompanyService companyService;
    @PostMapping("/register")
    public String registerCompany(@RequestBody CompanyRegistrationRequest companyRegistrationRequest) {
        Company company = companyService.registerCompany(companyRegistrationRequest);
        return "Company " +company.getName()+ "registered successfully with user: " + company.getUser().getEmailId() ;
    }
}
