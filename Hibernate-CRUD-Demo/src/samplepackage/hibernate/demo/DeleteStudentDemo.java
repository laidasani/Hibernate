package samplepackage.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import samplepackage.hibernate.demoentity.Student;

public class DeleteStudentDemo {
	
	public static void main(String args[]) {
		
		//create session factory
		SessionFactory factory=new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		//create session
		Session session=factory.getCurrentSession();
		
		try {
			int studentId=4;
			
			session.beginTransaction();
			
			//retrieve student based on id
			//Student myStudent=session.get(Student.class,studentId);
			
			//System.out.println("Deleting student " +myStudent);
			//session.delete(myStudent);
			
			session.createQuery("delete from Student where id=3").executeUpdate();
			
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done");
			
		}
		
		finally{
			factory.close();
		}
	}

}
