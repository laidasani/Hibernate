package samplepackage.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import samplepackage.hibernate.demoentity.Course;
import samplepackage.hibernate.demoentity.Instructor;
import samplepackage.hibernate.demoentity.InstructorDetail;


public class EagerLazyDemo {
	
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
			
			//get instructor from db
			int theId=1;
			Instructor tempInstructor= session.get(Instructor.class, theId);
			
			System.out.println("Instructor: "+ tempInstructor);
			System.out.println("Courses: "+tempInstructor.getCourses());
			
			session.getTransaction().commit();
			session.close();
			
			System.out.println("Courses: "+tempInstructor.getCourses());

			System.out.println("Done");
			
			
		}
		
		finally{
			
			session.close();
			factory.close();
		}
	}

}
