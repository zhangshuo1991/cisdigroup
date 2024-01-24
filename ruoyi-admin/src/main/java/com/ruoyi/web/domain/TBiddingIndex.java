package com.ruoyi.web.domain;

/**
 * @description t_bidding_index
 * @author zhangshuo
 * @date 2023-12-28
 */

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ApiModel("t_bidding_index")
public class TBiddingIndex implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * entname
     */
    @ApiModelProperty("entname")
    private String entname;

    /**
     * uniscid
     */
    @TableId
    @ApiModelProperty("uniscid")
    private String uniscid;

    /**
     * total_purchase_amount
     */
    @ApiModelProperty("total_purchase_amount")
    private BigDecimal totalPurchaseAmount;

    /**
     * top_five_supplier_amount_ratio
     */
    @ApiModelProperty("top_five_supplier_amount_ratio")
    private BigDecimal topFiveSupplierAmountRatio;

    /**
     * max_supplier_name
     */
    @ApiModelProperty("max_supplier_name")
    private String maxSupplierName;

    /**
     * max_supplier_id
     */
    @ApiModelProperty("max_supplier_id")
    private String maxSupplierId;

    public TBiddingIndex() {}
}
