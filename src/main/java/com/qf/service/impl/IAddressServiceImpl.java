package com.qf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.entity.Address;
import com.qf.mapper.IAddressMapper;
import com.qf.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;

@Service
public class IAddressServiceImpl implements IAddressService {
    @Autowired
    private IAddressMapper iAddressMapper;

    @Override
    public int insert(Address address) {
        return 0;
    }

    @Override
    public int deleteById(Integer id) {
        return 0;
    }

    @Override
    public int update(Address address) {
        return 0;
    }

    @Override
    public Address selectById(Integer id) {
        return iAddressMapper.selectById(id);
    }

    @Override
    public List<Address> selectList() {
        return null;
    }

    @Override
    public ModelMap selectPage(Page<Address> page, ModelMap map) {
        //去查询全部未被删除的用户并分页
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("status",1);
        IPage iPage = iAddressMapper.selectPage(page, queryWrapper);
        page.setRecords(iPage.getRecords());
        //将对象put入map中
        map.put("page",page);
        return map;
    }
}
