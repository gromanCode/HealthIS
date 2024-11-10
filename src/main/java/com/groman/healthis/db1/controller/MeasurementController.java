package com.groman.healthis.mysql_controller;

import com.groman.healthis.mysql_domain.Measurement;
import com.groman.healthis.mysql_dto.MeasurementCreateRequest;
import com.groman.healthis.mysql_dto.MeasurementDTO;
import com.groman.healthis.mysql_service.MeasurementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/measurement")
public class MeasurementController {

    private final MeasurementService measurementService;

    public MeasurementController(MeasurementService measurementService){
        this.measurementService = measurementService;
    }

    //Returns all measurements from one patient by his id
    @GetMapping("/{patient_id}")
    public ResponseEntity<List<MeasurementDTO>> getMeasurements(@PathVariable Long patient_id){
        return ResponseEntity.status(HttpStatus.FOUND).body(measurementService.getMeasurements(patient_id));
    }

    //Adds measurement to the direct user by referencing his id
    @PostMapping("/{patient_id}")
    public ResponseEntity<Measurement> createMeasurement(@PathVariable Long patient_id,@RequestBody MeasurementCreateRequest request){
    return ResponseEntity.status(HttpStatus.CREATED).body(measurementService.saveMeasurement(patient_id, request));
    }
}
