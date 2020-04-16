package com.qf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.entity.BackUserAndRole;
import com.qf.entity.Role;
import com.qf.mapper.IBackUserAndRoleMapper;
import com.qf.mapper.IRoleMapper;
import com.qf.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import java.util.List;

@Service
public class IRoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleMapper iRoleMapper;
    @Autowired
    private IBackUserAndRoleMapper iBackUserAndRoleMapper;

    @Override
    public int insert(Role role) {
        return 0;
    }

    @Override
    public int deleteById(Integer id) {
        return 0;
    }

    @Override
    public int update(Role role) {
        return 0;
    }

    @Override
    public Role selectById(Integer id) {
        return null;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Role> selectList() {
        List<Role> roleList = iRoleMapper.selectList(null);
        return roleList;
    }

    @Override
    public ModelMap selectPage(Page<Role> page, ModelMap map) {
        //去查询全部未被删除的用户并分页
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("status",1);
        IPage iPage = iRoleMapper.selectPage(page, queryWrapper);
        page.setRecords(iPage.getRecords());
        //将对象put入map中
        map.put("page",page);
        return map;
    }

    /**
     * 给用户绑定选中的角色
     * @param userIds
     * @param roleId
     */
    @Override
    @Transactional
    public void setTheRoleToUser(List<Integer> userIds, Integer roleId) {
        if (userIds.size()>0){
            for (int i = 0; i < userIds.size(); i++) {
                BackUserAndRole backUserAndRole =new BackUserAndRole();
                backUserAndRole.setStatus(1);
                backUserAndRole.setRid(roleId);
                backUserAndRole.setUid(userIds.get(i));
                iBackUserAndRoleMapper.insert(backUserAndRole);
            }
        }
    }
}
