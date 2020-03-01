package com.dj.job.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dj.job.common.ResultModel;
import com.dj.job.common.SystemConstant;
import com.dj.job.pojo.Car;
import com.dj.job.pojo.PmsUser;
import com.dj.job.pojo.UserCar;
import com.dj.job.service.CarService;
import com.dj.job.service.UserCarService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ProjectName: job
 * @Package: com.dj.job.web
 * @ClassName: UserCarController
 * @Author: Administrator
 * @Description:
 * @Date: 2020/3/1 12:50
 * @Version: 1.0
 */
@RestController
@RequestMapping("/userCar/")
public class UserCarController {

    @Autowired
    private UserCarService userCarService;



    @GetMapping("findTurnover")
    public ResultModel<Object> rent(@RequestParam("looks[]") Integer[] looks) {
        try {

            List<Double> sumList = new ArrayList<>();
            for (Integer look : looks) {
                Double sum = userCarService.findTurnoverByLook(look);
                if (null == sum) {
                    sum = 0.0;
                }
                sumList.add(sum);
            }
            return new ResultModel<>().success(sumList);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<>().error(SystemConstant.STRING_03 + e.getMessage());
        }

    }


    @PutMapping("repay")
    public ResultModel<Object> rent(Integer id) {

        try {
            userCarService.updateOrder(id);
            return new ResultModel<>().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<>().error(SystemConstant.STRING_03 + e.getMessage());
        }

    }


    @PostMapping("/list")
    public ResultModel<Object> show(Integer isDel, Integer pageNo, HttpSession session) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            PageHelper.startPage(pageNo, SystemConstant.PAGESIZE);

            PmsUser user = (PmsUser) session.getAttribute("user");
            List<Car> carList = userCarService.findUserOrder(user.getId(), isDel);

            PageInfo<Car> pageInfo = new PageInfo<Car>(carList);
            map.put("carList", pageInfo.getList());
            map.put("totalNum", pageInfo.getPages());
            return new ResultModel<>().success(map);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<>().error(SystemConstant.STRING_03 + e.getMessage());
        }

    }
}
