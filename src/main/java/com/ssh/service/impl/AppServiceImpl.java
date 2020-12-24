package com.ssh.service.impl;

import com.ssh.dao.AppCategoryMapper;
import com.ssh.dao.AppInfoMapper;
import com.ssh.dao.AppVersionMapper;
import com.ssh.domain.AppCategory;
import com.ssh.domain.AppInfo;
import com.ssh.domain.AppVersion;
import com.ssh.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AppServiceImpl implements AppService {

    @Autowired
    private AppInfoMapper appInfoMapper;
    @Autowired
    private AppCategoryMapper appCategoryMapper;
    @Autowired
    private AppVersionMapper appVersionMapper;


    @Override
    public List<AppInfo> selectAppInfoByCondition(AppInfo appInfo) {
        List<AppInfo> list = appInfoMapper.selectByCondition(appInfo);
        return list;
    }

    @Override
    public List<AppCategory> selectAppCategoryByParentId(String parentId) {

        List<AppCategory> list = appCategoryMapper.selectByParentId(parentId);

        return list;
    }

    @Override
    public boolean selectAPKNameExist(String APKName) {
        boolean isExsit = true;
        int count = appInfoMapper.selectAPKNameExist(APKName);
        if (count==0){
            isExsit = false;
        }
        return isExsit;
    }

    @Override
    public void addAppInfo(AppInfo appInfo) {
        appInfoMapper.insertSelective(appInfo);
    }

    @Override
    public AppInfo selectAppInfoById(Long id) {
        AppInfo appInfo = appInfoMapper.selectAppInfoById(id);
        return appInfo;
    }

    @Override
    public void updataAppInfoLogo(Long id) {
        AppInfo appInfo = new AppInfo();
        appInfo.setId(id);
        appInfo.setLogopicpath("");
        appInfo.setLogolocpath("");
        appInfoMapper.updateByPrimaryKeySelective(appInfo);
    }

    @Override
    public void updataAppInfo(AppInfo appInfo) {
        appInfoMapper.updateByPrimaryKeySelective(appInfo);
    }

    @Override
    public List<AppVersion> selectAppVersionList(String id) {

        List<AppVersion> appVersionList = appVersionMapper.selectAppVersionList(id);

        return appVersionList;
    }

    @Override
    @Transactional
    public void insertAppVersion(AppVersion appVersion) {
        //增加版本
        appVersionMapper.insertSelective(appVersion);
        //修改app_info表中的versionId字段 为最新版本
        //找出刚刚新增的数据的id
        String apkLocPath = appVersion.getApklocpath();
        AppVersion appVersion1 = appVersionMapper.selectByPath(apkLocPath);
        AppInfo appInfo = new AppInfo();
        appInfo.setId(appVersion.getAppid());
        appInfo.setVersionid(appVersion1.getId());
        appInfoMapper.updateByPrimaryKeySelective(appInfo);
    }
}
