package com.ruoyi.web.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description t_greylist
 * @author zhengkai.blog.csdn.net
 * @date 2023-12-11
 */
@Data
@ApiModel("t_greylist")
public class TGreylist implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * entid
     */
    @ApiModelProperty("entid")
    private String entid;

    /**
     * entname
     */
    @ApiModelProperty("entname")
    private String entname;

    /**
     * uniscid
     */
    @ApiModelProperty("uniscid")
    private String uniscid;

    /**
     * ename
     */
    @ApiModelProperty("ename")
    private String ename;

    /**
     * blacklist_type
     */
    @ApiModelProperty("blacklist_type")
    private String blacklistType;

    /**
     * rel_type
     */
    @ApiModelProperty("rel_type")
    private String relType;

    public TGreylist() {}
}
