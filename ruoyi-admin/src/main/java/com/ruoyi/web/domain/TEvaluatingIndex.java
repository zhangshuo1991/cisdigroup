package com.ruoyi.web.domain;

import lombok.Data;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * @description t_evaluating_index
 * @author zhangshuo
 * @date 2023-12-10
 */
@Data
@ApiModel("t_evaluating_index")
public class TEvaluatingIndex implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * development
     */
    @ApiModelProperty("development")
    private String development;

    /**
     * risk_grade
     */
    @ApiModelProperty("risk_grade")
    private String riskGrade;

    /**
     * creativity_grade
     */
    @ApiModelProperty("creativity_grade")
    private String creativityGrade;

    /**
     * identity_grade
     */
    @ApiModelProperty("identity_grade")
    private String identityGrade;

    /**
     * vigor
     */
    @ApiModelProperty("vigor")
    private String vigor;

    /**
     * overall_grade
     */
    @ApiModelProperty("overall_grade")
    private String overallGrade;

    /**
     * competitiveness
     */
    @ApiModelProperty("competitiveness")
    private String competitiveness;

    /**
     * update_date
     */
    @ApiModelProperty("update_date")
    private String updateDate;

    /**
     * development_grade
     */
    @ApiModelProperty("development_grade")
    private String developmentGrade;

    /**
     * ent_identity
     */
    @ApiModelProperty("ent_identity")
    private String entIdentity;

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
     * vigor_grade
     */
    @ApiModelProperty("vigor_grade")
    private String vigorGrade;

    /**
     * competitiveness_grade
     */
    @ApiModelProperty("competitiveness_grade")
    private String competitivenessGrade;

    /**
     * overall
     */
    @ApiModelProperty("overall")
    private String overall;

    /**
     * risk
     */
    @ApiModelProperty("risk")
    private String risk;

    /**
     * creativity
     */
    @ApiModelProperty("creativity")
    private String creativity;

    public TEvaluatingIndex() {}
}
