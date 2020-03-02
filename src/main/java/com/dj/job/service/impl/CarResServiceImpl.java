package com.dj.job.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.job.mapper.CarMapper;
import com.dj.job.mapper.CarResMapper;
import com.dj.job.pojo.Car;
import com.dj.job.pojo.CarRes;
import com.dj.job.service.CarResService;
import com.dj.job.service.CarService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ProjectName: job
 * @Package: com.dj.job.service.impl
 * @ClassName: CarResServiceImpl
 * @Author: Administrator
 * @Description:
 * @Date: 2020/3/2 20:38
 * @Version: 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CarResServiceImpl  extends ServiceImpl<CarResMapper, CarRes> implements CarResService {
}
