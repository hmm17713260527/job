package com.dj.job.web.page;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dj.job.common.ResultModel;
import com.dj.job.common.SystemConstant;
import com.dj.job.pojo.Car;
import com.dj.job.pojo.PmsUser;
import com.dj.job.service.CarService;
import com.dj.job.util.CaoZuoFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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

    @Autowired
    private CarService carService;


    @PostMapping("update")
    public String update(Car car, MultipartFile fileName) {

        String scfile = CaoZuoFile.scfile(fileName);
        car.setCarImg(scfile);
        carService.updateById(car);
        return "car/show";

    }


    @PostMapping("add")
    public String add(Car car, MultipartFile fileName) {

        String scfile = CaoZuoFile.scfile(fileName);
        car.setCarImg(scfile);
        carService.save(car);
        return "car/show";

    }


    @RequestMapping("toShow")
    public String toShow() {
        return "car/show";
    }
    @RequestMapping("toRecycleShow")
    public String toRecycleShow() {
        return "car/recycle_show";
    }

    @RequestMapping("toAdd")
    public String toAdd() {
        return "car/add";
    }

    @RequestMapping("toImg")
    public void toImg(HttpServletResponse response, String fileName) {
        CaoZuoFile.xzfile(response, fileName);
    }

    @RequestMapping("toUpdate/{id}")
    public ModelAndView toUpdate(@PathVariable("id") Integer id) {
        QueryWrapper<Car> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        Car car = carService.getOne(wrapper);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("car/update");
        modelAndView.addObject("car", car);
        return modelAndView;
    }
}
