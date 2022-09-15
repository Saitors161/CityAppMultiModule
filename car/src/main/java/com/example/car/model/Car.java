package com.example.car.model;

import com.example.car.util.TypeCar;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;
@Entity
@Table(name = "cars")
@Data
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "serial_number")
    private UUID serialNumber;
    @Column(name = "registered")
    private Date registered;
    @Column(name = "type_car")
    @Enumerated(EnumType.STRING)
    private TypeCar typeCar;
    @Column(name = "citizen_id")
    private Integer citizenId;
}
