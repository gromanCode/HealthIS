package com.groman.healthis.mysql_dto;

public record MeasurementCreateRequest(
        String title,
        long device_id
) {
}
