package com.baizhi.shiro;

import com.baizhi.entity.Admin;
import com.baizhi.entity.Permission;
import com.baizhi.entity.Role;
import com.baizhi.mapper.AdminMapper;
import com.baizhi.mapper.PermissionMapper;
import com.baizhi.mapper.RoleMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    AdminMapper adminMapper;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    PermissionMapper permissionMapper;
    @Override   //授权
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String primaryPrincipal = (String)principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //主体赋予 角色 权限   通过主体 查  角色   通过角色 查  权限
        Admin admin = adminMapper.selectOne(new Admin(null, primaryPrincipal, null, null));
        List<Role> roles = roleMapper.queryRole(admin.getId());
        for (Role role : roles) {
            List<Permission> permissions = permissionMapper.queryPerm(role.getId());
            authorizationInfo.addRole(role.getTypeName());
            for (Permission permission : permissions) {
                authorizationInfo.addStringPermission(permission.getPermissionName());
            }
        }
        return authorizationInfo;
    }

    @Override   //认证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //用户名
        String principal = (String)authenticationToken.getPrincipal();
        Admin admin = adminMapper.selectOne(new Admin(null, principal, null,null));
        if(admin!=null){
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(admin.getName(),admin.getPassword(), ByteSource.Util.bytes(admin.getSalt()),this.getName());
            return authenticationInfo;
        }
        return null;
    }
}
