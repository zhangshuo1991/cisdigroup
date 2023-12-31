package com.ruoyi.web.domain;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * @description 招投标关联企业
 * @author zhangshuo
 * @date 2023-12-27
 */
@Data
@ApiModel("招投标关联企业")
@TableName("t_biddings_all")
public class TBiddingsAllNew implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("类型")
    @TableField("notice_type")
    private String noticeType;


    @ApiModelProperty("项目编号")
    @TableField("proj_num")
    private String projNum;


    @ApiModelProperty("区域编码")
    @TableField("area_code")
    private String areaCode;


    @ApiModelProperty("区域名称")
    @TableField("area_name")
    private String areaName;


    @ApiModelProperty("内容id")
    @TableField("data_id")
    private String dataId;


    @ApiModelProperty("内容")
    @TableField("content")
    private String content;


    @ApiModelProperty("中标金额")
    @TableField("bid_amount")
    private String bidAmount;


    @ApiModelProperty("角色")
    @TableField("bidding_role")
    private String biddingRole;


    @ApiModelProperty("企业id")
    @TableField("eid")
    private String eid;


    @ApiModelProperty("企业名称")
    @TableField("ename")
    private String ename;


    @ApiModelProperty("时间")
    @TableField("dt")
    private String dt;

    public TBiddingsAllNew() {}
}