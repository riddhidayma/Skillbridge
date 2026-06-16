package com.skillbridge.service;

import com.skillbridge.entity.User;
import com.skillbridge.exception.ConflictException;
import com.skillbridge.exception.ResourceNotFoundException;
import com.skillbridge.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public User registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new ConflictException("Email already exists.");
        }

        String plainPassword = user.getPassword();

        // 2. Transform it using the BCrypt bean we created earlier
        String hashedPassword = encoder.encode(plainPassword);

        // 3. Put the SECURE version back into the user object
        user.setPassword(hashedPassword);

        // 4. Save to the database
        return userRepository.save(user);
    }



    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public User verifyLogin(String email, String rawPassword) {
        User user = userRepository.findByEmail(email).orElse(null);

        // We use encoder.matches() because we can't use .equals() on hashes!
        if (user != null && encoder.matches(rawPassword, user.getPassword())) {
            return user;
        }
        return null;
    }

    public User updateUser(Long id, String name, String email) {
        User user = getUserById(id);
        userRepository.findByEmail(email)
                .filter(existing -> !existing.getId().equals(id))
                .ifPresent(existing -> {
                    throw new ConflictException("Email already exists.");
                });
        user.setName(name);
        user.setEmail(email);
        return userRepository.save(user);
    }
}