package com.ruoyi.web.domain;



import lombok.Data;
import java.util.Date;
import java.util.List;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * @description t_patents_relations
 * @author zhangshuo
 * @date 2023-12-02
 */
@Data
@ApiModel("t_patents_relations")
public class TPatentsRelations implements Serializable {

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
     * type
     */
    @ApiModelProperty("type")
    private String type;

    /**
     * type_name
     */
    @ApiModelProperty("type_name")
    private String typeName;

    /**
     * patent_name
     */
    @ApiModelProperty("patent_name")
    private String patentName;

    /**
     * outhor_num
     */
    @ApiModelProperty("outhor_num")
    private String outhorNum;

    /**
     * request_num
     */
    @ApiModelProperty("request_num")
    private String requestNum;

    /**
     * brief
     */
    @ApiModelProperty("brief")
    private String brief;

    /**
     * patent_person
     */
    @ApiModelProperty("patent_person")
    private String patentPerson;

    /**
     * category_num
     */
    @ApiModelProperty("category_num")
    private String categoryNum;

    /**
     * request_date
     */
    @ApiModelProperty("request_date")
    private String requestDate;

    /**
     * outhor_date
     */
    @ApiModelProperty("outhor_date")
    private String outhorDate;

    /**
     * related_companies
     */
    @ApiModelProperty("related_companies")
    private String relatedCompanies;

    /**
     * row_update_time
     */
    @ApiModelProperty("row_update_time")
    private String rowUpdateTime;

    /**
     * local_row_update_time
     */
    @ApiModelProperty("local_row_update_time")
    private String localRowUpdateTime;

    public TPatentsRelations() {}
}

