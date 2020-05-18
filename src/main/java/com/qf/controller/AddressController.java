package com.qf.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.entity.Address;
import com.qf.service.IAddressService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/AddressController")
public class AddressController {
    @Autowired
    private IAddressService iAddressService;

    //TODO H2 controller接收请求去查询全部未被删除的用户收货地址并分页
    @RequestMapping("/selectAllUserAllAddressPage")
    @RequiresPermissions("AddressController:selectAllUserAllAddressPage")
    public String selectAllUserAllAddressPage(Page<Address> page, ModelMap map){
        ModelMap map1 = iAddressService.selectPage(page, map);
        map1.put("url","AddressController/selectAllUserAllAddressPage");
        map1.put("addressList",page.getRecords());
        return "addressList";
    }
}
