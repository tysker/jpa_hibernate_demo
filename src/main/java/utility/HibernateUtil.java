package utility;

import lombok.extern.slf4j.Slf4j;
import model.Course;
import model.Instructor;
import model.InstructorDetail;
import model.Student;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.io.FileInputStream;
import java.util.Properties;

@Slf4j
public class HibernateUtil {

    // == equivalent to EntityManagerFactory ==
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.load(new FileInputStream("src/main/resources/util.properties"));
                settings.put(Environment.DRIVER, settings.get("util.database_driver"));
                settings.put(Environment.URL, settings.get("util.database_url"));
                settings.put(Environment.USER, settings.get("util.database_user"));
                settings.put(Environment.PASS, settings.get("util.database_password"));
                settings.put(Environment.DIALECT, settings.get("util.database_dialect"));
                settings.put(Environment.HBM2DDL_AUTO, settings.get("util.database_automation"));
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                // == add settings to Configuration ==
                configuration.setProperties(settings);
                // == Entity classes ==
                configuration.addAnnotatedClass(Instructor.class);
                configuration.addAnnotatedClass(InstructorDetail.class);
                configuration.addAnnotatedClass(Course.class);
                configuration.addAnnotatedClass(Student.class);

                // == The main purpose of a service registry is to hold, manage and provide access to services. ==
                // https://docs.jboss.org/hibernate/core/4.0/devguide/en-US/html/ch07.html
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                System.out.println("Hibernate Java Config serviceRegistry created");
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

                return sessionFactory;

            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        return sessionFactory;
    }
}
