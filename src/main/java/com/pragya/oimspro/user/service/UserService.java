package com.pragya.oimspro.user.service;

import com.pragya.oimspro.company.entity.Company;
import com.pragya.oimspro.user.entity.User;
import com.pragya.oimspro.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService  {
    @Autowired
    private UserRepository userRepository;
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByEmailId(username).orElseThrow(() -> new UsernameNotFoundException("Invelid username"));
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
}
