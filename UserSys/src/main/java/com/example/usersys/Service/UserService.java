package com.example.usersys.Service;


import com.example.usersys.Api.ApiException;
import com.example.usersys.Model.User;
import com.example.usersys.Repsitory.UserRepository;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User addNewUser(User user) {

        userRepository.save(user);

        return user;
    }

    public User updateUser(Integer id, User user) {
        User oldUser = userRepository.findAllById(id);
        if (oldUser == null) {
            throw new ApiException("null");
        }
        oldUser.setAge(user.getAge());
        oldUser.setRole(user.getRole());
        oldUser.setName(user.getName());
        oldUser.setEmail(user.getEmail());
        oldUser.setPassword(user.getPassword());
        userRepository.save(oldUser);
        return oldUser;
    }

    public User delete(Integer id) {
        User user = userRepository.findAllById(id);
        if (user == null) {
            throw new ApiException("null");
        }
        userRepository.delete(user);
        return user;
    }


    public User checkPassAndUserName(String userName, String pass) {
        User user = userRepository.findAllByname(userName);

        if (user == null) {
            throw new ApiException(" not found  userName in Database");

        }



        if (user.getName().equals(userName) && !user.getPassword().equals(pass)) {
            throw new ApiException("userName or Pass is  Not Correct");
        }

        return user;
    }
    public @NotNull(message = "Cannot be name null ")
    @Size(min = 4, message = "Length more than 4 ") String
    getuserbyEmails(String email){
        User user=userRepository.findAllByEmail(email);
       if (user==null){
           throw new ApiException("not found your Email in my Database");
       }

        return user.getName();
    }

    public List<User> gauwsr(String role){
        List<User> user= userRepository.findAllByRole(role);
        if (user==null){
            throw  new ApiException("Sorry  not found this role");
        }


        return user;
    }

        public List<User> specificAgeOrAbove(Integer age){
      List<User> user=userRepository.findAllByAge(age);
        if (user==null){
            throw new ApiException("sorry not found age!!");
        }
        return user;
        }



}
