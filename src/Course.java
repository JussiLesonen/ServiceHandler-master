import java.util.ArrayList;
import java.util.List;

public class Course {
    private String name;
    private String teacher;
    public List<Student> courseStudents = new ArrayList<>();
    private static int id = 0;
    private int courseId;
    private String type;

    public Course(){
        id = FileService.countLineBufferedReader(FileService.fileCourses) + 1;
        name = "Course";
        teacher = "Teacher";
        courseId = id;
    }

    public Course(String name, String teacher) {
        id = FileService.countLineBufferedReader(FileService.fileCourses) + 1;
        this.name = name;
        this.teacher = teacher;
    }

    public Course(String name,int courseId, String teacher,  String type) {
        this.name = name;
        this.courseId = courseId;
        this.teacher = teacher;
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return this.teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public int getId(){
        return this.courseId;
    }

    public List<Student> getCourseStudents() {
        return this.courseStudents;
    }

    public void setCourseStudents(List<Student> courseStudents) {
        this.courseStudents = courseStudents;
    }

    public int getCourseId() {
        return this.courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void addStudent(Student student){
        courseStudents.add(student);
        System.out.println(student.getName() + " added to course " + this.name);
    }
}
