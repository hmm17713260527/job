package com.dj.job.web.page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dj.job.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ProjectName: pms
 * @Package: com.dj.pms.web.page
 * @ClassName: UserPageController
 * @Author: Administrator
 * @Description:
 * @Date: 2020/1/31 16:09
 * @Version: 1.0
 */
@Controller
@RequestMapping("/user/")
public class UserPageController {


    @Autowired
    private UserService userService;

    @RequestMapping("toLogin")
    public String toLogin() {
        return "user/login";
    }


    @RequestMapping("toAdd")
    public String toAdd() {
        return "user/add";
    }

    @RequestMapping("toActivate/{email}")
    public String toActivate(@PathVariable("email") String email, Model model) {
        model.addAttribute("email", email);
        return "user/activate";
    }


}
