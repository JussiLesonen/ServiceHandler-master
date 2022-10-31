public class Student {
    private String name;
    private static int id = 0;
    private int studentId;

    public Student(){
        id = FileService.countLineBufferedReader(FileService.fileStudents) + 1;
        name = "Student";
        studentId = id;
    }

    public Student(String name) {
        id = FileService.countLineBufferedReader(FileService.fileStudents) + 1;
        this.name = name;
        studentId = id;
    }

    public Student(String name, int studentId) {
        this.name = name;
        this.studentId = studentId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId(){
        return studentId;
    }
}
