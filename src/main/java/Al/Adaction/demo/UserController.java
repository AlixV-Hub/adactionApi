package Al.Adaction.demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// Classe pour représenter l'objet JSON reçu
class User {
    private String name;
    private int age;

    // Constructeur vide obligatoire pour Spring
    public User() {}

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getters et Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}

@RestController
public class UserController {

    @PostMapping("/user")
    public String createUser(@RequestBody User user) {
        return "Utilisateur reçu : " + user.getName() + ", âge : " + user.getAge();
    }
}
