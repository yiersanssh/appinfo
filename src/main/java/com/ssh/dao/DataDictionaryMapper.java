package com.ssh.dao;

import com.ssh.domain.DataDictionary;

import java.util.List;

public interface DataDictionaryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DataDictionary record);

    int insertSelective(DataDictionary record);

    DataDictionary selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DataDictionary record);

    int updateByPrimaryKey(DataDictionary record);

    List<DataDictionary> getDicList(String typeName);
}