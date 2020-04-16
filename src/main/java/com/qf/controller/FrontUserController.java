package com.qf.controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.entity.FrontUser;
import com.qf.service.IFrontUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 前台用户Controller
 */
@Controller
@RequestMapping("/frontUserController")
public class FrontUserController {
    @Autowired
    private IFrontUserService iFrontUserService;

    //TODO G2 controller接收请求去查询全部未被删除的用户并分页
    @RequestMapping("/selectAllUserPage")
    public String selectAllUserPage(Page<FrontUser> page, ModelMap map){
        ModelMap map1=iFrontUserService.selectPage(page,map);
        map1.put("url","frontUserController/selectAllUserPage");
        map1.put("frontUserList",page.getRecords());
        return "frontUserList";
    }

}
