package com.qf.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.entity.BackUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface IBackUserMapper extends BaseMapper<BackUser> {

    @Select("select * from back_user where id in(SELECT uid from user_role where rid=#{roleid})")
    Page<BackUser> getUserPageByRoleId(Page<BackUser> page, @Param("roleid") Integer roleid);

    @Select("SELECT u.* FROM back_user u WHERE id NOT IN ( SELECT uid FROM user_role WHERE rid =#{roleid})")
    Page<BackUser> getUserNotHaveRolePage(Page<BackUser> page, @Param("roleid") Integer roleid);
}
