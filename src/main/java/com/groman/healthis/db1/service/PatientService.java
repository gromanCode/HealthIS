package com.groman.healthis.mysql_service;

import com.groman.healthis.mysql_domain.Measurement;
import com.groman.healthis.mysql_domain.Patient;
import com.groman.healthis.mysql_dto.PatientCreateRequest;
import com.groman.healthis.mysql_dto.PatientDTO;
import com.groman.healthis.mysql_repository.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PatientService {

    PatientRepository  patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }


    //GET all patients
    public List<PatientDTO> getAllPatients(){
        List<Patient> all = patientRepository.findAll();

        return all.stream()
                .map(patient ->
                { List<String> measurement_date = patient.getMeasurements().stream()
                                    .map(Measurement -> Measurement.getDate().toString()).toList();
                    List<String> measurement_title = patient.getMeasurements().stream().map(Measurement::getTitle).toList();
                    return new PatientDTO(patient.getId(), patient.getName(), patient.getSurname(), patient.getAge(), measurement_date, measurement_title);
                }).toList();
    }

    //GET patient by id
    public PatientDTO getPatientById(Long id){
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pacient s id: " + id + " neexistuje."));

         List<String> measurement_date = patient.getMeasurements().stream().map(Measurement -> Measurement.getDate().toString()).toList();
         List<String> measurement_title = patient.getMeasurements().stream().map(Measurement::getTitle).toList();

        return new PatientDTO(id, patient.getName(), patient.getSurname(), patient.getAge(), measurement_date, measurement_title);
    }

    //POST, create or update patient
    @Transactional(readOnly = false)
    public Patient savePatient(PatientCreateRequest request){
        Patient patient = new Patient(request.name(), request.surname(), request.age());
        return patientRepository.save(patient);
    }

    @Transactional(readOnly = false)
    public Patient savePatient(Long id, PatientCreateRequest request){
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pacient s id: " + id + " neexistuje."));

        patient.setId(id);
        patient.setName(request.name());
        patient.setSurname(request.surname());
        patient.setAge(request.age());

        return patientRepository.save(patient);
    }

    @Transactional(readOnly = false)
    public Patient deletePatient(Long id){
            Patient patient = patientRepository.findById(id).
                    orElseThrow(() -> new IllegalArgumentException("Pacient s id: " + id + " neexistuje."));

            patientRepository.deleteById(id);
            return patient;
    }
}
