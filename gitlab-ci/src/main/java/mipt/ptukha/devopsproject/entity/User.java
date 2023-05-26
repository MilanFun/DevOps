package mipt.ptukha.devopsproject.entity;

/**
 *
 * @author aleksey
 */
public class User {
    private int id;
    private String firstName;

    private String lastName;

    private String email;

    private int age;
    
    public int getId() {
        return this.id;
    }
    
    public String getFirstName() {
        return this.firstName;
    }
    
    public String getLastName() {
        return this.lastName;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public int getAge() {
        return this.age;
    }

    public void setID(int id) {
        this.id = id;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
