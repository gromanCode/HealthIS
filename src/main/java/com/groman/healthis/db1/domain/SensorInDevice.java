package com.groman.healthis.db1.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "sensor_in_device")
public class SensorInDevice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "sensor_id")
    private Sensor sensor;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;

    public SensorInDevice() {
    }

    public SensorInDevice(Device device, Sensor sensor) {
        this.device = device;
        this.sensor = sensor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public Device getDevice() {
        return device;
    }

    public long getDeviceId() {return device.getId();}

    public void setDevice(Device device) {
        this.device = device;
    }

}
