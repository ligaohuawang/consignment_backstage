package com.qf.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.gson.Gson;
import com.qf.entity.Menu;
import com.qf.entity.Tree;
import com.qf.result.ResultDate;
import com.qf.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/MenuController")
public class MenuController {
    @Autowired
    private IMenuService iMenuService;

    //TODO L2 controller接收请求去查询所有未被删除的权限并分页显示
    @RequestMapping("/selectAllMenuPage")
    public String selectAllMenuPage(Page<Menu> page, ModelMap map){
        ModelMap map1 = iMenuService.selectPage(page, map);
        map1.put("url","MenuController/selectAllMenuPage");
        map1.put("menuList",page.getRecords());
        return "menuList";
    }

//TODO O3 controller获取权限初始化菜单树(给zNodes数据)
@RequestMapping("/getMenuListTree")
public String getMenuListTree(Integer roleId,ModelMap map){
    List<Tree> menus = iMenuService.getMenuListTree(roleId);
    Gson gson =new Gson();
    map.put("shuju",gson.toJson(menus));
    map.put("roleId",roleId);
    return "menuTree";
}


    //TODO P2.1 重新给角色设置权限,有数组
    /**
     *重新给角色设置权限,有数组
     * @param ids
     * @param roleId
     * @return
     */
    @RequestMapping("/changeMenuToRole")
    @ResponseBody
    public ResultDate changeMenuToRole(@RequestParam("ids[]")List<Integer> ids, @RequestParam("roleId") Integer roleId){

        return iMenuService.changeMenuToRole(ids,roleId);
    }

    //TODO P2.2 重新给角色设置权限,无数组
    /**
     *重新给角色设置权限，无数组
     * @param
     * @param roleId
     * @return
     */
    @RequestMapping("/changeMenuToRole2")
    @ResponseBody
    public ResultDate changeMenuToRole2(@RequestParam("roleId") Integer roleId){

        return iMenuService.changeMenuToRole2(roleId);
    }
    //TODO Q3.2 根据角色id查询该角色下所有的权限并分页
    @RequestMapping("/getMenuPageByRoleId")
    public String getMenuPageByRoleId(Integer roleid, ModelMap map, Page<Menu> page){
        ModelMap map1=iMenuService.getMenuPageByRoleId(roleid, page, map);
        map1.put("url","MenuController/getMenuPageByRoleId");
        map1.put("menuList",page.getRecords());
        map1.put("roleid",roleid);
        return "roleMenuList";
    }

}
