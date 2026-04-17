package com.skill4.main;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.example.main.AppConfig;
import com.skill4.model.Student;
public class StudentAnnoMain {
 public static void main(String[] args) {
 ApplicationContext context =
 new AnnotationConfigApplicationContext(AppConfig.class);
 Student student = context.getBean(Student.class);
 student.display();
 }
}