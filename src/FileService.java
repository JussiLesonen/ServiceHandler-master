import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileService {
    static File fileStudents = new File("./backupStudents.txt");
    static File fileCourses = new File("./backupCourses.txt");

    public void listBackupStudents() throws FileNotFoundException {
        Scanner scanner = new Scanner(fileStudents);

        if (scanner.hasNextLine() || !Service.students.isEmpty()) {
            while (scanner.hasNextLine()) {
                String studentData = scanner.nextLine();
                System.out.println(studentData);
            }
        } else {
            System.out.println("Student list is empty");
        }
        scanner.close();
    }

    public void listBackupCourses() throws FileNotFoundException {
        Scanner scanner = new Scanner(fileCourses);

        if (scanner.hasNextLine() || !Service.courses.isEmpty() ) {
            while (scanner.hasNextLine()) {
                String courseData = scanner.nextLine();
                System.out.println(courseData);
            }
        } else {
            System.out.println("Course list is empty");
        }
        scanner.close();
    }

    public static int countLineBufferedReader(File fileName) {
        int lines = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (reader.readLine() != null) lines++;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public void makeStudents() throws IOException {
        fileStudents.createNewFile();
        // Make student objects backup textfile
        FileWriter fw = new FileWriter(fileStudents, true);
        try {
        fileStudents.createNewFile();
        } catch (IOException e) {
        System.out.println("error");
        }
        fw.close();

        BufferedReader br = new BufferedReader(new FileReader(fileStudents));
        String line;
        while ((line = br.readLine()) != null) {
            String[] vals = line.split(", ");
            String a = (vals[0]);
            int b = Integer.parseInt(vals[1]);

            Student s = new Student(a, b);

            Service.students.add(s);

        }
        br.close();
    }

    public void makeCourses() throws NumberFormatException, IOException {
        fileCourses.createNewFile();
        FileWriter fw = new FileWriter(fileCourses, true);
        try {
        fileCourses.createNewFile();
        } catch (IOException e) {
        System.out.println("error");
        }
        fw.close();
        BufferedReader br = new BufferedReader(new FileReader(fileCourses));
        String line;
        while ((line = br.readLine()) != null) {
            String[] vals = line.split(", ");
            String a = (vals[0]);
            int b = Integer.parseInt(vals[1]);
            String c = (vals[2]);
            String d = (vals[3]);
            Course course = new Course(a, b, c, d);

            Service.courses.add(course);

        }
        br.close();
    }
}
