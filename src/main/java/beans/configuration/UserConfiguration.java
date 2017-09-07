package beans.configuration;

import beans.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Configuration
public class UserConfiguration {

    @Bean
    public User john() {
        return new User(9999, "John", "user@gmail.com", LocalDate.of(1990, 1, 1));
    }

    @Bean
    public List<User> baseUsers() {
        return Arrays.asList(john());
    }
}
