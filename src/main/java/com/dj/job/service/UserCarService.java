package com.dj.job.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.job.pojo.Car;
import com.dj.job.pojo.UserCar;

import java.util.List;

/**
 * @ProjectName: job
 * @Package: com.dj.job.service
 * @ClassName: UserCarService
 * @Author: Administrator
 * @Description:
 * @Date: 2020/3/1 12:44
 * @Version: 1.0
 */
public interface UserCarService extends IService<UserCar> {
    List<Car> findUserOrder(Integer id, Integer isDel) throws Exception;

    Double findTurnoverByLook(Integer look) throws Exception;
}
