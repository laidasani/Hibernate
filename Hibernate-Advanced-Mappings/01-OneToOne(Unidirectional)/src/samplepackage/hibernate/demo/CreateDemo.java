package samplepackage.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import samplepackage.hibernate.demoentity.Instructor;
import samplepackage.hibernate.demoentity.InstructorDetail;


public class CreateDemo {
	
	public static void main(String args[]) {
		
		//create session factory
		SessionFactory factory=new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.buildSessionFactory();
		
		//create session
		Session session=factory.getCurrentSession();
		
		try {
			
			//create objects
			/*Instructor tempInstructor=new Instructor("Luv","Aidasani","laidasani@gmail.com");
			InstructorDetail tempInstructorDetail=new InstructorDetail("abc.youtube.com","Cricket");*/
			
			Instructor tempInstructor=new Instructor("Luvvv","Aidasani","lvvaidasani@gmail.com");
			InstructorDetail tempInstructorDetail=new InstructorDetail("abcvv.youtube.com","Cricketvv");
			//associate objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			//start a transaction
			session.beginTransaction();
			
			//save instructor
			System.out.println("Saving instructor "+tempInstructor);
			session.save(tempInstructor);
			
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done");
			
			
		}
		
		finally{
			factory.close();
		}
	}

}
