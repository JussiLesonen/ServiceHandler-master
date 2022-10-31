import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Service {
    Scanner sc = new Scanner(System.in);
    FileService fs = new FileService();
    boolean end = false;

    public static List<Student> students = new ArrayList<>();
    public static List<Course> courses = new ArrayList<>();

    public void Start()throws IOException{
        //Program runs until the "end" boolean is true
        fs.makeCourses();
        fs.makeStudents();
        while(!end){
            begin();
        }
    }

    //Main menu
    public void begin() throws IOException{
        System.out.println("");
        System.out.println("Input a command:");
        System.out.println("[1] Create new student");
        System.out.println("[2] List students");
        System.out.println("[3] Create new course");
        System.out.println("[4] List courses");
        System.out.println("[5] Add student to course");
        System.out.println("[6] Exit");
        String selection = sc.nextLine();

        switch(selection){
            case "1":
                createStudent();
                break;
            case "2":
                listStudents();
                break;
            case "3":
                createCourse();
                break;
            case "4":
                listCourses();
                break;
            case "5":
                addStudentToCourse();
                break;
            case "6":
                end = true;
                break;
            default:
            break;
        }
    }

    //Create student and add them into a list
    public Student createStudent() throws IOException{
        System.out.println("");
        System.out.println("Input student name:");

        String name = sc.nextLine();
        Student s = new Student();

        s.setName(name);
        students.add(s);

        FileWriter fw = new FileWriter(FileService.fileStudents, true);
        try {
            FileService.fileStudents.createNewFile();
        } catch (IOException e) {}
        
        fw.write(s.getName() + ", " + s.getId() + System.lineSeparator());
        fw.close();

        System.out.println("Student " + name + " added (id: " + s.getId() + ")");

        return s;
    }

    //List all existing students
    public void listStudents() throws FileNotFoundException{
        System.out.println("");
        
            // for (Student student : students) {
            //     System.out.println(student.getName() + " (" + student.getId() + ")");
            // }
        
        try {
            fs.listBackupStudents();
        } catch (FileNotFoundException e) {
            System.out.println("Student files not found");
        }

    }

    //Here you decide whether the course will be online or in classroom
    public void createCourse() throws IOException{
        System.out.println("");
        System.out.println("Input course type: (press O for online or C for classroom)");

        String selection = sc.nextLine();

        switch(selection){
            case "o":
                createOnlineCourse();
            break;
            case "c":
                createClassRoomCourse();
            break;
            default:
            break;
        }
    }

    //Create online course and add it to a list
    public OnlineCourse createOnlineCourse() throws IOException{
        System.out.println("");
        OnlineCourse o = new OnlineCourse();

        System.out.println("Input course name:");
        String name = sc.nextLine();

        for (Course course : courses) {
            if(course.getName().equals(name)){
                System.out.println("Course with that name already exists");
                createCourse();
            }else{
                continue;
            }
        }
        System.out.println("Input course teacher:");
        String teacher = sc.nextLine();

        System.out.println("Input course link:");
        String link = sc.nextLine();

        o.setName(name);
        o.setTeacher(teacher);
        o.setLink(link);
        o.setType("online");

        FileWriter fw = new FileWriter(FileService.fileCourses, true);
        try {
            FileService.fileCourses.createNewFile();
        } catch (IOException e) {}
        
        fw.write(o.getName() + ", " + o.getId() + ", " + o.getTeacher() + ", " + o.getType() + ", " + o.getLink() + System.lineSeparator());
        fw.close();

        System.out.println("Course '" + name + "' added (teacher: " + teacher + ", link: " + link + ", id: " + o.getId() + ")");
        courses.add(o);

        return o;
    }

    //Create classroom course and add it to a list
    public ClassRoomCourse createClassRoomCourse() throws IOException{
        System.out.println("");
        ClassRoomCourse c = new ClassRoomCourse();

        System.out.println("Input course name:");
        String name = sc.nextLine();

        for (Course course : courses) {
            if(course.getName().equals(name)){
                System.out.println("Course with that name already exists");
                createCourse();
            }else{
                continue;
            }
        }
        System.out.println("Input course teacher:");
        String teacher = sc.nextLine();

        System.out.println("Input course classroom:");
        String room = sc.nextLine();

        c.setName(name);
        c.setTeacher(teacher);
        c.setRoomName(room);
        c.setType("campus");

        FileWriter fw = new FileWriter(FileService.fileCourses, true);
        try {
            FileService.fileCourses.createNewFile();
        } catch (IOException e) {}
        
        if(c.courseStudents.size() > 0){
            fw.write(c.getName() + ", " + c.getId()  + ", " + c.getTeacher() + ", " + c.getType() + ", " + c.getRoomName() + ", " + c.getCourseStudents() + System.lineSeparator());
        }else {
            fw.write(c.getName() + ", " + c.getId()  + ", " + c.getTeacher() + ", " + c.getType() + ", " + c.getRoomName() + System.lineSeparator());
        }
        
        fw.close();
        
        System.out.println("Course '" + name + "' added (teacher: " + teacher + ", classroom: " + room + ", id: " + c.getId() + ")");
        courses.add(c);

        return c;
    }

    //Add a student to a course by selecting their corresponding IDs
    public void addStudentToCourse(){
        System.out.println("");
        System.out.println("Input student id:");

        int value = Integer.parseInt(sc.nextLine());
        for (Student student : students) {
            if(student.getId() == value){
                System.out.println(student.getName() + " (" + student.getId() + ")");

                System.out.println("Input course id:");

                int value2 = Integer.parseInt(sc.nextLine());
                for (Course course : courses) {
                    if(course.getId() == value2){
                        course.addStudent(student);
                    }
                }
            }
        }
    }
    
    //List all existing courses
    public void listCourses() throws FileNotFoundException{
        System.out.println("");
        try {
            fs.listBackupCourses();
            for (Course course : courses) {
                System.out.println(course.getCourseStudents());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Course files not found");
        }
    }
}
