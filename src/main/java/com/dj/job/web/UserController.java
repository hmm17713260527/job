package com.dj.job.web;

import com.dj.job.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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





}
