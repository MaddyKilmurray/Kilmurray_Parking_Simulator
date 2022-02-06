package com.kilmurray.trigg_java_developer_challenge.dao;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String licencePlate;

    private LocalDateTime entranceDate;
    private LocalDateTime exitDate;

    private String qrCode;

    public Car(String licencePlate, LocalDateTime entranceDate, String qrCode) {
        this.licencePlate = licencePlate;
        this.entranceDate = entranceDate;
        this.qrCode = qrCode;
    }
}
