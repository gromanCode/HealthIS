package com.groman.healthis.db1.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "device")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;
    private boolean active;

    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SensorInDevice> sensorInDevices = new ArrayList<>();

    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Measurement> measurements = new ArrayList<>();

    public Device() {
    }

    public Device(String name) {
        this.active = false;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<SensorInDevice> getSensorInDevices() {
        return sensorInDevices;
    }

    public void setSensorInDevices(List<SensorInDevice> sensorInDevices) {
        this.sensorInDevices = sensorInDevices;
    }

    public List<Measurement> getMeasurements() {return measurements;}

    public void setMeasurements(List<Measurement> measurements) {this.measurements = measurements;}

    public void addMeasurement(Measurement measurement) {this.measurements.add(measurement);}
}
