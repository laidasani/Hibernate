package samplepackage.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import samplepackage.hibernate.demoentity.Student;

public class QueryStudentDemo {
	
	public static void main(String args[]) {
		
		//create session factory
		SessionFactory factory=new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		//create session
		Session session=factory.getCurrentSession();
		
		try {
			
			//start a transaction
			session.beginTransaction();
			
			//query students
			List<Student> theStudents=session.createQuery("from Student").getResultList();
			
			//display students
			System.out.println("All students:");
			displayStudents(theStudents);
			
			//query students last name: Aidasani
			theStudents=session.createQuery("from Student s where s.lastName='Aidasani'").getResultList();
			
			//display students
			System.out.println("\n\nAll students whose last name is Aidasani:");
			displayStudents(theStudents);
			
			//query students last name: Aidasani or first name: A
			theStudents=session.createQuery("from Student s where s.lastName='Aidasani' OR s.firstName='A'").getResultList();
			
			//display students
			System.out.println("\n\nAll students whose last name is Aidasani or first name is A:");
			displayStudents(theStudents);
			
			//query students where email like @gmail.com 
			theStudents=session.createQuery("from Student s where s.email LIKE '%@gmail.com'").getResultList();
			
			//display students
			System.out.println("\n\nAll students whose email ends with @gmail.com:");
			displayStudents(theStudents);
			
			
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done");
			
			
		}
		
		finally{
			factory.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for(Student tempStudent:theStudents) {
			System.out.println(tempStudent);
		}
	}

}
