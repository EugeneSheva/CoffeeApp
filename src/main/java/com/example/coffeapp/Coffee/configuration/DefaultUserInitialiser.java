package com.example.coffeapp.Coffee.configuration;
import com.example.coffeapp.Coffee.Model.Users.User;
import com.example.coffeapp.Coffee.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
class DefaultUserInitializer implements CommandLineRunner {

    private final UserRepository userRepository;


    public DefaultUserInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByUsername("0637579998") == null) {
            User user = new User();
            user.setUsername("0637579998");
            user.setName("director");
            user.setPassword("$2a$12$FOhsYOephsRWkUHe2RoJcOZ/vAC0isIufmaNWB/rE4Lw07WZnBVZu");
            user.setRole("ROLE_DIRECTOR");
            user.setActive(true);
            user.setLanguage("ukrainian");
            userRepository.save(user);
        }
    }
}