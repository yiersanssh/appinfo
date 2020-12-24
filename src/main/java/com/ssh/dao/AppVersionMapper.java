package com.ssh.dao;

import com.ssh.domain.AppVersion;

import java.util.List;

public interface AppVersionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AppVersion record);

    int insertSelective(AppVersion record);

    AppVersion selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AppVersion record);

    int updateByPrimaryKey(AppVersion record);

    List<AppVersion> selectAppVersionList(String id);

    AppVersion selectByPath(String apkLocPath);
}