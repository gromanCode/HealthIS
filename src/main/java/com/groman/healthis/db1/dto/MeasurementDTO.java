package com.groman.healthis.mysql_dto;
import java.util.Date;
import java.util.List;

public record MeasurementDTO(
        long id,
        String title,
        Date date,
        List<String> values,
        Long device_id
) {
}
