package com.Spring.DevDiary.Service;

import com.Spring.DevDiary.Exception.ResourceNotFoundException;
import com.Spring.DevDiary.Model.User;
import com.Spring.DevDiary.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAuthService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User User) {
        return userRepository.save(User);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public User getUserByUserName(String userName) {
        return userRepository.findByUserNameIgnoringCase(userName);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }
}


