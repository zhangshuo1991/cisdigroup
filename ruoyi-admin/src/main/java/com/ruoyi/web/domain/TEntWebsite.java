package com.ruoyi.web.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description t_ent_website
 * @author zhengkai.blog.csdn.net
 * @date 2023-12-11
 */
@Data
@ApiModel("t_ent_website")
public class TEntWebsite implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("uniscid")
    private String uniscid;

    /**
     * fba_wz_ym
     */
    @ApiModelProperty("fba_wz_ym")
    private String fbaWzYm;

    /**
     * fba_wz_wz
     */
    @ApiModelProperty("fba_wz_wz")
    private String fbaWzWz;

    /**
     * fba_wz_xkz
     */
    @ApiModelProperty("fba_wz_xkz")
    private String fbaWzXkz;

    /**
     * fba_wz_mc
     */
    @ApiModelProperty("fba_wz_mc")
    private String fbaWzMc;

    public TEntWebsite() {}
}
