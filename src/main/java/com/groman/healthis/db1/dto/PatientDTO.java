package com.groman.healthis.mysql_dto;

import java.util.List;

public record PatientDTO(
        long id,
        String name,
        String surname,
        short age,
        List<String> measurement_date,
        List<String> measurement_title
) {
}
