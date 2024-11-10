package com.groman.healthis.mysql_service;

import com.groman.healthis.mysql_domain.Device;
import com.groman.healthis.mysql_domain.Measurement;
import com.groman.healthis.mysql_domain.Patient;
import com.groman.healthis.mysql_dto.MeasurementCreateRequest;
import com.groman.healthis.mysql_dto.MeasurementDTO;
import com.groman.healthis.mysql_repository.DeviceRepository;
import com.groman.healthis.mysql_repository.MeasurementRepository;
import com.groman.healthis.mysql_repository.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementService {

    MeasurementRepository measurementRepository;
    PatientRepository patientRepository;
    DeviceRepository deviceRepository;

    public MeasurementService(MeasurementRepository measurementRepository, PatientRepository patientRepository, DeviceRepository deviceRepository) {
        this.measurementRepository = measurementRepository;
        this.patientRepository = patientRepository;
        this.deviceRepository = deviceRepository;
    }

    public List<MeasurementDTO> getMeasurements(Long patient_id){
        Patient patient = patientRepository.findById(patient_id).
                orElseThrow(() -> new IllegalArgumentException("Pre používateľa s id: " + patient_id + " neexistujú žiadne merania."));

        return patient.getMeasurements().stream().map(Measurement -> {
                    List<String> measurement_values = Measurement.getValues().stream().map(Values -> Values.getValue().toString()).toList();
                    return new MeasurementDTO(Measurement.getId(), Measurement.getTitle(), Measurement.getDate(), measurement_values, Measurement.getDeviceId());
                }).toList();
    }

    @Transactional(readOnly = false)
    public Measurement saveMeasurement(Long patient_id, MeasurementCreateRequest request){
        Patient patient = patientRepository.findById(patient_id).
                orElseThrow(() -> new IllegalArgumentException("Nemožno uložiť pre používateľa s id: " + patient_id + " , používateľ sa nenašiel"));

        Measurement measurement = new Measurement(request.title());
        measurement.setPatient(patient);

        Device device = deviceRepository.findById(request.device_id()).
                orElseThrow(() -> new IllegalArgumentException("Zariadenie s týmto id: " + request.device_id() + " sa nenašlo."));
        measurement.setDevice(device);

        return measurementRepository.save(measurement);
    }
}
