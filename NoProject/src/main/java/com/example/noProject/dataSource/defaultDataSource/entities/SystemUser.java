package com.example.noProject.dataSource.defaultDataSource.entities;

import com.example.noProject.criterion.sql.SqlStandard;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SystemUser extends SqlStandard {
    private String userName;
    private String password;
    private String fullName;
    private String phone;
    private String email;
    private boolean status;
}
