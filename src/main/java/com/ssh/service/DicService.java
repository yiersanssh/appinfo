package com.ssh.service;

import com.ssh.domain.DataDictionary;

import java.util.List;
import java.util.Map;

public interface DicService {
    Map<String, List> getAll();

    List<DataDictionary> getStatusList(String typeName);

    List<DataDictionary> getflatFormList(String typeName);
}
