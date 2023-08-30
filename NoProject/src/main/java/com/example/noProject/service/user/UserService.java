package com.example.noProject.service.user;


import com.example.noProject.dataSource.defaultDataSource.entities.SystemUser;

public interface UserService {
    /**
     *验证用户
     * @param userName
     * @param userPassword
     * @return
     * @throws Exception
     */
    int validateUser(String userName,String userPassword) throws Exception;


    SystemUser getUserByName(String username);
}
