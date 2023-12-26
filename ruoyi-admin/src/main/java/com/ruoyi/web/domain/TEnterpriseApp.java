package com.ruoyi.web.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description t_enterprise_app
 * @author zhengkai.blog.csdn.net
 * @date 2023-12-11
 */
@Data
@ApiModel("t_enterprise_app")
public class TEnterpriseApp extends BaseWebEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * app_system
     */
    @ApiModelProperty("app_system")
    private String appSystem;

    /**
     * appname
     */
    @ApiModelProperty("appname")
    private String appname;

    /**
     * appinfo
     */
    @ApiModelProperty("appinfo")
    private String appinfo;

    /**
     * app_type
     */
    @ApiModelProperty("app_type")
    private String appType;

    /**
     * app_image
     */
    @ApiModelProperty("app_image")
    private String appImage;

    public TEnterpriseApp() {}
}
