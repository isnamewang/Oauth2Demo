package com.example.util;

import com.example.bean.ResourceBean;
import com.example.bean.RoleBean;
import com.example.bean.UserBean;
import org.springframework.stereotype.Component;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TestData {

    private List<UserBean> allUser;



    private List<UserBean> getAllUser(){
        if(allUser == null){
            allUser = new ArrayList<>();

            ResourceBean mobileResource = new ResourceBean("1","mobile");
            ResourceBean salaryResource = new ResourceBean("2","salary");
            List<ResourceBean> adminResources = new ArrayList<>();
            adminResources.add(mobileResource);
            adminResources.add(salaryResource);

            List<ResourceBean> managerResources = new ArrayList<>();
            managerResources.add(salaryResource);

            RoleBean adminRole = new RoleBean("1","mobile");
            adminRole.setResources(adminResources);
            RoleBean managerRole = new RoleBean("2","salary");
            managerRole.setResources(managerResources);
            List<RoleBean> adminRoles = new ArrayList<>();
            adminRoles.add(adminRole);
            List<RoleBean> managerRoles = new ArrayList<>();
            managerRoles.add(managerRole);
            UserBean user1 = new UserBean("1","admin","123456");
            user1.setUserRoles(adminRoles);
            UserBean user2 = new UserBean("2","manager","123456");
            user2.setUserRoles(managerRoles);
            UserBean user3 = new UserBean("3","worker","123456");

            allUser.add(user1);
            allUser.add(user2);
            allUser.add(user3);
            return allUser;
        }
        return allUser;
    }

    public UserBean qeryUser(UserBean user){
        List<UserBean> allUser = getAllUser();
        List<UserBean> list = allUser.stream().filter(userBean ->
                userBean.getUserName().equals(user.getUserName()) &&
                userBean.getUserPass().equals(user.getUserPass())).collect(Collectors.toList());
        return list.size()>0?list.get(0):null;
    }

}
