package com.dj.job.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.job.mapper.CarMapper;
import com.dj.job.mapper.UserCarMapper;
import com.dj.job.pojo.Car;
import com.dj.job.pojo.PmsUser;
import com.dj.job.pojo.UserCar;
import com.dj.job.service.CarService;
import com.dj.job.service.UserCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @ProjectName: job
 * @Package: com.dj.job.service.impl
 * @ClassName: CarServiceImpl
 * @Author: Administrator
 * @Description:
 * @Date: 2020/2/29 17:09
 * @Version: 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements CarService {

    @Autowired
    private UserCarMapper userCarMapper;

    @Autowired
    private CarMapper carMapper;

    @Override
    public void updateOrder(Car car, PmsUser user) {
        userCarMapper.insert(new UserCar().setIsDel(1).setUserId(user.getId()).setCarId(car.getId()).setCreateTime(new Date()).setPrice(car.getPrice()));

        Integer count = car.getCount() - 1;
        car.setCount(count);
        carMapper.updateById(car);
    }
}
