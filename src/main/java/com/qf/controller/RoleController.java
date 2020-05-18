package com.qf.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.entity.Role;
import com.qf.result.ResultDate;
import com.qf.service.IRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/RoleController")
public class RoleController {
    @Autowired
    private IRoleService iRoleService;

    //TODO K2 controller接收请求查询角色并分页
    @RequiresPermissions("RoleController:selectAllRolePage")
    @RequestMapping("/selectAllRolePage")
    public String selectAllRolePage(Page<Role> page, ModelMap map){
        ModelMap map1 = iRoleService.selectPage(page, map);
        map1.put("url","RoleController/selectAllRolePage");
        map1.put("rolesList",page.getRecords());
        return "rolesList";
    }

    //TODO M2 controller 接收请求查询所有角色给下拉框
    @RequiresPermissions("RoleController:getRoleList")
    @RequestMapping("/getRoleList")
    public String getRoleList(ModelMap map){
    List<Role> roleList=iRoleService.selectList();
    map.put("roleList",roleList);
        return "authList";
    }

    /**
     * 给用户设置角色
     * @param userIds
     * @param roleId
     * @return
     */
    //TODO R3 controller接收请求去给用户绑定选中的角色
    @RequestMapping("/setTheRoleToUser")
    @ResponseBody
    public ResultDate setTheRoleToUser(@RequestParam("userIds[]") List<Integer> userIds, Integer roleId){
        iRoleService.setTheRoleToUser(userIds,roleId);
        return new ResultDate().setResult(true).setData("设置成功！");
    }
}
