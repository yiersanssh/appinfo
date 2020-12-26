package com.ssh.service;

import com.ssh.domain.BackendUser;

public interface BackendUserService {
    BackendUser login(String userCode, String userPassword);
}
