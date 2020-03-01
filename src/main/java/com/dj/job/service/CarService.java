package com.dj.job.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.job.pojo.Car;
import com.dj.job.pojo.PmsUser;

/**
 * @ProjectName: job
 * @Package: com.dj.job.service
 * @ClassName: CarService
 * @Author: Administrator
 * @Description:
 * @Date: 2020/2/29 17:08
 * @Version: 1.0
 */
public interface CarService extends IService<Car> {
    void updateOrder(Car car, PmsUser user) throws Exception;
}
