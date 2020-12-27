package com.ssh.service;

import com.ssh.domain.AppCategory;
import com.ssh.domain.AppInfo;
import com.ssh.domain.AppVersion;
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

    List<AppVersion> selectAppVersionList(String id);

    void insertAppVersion(AppVersion appVersion);

    AppVersion selectAppVersion(Long vid);

    void updataAppVersionApk(Long id);

    void updataAppVersion(AppVersion appVersion);

    AppInfo selectAppInfoMessage(Long id);


    int deleteAppinfo(Long id);

    List<AppInfo> selectNoReviewAppInfo(AppInfo appInfo);
}
