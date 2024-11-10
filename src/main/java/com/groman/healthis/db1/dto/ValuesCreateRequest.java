package com.groman.healthis.mysql_dto;

public record ValuesCreateRequest(
        long value,
        long sensor_id
) { }
