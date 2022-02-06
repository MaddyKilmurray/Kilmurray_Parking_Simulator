package com.kilmurray.trigg_java_developer_challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EntryFormDto {

    private String licencePlate;

    private LocalDateTime entranceDate;

    private String qrCode;
}
