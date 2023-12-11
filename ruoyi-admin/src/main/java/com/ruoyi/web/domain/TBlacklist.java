package com.ruoyi.web.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description t_blacklist
 * @author zhengkai.blog.csdn.net
 * @date 2023-12-11
 */
@Data
@ApiModel("t_blacklist")
public class TBlacklist implements Serializable {

    private static final long serialVersionUID = 1L;

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
     * blacklist_type
     */
    @ApiModelProperty("blacklist_type")
    private String blacklistType;

    /**
     * reason
     */
    @ApiModelProperty("reason")
    private String reason;

    /**
     * department
     */
    @ApiModelProperty("department")
    private String department;

    /**
     * datasource
     */
    @ApiModelProperty("datasource")
    private String datasource;

    /**
     * del
     */
    @ApiModelProperty("del")
    private String del;

    /**
     * del_reason
     */
    @ApiModelProperty("del_reason")
    private String delReason;

    /**
     * datadate
     */
    @ApiModelProperty("datadate")
    private String datadate;

    public TBlacklist() {}
}
