package com.pragya.oimspro.company.entity;

import com.pragya.oimspro.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="COMPANY")
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @TableGenerator(
            name = "COMPANY_ID",
            table = "ID_GEN",
            pkColumnName = "GEN_KEY",
            valueColumnName = "GEN_VALUE",
            pkColumnValue = "COMPANY_ID",
            allocationSize = 1)
    private long id;

    @Column(name="NAME")
    private String name;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name="ADDRESS")
    private String address;

    @Column(name="SITE")
    private String website;

    @Column(name="CONTACT_NUMBER")
    private String contactNumber;

    @Column(name="CONTACT_MAIL_ID")
    private String contactMailId;

}
