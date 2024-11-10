package com.groman.healthis.db1.domain;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "measurement_values")
public class Values {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private Long value;
    @Column(nullable = false, name = "when_measured")
    private Timestamp when;

    @ManyToOne
    @JoinColumn(name = "measurement_id")
    private Measurement measurement;

    @ManyToOne
    @JoinColumn(name = "sensor_id")
    private Sensor sensor;

    public Values(){}

    public Values(Long value) {
        this.value = value;
        this.when = new Timestamp(System.currentTimeMillis());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Measurement getMeasurement() {
        return measurement;
    }

    public void setMeasurement(Measurement measurement) {
        this.measurement = measurement;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public Long getSensorId(){return sensor.getId();}

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public Timestamp getWhen() {
        return when;
    }

    public void setWhen(Timestamp when) {
        this.when = when;
    }
}
