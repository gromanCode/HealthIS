package com.groman.healthis.mysql_service;

import com.groman.healthis.mysql_domain.Measurement;
import com.groman.healthis.mysql_domain.Sensor;
import com.groman.healthis.mysql_domain.Values;
import com.groman.healthis.mysql_dto.ValuesCreateRequest;
import com.groman.healthis.mysql_dto.ValuesDTO;
import com.groman.healthis.mysql_repository.MeasurementRepository;
import com.groman.healthis.mysql_repository.SensorRepository;
import com.groman.healthis.mysql_repository.ValuesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ValuesService {

    ValuesRepository valuesRepository;
    SensorRepository sensorRepository;
    MeasurementRepository measurementRepository;

    public ValuesService(ValuesRepository valuesRepository,SensorRepository sensorRepository,MeasurementRepository measurementRepository) {
        this.valuesRepository = valuesRepository;
        this.sensorRepository = sensorRepository;
        this.measurementRepository = measurementRepository;
    }


    public List<ValuesDTO> getValues(Long measurement_id){
        Measurement measurement = measurementRepository.findById(measurement_id).
                orElseThrow(() -> new IllegalArgumentException("Measurement s takým id: " + measurement_id + " neexistuje."));

        return measurement.getValues().stream().map(Values -> new ValuesDTO(Values.getId(), Values.getValue(), Values.getWhen(), Values.getSensorId())).toList();
    }

    public Values saveValues(Long measurement_id,ValuesCreateRequest request){
        Measurement measurement = measurementRepository.findById(measurement_id)
                .orElseThrow(() -> new IllegalArgumentException("Measurement s takým id " + measurement_id + " neexistuje."));

        Sensor sensor = sensorRepository.findById(request.sensor_id())
                .orElseThrow(() -> new IllegalArgumentException("Sensor id " + request.sensor_id() + " neexistuje."));

        Values values = new Values(request.value());
        values.setMeasurement(measurement);
        values.setSensor(sensor);

        return valuesRepository.save(values);
    }




}
