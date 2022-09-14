package com.example.citizen.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;
@Entity
@Data
@Table(name = "passports")
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "number")
    private UUID number;
    @Column(name = "date_of_created", nullable = false)
    private Date dateOfCreated;
    @OneToOne(mappedBy = "passport")
    @Transient
    private Citizen citizen;
}
