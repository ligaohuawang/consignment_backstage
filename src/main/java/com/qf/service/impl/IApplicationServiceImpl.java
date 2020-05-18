package com.qf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.entity.Application;
import com.qf.mapper.IApplicationMapper;
import com.qf.service.IApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

@Service
public class IApplicationServiceImpl implements IApplicationService {

    @Autowired
    private IApplicationMapper iApplicationMapper;

    @Override
    public ModelMap selectPage(Page<Application> page, ModelMap map) {
        //去查询全部未被删除的用户订单并分页
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("status",0);
        IPage iPage = iApplicationMapper.selectPage(page, queryWrapper);
        page.setRecords(iPage.getRecords());
        //将对象put入map中
        map.put("page",page);
        return map;
    }
}
