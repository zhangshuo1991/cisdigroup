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
 * @description t_supplier_relevance
 * @author zhangshuo
 * @date 2023-12-02
 */
@Data
@ApiModel("t_supplier_relevance")
public class TSupplierRelevance implements Serializable {

    private static final long serialVersionUID = 1L;

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
     * relpartyp
     */
    @ApiModelProperty("relpartyp")
    private String relpartyp;

    /**
     * partyid
     */
    @ApiModelProperty("partyid")
    private String partyid;

    /**
     * partyname
     */
    @ApiModelProperty("partyname")
    private String partyname;

    /**
     * partytyp
     */
    @ApiModelProperty("partytyp")
    private String partytyp;

    /**
     * partystatus
     */
    @ApiModelProperty("partystatus")
    private String partystatus;

    public TSupplierRelevance() {}
}

