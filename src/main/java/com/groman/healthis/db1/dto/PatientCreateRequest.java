package com.groman.healthis.mysql_dto;

public record PatientCreateRequest(
        String name,
        String surname,
        short age
) {
}
