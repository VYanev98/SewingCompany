package com.SewingCompany.SewingCompany.data.entities;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String detailType;

    @Column
    private String detailColor;

    @Column
    private double materialCosts;

    @Column
    private double hoursToMake;

    @Column
    private double detailSellPrice;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
