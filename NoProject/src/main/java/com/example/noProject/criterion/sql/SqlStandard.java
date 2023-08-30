package com.example.noProject.criterion.sql;

import lombok.Data;

import java.util.Date;

@Data
public class SqlStandard {
    private long id;
    private Date createTime;
    private Date updateTime;
    private boolean isDeleted;
}
