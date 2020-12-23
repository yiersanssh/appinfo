package com.ssh.dao;

import com.ssh.domain.AppCategory;

import java.util.List;

public interface AppCategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AppCategory record);

    int insertSelective(AppCategory record);

    AppCategory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AppCategory record);

    int updateByPrimaryKey(AppCategory record);

    List<AppCategory> selectParentIdNull();
    List<AppCategory> selectByParentId(String parentId);

    List<AppCategory> selectcategoryLevel2();

    List<AppCategory> selectcategoryLevel3();
}