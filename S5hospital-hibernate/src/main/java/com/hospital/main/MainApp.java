package com.hospital.main;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.hospital.entity.Patient;
import com.hospital.entity.Appointment;
import com.hospital.util.HibernateUtil;

public class MainApp {
	 public static void main(String[] args) {

	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction tx = session.beginTransaction();

	        // ---------------- INSERT PATIENTS ----------------

	        Patient p1 = new Patient("Rahul Sharma", 45, "Male", "Heart Disease");
	        Patient p2 = new Patient("Anjali Reddy", 30, "Female", "Migraine");
	        Patient p3 = new Patient("Kiran Kumar", 55, "Male", "Arthritis");
	        Patient p4 = new Patient("Sneha Patel", 28, "Female", "Diabetes");
	        Patient p5 = new Patient("Arjun Mehta", 40, "Male", "Back Pain");
	        Patient p6 = new Patient("Divya Nair", 35, "Female", "Asthma");
	        Patient p7 = new Patient("Vikram Rao", 60, "Male", "Hypertension");
	        Patient p8 = new Patient("Meena Das", 50, "Female", "Thyroid");

	        session.save(p1);
	        session.save(p2);
	        session.save(p3);
	        session.save(p4);
	        session.save(p5);
	        session.save(p6);
	        session.save(p7);
	        session.save(p8);

	        // ---------------- INSERT APPOINTMENTS ----------------

	        Appointment a1 = new Appointment("Dr. Rao", "Cardiology", new Date(), 1200.0, p1);
	        Appointment a2 = new Appointment("Dr. Sharma", "Neurology", new Date(), 900.0, p2);
	        Appointment a3 = new Appointment("Dr. Kumar", "Orthopedics", new Date(), 1500.0, p3);
	        Appointment a4 = new Appointment("Dr. Reddy", "Endocrinology", new Date(), 800.0, p4);
	        Appointment a5 = new Appointment("Dr. Singh", "Orthopedics", new Date(), 700.0, p5);
	        Appointment a6 = new Appointment("Dr. Nair", "Pulmonology", new Date(), 1100.0, p6);
	        Appointment a7 = new Appointment("Dr. Gupta", "Cardiology", new Date(), 1300.0, p7);
	        Appointment a8 = new Appointment("Dr. Das", "General Medicine", new Date(), 600.0, p8);

	        session.save(a1);
	        session.save(a2);
	        session.save(a3);
	        session.save(a4);
	        session.save(a5);
	        session.save(a6);
	        session.save(a7);
	        session.save(a8);

	        tx.commit();

	        // ---------------- SORT BY FEE ASC ----------------

	        Query<Appointment> q1 = session.createQuery(
	                "FROM Appointment a ORDER BY a.consultationFee ASC",
	                Appointment.class);
	        List<Appointment> ascList = q1.list();

	        System.out.println("\nSorted by Fee ASC:");
	        ascList.forEach(a -> System.out.println(a.getConsultationFee()));

	        // ---------------- SORT BY DATE DESC ----------------

	        Query<Appointment> q2 = session.createQuery(
	                "FROM Appointment a ORDER BY a.appointmentDate DESC",
	                Appointment.class);
	        List<Appointment> dateList = q2.list();

	        System.out.println("\nSorted by Date DESC:");
	        dateList.forEach(a -> System.out.println(a.getAppointmentDate()));

	        // ---------------- PAGINATION ----------------

	        Query<Appointment> q3 = session.createQuery("FROM Appointment", Appointment.class);
	        q3.setFirstResult(0);
	        q3.setMaxResults(3);
	        List<Appointment> first3 = q3.list();

	        System.out.println("\nFirst 3 Appointments:");
	        first3.forEach(a -> System.out.println(a.getDoctorName()));

	        // ---------------- COUNT PATIENTS ----------------

	        Long totalPatients = session.createQuery(
	                "SELECT COUNT(p) FROM Patient p",
	                Long.class).uniqueResult();

	        System.out.println("\nTotal Patients: " + totalPatients);

	        // ---------------- COUNT APPOINTMENTS ----------------

	        Long totalAppointments = session.createQuery(
	                "SELECT COUNT(a) FROM Appointment a",
	                Long.class).uniqueResult();

	        System.out.println("Total Appointments: " + totalAppointments);

	        // ---------------- FEE RANGE FILTER ----------------

	        Query<Appointment> q4 = session.createQuery(
	                "FROM Appointment a WHERE a.consultationFee BETWEEN 500 AND 1500",
	                Appointment.class);
	        List<Appointment> rangeList = q4.list();

	        System.out.println("\nAppointments Between 500 and 1500:");
	        rangeList.forEach(a -> System.out.println(a.getConsultationFee()));

	        // ---------------- LIKE QUERY ----------------

	        Query<Patient> q5 = session.createQuery(
	                "FROM Patient p WHERE p.patientName LIKE 'A%'",
	                Patient.class);
	        List<Patient> patientList = q5.list();

	        System.out.println("\nPatients Starting With A:");
	        patientList.forEach(p -> System.out.println(p.getPatientName()));

	        session.close();
	        HibernateUtil.getSessionFactory().close();
	    }

}
