package facade;

import config.HibernateUtil;
import entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import java.util.List;

@Slf4j
public class StudentFacade {

    private static final Session session = HibernateUtil.getSessionFactory().openSession();

    public void displayStudents(Object... values ) {
        for (Object st:
                values) {
          log.info("Student = {}", st);
        }
        System.out.println("==========================================================================================");
    }

    public void saveObject(Object obj) {
        try{
            session.beginTransaction();
            session.save(obj);
            session.getTransaction().commit();
        } catch(Exception e) {
            log.error(e.getMessage());
        }
    }

    public void saveObjectTryWithResources(Object obj) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            session.save(obj);
            session.getTransaction().commit();
        } catch(Exception e) {
            log.error(e.getMessage());
        }
    }

    public List<Student> getAllStudents() {
        try {
            return session.createQuery("from Student", Student.class).list();
        }catch(Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public Student findStudentById(long id) {
        try {
            return session.find(Student.class, id);
        } catch(Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public List<Student> getAllStudentsBySameLastName(String lastName) {
        try {
            return session.createQuery("SELECT s from Student s where s.lastName =: lastName", Student.class)
                    .setParameter("lastName", lastName)
                    .list();
        } catch(Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public List<Student> getAllStudentsWithSameEmailProvider(String provider) {
        try {
            return session.createQuery("SELECT s from Student s where s.email like :provider", Student.class)
                    .setParameter("provider", "%" + provider)
                    .list();
        } catch(Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public List<Student> getAllStudentsWithFirstNameOrLastName(String firstName, String lastName) {
        try {
            return session.createQuery("SELECT s from Student s where s.firstName = :firstName or s.lastName = :lastName", Student.class)
                    .setParameter("firstName", firstName)
                    .setParameter("lastName", lastName)
                    .list();
        } catch(Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

}
