package com.pragya.oimspro.company.resolver;

import com.pragya.oimspro.company.entity.Company;
import com.pragya.oimspro.company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping
public class CompanyResolver {

    @Autowired
    private CompanyService companyService;

    @PostMapping
    public void createCompany(@RequestBody Company company) {
        companyService.createCompany(company);
    }
}
