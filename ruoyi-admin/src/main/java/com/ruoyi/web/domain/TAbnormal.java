package com.ruoyi.web.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description t_abnormal
 * @author zhengkai.blog.csdn.net
 * @date 2023-12-12
 */
@Data
@ApiModel("t_abnormal")
public class TAbnormal implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * eid
     */
    @ApiModelProperty("eid")
    private String eid;

    /**
     * ename
     */
    @ApiModelProperty("ename")
    private String ename;

    /**
     * uniscid
     */
    @ApiModelProperty("uniscid")
    private String uniscid;

    /**
     * in_reason
     */
    @ApiModelProperty("in_reason")
    private String inReason;

    /**
     * in_date
     */
    @ApiModelProperty("in_date")
    private String inDate;

    /**
     * out_reason
     */
    @ApiModelProperty("out_reason")
    private String outReason;

    /**
     * out_date
     */
    @ApiModelProperty("out_date")
    private String outDate;

    /**
     * department
     */
    @ApiModelProperty("department")
    private String department;

    /**
     * out_department
     */
    @ApiModelProperty("out_department")
    private String outDepartment;

    /**
     * last_update_time
     */
    @ApiModelProperty("last_update_time")
    private String lastUpdateTime;

    public TAbnormal() {}
}