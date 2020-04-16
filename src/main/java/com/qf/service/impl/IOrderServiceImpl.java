package com.qf.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.entity.*;
import com.qf.mapper.IOrdersMapper;
import com.qf.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import java.util.List;
@Service
public class IOrderServiceImpl implements IOrderService {
    @Autowired
    private IOrdersMapper iOrdersMapper;

    @Override
    public int insert(Orders orders) {
        return 0;
    }

    @Override
    public int deleteById(Integer id) {
        return 0;
    }

    @Override
    public int update(Orders orders) {
        return 0;
    }

    @Override
    public Orders selectById(Integer id) {
        return null;
    }

    @Override
    public List<Orders> selectList() {
        return null;
    }

    @Override
    public ModelMap selectPage(Page<Orders> page, ModelMap map) {
        //去查询全部未被删除的用户订单并分页
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("status",1);
        IPage iPage = iOrdersMapper.selectPage(page, queryWrapper);
        page.setRecords(iPage.getRecords());
        //将对象put入map中
        map.put("page",page);
        return map;
    }

}
