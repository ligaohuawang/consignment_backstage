package com.qf.controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.entity.BackUser;
import com.qf.service.IBackUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/BackUserController")
public class BackUserController {
    @Autowired
    private IBackUserService iBackUserService;

    //TODO A 用户登录(BackUserController)
    @RequestMapping("/login")
    public String login(BackUser backUser){
        BackUser backUser2=iBackUserService.selectByUserName(backUser.getUsername());
        if (backUser.getPassword().equals(backUser2.getPassword())){//登录成功
            return "index2";
        }
        return "index";
    }

    //TODO N3 controller接收请求，根据角色id查询未拥有该角色的所有用户并分页
    @RequestMapping("/getUserNotHaveRolePage")
    public String getUserNotHaveRolePage(Integer roleid, ModelMap map, Page<BackUser> page){
        ModelMap map1=iBackUserService.getUserNotHaveRolePage(roleid, page, map);
        /*ModelMap map1 = iBackUserService.selectPage2(roleid, page, map);*/
        map1.put("url","BackUserController/getUserNotHaveRolePage");
        map1.put("backUserNotHaveRoleList",page.getRecords());
        map1.put("roleid",roleid);
        return "authUserList";
    }
    //TODO Q3.1 根据角色id查询该角色下所有的用户并分页
    @RequestMapping("/getUserPageByRoleId")
    public String getUserPageByRoleId(Integer roleid,Page<BackUser> page, ModelMap map){
        ModelMap map1 = iBackUserService.selectUserPageByRoleId(roleid, page, map);
        map1.put("url","BackUserController/getUserPageByRoleId");
        map1.put("backUserList",page.getRecords());
        map1.put("roleid",roleid);
        return "roleUserList";
    }
}
