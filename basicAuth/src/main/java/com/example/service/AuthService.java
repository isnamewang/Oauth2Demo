package com.example.service;


import com.example.bean.UserBean;
import com.example.util.TestData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

@Service
public class AuthService {
    private final String demoUserName = "admin";
    private final String demoUserPass="123456";

    @Resource
    private TestData testData;

    public UserBean userLogin(UserBean user){
        UserBean queryUser = testData.qeryUser(user);
        if (queryUser != null){
            queryUser.setUserId(UUID.randomUUID().toString());
        }
        return queryUser;
    }


}
