package com.ruoyi.web.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description t_enterprise_desc
 * @author zhengkai.blog.csdn.net
 * @date 2023-12-11
 */
@Data
@ApiModel("t_enterprise_desc")
public class TEnterpriseDesc implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("uniscid")
    private String uniscid;

    /**
     * ent_desc
     */
    @ApiModelProperty("ent_desc")
    private String entDesc;

    public TEnterpriseDesc() {}
}
