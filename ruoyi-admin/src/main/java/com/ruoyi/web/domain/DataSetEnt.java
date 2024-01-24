package com.ruoyi.web.domain;


import com.ruoyi.common.annotation.Excel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter
public class DataSetEnt {

    @Excel(name = "数据集名称")
    private String dataSetName;

    @Excel(name = "数据集描述")
    private String dataSetDesc;

    @Excel(name = "企业名称")
    private String entName;

    @Excel(name = "注册资本")
    private String regCap;

    @Excel(name = "注册地址")
    private String regAddr;

    @Excel(name = "经营范围")
    private String busiScope;

    @Excel(name = "法人代表")
    private String legalPerson;

    @Excel(name = "成立日期")
    private String estDate;

    @Excel(name = "企业状态")
    private String entStatus;

    @Excel(name = "统一社会信用代码")
    private String creditCode;

    @Excel(name = "企业综合评分")
    private String overall;

    @Excel(name = "身份特征")
    private String entIdentity;

    @Excel(name = "经营风险")
    private String risk;

    @Excel(name = "行业实力")
    private String competitiveness;

    @Excel(name = "发展速度")
    private String development;

    @Excel(name = "活跃程度")
    private String vigor;

    @Excel(name = "创新能力")
    private String creativity;
}
