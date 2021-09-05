package facade;

import lombok.extern.slf4j.Slf4j;
import model.Course;
import org.hibernate.Session;
import utility.HibernateUtil;

@Slf4j
public class CourseFacade {

    private static final Session session = HibernateUtil.getSessionFactory().openSession();

    public long saveCourse(Course obj) {
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
}
