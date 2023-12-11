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
 * @description t_restricted_consumer
 * @author zhangshuo
 * @date 2023-12-02
 */
@Data
@ApiModel("t_restricted_consumer")
public class TRestrictedConsumer implements Serializable {

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
     * name
     */
    @ApiModelProperty("name")
    private String name;

    /**
     * sex
     */
    @ApiModelProperty("sex")
    private String sex;

    /**
     * filing_date
     */
    @ApiModelProperty("filing_date")
    private String filingDate;

    /**
     * case_no
     */
    @ApiModelProperty("case_no")
    private String caseNo;

    /**
     * court
     */
    @ApiModelProperty("court")
    private String court;

    /**
     * execution_applicant
     */
    @ApiModelProperty("execution_applicant")
    private String executionApplicant;

    /**
     * case_reason
     */
    @ApiModelProperty("case_reason")
    private String caseReason;

    /**
     * release_date
     */
    @ApiModelProperty("release_date")
    private String releaseDate;

    /**
     * content
     */
    @ApiModelProperty("content")
    private String content;

    /**
     * last_update_time
     */
    @ApiModelProperty("last_update_time")
    private String lastUpdateTime;

    public TRestrictedConsumer() {}
}
