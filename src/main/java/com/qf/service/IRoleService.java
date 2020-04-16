package com.qf.service;
import com.qf.Base.BaseService;
import com.qf.entity.Role;

import java.util.List;

public interface IRoleService extends BaseService<Role> {

    /**
     * 给用户绑定选中的角色
     * @param userIds
     * @param roleId
     */
    void setTheRoleToUser(List<Integer> userIds, Integer roleId);
}
