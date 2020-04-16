package com.qf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.entity.Menu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
public interface IMenuMapper extends BaseMapper<Menu> {
    @Select("select * from menu where id in(SELECT mid from role_menu where rid=#{roleid})")
    Page<Menu> getMenuPageByRoleId(Page<Menu> page,@Param("roleid") Integer roleid);
}
