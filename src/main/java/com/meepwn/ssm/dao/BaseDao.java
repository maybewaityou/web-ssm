package com.meepwn.ssm.dao;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import javax.annotation.PostConstruct;

public abstract class BaseDao extends SqlSessionDaoSupport {

    @PostConstruct
    public abstract void init();

}
