package com.example.service;

import com.example.domain.User;
import com.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements  UserService{
    private static int count = 1;
    @Autowired
    UserMapper userMapper;

    public void addUser(User user){
        userMapper.addUser(user);
    }

    @Scheduled(cron = "0 */5 * * * ?")
    public void dataCount() {
        System.out.println("当前用户总数是" + userMapper.countUser());
    }
}
