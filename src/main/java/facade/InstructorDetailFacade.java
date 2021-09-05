package facade;

import lombok.extern.slf4j.Slf4j;
import model.InstructorDetail;
import org.hibernate.Session;
import utility.HibernateUtil;

@Slf4j
public class InstructorDetailFacade {

    private static final Session session = HibernateUtil.getSessionFactory().openSession();

    public long saveInstructorDetail(InstructorDetail obj) {
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

    public InstructorDetail findInstructorDetail(long id) {
        try {
            InstructorDetail instructorDetail = session.find(InstructorDetail.class, id);
            if (instructorDetail != null) {
                return instructorDetail;
            } else {
                log.error("InstructorDetail with id {} does not exist.", id);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public void deleteInstructorDetail(long id) {
        Object original = findInstructorDetail(id);
        try {
            if (original != null) {
                session.beginTransaction();
                // only used if bi-directional where you don't want to cascade.delete the other entity
                //original.getInstructor().setInstructorDetail(null);
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
