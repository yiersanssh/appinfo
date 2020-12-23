package com.ssh.service;

import com.ssh.domain.AppCategory;
import com.ssh.domain.AppInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppService {

    List<AppInfo> selectAppInfoByCondition(AppInfo appInfo);

    List<AppCategory> selectAppCategoryByParentId(String pid);

    boolean selectAPKNameExist(String apkName);

    void addAppInfo(AppInfo appInfo);

    AppInfo selectAppInfoById(Long id);

    void updataAppInfoLogo(Long id);

    void updataAppInfo(AppInfo appInfo);
}
