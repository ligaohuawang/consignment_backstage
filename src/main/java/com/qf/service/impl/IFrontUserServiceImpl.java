package com.qf.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.entity.FrontUser;
import com.qf.mapper.IFrontUserMapper;
import com.qf.service.IFrontUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;

@Service
public class IFrontUserServiceImpl implements IFrontUserService {
    @Autowired
    private IFrontUserMapper iFrontUserMapper;

    @Override
    public int insert(FrontUser user) {
        return 0;
    }

    @Override
    public int deleteById(Integer id) {
        return 0;
    }

    @Override
    public int update(FrontUser user) {
        return 0;
    }

    @Override
    public FrontUser selectById(Integer id) {
        return null;
    }

    @Override
    public List<FrontUser> selectList() {
        return null;
    }

    /***
     * 去查询全部未被删除的用户并分页
     * @param page
     * @param map
     * @return
     */
    @Override
    public ModelMap selectPage(Page<FrontUser> page, ModelMap map) {
        //去查询全部未被删除的用户并分页
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("status",1);
        IPage iPage = iFrontUserMapper.selectPage(page, queryWrapper);
        page.setRecords(iPage.getRecords());
        //将对象put入map中
        map.put("page",page);
        return map;
    }
}
