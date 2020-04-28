package com.qf.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.entity.Goods;
import com.qf.entity.Orders;
import com.qf.entity.PurchaseOrder;
import com.qf.result.ResultData;
import com.qf.result.ResultDate;
import com.qf.service.IGoodsService;
import com.qf.service.IOrderService;
import com.qf.service.IPurchaseOrderService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/GoodsController")
public class GoodsController {
    @Autowired
    private IGoodsService iGoodsService;
    @Autowired
    private IOrderService iOrderService;
    @Autowired
    private IPurchaseOrderService iPurchaseOrderService;

    //准备一个本地磁盘的路径
    private String localpath = "F:/bysj/images";

    /**
     * 商品列表--查询未下架的所有商品
     * @param page
     * @param map
     * @return
     */
    //TODO B2 查询未下架的所有商品
    //TODO S7 这里需要判断这个用户是否拥有GoodsController:selectGoodsPage权限
    @RequiresPermissions("GoodsController:selectGoodsPage")
    @RequestMapping("/selectGoodsPage")
    public String selectGoodsPage(Page<Goods> page, ModelMap map){
        ModelMap map1 = iGoodsService.selectPage(page, map);
        map1.put("url","GoodsController/selectGoodsPage");
        map1.put("goodsList",page.getRecords());
        return "productList";
    }

    /**
     * 准备添加商品
     * @param
     * @return
     */
    //TODO C3 做添加商品前的准备
    @RequestMapping("toAddGoods")
    public String toAddGoods(){

        return "goodsAdd";
    }

    /**
     * 根据商品id查询商品详情
     * @param gid
     * @param map
     * @return
     */
    @RequestMapping("/productDetails")
    public String selectGoodsOne(Integer gid, ModelMap map){
        Goods goods = iGoodsService.selectById(gid);
        map.put("goodsDetatis",goods);
        return "productDetails";
    }

    /**
     * 上传图片到本地磁盘
     * @param file
     * @return
     */
    //TODO D2 图片上传到这个controller（封面）（多张图片）
    @RequestMapping("/shangchuan")
    @ResponseBody
    public ResultData<String> shangchuan(MultipartFile file) {
        //输出流必须写到文件名，所以先得到一个文件名
        String fileName = UUID.randomUUID().toString()+file.getOriginalFilename();

        //上传的真实路径
        String ShangChuanRealpath = localpath + "/" + fileName;
       try(
               //从file得到一个输入流
               InputStream is = file.getInputStream();
               //构造一个输出流
               OutputStream out = new FileOutputStream(ShangChuanRealpath);
                ) {
            IOUtils.copy(is,out);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultData<String>().setCode(ResultData.ResultCodeList.OK).setData(ShangChuanRealpath);
    }

    /**
     * ajax上传图片的成功回调函数给img标签设置地址，img标签发送请求，
     * 方法将图片信息响应回img
     * @param ShangChuanRealpath
     * @param response
     */
    //TODO D4 获得img标签发过来的图片路径去读取图片并返回给img标签（封面）（多张图片）
    @RequestMapping("queryImageByServer")
    public void queryImageByServer(String ShangChuanRealpath, HttpServletResponse response){
        try (
                InputStream in = new FileInputStream(ShangChuanRealpath);
                ServletOutputStream out = response.getOutputStream();
        ){
            IOUtils.copy(in, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加商品
     * @param goods
     * @return
     */
    //TODO E3 controller接收ajax提交的商品信息
    @RequestMapping("/addGood")
    @ResponseBody
    public ResultDate addGood(Goods goods) {
        iGoodsService.addGood(goods);
        //返回信息给Ajax
        return new ResultDate().setResult(true);
    }

    //TODO F3 controller删除多个商品
    @RequestMapping("deleteAll")
    @ResponseBody
    public ResultDate deleteAll(@RequestParam("pids") String pids){
        String [] a = pids.substring(1,pids.length()-1).split(",");
        List<Integer> idList = new ArrayList<>();
        for (int i = 0 ; i < a.length ; i++){
            idList.add(Integer.parseInt(a[i]));
        }
        return iGoodsService.deleteAll(idList);
    }

    /**
     * 删除单个商品
     * @param pid
     * @return
     */
    @RequestMapping("deleteOne")
    @ResponseBody
    public ResultDate deleteOne(@RequestParam("pid") int pid){
        System.out.println("接收到id："+pid);
        return  iGoodsService.deleteOne(pid);
    }
    //TODO I2 controller接收请求去查询全部未被删除的用户商品订单并分页
    @RequestMapping("/selectAllUserAllGoodsOrderPage")
    public String selectAllUserAllGoodsOrderPage(Page<Orders> page, ModelMap map){
        ModelMap map1 = iOrderService.selectPage(page, map);
        map1.put("url","GoodsController/selectAllUserAllGoodsOrderPage");
        map1.put("goodsOrder",page.getRecords());
        return "productOrderList";
    }

    //TODO J2 controller接收请求去查询全部未被删除的用户采购订单并分页
    @RequestMapping("/selectAllUserAllPurchaseOrderPage")
    public String selectAllUserAllPurchaseOrderPage(Page<PurchaseOrder> page, ModelMap map){
        ModelMap map1 = iPurchaseOrderService.selectPage(page, map);
        map1.put("url","GoodsController/selectAllUserAllPurchaseOrderPage");
        map1.put("purchaseOrder",page.getRecords());
        return "purchaseOrderList";
    }
}
