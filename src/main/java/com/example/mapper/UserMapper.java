package com.example.mapper;

import com.example.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    public List<User> getUser();
    public Integer countUser();
    public void addUser(User user);
}
