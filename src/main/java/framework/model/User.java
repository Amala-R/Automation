package framework.model;

import com.github.javafaker.Faker;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    public static Faker faker = new Faker();

    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private String userStatus;
    public User(){}

    public User(int id) {
        this.id = faker.number().digit()+id;
        this.firstName = faker.name().firstName();
        this.lastName = faker.name().lastName();
        this.username = firstName + " " + lastName;
        this.email =  firstName + lastName + "@gmail.com";
        this.password = faker.internet().password();
        this.phone = faker.phoneNumber().cellPhone();
        this.userStatus = faker.number().digit();
    }
}
