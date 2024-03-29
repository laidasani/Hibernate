package samplepackage.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import samplepackage.hibernate.demoentity.Instructor;
import samplepackage.hibernate.demoentity.InstructorDetail;


public class DeleteInstructorDetailDemo {
	
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
			
			
			//start a transaction
			session.beginTransaction();
			
			
			//get instructor detail object
			int theId=3;
			InstructorDetail tempInstructorDetail=session.get(InstructorDetail.class, theId);
			
			//print the instructor detail
			System.out.println("tempInstructorDetails: "+ tempInstructorDetail);
			//print associated instructor
			System.out.println("the associated instructor: "+ tempInstructorDetail.getInstructor());
			
			//remove the associated object reference; break the bidirectional link
			tempInstructorDetail.getInstructor().setInstructorDetail(null);
			
			//delete instructor detail
			System.out.println("Deleting instructor detail: "+ tempInstructorDetail);
			session.delete(tempInstructorDetail);
			
		
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done");
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		finally{
			
			//handle connection leak issue
			session.close();
			factory.close();
		}
	}

}
