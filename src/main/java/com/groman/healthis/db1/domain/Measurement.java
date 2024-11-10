package com.groman.healthis.db1.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "measurement")
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @OneToMany(mappedBy = "measurement", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Values> values = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;

    public Measurement() {
    }

    public Measurement(String title) {
        this.title = title;
        this.date = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<Values> getValues() {
        return values;
    }

    public void setValues(List<Values> values) {this.values = values;}

    public void setValues(Values values) {this.values.add(values);}

    public Device getDevice() {return device;}

    public Long getDeviceId() {return device.getId();}

    public void setDevice(Device device) {this.device = device;}
}


