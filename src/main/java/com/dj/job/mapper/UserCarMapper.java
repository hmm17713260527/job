package com.dj.job.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dj.job.pojo.Car;
import com.dj.job.pojo.UserCar;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * @ProjectName: job
 * @Package: com.dj.job.mapper
 * @ClassName: UserCarMapper
 * @Author: Administrator
 * @Description:
 * @Date: 2020/3/1 12:43
 * @Version: 1.0
 */
public interface UserCarMapper extends BaseMapper<UserCar> {

    List<Car> findUserOrder(@Param("id") Integer id, @Param("isDel") Integer isDel) throws DataAccessException;
}
