import java.io.Serializable;

public class Student implements Serializable{


    private static final long serialVersionUID = 6986014933814683726L;
    private String stuId;
    private String stuName;
    private int age;
    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getStuId() {
        return stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Student(String stuId, String stuName, int age) {
        this.stuId = stuId;
        this.stuName = stuName;
        this.age = age;
    }
}
