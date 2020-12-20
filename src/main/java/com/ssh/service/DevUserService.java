package com.ssh.service;

import com.ssh.domain.DevUser;

public interface DevUserService {
    DevUser dologin(String devCode, String devPassword);
}
