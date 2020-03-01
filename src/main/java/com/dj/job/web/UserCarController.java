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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
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

    @Autowired
    private CarService carService;


    @PutMapping("repay")
    public ResultModel<Object> rent(Integer id) {

        try {
            QueryWrapper<UserCar> wrapper = new QueryWrapper<UserCar>();
            wrapper.eq("id", id);
            UserCar userCar = userCarService.getOne(wrapper);

            QueryWrapper<Car> carWrapper = new QueryWrapper<Car>();
            carWrapper.eq("id", userCar.getCarId());

            Car car = carService.getOne(carWrapper);
            Integer count = car.getCount() + 1;
            car.setCount(count);
            carService.updateById(car);

            userCar.setIsDel(2);
            userCarService.updateById(userCar);

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
            PageHelper.startPage(pageNo, 2);

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
