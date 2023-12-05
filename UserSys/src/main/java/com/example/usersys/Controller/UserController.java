package com.example.usersys.Controller;


import com.example.usersys.Model.User;
import com.example.usersys.Service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usersys")
@RequiredArgsConstructor
public class UserController {
    private  final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());

    }


    @PostMapping("/add")
    public ResponseEntity addNewUser(@Valid @RequestBody User user, Errors errors){
        if (errors.hasErrors()){
            String message= errors.getFieldError().getDefaultMessage();
            return  ResponseEntity.status(400).body(message);
        }
        User user2=userService.addNewUser(user);
        return  ResponseEntity.status(HttpStatus.OK) .body("Successfully added \n"+user2);
    }

@PutMapping("/update/{id}")
    public ResponseEntity updateUsers(@PathVariable Integer id,@Valid @RequestBody User user,Errors errors){
    if (errors.hasErrors()){
        String message= errors.getFieldError().getDefaultMessage();
        return  ResponseEntity.status(400).body(message);
    }

    User user1=userService.updateUser(id, user);
    return ResponseEntity.status(HttpStatus.OK).body(user1);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
    User user  = userService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(" Successfully Deleted ID:  "+user.getId()+" Name:"+user.getName());
    }

    @GetMapping("/check/{userName}/{pass}")
    public ResponseEntity check(@PathVariable String userName,@PathVariable String pass){
        User user=userService.checkPassAndUserName(userName,pass);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/checkbyEmail/{email}")
    public ResponseEntity check(@PathVariable String email){
        @NotNull(message = "Cannot be name null ") @Size(min = 4, message = "Length more than 4 ")
        String user=userService.getuserbyEmails(email);
        return ResponseEntity.status(HttpStatus.OK).body("I found  userName: "+user);
    }


    @GetMapping("/role/{roles}")
    public ResponseEntity role(@PathVariable String roles){
        List<User> user=userService.gauwsr(roles);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
    @GetMapping("/ages/{age}")
    public ResponseEntity ages(@PathVariable Integer age){
        List<User> user=userService.specificAgeOrAbove(age);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }


}


