package com.ruoyi.web.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description t_action_person_graph_detail
 * @author zhengkai.blog.csdn.net
 * @date 2023-12-12
 */
@Data
@ApiModel("t_action_person_graph_detail")
public class TActionPersonGraphDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * uniscid
     */
    @ApiModelProperty("uniscid")
    private String uniscid;

    /**
     * graph_detail
     */
    @ApiModelProperty("graph_detail")
    private String graphDetail;

    /**
     * line_detail
     */
    @ApiModelProperty("line_detail")
    private String lineDetail;

    /**
     * actrelid
     */
    @ApiModelProperty("actrelid")
    private String actrelid;

    /**
     * id
     */
    @ApiModelProperty("id")
    private String id;

    public TActionPersonGraphDetail() {}
}
