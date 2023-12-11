package com.ruoyi.web.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description t_for_guarantee_info
 * @author zhangshuo
 * @date 2023-12-09
 */
@Data
@ApiModel("t_for_guarantee_info")
public class TForGuaranteeInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("zqzl")
    private String zqzl;

    /**
     * zqse
     */
    @ApiModelProperty("zqse")
    private String zqse;

    /**
     * zwr
     */
    @ApiModelProperty("zwr")
    private String zwr;

    /**
     * zqqxto
     */
    @ApiModelProperty("zqqxto")
    private String zqqxto;

    /**
     * zqqxfrom
     */
    @ApiModelProperty("zqqxfrom")
    private String zqqxfrom;

    /**
     * zqr
     */
    @ApiModelProperty("zqr")
    private String zqr;

    /**
     * bzfs
     */
    @ApiModelProperty("bzfs")
    private String bzfs;

    /**
     * bzqj
     */
    @ApiModelProperty("bzqj")
    private String bzqj;

    /**
     * uniscid
     */
    @ApiModelProperty("uniscid")
    private String uniscid;

    public TForGuaranteeInfo() {}
}
