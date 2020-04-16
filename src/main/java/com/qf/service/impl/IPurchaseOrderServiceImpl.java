package com.qf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.entity.PurchaseOrder;
import com.qf.mapper.IPurchaseOrderMapper;
import com.qf.service.IPurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;

@Service
public class IPurchaseOrderServiceImpl implements IPurchaseOrderService {
    @Autowired
    private IPurchaseOrderMapper iPurchaseOrderMapper;

    @Override
    public int insert(PurchaseOrder purchaseOrder) {
        return 0;
    }

    @Override
    public int deleteById(Integer id) {
        return 0;
    }

    @Override
    public int update(PurchaseOrder purchaseOrder) {
        return 0;
    }

    @Override
    public PurchaseOrder selectById(Integer id) {
        return null;
    }

    @Override
    public List<PurchaseOrder> selectList() {
        return null;
    }

    @Override
    public ModelMap selectPage(Page<PurchaseOrder> page, ModelMap map) {
        //去查询全部未被删除的用户订单并分页
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("status",1);
        IPage iPage = iPurchaseOrderMapper.selectPage(page, queryWrapper);
        page.setRecords(iPage.getRecords());
        //将对象put入map中
        map.put("page",page);
        return map;
    }

}
