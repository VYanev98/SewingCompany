package com.SewingCompany.SewingCompany.data.entities;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;

    @OneToMany(mappedBy = "employee")
    private List<Detail> details = new ArrayList<>();

    @Override
    public String toString() {
        return String.format(firstName + " " + lastName);
    }

    public String detailsMadeByEmployee() {
        String res = "";
        List<String> detailsList = new ArrayList<>();
        for (Detail detail : details) {
            detailsList.add(detail.getDetailType());
        }

        for (Detail detail : details) {
            if (!res.contains(detail.getDetailType())) {
                res = res + " " + detail.getDetailType() + " (" + Collections.frequency(detailsList, detail.getDetailType()) + ")";
            }
        }
        return res;
    }
}
