package com.qf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.entity.BackUser;
import com.qf.entity.BackUserAndRole;
import com.qf.mapper.IBackUserAndRoleMapper;
import com.qf.mapper.IBackUserMapper;
import com.qf.service.IBackUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;

@Service
public class IBackUserServiceImpl implements IBackUserService {

    @Autowired
    private IBackUserMapper iBackUserMapper;
    @Autowired
    private IBackUserAndRoleMapper iBackUserAndRoleMapper;

    @Override
    public int insert(BackUser backUser) {
        return 0;
    }

    @Override
    public int deleteById(Integer id) {
        return 0;
    }

    @Override
    public int update(BackUser backUser) {
        return 0;
    }

    @Override
    public BackUser selectById(Integer id) {
        BackUser backUser = iBackUserMapper.selectById(id);
        return backUser;
    }

    @Override
    public List<BackUser> selectList() {
        return null;
    }

    @Override
    public ModelMap selectPage(Page<BackUser> page, ModelMap map) {
        return null;
    }

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    @Override
    public BackUser selectByUserName(String username) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username",username);
        BackUser backUser = iBackUserMapper.selectOne(queryWrapper);
        return backUser;
    }

    /**
     * 根据角色id查询未拥有该角色的所有用户并分页
     * @param roleid
     * @param page
     * @param map
     * @return
     */
    @Override
    public ModelMap selectPage2(Integer roleid, Page<BackUser> page, ModelMap map) {
        //根据角色id查询用户角色中间表中所有数据
        QueryWrapper queryWrapper = new QueryWrapper();
         queryWrapper.eq("rid", roleid);
        List<BackUserAndRole> backUserAndRoleList=iBackUserAndRoleMapper.selectList(queryWrapper);
        //得到用户id集合
        List<Integer> backUserIdList = new ArrayList<>();
        if (backUserAndRoleList.size()>0){
            for (int i = 0; i < backUserAndRoleList.size(); i++) {
                backUserIdList.add(backUserAndRoleList.get(i).getUid());
            }
        }

        QueryWrapper queryWrapper2 = new QueryWrapper();
        queryWrapper.eq("status", 1);
        IPage<BackUser> iPage = iBackUserMapper.selectPage(page,queryWrapper2);
        List<BackUser> backUserList = new ArrayList<>();
        if (iPage.getRecords().size()>0){
            if (backUserIdList.size()>0){
                for (int i = 0; i < iPage.getRecords().size(); i++) {
                    //当前用户id是否在backUserIdList里
                    boolean contains = backUserIdList.contains(iPage.getRecords().get(i).getId());
                    if (!contains){
                        //不在，将当前用户装入一个集合中
                        backUserList.add(iPage.getRecords().get(i));
                    }
                }
            }
        }
        //重新设置了数据个数
        page.setRecords(backUserList);
        //因为重新设置了数据个数，所有page的total也要改了
        page.setTotal(backUserList.size());
        map.put("page",page);
        return map;
    }

    /**
     * 根据角色id查询该角色下所有的用户并分页
     * @param roleid
     * @param page
     * @param map
     * @return
     */
    @Override
    public ModelMap selectUserPageByRoleId(Integer roleid, Page<BackUser> page, ModelMap map) {
        Page<BackUser> page2 = iBackUserMapper.getUserPageByRoleId(page, roleid);
        map.put("page",page2);
        return map;
    }

    /**
     * 根据角色id查询未拥有该角色的所有用户并分页
     * @param roleid
     * @param page
     * @param map
     * @return
     */
    @Override
    public ModelMap getUserNotHaveRolePage(Integer roleid, Page<BackUser> page, ModelMap map) {
        Page<BackUser> userNotHaveRolePage = iBackUserMapper.getUserNotHaveRolePage(page, roleid);
        map.put("page",userNotHaveRolePage);
        return map;
    }

}
