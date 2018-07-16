package data;

/**
 * Created by 44223 on 2018/7/12.
 */

public class Student {
    public String name;
    public String password;
    public String email;

    public Student() {
        super();
    }

    public Student(String name, String password, String email) {
        super();
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student{" +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email=" + email +
                '}';
    }
}
