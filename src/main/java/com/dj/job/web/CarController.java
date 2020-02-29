package com.dj.job.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dj.job.common.ResultModel;
import com.dj.job.pojo.Car;
import com.dj.job.service.CarService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @ProjectName: job
 * @Package: com.dj.job.web
 * @ClassName: CarController
 * @Author: Administrator
 * @Description:
 * @Date: 2020/2/29 17:10
 * @Version: 1.0
 */
@RestController
@RequestMapping("/car/")
public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping("/list")
    public ResultModel<Object> show(Car car, Integer pageNo) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            PageHelper.startPage(pageNo, 2);
            QueryWrapper<Car> wrapper = new QueryWrapper<>();
            if (null != car.getBrand()) {
                wrapper.like("brand", car.getBrand());
            }
            wrapper.eq("is_del", car.getIsDel());
            List<Car> carList = carService.list(wrapper);
            PageInfo<Car> pageInfo = new PageInfo<Car>(carList);
            map.put("carList", pageInfo.getList());
            map.put("totalNum", pageInfo.getPages());
            return new ResultModel<>().success(map);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<>().error("服务器异常");
        }

    }
}
