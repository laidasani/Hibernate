package samplepackage.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import samplepackage.hibernate.demoentity.Course;
import samplepackage.hibernate.demoentity.Instructor;
import samplepackage.hibernate.demoentity.InstructorDetail;


public class DeleteCourseDemo {
	
	public static void main(String args[]) {
		
		//create session factory
		SessionFactory factory=new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.buildSessionFactory();
		
		//create session
		Session session=factory.getCurrentSession();
		
		try {
			
			
			//start a transaction
			session.beginTransaction();
			
			//get a course
			int theId=10;
			Course theCourse=session.get(Course.class,theId);
			
			//delete a course
			System.out.println("Deleting course: " +theCourse);
			session.delete(theCourse);
			
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done");
			
			
		}
		
		finally{
			
			session.close();
			factory.close();
		}
	}

}
