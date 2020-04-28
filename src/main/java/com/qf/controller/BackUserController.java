package com.qf.controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.entity.BackUser;
import com.qf.service.IBackUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
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
        //TODO S4 用户登录时先判断是否已经认证，没有认证就去认证
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()){//未进行认证
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(backUser.getUsername(),backUser.getPassword());
            try {
                //TODO S5 前往自定义realm进行认证
                subject.login(usernamePasswordToken);
            }catch (AuthenticationException a){
                System.err.println("认证失败");
                return "index";
            }

        }
        return "index2";
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
