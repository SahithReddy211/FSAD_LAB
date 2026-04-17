package com.hospital.entity;

import javax.persistence.*;


@Entity
@Table(name = "patient")

public class Patient {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long patientId;

    private String patientName;
    private Integer age;
    private String gender;
    private String disease;
    public Patient() {
    }

	public Patient(String patientName, int age, String gender, String disease) {
		// TODO Auto-generated constructor stub
	}
	public char[] getPatientName() {
		// TODO Auto-generated method stub
		return null;
	}

}
