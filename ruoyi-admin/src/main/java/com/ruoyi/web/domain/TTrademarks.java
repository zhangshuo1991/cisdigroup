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
 * @description t_trademarks
 * @author zhangshuo
 * @date 2023-12-02
 */
@Data
@ApiModel("t_trademarks")
public class TTrademarks extends BaseWebEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
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
     * id_id
     */
    @ApiModelProperty("id_id")
    private String idId;

    /**
     * reg_number
     */
    @ApiModelProperty("reg_number")
    private String regNumber;

    /**
     * type_num
     */
    @ApiModelProperty("type_num")
    private String typeNum;

    /**
     * status
     */
    @ApiModelProperty("status")
    private String status;

    /**
     * name
     */
    @ApiModelProperty("name")
    private String name;

    /**
     * now_status
     */
    @ApiModelProperty("now_status")
    private String nowStatus;

    /**
     * trademark_type
     */
    @ApiModelProperty("trademark_type")
    private String trademarkType;

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
     * reg_pubdate
     */
    @ApiModelProperty("reg_pubdate")
    private String regPubdate;

    /**
     * last_updated_time
     */
    @ApiModelProperty("last_updated_time")
    private String lastUpdatedTime;

    public TTrademarks() {}
}

