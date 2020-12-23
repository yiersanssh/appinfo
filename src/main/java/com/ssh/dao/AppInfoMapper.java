package com.ssh.dao;

import com.ssh.domain.AppInfo;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Appinfo;

import java.util.List;

public interface AppInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AppInfo record);

    int insertSelective(AppInfo record);

    AppInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AppInfo record);

    int updateByPrimaryKey(AppInfo record);

    List<AppInfo> selectByCondition(AppInfo appinfo);

    int selectAPKNameExist(String APKName);
}