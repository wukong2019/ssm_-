package com.itheima.service;

import com.itheima.domain.SysLog;

import java.util.List;

public interface SysLogService {
    List<SysLog> findByPage(Integer page,Integer size);

    void save(SysLog sysLog);
}
