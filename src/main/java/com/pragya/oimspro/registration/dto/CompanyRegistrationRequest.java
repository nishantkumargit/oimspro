package com.pragya.oimspro.registration.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyRegistrationRequest {
    private String name;
    private String userEmailId;
    private String address;
    private String website;
    private String contactNumber;
    private String contactMailId;
}
