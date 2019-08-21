package samplepackage.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import samplepackage.hibernate.demoentity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {

			//create session factory
			SessionFactory factory=new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
			
			//create session
			Session session=factory.getCurrentSession();
			
			try {
				//create 3 student objects
				System.out.println("Creating 3 student objects..");
				Student tempStudent1=new Student("Luvv","Aidasani","lvaidasani@gmail.com");
				Student tempStudent2=new Student("Luvvv","Aidasani","lvvaidasani@gmail.com");
				Student tempStudent3=new Student("Luvvvv","Aidasani","lvvvaidasani@gmail.com");
				
				
				//start a transaction
				session.beginTransaction();
				
				//save the student object
				System.out.println("Saving the students");
				session.save(tempStudent1);
				session.save(tempStudent2);
				session.save(tempStudent3);
				
				//commit the transaction
				session.getTransaction().commit();
				
				System.out.println("Done");
				
				
			}
			
			finally{
				factory.close();
			}
		}


}
