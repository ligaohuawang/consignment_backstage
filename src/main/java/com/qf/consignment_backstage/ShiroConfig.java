package com.qf.consignment_backstage;

import com.qf.realm.MyRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

//TODO S3 配置自定义realm   安全管理器   过滤器链

    //配置自定义realm
    @Bean
    public MyRealm registerMyRealm(){
        MyRealm myRealm = new MyRealm();
        return myRealm;
    }
    //配置安全管理器
    @Bean
    public SecurityManager registerSecurityManager(){
        //DefaultSecurityManager defaultSecurityManager =new DefaultSecurityManager(registerMyRealm());
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager(registerMyRealm());
        return defaultWebSecurityManager;
    }
    //配置shiro过滤器
    @Bean
    public ShiroFilterFactoryBean registerShiroFilterFactoryBean(SecurityManager securityManager){

        // 定义shiroFactoryBean
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        // 设置自定义的securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 设置默认登录的url，身份认证失败会访问该url
        //shiroFilterFactoryBean.setLoginUrl("/login");
        // 设置成功之后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");
        // 设置未授权界面，权限认证失败会访问该url
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");

        // LinkedHashMap是有序的，进行顺序拦截器配置
        Map<String,String> filterChainMap = new LinkedHashMap<>();

        // 配置可以匿名访问的地址，可以根据实际情况自己添加，放行一些静态资源等，anon表示放行
        filterChainMap.put("/resources/scripts/**", "anon");
        filterChainMap.put("/resources/static/**", "anon");
        filterChainMap.put("/resources/templates/**", "anon");
        // 登录url 放行
        filterChainMap.put("/BackUserController/login", "anon");

        // 配置logout过滤器
        filterChainMap.put("/logout", "logout");

        // “/BackUserController” 开头的需要身份认证，authc表示要身份认证
        filterChainMap.put("/BackUserController/**", "authc");
        // “/AddressController” 开头的需要身份认证，authc表示要身份认证
        filterChainMap.put("/AddressController/**", "authc");
        // “/frontUserController” 开头的需要身份认证，authc表示要身份认证
        filterChainMap.put("/frontUserController/**", "authc");
        // “/GoodsController” 开头的需要身份认证，authc表示要身份认证
        filterChainMap.put("/GoodsController/**", "authc");
        // “/MenuController” 开头的需要身份认证，authc表示要身份认证
        filterChainMap.put("/MenuController/**", "authc");
        // “/RoleController” 开头的需要身份认证，authc表示要身份认证
        filterChainMap.put("/RoleController/**", "authc");

        // “/user/student” 开头的需要角色认证，是“admin”才允许
        //filterChainMap.put("/user/student*/**", "roles[admin]");
        // “/user/teacher” 开头的需要权限认证，是“user:create”才允许
       // filterChainMap.put("/user/teacher*/**", "perms[\"user:create\"]");



        // 设置shiroFilterFactoryBean的FilterChainDefinitionMap
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);

        return shiroFilterFactoryBean;
    }



    /**
     *  开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator和AuthorizationAttributeSourceAdvisor)即可实现此功能
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }
    /**
     * 开启aop注解支持
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}
