package com.ruoyi.web.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description t_enterprise_label
 * @author zhengkai.blog.csdn.net
 * @date 2023-12-13
 */
@Data
@ApiModel("t_enterprise_label")
public class TEnterpriseLabel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * uniscid
     */
    @ApiModelProperty("uniscid")
    private String uniscid;

    /**
     * ent_label
     */
    @ApiModelProperty("ent_label")
    private String entLabel;

    public TEnterpriseLabel() {}
}
