## **Hibernate**


### **Hibernate Documentation**

* [Hibernate book (pdf)](https://livebook.manning.com/book/java-persistence-with-hibernate/table-of-contents/)

**Hibernate persistence.xml**

* [Short tutorial for persistence.xml](https://thorben-janssen.com/jpa-persistence-xml/)
* [Cheatsheet](http://www.mastertheboss.com/hibernate-jpa/jpa-configuration/persistence-xml-cheatsheet/)
* [hibernate.org](https://hibernate.org/)
* [Hibernate Developer Guide](https://www.javaguides.net/p/hibernate-developer-guide.html)


### Difference between JDBC and Hibernate in Java 
(from https://www.geeksforgeeks.org/difference-between-jdbc-and-hibernate-in-java/)

**JDBC:** JDBC stands for Java Database Connectivity. It is a java application programming interface to provide a connection between the Java programming language and a wide range of databases (i.e), it establishes a link between the two so that a programmer could send data from Java code and store it in the database for future use.

**Hibernate:** Hibernate is an open-source, non-invasive, light-weight java ORM(Object-relational mapping) framework to develop objects which are independent of the database software and make independent persistence logic in all JAVA, JEE. It simplifies the interaction of java applications with databases. Hibernate is an implementation of JPA(Java Persistence API).

* https://www.educba.com/hibernate-vs-jdbc/
* https://codippa.com/jdbc-hibernate-comparison/

***

### There are two ways of configure hibernate
    1. hibernate.cfg.xml
    2. use of Java Properties

### hibernate.cfg.xml utility class

    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                // Create registry
                registry = new StandardServiceRegistryBuilder().configure().build();

                // Create MetadataSources
                MetadataSources sources = new MetadataSources(registry);

                // Create Metadata
                Metadata metadata = sources.getMetadataBuilder().build();

                // Create SessionFactory
                sessionFactory = metadata.getSessionFactoryBuilder().build();

            } catch (Exception e) {
                log.error(e.getMessage());
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
            }
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

### Java class configuration

    public class HibernateUtil {

    // == equivalent to EntityManagerFactory ==
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties

                Properties settings = new Properties();

                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/hibernate?useSSL=false");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "Tysker/3085");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "create-drop");
                // == add settings to Configuration ==
                configuration.setProperties(settings);
                // == Entity classes ==
                configuration.addAnnotatedClass(Student.class);

                // == The main purpose of a service registry is to hold, manage and provide access to services. ==
                // https://docs.jboss.org/hibernate/core/4.0/devguide/en-US/html/ch07.html
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                System.out.println("Hibernate Java Config serviceRegistry created");
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                return sessionFactory;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }