package samplepackage.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import samplepackage.hibernate.demoentity.Student;

public class ReadStudentDemo {
	
	public static void main(String args[]) {
		
		//create session factory
		SessionFactory factory=new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		//create session
		Session session=factory.getCurrentSession();
		
		try {
			//create a student object
			System.out.println("Creating new student object..");
			Student tempStudent=new Student("A","B","ab@gmail.com");
			
			
			//start a transaction
			session.beginTransaction();
			
			//save the student object
			System.out.println("Saving the student");
			session.save(tempStudent);
			System.out.println(tempStudent);
			
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done");
			
			
			//READ CODE
			
			//get a new session and begin a new transaction
			session=factory.getCurrentSession();
			session.beginTransaction();
			
			//retrieve student based on id
			System.out.println("Getting student with id " +tempStudent.getId());
			Student myStudent=session.get(Student.class,tempStudent.getId());
			System.out.println("Get Complete "+myStudent);
			
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done");
		}
		
		finally{
			factory.close();
		}
	}

}
