package com.skillhunt.db.company;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="company")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class CompanyEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "company_name")
    private String companyName;
}
