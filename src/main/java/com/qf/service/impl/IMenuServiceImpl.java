package com.qf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.entity.Menu;
import com.qf.entity.RoleAndMenu;
import com.qf.entity.Tree;
import com.qf.mapper.IMenuMapper;
import com.qf.mapper.IRoleAndMenuMapper;
import com.qf.result.ResultDate;
import com.qf.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;

@Service
public class IMenuServiceImpl implements IMenuService {

    @Autowired
    private IMenuMapper iMenuMapper;
    @Autowired
    private IRoleAndMenuMapper iRoleAndMenuMapper;

    @Override
    public int insert(Menu menu) {
        return 0;
    }

    @Override
    public int deleteById(Integer id) {
        return 0;
    }

    @Override
    public int update(Menu menu) {
        return 0;
    }

    @Override
    public Menu selectById(Integer id) {
        return null;
    }

    @Override
    public List<Menu> selectList() {
        return null;
    }

    @Override
    public ModelMap selectPage(Page<Menu> page, ModelMap map) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("status",1);
        IPage iPage = iMenuMapper.selectPage(page, queryWrapper);
        page.setRecords(iPage.getRecords());
        map.put("page",page);
        return map;
    }


    @Override
    public List<Tree> getMenuListTree(Integer roleId) {
        //1.需要查询全部的菜单给菜单树进行展示
        List<Menu> menus = iMenuMapper.selectList(null);
        //2.需要查询某个角色下所拥有的菜单id，用来判断是否选择复选框
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("rid",roleId);
        List<RoleAndMenu> roleAndMenuList = iRoleAndMenuMapper.selectList(queryWrapper);
        List<Integer> ids=new ArrayList<>();;
        if (roleAndMenuList.size()>0){
            for (int i = 0; i < roleAndMenuList.size(); i++) {
                ids.add(roleAndMenuList.get(i).getMid());
            }
        }
        //3.根据1.2步构建返回值
        List<Tree> treeList = new ArrayList<>();
        for (Menu Menu:
                menus) {
            Tree tree = new Tree();
            tree.setId(Menu.getId());
            tree.setPid(Menu.getPId());
            tree.setName(Menu.getMName());
            //如果这个权限id在查出来的权限id的集合中
            if(ids.contains(Menu.getId())){
                //那就勾选中这个复选框
                tree.setChecked(true);
            }
            treeList.add(tree);
        }
        return treeList;
    }

    //重新给角色设置权限,有数组
    @Override
    @Transactional
    public ResultDate changeMenuToRole(List<Integer> ids, Integer roleId) {
        //先根据roleid删除角色权限中间表的数据
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("rid",roleId);
        int delete = iRoleAndMenuMapper.delete(queryWrapper);
        if(delete>0){
            //再根据roleid和ids插入角色权限中间表数据
            for (int i = 0; i < ids.size(); i++) {
                RoleAndMenu roleAndMenu = new RoleAndMenu();
                roleAndMenu.setMid(ids.get(i));
                roleAndMenu.setRid(roleId);
                roleAndMenu.setStatus(1);
                iRoleAndMenuMapper.insert(roleAndMenu);
            }
        }
        return new ResultDate().setResult(true).setData("设置成功！");
    }

    //重新给角色设置权限，无数组
    @Override
    public ResultDate changeMenuToRole2(Integer roleId) {
        //先根据roleid删除角色权限中间表的数据
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("rid",roleId);
        int delete = iRoleAndMenuMapper.delete(queryWrapper);
        if (delete>0){
           return new ResultDate().setResult(true).setData("设置成功！");
        }
        return new ResultDate().setResult(false).setData("设置失败！");
    }

    //根据角色id(管理员1，普通用户2)查询该角色下所有的权限并分页
    @Override
    public ModelMap getMenuPageByRoleId(Integer roleid, Page<Menu> page, ModelMap map) {
        Page<Menu> page2=iMenuMapper.getMenuPageByRoleId(page,roleid);
        map.put("page",page2);
        return map;
    }
}
