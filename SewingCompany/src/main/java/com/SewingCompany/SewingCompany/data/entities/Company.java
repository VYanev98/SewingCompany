package com.SewingCompany.SewingCompany.data.entities;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;

    @Column
    private String owner;

    @Column
    private String bulstat;

    @Column
    private String address;

    @OneToMany(mappedBy = "company")
    private List<Employee> employees = new ArrayList<>();

    @OneToOne(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Funds funds;

    public String detailsMadeByCompany() {
        String res = "";
        List<String> detailsList = new ArrayList<>();
        for (Employee employee : employees) {
            for (Detail detail : employee.getDetails()) {
                detailsList.add(detail.getDetailType());
            }
        }

        for (Employee employee : employees) {
            for (Detail detail : employee.getDetails()) {
                if (!res.contains(detail.getDetailType())) {
                    res = res + " " + detail.getDetailType() + " (" + Collections.frequency(detailsList, detail.getDetailType()) + ")";
                }
            }
        }
        return res;
    }
}
