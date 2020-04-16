package com.qf.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.Base.BaseService;
import com.qf.entity.Menu;
import com.qf.entity.Tree;
import com.qf.result.ResultDate;
import org.springframework.ui.ModelMap;

import java.util.List;

public interface IMenuService extends BaseService<Menu> {
    List<Tree> getMenuListTree(Integer roleId);

    //重新给角色设置权限,有数组
    ResultDate changeMenuToRole(List<Integer> ids, Integer roleId);

    //重新给角色设置权限，无数组
    ResultDate changeMenuToRole2(Integer roleId);

    //根据角色id查询该角色下所有的权限并分页
    ModelMap getMenuPageByRoleId(Integer roleid, Page<Menu> page, ModelMap map);
}
