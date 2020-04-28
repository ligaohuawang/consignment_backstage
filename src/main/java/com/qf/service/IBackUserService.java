package com.qf.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.Base.BaseService;
import com.qf.entity.BackUser;
import org.springframework.ui.ModelMap;

import java.util.Set;

public interface IBackUserService extends BaseService<BackUser> {
    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    BackUser selectByUserName(String username);

    /**
     * 根据角色id查询未拥有该角色的所有用户并分页
     * @param roleid
     * @param page
     * @param map
     */
    ModelMap selectPage2(Integer roleid, Page<BackUser> page, ModelMap map);

    /**
     * 根据角色id查询该角色下所有的用户并分页
     * @param roleid
     * @param page
     * @param map
     * @return
     */
    ModelMap selectUserPageByRoleId(Integer roleid, Page<BackUser> page, ModelMap map);

    /**
     * 根据角色id查询未拥有该角色的所有用户并分页
     * @param roleid
     * @param page
     * @param map
     * @return
     */
    ModelMap getUserNotHaveRolePage(Integer roleid, Page<BackUser> page, ModelMap map);

    /**
     * 根据用户ID查询用户权限
     * @param id
     * @return
     */
    Set<String> selectUserMenu(Integer id);
}
