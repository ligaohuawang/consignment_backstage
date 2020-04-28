package com.qf.realm;

import com.qf.entity.BackUser;
import com.qf.service.IBackUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

//TODO S2 自定义realm
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private IBackUserService iBackUserService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //TODO S8 这里查出用户拥有的角色，该角色下的所有权限。用set是因为一个用户可能会拥有多个角色，权限可能会重复，所以用来去重
        BackUser backUser = (BackUser) principalCollection.getPrimaryPrincipal();
        Set<String> menuCode = iBackUserService.selectUserMenu(backUser.getId());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(menuCode);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //TODO S6 拿到用户身份去认证
        String username = (String) authenticationToken.getPrincipal();
        BackUser backUser = iBackUserService.selectByUserName(username);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(backUser,backUser.getPassword(),getName());
        return info;
    }
}
