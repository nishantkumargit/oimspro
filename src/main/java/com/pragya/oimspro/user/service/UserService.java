package com.pragya.oimspro.user.service;

import com.pragya.oimspro.user.entity.User;
import com.pragya.oimspro.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByEmailId(username).orElseThrow(() -> new UsernameNotFoundException("Invalid username"));
            }
        };
    }
    @Transactional
    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserByEmailId(String emailId) {
        return userRepository.findByEmailId(emailId).orElseThrow(() -> new UsernameNotFoundException("Invalid username"));
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public long getTotalUser() {
        return userRepository.count();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }
}
