package com.dj.job.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dj.job.common.ResultModel;
import com.dj.job.common.SystemConstant;
import com.dj.job.pojo.Car;
import com.dj.job.pojo.PmsUser;
import com.dj.job.service.CarService;
import com.dj.job.util.CaoZuoFile;
import com.dj.job.util.EmailUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
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

    @PutMapping
    public ResultModel<Object> update(Car car, MultipartFile fileName) {

        try {
            String scfile = CaoZuoFile.scfile(fileName);
            car.setCarImg(scfile);
            carService.updateById(car);
            return new ResultModel<>().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<>().error(SystemConstant.STRING_03 + e.getMessage());
        }

    }


    @DeleteMapping("del")
    public ResultModel<Object> del(Car car) {
        try {
            carService.updateById(car);
            return new ResultModel<>().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<>().error(SystemConstant.STRING_03 + e.getMessage());
        }

    }


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
            return new ResultModel<>().error(SystemConstant.STRING_03 + e.getMessage());
        }

    }
}
