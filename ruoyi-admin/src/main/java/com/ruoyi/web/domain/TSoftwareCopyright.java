package com.ruoyi.web.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description t_software_copyright
 * @author zhengkai.blog.csdn.net
 * @date 2023-12-11
 */
@Data
@ApiModel("t_software_copyright")
public class TSoftwareCopyright implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * frj_bbh
     */
    @ApiModelProperty("frj_bbh")
    private String frjBbh;

    /**
     * frj_rjjc
     */
    @ApiModelProperty("frj_rjjc")
    private String frjRjjc;

    /**
     * frj_scfbdate
     */
    @ApiModelProperty("frj_scfbdate")
    private String frjScfbdate;

    /**
     * frj_djh
     */
    @ApiModelProperty("frj_djh")
    private String frjDjh;

    /**
     * frj_djdate
     */
    @ApiModelProperty("frj_djdate")
    private String frjDjdate;

    /**
     * frj_rjflh
     */
    @ApiModelProperty("frj_rjflh")
    private String frjRjflh;

    /**
     * frj_rjqc
     */
    @ApiModelProperty("frj_rjqc")
    private String frjRjqc;

    /**
     * frj_hyflh
     */
    @ApiModelProperty("frj_hyflh")
    private String frjHyflh;

    /**
     * frj_zzqr_gj
     */
    @ApiModelProperty("frj_zzqr_gj")
    private String frjZzqrGj;

    /**
     * uniscid
     */
    @ApiModelProperty("uniscid")
    private String uniscid;

    public TSoftwareCopyright() {}
}