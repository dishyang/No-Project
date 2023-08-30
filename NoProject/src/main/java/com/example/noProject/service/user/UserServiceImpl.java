package com.example.noProject.service.user;

import com.example.noProject.dataSource.defaultDataSource.entities.SystemUser;
import com.example.noProject.dataSource.defaultDataSource.mappers.SystemUsersMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl implements UserService {
    @Resource
    SystemUsersMapper systemUsersMapper;

    @Override
    public int validateUser(String userName, String userPassword) throws Exception {
        return 0;
    }

    @Override
    public SystemUser getUserByName(String username) {
        return systemUsersMapper.selectByName(username);
    }
}
