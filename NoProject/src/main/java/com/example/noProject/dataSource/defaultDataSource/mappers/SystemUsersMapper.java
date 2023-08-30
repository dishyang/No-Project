package com.example.noProject.dataSource.defaultDataSource.mappers;


import com.example.noProject.dataSource.defaultDataSource.entities.SystemUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemUsersMapper {
    //查询SystemUser
    List<SystemUser> selectSystemUser();

    //根据用户名查询SystemUser
    SystemUser selectByName(@Param("username") String username);

}
