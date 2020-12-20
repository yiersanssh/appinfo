package com.ssh.dao;

import com.ssh.domain.DevUser;
import org.apache.ibatis.annotations.Param;

public interface DevUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DevUser record);

    int insertSelective(DevUser record);

    DevUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DevUser record);

    int updateByPrimaryKey(DevUser record);

    //DevUser selectByCondition(@Param("devCode")String devCode,@Param("devPassword") String devPassword);
    DevUser selectByCondition(DevUser devUser);
}