package samplepackage.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import samplepackage.hibernate.demoentity.Student;

public class UpdateStudentDemo {
	
	public static void main(String args[]) {
		
		//create session factory
		SessionFactory factory=new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		//create session
		Session session=factory.getCurrentSession();
		
		try {
			int studentId=1;
			
			session.beginTransaction();
			
			//retrieve student based on id
			System.out.println("Updating student with id " +studentId);
			Student myStudent=session.get(Student.class,studentId);
			System.out.println("Updating student");
			
			myStudent.setFirstName("Scooby");
			
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done");
			
			//begin new session
			session=factory.getCurrentSession();
			session.beginTransaction();
			
			//update query
			System.out.println("Updating email id of all students");
			session.createQuery("update Student s set s.email='abcd@luv.com'").executeUpdate();
			//session.createQuery("update Student set email='abcd@luv.com'").executeUpdate();  Same as above
			
			//commit the transaction
			session.getTransaction().commit();
		}
		
		finally{
			factory.close();
		}
	}

}
