package com.dj.job.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.job.common.SystemConstant;
import com.dj.job.mapper.UserCarMapper;
import com.dj.job.pojo.Car;
import com.dj.job.pojo.UserCar;
import com.dj.job.service.CarService;
import com.dj.job.service.UserCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ProjectName: job
 * @Package: com.dj.job.service.impl
 * @ClassName: UserCarServiceImpl
 * @Author: Administrator
 * @Description:
 * @Date: 2020/3/1 12:44
 * @Version: 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserCarServiceImpl extends ServiceImpl<UserCarMapper, UserCar> implements UserCarService {

    @Autowired
    private UserCarMapper userCarMapper;

    @Autowired
    private CarService carService;

    @Override
    public List<Car> findUserOrder(Integer id, Integer isDel) throws Exception {
        return userCarMapper.findUserOrder(id, isDel);
    }

    @Override
    public Double findTurnoverByLook(Integer look) throws Exception {
        return userCarMapper.findTurnoverByLook(look);
    }

    /**
     * 用户返还
     * @param id
     */
    @Override
    public void updateOrder(Integer id) {
        UserCar userCar = userCarMapper.selectById(id);

        QueryWrapper<Car> carWrapper = new QueryWrapper<Car>();
        carWrapper.eq("id", userCar.getCarId());

        Car car = carService.getOne(carWrapper);
        Integer count = car.getCount() + SystemConstant.I;
        car.setCount(count);
        carService.updateById(car);

        userCar.setIsDel(2);
        userCarMapper.updateById(userCar);
    }
}
