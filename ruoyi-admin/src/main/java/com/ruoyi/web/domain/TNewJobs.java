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
 * @description t_new_jobs
 * @author zhangshuo
 * @date 2023-12-02
 */
@Data
@ApiModel("t_new_jobs")
public class TNewJobs implements Serializable {

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
     * id_id
     */
    @ApiModelProperty("id_id")
    private String idId;

    /**
     * title
     */
    @ApiModelProperty("title")
    private String title;

    /**
     * start_date
     */
    @ApiModelProperty("start_date")
    private String startDate;

    /**
     * end_date
     */
    @ApiModelProperty("end_date")
    private String endDate;

    /**
     * date
     */
    @ApiModelProperty("date")
    private String date;

    /**
     * years
     */
    @ApiModelProperty("years")
    private String years;

    /**
     * description
     */
    @ApiModelProperty("description")
    private String description;

    /**
     * industry
     */
    @ApiModelProperty("industry")
    private String industry;

    public TNewJobs() {}
}

