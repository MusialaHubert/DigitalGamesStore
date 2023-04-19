package musialkov.digitalgamesstore.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) throws Exception {
        String password = passwordEncoder.encode("password");
        String sql = String.format("INSERT INTO shop_user (id, nickname, email, password, role)" +
                "VALUES (0, 'Musialkov', 'musialkov@gmail.com', '%s' , 'ADMIN')", password);
        jdbcTemplate.update(sql);
    }
}
