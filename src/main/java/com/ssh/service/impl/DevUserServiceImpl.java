package com.ssh.service.impl;

import com.ssh.dao.DevUserMapper;
import com.ssh.domain.DevUser;
import com.ssh.service.DevUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DevUserServiceImpl implements DevUserService {

    @Autowired
    private DevUserMapper devUserMapper;


    @Override
    public DevUser dologin(String devCode, String devPassword) {

        DevUser du = new DevUser();
        du.setDevcode(devCode);
        du.setDevpassword(devPassword);
        DevUser devUser = devUserMapper.selectByCondition(du);

        return devUser;
    }
}
