package com.dj.job.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.job.mapper.CarMapper;
import com.dj.job.pojo.Car;
import com.dj.job.service.CarService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
