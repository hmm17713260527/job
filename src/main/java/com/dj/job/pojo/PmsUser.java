package com.dj.job.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @ProjectName: pms
 * @Package: com.dj.pms.pojo
 * @ClassName: PmsUser
 * @Author: Administrator
 * @Description:
 * @Date: 2020/1/31 13:03
 * @Version: 1.0
 */
@Data
@Accessors(chain = true)
@TableName("pms_user")
public class PmsUser {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String userName;

    private String password;

    private String phone;

    private String email;

    /**
     * 1激活，2未激活
     */
    private Integer status;

    /**
     * 1男，2女
     */
    private Integer sex;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 1在，2无
     */
    private Integer isDel;

    private String salt;

    private Integer age;








}
