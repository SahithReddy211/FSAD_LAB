package com.example.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.example.config.AppConfig;
import com.example.model.Student;

public class AutoWiredDemoApplication {
	public static void main(String[] args) {
		 @SuppressWarnings("resource")
		 ApplicationContext context =
		 new AnnotationConfigApplicationContext(AppConfig.class);
		 Student student = context.getBean(Student.class);
		 student.display();
		 }

}
