package com.ruoyi.web.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description t_biddingsall
 * @author zhengkai.blog.csdn.net
 * @date 2023-12-12
 */
@Data
@ApiModel("t_biddingsall")
public class TBiddingsall implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * bidder_eid
     */
    @ApiModelProperty("bidder_eid")
    private String bidderEid;

    /**
     * win_bidder_eid
     */
    @ApiModelProperty("win_bidder_eid")
    private String winBidderEid;

    /**
     * bidder_creditno
     */
    @ApiModelProperty("bidder_creditno")
    private String bidderCreditno;

    /**
     * bidder_name
     */
    @ApiModelProperty("bidder_name")
    private String bidderName;

    /**
     * win_bidder_creditno
     */
    @ApiModelProperty("win_bidder_creditno")
    private String winBidderCreditno;

    /**
     * win_bidder_name
     */
    @ApiModelProperty("win_bidder_name")
    private String winBidderName;

    /**
     * bid_amount
     */
    @ApiModelProperty("bid_amount")
    private String bidAmount;

    public TBiddingsall() {}
}
