package com.example.appwebhookbotserver.components;

import com.example.appwebhookbotserver.entity.User;
import com.example.appwebhookbotserver.entity.enums.RoleName;
import com.example.appwebhookbotserver.repository.RoleRepository;
import com.example.appwebhookbotserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Value("${spring.sql.init.mode}")
    private String initMode;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    public DataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (initMode.equals("always")) {
            userRepository.save(new User(
                    "+998993631856",
                    passwordEncoder.encode("root123"),
                    "Admin Admin",
                    "admin",
                    roleRepository.findAll()
            ));

            userRepository.save(new User(
                    "+998993631888",
                    passwordEncoder.encode("root123"),
                    "Simple User",
                    "admin",
                    roleRepository.findAllByRole(RoleName.ROLE_USER)
            ));

            userRepository.save(new User(
                    "+998993631899",
                    passwordEncoder.encode("root123"),
                    "Company Manager",
                    "manager",
                    roleRepository.findAllByRole(RoleName.ROLE_MANAGER)
            ));

            System.out.println(passwordEncoder.encode("root123"));
        }
    }
}
