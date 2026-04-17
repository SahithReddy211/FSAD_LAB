package com.hospital.entity;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;

    private String doctorName;

    private String department;

    @Temporal(TemporalType.DATE)
    private Date appointmentDate;

    private Double consultationFee;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    // Default constructor (VERY IMPORTANT)
    public Appointment() {
    }

    // Parameterized constructor
    public Appointment(String doctorName, String department,
                       Date appointmentDate, Double consultationFee,
                       Patient patient) {
        this.doctorName = doctorName;
        this.department = department;
        this.appointmentDate = appointmentDate;
        this.consultationFee = consultationFee;
        this.patient = patient;
    }

    // Getters and Setters

    public Long getAppointmentId() {
        return appointmentId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getDepartment() {
        return department;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public Double getConsultationFee() {
        return consultationFee;
    }

    public Patient getPatient() {
        return patient;
    }
}

