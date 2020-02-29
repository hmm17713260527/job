package com.dj.job.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dj.job.common.SystemConstant;
import com.dj.job.pojo.PmsUser;
import com.dj.job.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @ProjectName: pms
 * @Package: com.dj.pms.config
 * @ClassName: ShiroRealm
 * @Author: Administrator
 * @Description:
 * @Date: 2020/2/19 12:02
 * @Version: 1.0
 */
@Component
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;


    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        // 创建简单授权信息
//        SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
//        Session session = SecurityUtils.getSubject().getSession();
//        List<PmsResource> PmsResourceList = (List<PmsResource>) session.getAttribute("PmsResourceList");
//        for (PmsResource PmsResource : PmsResourceList) {
//            simpleAuthorInfo.addStringPermission(PmsResource.getUrl());
//        }
//        return simpleAuthorInfo;
        return null;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        // 得到用户名
        String userName = (String) authenticationToken.getPrincipal();
        // 得到密码
        String password = new String((char[]) authenticationToken.getCredentials());

        try {

            QueryWrapper<PmsUser> wrapper = new QueryWrapper<PmsUser>();
            wrapper.or(i -> i.eq("user_name", userName)
                    .or().eq("email", userName)
                    .or().eq("phone", userName));
            wrapper.eq("password", password);
            PmsUser user = userService.getOne(wrapper);
            if (null == user || user.getIsDel() == 2) {
                throw new AccountException(SystemConstant.STRING_02);
            }
            if (user.getStatus() == 2) {
                throw new AccountException(SystemConstant.STRING_04);
            }

            Session session = SecurityUtils.getSubject().getSession();

            session.setAttribute("user", user);

        } catch (Exception e) {
            throw new AccountException(e.getMessage());
        }
        return new SimpleAuthenticationInfo(userName, password, getName());
    }
}
