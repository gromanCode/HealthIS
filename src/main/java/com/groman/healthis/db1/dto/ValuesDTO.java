package com.groman.healthis.mysql_dto;

import java.sql.Timestamp;

public record ValuesDTO(
        long id,
        long value,
        Timestamp when,
        long sensor_id
) { }
