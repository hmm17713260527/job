package com.dj.job.web;

import com.dj.job.pojo.CarRes;
import com.dj.job.pojo.PmsUser;
import com.dj.job.service.CarResService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: job
 * @Package: com.dj.job.web
 * @ClassName: CarResController
 * @Author: Administrator
 * @Description:
 * @Date: 2020/3/2 20:44
 * @Version: 1.0
 */
@RestController
@RequestMapping("/carRes/")
public class CarResController {

    @Autowired
    private CarResService carResService;

    @RequestMapping("toList")
    public List<CarRes> toList(Integer id, HttpSession session) throws Exception {
        PmsUser user = (PmsUser) session.getAttribute("user");
        List<CarRes> carResList1 = new ArrayList<>();
        List<CarRes> carResList = carResService.list();
        if (user.getType() == 1) {
            for (CarRes carRes : carResList) {
                if (carRes.getType().equals(1) || carRes.getType().equals(2)) {
                    carResList1.add(carRes);
                }
            }
            return carResList1;
        }
        for (CarRes carRes : carResList) {
            if (carRes.getType().equals(1) || carRes.getType().equals(3)) {
                carResList1.add(carRes);
            }
        }

        return carResList1;
    }


}
