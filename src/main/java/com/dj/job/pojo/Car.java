package com.dj.job.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ProjectName: job
 * @Package: com.dj.job.pojo
 * @ClassName: Car
 * @Author: Administrator
 * @Description:
 * @Date: 2020/2/29 16:49
 * @Version: 1.0
 */
@Data
@Accessors(chain = true)
@TableName("car")
public class Car {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String brand;

    private String carName;

    private String carImg;

    private Integer count;

    private Double price;

    /**
     * 1在，2无
     */
    private Integer isDel;


}
