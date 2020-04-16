package com.qf.service;

import com.qf.Base.BaseService;
import com.qf.entity.Goods;
import com.qf.result.ResultDate;

import java.util.List;

public interface IGoodsService extends BaseService<Goods> {
    /**
     * 添加商品
     * @param goods
     */
    void addGood(Goods goods);

    /**
     * 批量删除商品
     * @param idList
     * @return
     */
    ResultDate deleteAll(List<Integer> idList);

    /**
     * 删除单个商品
     * @param pid
     * @return
     */
    ResultDate deleteOne(int pid);
}
