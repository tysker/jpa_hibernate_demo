import facade.CourseFacade;
import facade.InstructorDetailFacade;
import facade.InstructorFacade;
import model.Course;
import model.Instructor;
import model.InstructorDetail;

public class Main {
    public static void main(String[] args) {

        InstructorFacade facade = new InstructorFacade();
        InstructorDetailFacade detailFacade = new InstructorDetailFacade();
        CourseFacade courseFacade = new CourseFacade();

        Instructor int1 = new Instructor("Joe", "Smith", "smith@gmail.com");
        Instructor int2 = new Instructor("Steve", "Nielsen", "nielsen@gmail.com");
        Instructor int3 = new Instructor("Joe", "Smith", "smith@gmail.com");
        Instructor int4 = new Instructor("Steve", "Nielsen", "nielsen@gmail.com");

        InstructorDetail intDet1 = new InstructorDetail("youTube/camping", "camping");
        InstructorDetail intDet2 = new InstructorDetail("youTube/running", "running");

        int1.setInstructorDetail(intDet1);
        int2.setInstructorDetail(intDet2);

        System.out.println("Saved object id: " + facade.saveInstructor(int1));
        System.out.println("Saved object id: " + facade.saveInstructor(int2));

        // == find Instructor by ID ==
        Instructor instructor = facade.findInstructorById(1);
        // == find InstructorDetail by ID ==
        InstructorDetail instructorDetail = detailFacade.findInstructorDetail(1);
        // == Printout ==
        System.out.println(instructorDetail.getHobby());
        System.out.println(instructor.getInstructorDetail().getHobby());
        System.out.println(instructor.getInstructorDetail().getYoutubeChannel());

        // == Add Courses ==
        Course c1 = new Course("java");
        Course c2 = new Course("python");
        Course c3 = new Course("c#");
        Course c4 = new Course("haskell");

        int1.addToCourse(c1);
        int1.addToCourse(c2);
        int1.addToCourse(c3);
        int1.addToCourse(c4);

        courseFacade.saveCourse(c1);
        courseFacade.saveCourse(c2);
        courseFacade.saveCourse(c3);
        courseFacade.saveCourse(c4);

    }
}
