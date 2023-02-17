package com.wentong.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("tiny_url")
public class TinyUrl {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String url;
    private String sourceUrl;
    private Date createTime;
    private Date updateTime;

}
