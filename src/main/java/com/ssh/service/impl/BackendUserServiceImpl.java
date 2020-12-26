package com.ssh.service.impl;

import com.ssh.dao.BackendUserMapper;
import com.ssh.domain.BackendUser;
import com.ssh.service.BackendUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BackendUserServiceImpl implements BackendUserService {

    @Autowired
    private BackendUserMapper backendUserMapper;

    @Override
    public BackendUser login(String userCode, String userPassword) {
        boolean flag = false;
        BackendUser backendUser = backendUserMapper.selectByCondition(userCode,userPassword);

        return backendUser;
    }
}
