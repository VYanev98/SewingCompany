package com.SewingCompany.SewingCompany.data.entities;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String employeeType;

    @Column
    private double salaryPerHour;

    @Column
    private int maxDetails;

    @OneToMany(mappedBy = "type")
    private List<Employee> employeesType = new ArrayList<>();
}
