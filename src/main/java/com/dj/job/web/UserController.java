package com.dj.job.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dj.job.common.ResultModel;
import com.dj.job.common.SystemConstant;
import com.dj.job.pojo.PmsUser;
import com.dj.job.service.UserService;
import com.dj.job.util.EmailUtil;
import com.dj.job.util.PasswordSecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @ProjectName: pms
 * @Package: com.dj.pms.web
 * @ClassName: UserController
 * @Author: Administrator
 * @Description:
 * @Date: 2020/1/31 13:15
 * @Version: 1.0
 */
@RestController
@RequestMapping("/user/")
public class UserController {


    @Autowired
    private UserService userService;

    @PutMapping("updateStatusByEmail")
    public ResultModel<Object> updateStatusByEmail(Integer status, String email) {
        try {
            QueryWrapper<PmsUser> wrapper = new QueryWrapper<PmsUser>();
            wrapper.eq("email", email);
            PmsUser user = userService.getOne(wrapper);
            user.setStatus(status);
            userService.updateById(user);
            return new ResultModel<>().success("激活成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<>().error("服务器异常");
        }

    }

    @PostMapping("addUser")
    public ResultModel<Object> addUser(PmsUser pmsUser) {

        try {
            String salt = PasswordSecurityUtil.generateSalt();
            String pwdSalt = PasswordSecurityUtil.enCode32(pmsUser.getPassword() + salt);

            pmsUser.setCreateTime(new Date()).setStatus(2).setPassword(pwdSalt).setSalt(salt);
            userService.save(pmsUser);

            EmailUtil.sendEmail(pmsUser.getEmail(), "激活链接", "<a href=\"http://127.0.0.1:8080/pms/user/toActivate/"+pmsUser.getEmail()+"\">点击激活</a>");
            return new ResultModel<>().success("注册成功,需邮箱激活");

        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<>().error(SystemConstant.STRING_03 + e.getMessage());
        }

    }

    @GetMapping("login")
    public ResultModel<Object> login1(String userName, String password, HttpSession session) {
        try {
            if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
                return new ResultModel<>().error(SystemConstant.STRING_01);
            }

            //shiro登陆
            Subject subject = SecurityUtils.getSubject(); //获取subject
            UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
            subject.login(token);

            return new ResultModel<>().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<>().error(SystemConstant.STRING_03 + e.getMessage());
        }

    }

    @GetMapping("findSalt")
    public ResultModel<Object> findSalt(String userName) {
        QueryWrapper<PmsUser> wrapper = new QueryWrapper<PmsUser>();
        wrapper.eq("user_name", userName);
        PmsUser user = userService.getOne(wrapper);
        return new ResultModel<>().success(user.getSalt());
    }

    @GetMapping("findByEmail")
    public boolean findByEmail(String email) {
        try {
            QueryWrapper<PmsUser> wrapper = new QueryWrapper<PmsUser>();
            wrapper.eq("email", email);
            PmsUser user = userService.getOne(wrapper);
            return null == user ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @GetMapping("findByName")
    public boolean findByName(String userName) {
        try {
            QueryWrapper<PmsUser> wrapper = new QueryWrapper<PmsUser>();
            wrapper.eq("user_name", userName);
            PmsUser user = userService.getOne(wrapper);
            return null == user ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @GetMapping("findByPhone")
    public boolean findByPhone(String phone) {
        try {
            QueryWrapper<PmsUser> wrapper = new QueryWrapper<PmsUser>();
            wrapper.eq("phone", phone);
            PmsUser user = userService.getOne(wrapper);
            return null == user ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }




}
