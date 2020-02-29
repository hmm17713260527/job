package com.dj.job.web.page;

import com.dj.job.util.CaoZuoFile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * @ProjectName: job
 * @Package: com.dj.job.web.page
 * @ClassName: CarPageController
 * @Author: Administrator
 * @Description:
 * @Date: 2020/2/29 17:11
 * @Version: 1.0
 */
@Controller
@RequestMapping("/car/")
public class CarPageController {

    @RequestMapping("toShow")
    public String toShow() {
        return "car/show";
    }

    @RequestMapping("toImg")
    public void toImg(HttpServletResponse response, String fileName) {
        CaoZuoFile.xzfile(response, fileName);
    }
}
