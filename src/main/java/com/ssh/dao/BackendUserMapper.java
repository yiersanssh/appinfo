package com.ssh.dao;

import com.ssh.domain.BackendUser;
import org.apache.ibatis.annotations.Param;

public interface BackendUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BackendUser record);

    int insertSelective(BackendUser record);

    BackendUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BackendUser record);

    int updateByPrimaryKey(BackendUser record);

    BackendUser selectByCondition(@Param("userCode") String userCode, @Param("userPassword") String userPassword);
}