package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.SysLogDao;
import com.itheima.domain.SysLog;
import com.itheima.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogDao sysLogDao;


    @Override
    public List<SysLog> findByPage(Integer page,Integer size) {

        PageHelper.startPage(page,size);
        return sysLogDao.findByPage(page,size);
    }

    @Override
    public void save(SysLog sysLog) {
        sysLogDao.save(sysLog);
    }
}
