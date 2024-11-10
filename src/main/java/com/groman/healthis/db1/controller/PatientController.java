package com.groman.healthis.mysql_controller;

import com.groman.healthis.mysql_domain.Patient;
import com.groman.healthis.mysql_dto.PatientCreateRequest;
import com.groman.healthis.mysql_dto.PatientDTO;
import com.groman.healthis.mysql_service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;

    PatientController(PatientService patientService){
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<List<PatientDTO>> getPatient(){
        return ResponseEntity.status(HttpStatus.FOUND).body(patientService.getAllPatients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getPatient(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.FOUND).body(patientService.getPatientById(id));
    }

    //POSTs new patient
    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody PatientCreateRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.savePatient(request));
    }

    //UPDATE existing patient
    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody PatientCreateRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.savePatient(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Patient> deletePatient(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.GONE).body(patientService.deletePatient(id));
    }
}
