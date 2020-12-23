package com.ssh.service.impl;

import com.ssh.dao.AppCategoryMapper;
import com.ssh.dao.DataDictionaryMapper;
import com.ssh.domain.AppCategory;
import com.ssh.domain.DataDictionary;
import com.ssh.service.DicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DicServiceImpl implements DicService {

    @Autowired
    private DataDictionaryMapper dataDictionaryMapper;
    @Autowired
    private AppCategoryMapper appCategoryMapper;


    @Override
    public Map<String, List> getAll() {

        //获取数据字典中的值
        List<DataDictionary> userTypeList = dataDictionaryMapper.getDicList("用户类型");
        List<DataDictionary> appStatusList = dataDictionaryMapper.getDicList("APP状态");
        List<DataDictionary> appFlatFormList = dataDictionaryMapper.getDicList("所属平台");
        List<DataDictionary> publishStatusList = dataDictionaryMapper.getDicList("发布状态");

        //获取级别分类中的值
        List<AppCategory> categoryLevel1List = appCategoryMapper.selectParentIdNull();
        List<AppCategory> categoryLevel2List = appCategoryMapper.selectcategoryLevel2();
        List<AppCategory> categoryLevel3List = appCategoryMapper.selectcategoryLevel3();


        Map<String,List> map = new HashMap<>();
        map.put("userTypeList",userTypeList);
        map.put("statusList",appStatusList);
        map.put("flatFormList",appFlatFormList);
        map.put("publishStatusList",publishStatusList);
        map.put("categoryLevel1List",categoryLevel1List);
        //map.put("categoryLevel2List",categoryLevel2List);
        //map.put("categoryLevel3List",categoryLevel3List);


        return map;
    }

    @Override
    public List<DataDictionary> getStatusList(String typeName) {
        List<DataDictionary> statusList = dataDictionaryMapper.getDicList(typeName);

        return statusList;
    }

    @Override
    public List<DataDictionary> getflatFormList(String typeName) {
        List<DataDictionary> flatFormList = dataDictionaryMapper.getDicList(typeName);
        return flatFormList;
    }
}
