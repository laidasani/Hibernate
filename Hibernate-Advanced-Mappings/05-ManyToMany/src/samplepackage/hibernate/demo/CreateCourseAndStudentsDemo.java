package samplepackage.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import samplepackage.hibernate.demoentity.Course;
import samplepackage.hibernate.demoentity.Instructor;
import samplepackage.hibernate.demoentity.InstructorDetail;
import samplepackage.hibernate.demoentity.Review;
import samplepackage.hibernate.demoentity.Student;


public class CreateCourseAndStudentsDemo {
	
	public static void main(String args[]) {
		
		//create session factory
		SessionFactory factory=new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		//create session
		Session session=factory.getCurrentSession();
		
		try {
			
			
			//start a transaction
			session.beginTransaction();
			
			//create a course
			Course tempCourse=new Course("Pacman Tutorial");
			
			//save the course
			System.out.println("Saving the course");
			session.save(tempCourse);	
			System.out.println("Saved the course: "+tempCourse);
			
			
			//create the students
			Student tempStudent1=new Student("Luv","Aidasani","laidasani@gmail.com");
			Student tempStudent2=new Student("Luvv","Aidasani","lvaidasani@gmail.com");
			
			tempCourse.addStudent(tempStudent1);
			tempCourse.addStudent(tempStudent2);
			//save the students
			System.out.println("Saving the students");
			session.save(tempStudent1);
			session.save(tempStudent2);
			System.out.println("Saved students: "+tempCourse.getStudents());
			
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
