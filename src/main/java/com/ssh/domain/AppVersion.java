package com.ssh.domain;

import java.math.BigDecimal;
import java.util.Date;

public class AppVersion {
    private Long id;

    private Long appid;

    private String versionno;

    private String versioninfo;

    private Long publishstatus;

    private String downloadlink;

    private BigDecimal versionsize;

    private Long createdby;

    private Date creationdate;

    private Long modifyby;

    private Date modifydate;

    private String apklocpath;

    private String apkfilename;

    private String appname;
    private String publishstatusname;

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getPublishstatusname() {
        return publishstatusname;
    }

    public void setPublishstatusname(String publishstatusname) {
        this.publishstatusname = publishstatusname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAppid() {
        return appid;
    }

    public void setAppid(Long appid) {
        this.appid = appid;
    }

    public String getVersionno() {
        return versionno;
    }

    public void setVersionno(String versionno) {
        this.versionno = versionno;
    }

    public String getVersioninfo() {
        return versioninfo;
    }

    public void setVersioninfo(String versioninfo) {
        this.versioninfo = versioninfo;
    }

    public Long getPublishstatus() {
        return publishstatus;
    }

    public void setPublishstatus(Long publishstatus) {
        this.publishstatus = publishstatus;
    }

    public String getDownloadlink() {
        return downloadlink;
    }

    public void setDownloadlink(String downloadlink) {
        this.downloadlink = downloadlink;
    }

    public BigDecimal getVersionsize() {
        return versionsize;
    }

    public void setVersionsize(BigDecimal versionsize) {
        this.versionsize = versionsize;
    }

    public Long getCreatedby() {
        return createdby;
    }

    public void setCreatedby(Long createdby) {
        this.createdby = createdby;
    }

    public Date getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(Date creationdate) {
        this.creationdate = creationdate;
    }

    public Long getModifyby() {
        return modifyby;
    }

    public void setModifyby(Long modifyby) {
        this.modifyby = modifyby;
    }

    public Date getModifydate() {
        return modifydate;
    }

    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }

    public String getApklocpath() {
        return apklocpath;
    }

    public void setApklocpath(String apklocpath) {
        this.apklocpath = apklocpath;
    }

    public String getApkfilename() {
        return apkfilename;
    }

    public void setApkfilename(String apkfilename) {
        this.apkfilename = apkfilename;
    }
}