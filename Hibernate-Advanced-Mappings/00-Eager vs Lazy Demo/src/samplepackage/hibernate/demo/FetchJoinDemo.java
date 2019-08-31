package samplepackage.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import samplepackage.hibernate.demoentity.Course;
import samplepackage.hibernate.demoentity.Instructor;
import samplepackage.hibernate.demoentity.InstructorDetail;


public class FetchJoinDemo {
	
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
			//option 2: HQL
			Query<Instructor> query=session.createQuery("Select i from Instructor i JOIN FETCH i.courses where i.id=:theInstructorId",Instructor.class);
			
			//set parameter on query
			query.setParameter("theInstructorId", theId);
			
			//execute query and get instructor
			Instructor tempInstructor=query.getSingleResult();
			System.out.println("Instructor: "+tempInstructor);
			
			
			session.getTransaction().commit();
			session.close();
			
			System.out.println("Session now closed");
			System.out.println("Courses: "+tempInstructor.getCourses());
			System.out.println("Done");
			
			
		}
		
		finally{
			
			session.close();
			factory.close();
		}
	}

}
