package com.meepwn.ssm.dao;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import javax.annotation.PostConstruct;

/**
 * @author MeePwn
 */
public abstract class BaseDao extends SqlSessionDaoSupport {

    /**
     * 初始化方法
     */
    @PostConstruct
    public abstract void init();

}
