package com.dj.job.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ProjectName: job
 * @Package: com.dj.job.pojo
 * @ClassName: CarRes
 * @Author: Administrator
 * @Description:
 * @Date: 2020/3/2 20:31
 * @Version: 1.0
 */
@Data
@Accessors(chain = true)
@TableName("car_res")
public class CarRes {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String resName;
    private Integer parentId;
    private String url;
    /**
     * 1在，2无
     */
    private Integer isDel;

    private Integer type;
}
