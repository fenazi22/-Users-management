package com.example.usersys.Repsitory;

import com.example.usersys.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findAllById(Integer id);


    User findAllByname(String name);

    User findAllByEmail(String email);

    @Query("select  u from User u where u.role=?1")
    List<User>  findAllByRole(String role);

    @Query("select a from User a where a.age>=?1 ")
    List<User>  findAllByAge(Integer age);


}
