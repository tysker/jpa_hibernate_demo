package facade;

import lombok.extern.slf4j.Slf4j;
import model.Instructor;
import model.InstructorDetail;
import org.hibernate.Session;
import utility.HibernateUtil;

@Slf4j
public class InstructorFacade {

    private static final Session session = HibernateUtil.getSessionFactory().openSession();

    public long saveInstructor(Instructor obj) {
        try {
            session.beginTransaction();
            long id = (long) session.save(obj);
            session.getTransaction().commit();
            return id;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return 0;
    }

    public Instructor findInstructorById(long id) {
        try {
            Instructor instructor = session.find(Instructor.class, id);
            if (instructor != null) {
                return instructor;
            } else {
                log.error("Instructor with id {} does not exist.", id);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public void deleteInstructor(long id) {
        Object original = findInstructorById(id);
        try {
            if (original != null) {
                session.beginTransaction();
                session.delete(original);
                session.getTransaction().commit();
            } else {
                log.error("Instructor with id {} can not be deleted.", id);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
