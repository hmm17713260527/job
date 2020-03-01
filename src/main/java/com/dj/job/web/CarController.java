package com.dj.job.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dj.job.common.ResultModel;
import com.dj.job.common.SystemConstant;
import com.dj.job.pojo.Car;
import com.dj.job.pojo.PmsUser;
import com.dj.job.pojo.UserCar;
import com.dj.job.service.CarService;
import com.dj.job.service.UserCarService;
import com.dj.job.util.CaoZuoFile;
import com.dj.job.util.EmailUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
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

    @Autowired
    private UserCarService userCarService;

    @PutMapping("rent")
    public ResultModel<Object> rent(Integer id, HttpSession session) {

        try {
            QueryWrapper<Car> wrapper = new QueryWrapper<Car>();
            wrapper.eq("id", id);
            Car car = carService.getOne(wrapper);
            if (null == car.getCount() || car.getCount() == 0) {
                return new ResultModel<>().error("现阶段无此货");
            }

            PmsUser user = (PmsUser) session.getAttribute("user");

            userCarService.save(new UserCar().setIsDel(1).setUserId(user.getId()).setCarId(id).setCreateTime(new Date()).setPrice(car.getPrice()));

            Integer count = car.getCount() - 1;
            car.setCount(count);
            carService.updateById(car);

            return new ResultModel<>().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<>().error(SystemConstant.STRING_03 + e.getMessage());
        }

    }
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
