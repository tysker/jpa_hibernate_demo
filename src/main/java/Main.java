
import entity.Student;
import facade.StudentFacade;

public class Main {
    public static void main(String[] args) {

        StudentFacade facade = new StudentFacade();

        Student s1 = new Student("joe", "rur", "joe@gmail.com");
        Student s2 = new Student("steve", "nielsen", "steve@microsoft.com");
        Student s3 = new Student("karl", "schmidt", "karl@test.com");
        Student s4 = new Student("benjamin", "nielsen", "maja@gmail.com");


        System.out.println("==========================================================================================");
        System.out.println("Save objects!");
        facade.saveObject(s1);
        facade.saveObject(s2);
        facade.saveObject(s3);
        facade.saveObject(s4);

        System.out.println("getAllStudents()");
        facade.displayStudents(facade.getAllStudents());

        System.out.println("findStudentById");
        facade.displayStudents(facade.findStudentById(1));

        System.out.println("getAllStudentsBySameLastName");
        facade.displayStudents(facade.getAllStudentsBySameLastName("nielsen"));

        System.out.println("getAllStudentsWithSameEmailProvider()");
        facade.displayStudents(facade.getAllStudentsWithSameEmailProvider("gmail.com"));

        System.out.println("getAllStudentsWithFirstNameOrLastName()");
        facade.displayStudents(facade.getAllStudentsWithFirstNameOrLastName("karl", "nielsen"));
    }
}
