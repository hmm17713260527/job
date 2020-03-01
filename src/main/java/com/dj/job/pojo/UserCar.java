package com.dj.job.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @ProjectName: job
 * @Package: com.dj.job.pojo
 * @ClassName: UserCar
 * @Author: Administrator
 * @Description:
 * @Date: 2020/3/1 12:41
 * @Version: 1.0
 */
@Data
@Accessors(chain = true)
@TableName("user_car")
public class UserCar {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private Integer carId;

    private Double price;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 1在，2无
     */
    private Integer isDel;

}
