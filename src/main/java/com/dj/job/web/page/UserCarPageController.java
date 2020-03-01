package com.dj.job.web.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ProjectName: job
 * @Package: com.dj.job.web.page
 * @ClassName: UserCarPageController
 * @Author: Administrator
 * @Description:
 * @Date: 2020/3/1 12:51
 * @Version: 1.0
 */
@Controller
@RequestMapping("/userCar/")
public class UserCarPageController {

    @RequestMapping("toShow")
    public String toShow() {
        return "userCar/show";
    }

    @RequestMapping("toTurnoverShow")
    public String toTurnoverShow() {
        return "userCar/turnover_show";
    }
}
