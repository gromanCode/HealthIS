package com.groman.healthis.db1.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sensor")
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String type;
    private String name;

    @OneToMany(mappedBy = "sensor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Values> values = new ArrayList<>();

    @OneToMany(mappedBy = "sensor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SensorInDevice> sensorInDevices = new ArrayList<>();

    public Sensor() {}

    public Sensor(String name,String type) {
        this.name = name;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Values> getValues() {
        return values;
    }

    public void setValues(List<Values> values) {
        this.values = values;
    }

    public void setValues(Values values) {this.values.add(values);}

    public List<SensorInDevice> getSensorInDevices() {
        return sensorInDevices;
    }

    public void setSensorInDevices(List<SensorInDevice> sensorInDevices) {
        this.sensorInDevices = sensorInDevices;
    }
}
