package com.ruoyi.web.service;


import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.uuid.UUID;
import com.ruoyi.web.domain.*;
import com.ruoyi.web.mapper.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.compress.utils.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class EntInfoService {

    @Autowired
    private ITEnterpriseBasicMapper itEnterpriseBasicMapper;

    @Autowired
    private ITPatentsRelationsMapper itPatentsRelationsMapper;

    @Autowired
    private ITTrademarksMapper itTrademarksMapper;

    @Autowired
    private ITNewJobsMapper itNewJobsMapper;

    @Autowired
    private ITEnterpriseDatasetMapper itEnterpriseDatasetMapper;

    // 插入数据集-企业关系表
    @Autowired
    private ITDatasetEnterpriseMapper itDatasetEnterpriseMapper;

    @Autowired
    private ITEnterpriseStockholderMapper itEnterpriseStockholderMapper;

    @Autowired
    private ITEnterpriseInvestmentMapper itEnterpriseInvestmentMapper;

    @Autowired
    private ITSupplierRelevanceMapper itSupplierRelevanceMapper;

    @Autowired
    private ITActualControllerMapper itActualControllerMapper;

    @Autowired
    private ITGroupTagMapper itGroupTagMapper;

    @Autowired
    private ITGroupRelationMapper itGroupRelationMapper;

    @Autowired
    private ITForGuaranteeInfoMapper itForGuaranteeInfoMapper;

    @Autowired
    private ITListedCompanyProfitsInfoMapper itListedCompanyProfitsInfoMapper;

    @Autowired
    private ITListedCompanyBalanceMapper itListedCompanyBalanceMapper;

    @Autowired
    private ITListedCompanyCashFlowMapper itListedCompanyCashFlowMapper;

    @Autowired
    private ITEvaluatingIndexMapper itEvaluatingIndexMapper;

    @Autowired
    private ITGreylistMapper itGreylistMapper;

    @Autowired
    private ITBlacklistMapper itBlacklistMapper;

    @Autowired
    private ITEnterpriseDescMapper itEnterpriseDescMapper;

    @Autowired
    private ITEnterpriseAppMapper itEnterpriseAppMapper;

    public AjaxResult searchInfoByKeyword(JSONObject searchParams, int pageNum, int pageSize) {
        if (!searchParams.containsKey("keywords") || StringUtils.isBlank(searchParams.getString("keywords"))) {
            return AjaxResult.error("请输入关键字");
        }
        String keyword = searchParams.getString("keywords");
        List<TPatentsRelations> tPatentsRelationsList =itPatentsRelationsMapper.selectList(
                new QueryWrapper<TPatentsRelations>().like("brief",keyword)
        );
        List<TTrademarks> tTrademarksList = itTrademarksMapper.selectList(
                new QueryWrapper<TTrademarks>()
                        .like("name",keyword)
        );

        List<TNewJobs> tNewJobsList = itNewJobsMapper.selectList(
                new QueryWrapper<TNewJobs>().like("title",keyword)
        );

        List<TEnterpriseBasicDto> tEnterpriseBasicDtoList = itEnterpriseBasicMapper.selectList(
                new QueryWrapper<TEnterpriseBasicDto>()
                        .like("entname",keyword)
                        .or()
                        .like("uniscid",keyword)
        );

        List<TEnterpriseDesc> tEnterpriseDescList = itEnterpriseDescMapper.selectList(
                new QueryWrapper<TEnterpriseDesc>()
                        .like("ent_desc",keyword)
        );

        List<TEnterpriseApp> tEnterpriseAppList = itEnterpriseAppMapper.selectList(
                new QueryWrapper<TEnterpriseApp>()
                        .like("appname",keyword)
                        .or()
                        .like("appinfo",keyword)
        );

        Set<String> allSearchUniscid = Sets.newHashSet();
        tPatentsRelationsList.forEach(tPatentsRelations -> {
            allSearchUniscid.add(tPatentsRelations.getUniscid());
        });
        tTrademarksList.forEach(tTrademarks -> {
            allSearchUniscid.add(tTrademarks.getUniscid());
        });
        tNewJobsList.forEach(tNewJobs -> {
            allSearchUniscid.add(tNewJobs.getUniscid());
        });
        tEnterpriseBasicDtoList.forEach(tEnterpriseBasicDto -> {
            allSearchUniscid.add(tEnterpriseBasicDto.getUniscid());
        });
        tEnterpriseDescList.forEach(tEnterpriseDesc -> {
            allSearchUniscid.add(tEnterpriseDesc.getUniscid());
        });
        tEnterpriseAppList.forEach(tEnterpriseApp -> {
            allSearchUniscid.add(tEnterpriseApp.getUniscid());
        });
        if (allSearchUniscid.size() == 0) {
            return AjaxResult.success(Lists.newArrayList());
        }
        // 根据查询到的信息，查询企业基本信息
        QueryWrapper<TEnterpriseBasicDto> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("uniscid",allSearchUniscid);
        List<TEnterpriseBasicDto> tempResultList = itEnterpriseBasicMapper.selectList(queryWrapper);
        tempResultList.forEach(tEnterpriseBasicDto -> {
            tEnterpriseBasicDto.setRegcap(tEnterpriseBasicDto.getRegcap().split("\\.")[0]);
            tEnterpriseBasicDto.setTEvaluatingIndex(itEvaluatingIndexMapper.selectOne(
                    new QueryWrapper<TEvaluatingIndex>().eq("uniscid",tEnterpriseBasicDto.getUniscid())
            ));
            tEnterpriseBasicDto.setTPatentsRelationsList(itPatentsRelationsMapper.selectList(
                    new QueryWrapper<TPatentsRelations>()
                            .eq("uniscid",tEnterpriseBasicDto.getUniscid())
                            .like("brief",keyword)
                            .last(" limit 5 ")
            ));
            tEnterpriseBasicDto.setTTrademarksList(itTrademarksMapper.selectList(
                    new QueryWrapper<TTrademarks>()
                            .eq("uniscid",tEnterpriseBasicDto.getUniscid())
                            .last(" limit 10 ")
            ));
            tEnterpriseBasicDto.setTNewJobsList(itNewJobsMapper.selectList(
                    new QueryWrapper<TNewJobs>()
                            .eq("uniscid",tEnterpriseBasicDto.getUniscid())
                            .like("title",keyword)
                            .last(" limit 5 ")
            ));
            tEnterpriseBasicDto.setTEnterpriseDescList(itEnterpriseDescMapper.selectList(
                    new QueryWrapper<TEnterpriseDesc>()
                            .eq("uniscid",tEnterpriseBasicDto.getUniscid())
                            .like("ent_desc",keyword)
                            .last(" limit 5 ")
            ));

            tEnterpriseBasicDto.setTEnterpriseAppList(itEnterpriseAppMapper.selectList(
                    new QueryWrapper<TEnterpriseApp>()
                            .eq("uniscid",tEnterpriseBasicDto.getUniscid())
            ));
        });
        return AjaxResult.success(tempResultList);
    }

    public AjaxResult searchInfoByKeywordSimple(JSONObject searchParams, int pageNum, int pageSize) {

        QueryWrapper<TEnterpriseBasicDto> queryWrapper = new QueryWrapper<>();
        if (searchParams.containsKey("keyword") && StringUtils.isNotBlank(searchParams.getString("keyword"))) {
            String keyword = searchParams.getString("keyword");
            queryWrapper.like("entname",keyword)
                    .or().eq("uniscid",keyword);
        }
        Page<TEnterpriseBasicDto> page = new Page<>(pageNum,pageSize);
        Page<TEnterpriseBasicDto> pageResult = itEnterpriseBasicMapper.selectPage(page, queryWrapper);
        List<TEnterpriseBasicDto> basicList = Lists.newArrayList();

        for (TEnterpriseBasicDto basic : pageResult.getRecords()) {
            basic.setRegcap(basic.getRegcap().split("\\.")[0]);
            basicList.add(basic);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total",pageResult.getTotal());
        jsonObject.put("pages",pageResult.getPages());
        jsonObject.put("pageNum",pageResult.getCurrent());
        jsonObject.put("pageSize",pageResult.getSize());
        jsonObject.put("item",basicList);
        return AjaxResult.success(jsonObject);
    }

    public AjaxResult getShareHolderAndInvestment(String uniscid) {
        List<TEnterpriseStockholder> stockholderList = itEnterpriseStockholderMapper.selectList(
                new QueryWrapper<TEnterpriseStockholder>().eq("uniscid",uniscid)
        );
        List<TEnterpriseInvestment> investmentList = itEnterpriseInvestmentMapper.selectList(
                new QueryWrapper<TEnterpriseInvestment>().eq("uniscid",uniscid)
        );
        TEnterpriseBasicDto baseEnterpriseInfo = itEnterpriseBasicMapper.selectOne(
                new QueryWrapper<TEnterpriseBasicDto>().eq("uniscid", uniscid)
        );
        JSONObject basic = new JSONObject();
        basic.put("stockholderList",stockholderList);
        basic.put("investmentList",investmentList);
        basic.put("baseEnterpriseInfo",baseEnterpriseInfo);
        return AjaxResult.success(basic);
    }

    public AjaxResult getRelatedEnterprise(JSONObject searchParams, int pageNum, int pageSize) {
        QueryWrapper<TEnterpriseBasicDto> queryWrapper = new QueryWrapper<>();
        if (searchParams.containsKey("keyword") && StringUtils.isNotBlank(searchParams.getString("keyword"))) {
            String keyword = searchParams.getString("keyword");
            queryWrapper.like("entname",keyword)
                    .or().eq("uniscid",keyword);
        }
        Page<TEnterpriseBasicDto> page = new Page<>(pageNum,pageSize);
        Page<TEnterpriseBasicDto> pageResult = itEnterpriseBasicMapper.selectPage(page, queryWrapper);
        List<TEnterpriseBasicDto> basicList = Lists.newArrayList();
        for (TEnterpriseBasicDto basic : pageResult.getRecords()) {
            basic.setRegcap(basic.getRegcap().split("\\.")[0]);
            basic.setRelaNums(itSupplierRelevanceMapper.selectCount(
                    new QueryWrapper<TSupplierRelevance>().eq("uniscid",basic.getUniscid())
            ));
            basicList.add(basic);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total",pageResult.getTotal());
        jsonObject.put("pages",pageResult.getPages());
        jsonObject.put("pageNum",pageResult.getCurrent());
        jsonObject.put("pageSize",pageResult.getSize());
        jsonObject.put("item",basicList);
        return AjaxResult.success(jsonObject);
    }

    public AjaxResult getGroupList(JSONObject searchParams, int pageNum, int pageSize) {
        QueryWrapper<TGroupTag> queryWrapper = new QueryWrapper<>();
        if (searchParams.containsKey("keyword") && StringUtils.isNotBlank(searchParams.getString("keyword"))) {
            String keyword = searchParams.getString("keyword");
            queryWrapper.like("grpname",keyword);
        }
        Page<TGroupTag> page = new Page<>(pageNum,pageSize);
        Page<TGroupTag> pageResult = itGroupTagMapper.selectPage(page, queryWrapper);
        List<TGroupTag> groupList = Lists.newArrayList();
        for (TGroupTag group : pageResult.getRecords()) {
            group.setTGroupRelationList(itGroupRelationMapper.selectList(
                    new QueryWrapper<TGroupRelation>().eq("groupid",group.getGrpid())
            ));
            groupList.add(group);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total",pageResult.getTotal());
        jsonObject.put("pages",pageResult.getPages());
        jsonObject.put("pageNum",pageResult.getCurrent());
        jsonObject.put("pageSize",pageResult.getSize());
        jsonObject.put("item",groupList);
        return AjaxResult.success(jsonObject);
    }

    public AjaxResult getGuaranteeList(JSONObject searchParams, int pageNum, int pageSize) {
        QueryWrapper<TEnterpriseBasicDto> queryWrapper = new QueryWrapper<>();
        if (searchParams.containsKey("keyword") && StringUtils.isNotBlank(searchParams.getString("keyword"))) {
            String keyword = searchParams.getString("keyword");
            queryWrapper.like("entname",keyword)
                    .or().eq("uniscid",keyword);
        }
        Page<TEnterpriseBasicDto> page = new Page<>(pageNum,pageSize);
        Page<TEnterpriseBasicDto> pageResult = itEnterpriseBasicMapper.selectPage(page, queryWrapper);
        List<TEnterpriseBasicDto> basicList = Lists.newArrayList();
        for (TEnterpriseBasicDto basic : pageResult.getRecords()) {
            basic.setRegcap(basic.getRegcap().split("\\.")[0]);
            basic.setTForGuaranteeInfoList(itForGuaranteeInfoMapper.selectList(
                    new QueryWrapper<TForGuaranteeInfo>().eq("uniscid",basic.getUniscid())
            ));
            basicList.add(basic);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total",pageResult.getTotal());
        jsonObject.put("pages",pageResult.getPages());
        jsonObject.put("pageNum",pageResult.getCurrent());
        jsonObject.put("pageSize",pageResult.getSize());
        jsonObject.put("item",basicList);
        return AjaxResult.success(jsonObject);
    }

    public AjaxResult getSupplierRelation(String uniscid) {
        List<TSupplierRelevance> supplierRelevanceList = itSupplierRelevanceMapper.selectList(
                new QueryWrapper<TSupplierRelevance>().eq("uniscid",uniscid)
        );
        TEnterpriseBasicDto baseEnterpriseInfo = itEnterpriseBasicMapper.selectOne(
                new QueryWrapper<TEnterpriseBasicDto>().eq("uniscid", uniscid)
        );
        JSONObject basic = new JSONObject();
        basic.put("supplierRelevanceList",supplierRelevanceList);
        basic.put("baseEnterpriseInfo",baseEnterpriseInfo);
        return AjaxResult.success(basic);
    }

    public AjaxResult getRelaEntList(String uniscid) {
        List<TSupplierRelevance> supplierRelevanceList = itSupplierRelevanceMapper.selectList(
                new QueryWrapper<TSupplierRelevance>().eq("uniscid",uniscid));
        return AjaxResult.success(supplierRelevanceList);
    }

    public AjaxResult getEntDetail(String uniscid) {

        TEnterpriseBasicDto basic = itEnterpriseBasicMapper.selectById(uniscid);
        basic.setTActualControllerList(itActualControllerMapper.selectList(
                new QueryWrapper<TActualController>().eq("uniscid",uniscid)
        ));
        basic.setTEnterpriseStockholderList(itEnterpriseStockholderMapper.selectList(
                new QueryWrapper<TEnterpriseStockholder>().eq("uniscid",uniscid)
        ));
        basic.setTEnterpriseInvestmentList(itEnterpriseInvestmentMapper.selectList(
                new QueryWrapper<TEnterpriseInvestment>().eq("uniscid",uniscid)
        ));
//        basic.setTSupplierRelevanceList(itSupplierRelevanceMapper.selectList(
//                new QueryWrapper<TSupplierRelevance>().eq("uniscid",uniscid)
//        ));
        basic.setRelaNums(itSupplierRelevanceMapper.selectCount(
                new QueryWrapper<TSupplierRelevance>().eq("uniscid",uniscid)
        ));

        basic.setTGroupTag(itGroupTagMapper.selectOne(
                new QueryWrapper<TGroupTag>().eq("grpid",basic.getGrpid())
        ));
//        basic.setTGroupRelationList(itGroupRelationMapper.selectList(
//                new QueryWrapper<TGroupRelation>().eq("groupid",basic.getGrpid())
//        ));
        basic.setGroupInfoNums(itGroupRelationMapper.selectCount(
                new QueryWrapper<TGroupRelation>().eq("groupid",basic.getGrpid())
        ));
        basic.setTListedCompanyBalanceList(itListedCompanyBalanceMapper.selectList(
                new QueryWrapper<TListedCompanyBalance>().eq("uniscid",uniscid)
                        .orderByDesc("rpt_date").last(" limit 5 ")
        ));
        basic.setTListedCompanyCashFlowList(itListedCompanyCashFlowMapper.selectList(
                new QueryWrapper<TListedCompanyCashFlow>().eq("uniscid",uniscid)
                        .orderByDesc("rpt_date").last(" limit 5 ")
        ));
        basic.setTListedCompanyProfitsInfoList(itListedCompanyProfitsInfoMapper.selectList(
                new QueryWrapper<TListedCompanyProfitsInfo>().eq("uniscid",uniscid)
                        .orderByDesc("rpt_date").last(" limit 5 ")
        ));
        basic.setTEvaluatingIndex(itEvaluatingIndexMapper.selectOne(
                new QueryWrapper<TEvaluatingIndex>().eq("uniscid",uniscid)
        ));
        return AjaxResult.success(basic);
    }

    public AjaxResult getActualController(JSONObject searchParams, int pageNum, int pageSize) {
        QueryWrapper<TEnterpriseBasicDto> queryWrapper = new QueryWrapper<>();
        if (searchParams.containsKey("keyword") && StringUtils.isNotBlank(searchParams.getString("keyword"))) {
            String keyword = searchParams.getString("keyword");
            queryWrapper.like("entname",keyword)
                    .or().eq("uniscid",keyword);
        }

        List<String> allUniscidList = Lists.newArrayList();
        itActualControllerMapper.selectList(
                new QueryWrapper<TActualController>().select("uniscid")
        ).forEach(actualController -> {
            allUniscidList.add(actualController.getUniscid());
        });
        queryWrapper.in("uniscid",allUniscidList);
        Page<TEnterpriseBasicDto> page = new Page<>(pageNum,pageSize);
        Page<TEnterpriseBasicDto> pageResult = itEnterpriseBasicMapper.selectPage(page, queryWrapper);
        List<TEnterpriseBasicDto> basicList = Lists.newArrayList();
        log.info("getRecords:{}", pageResult.getRecords());
        for (TEnterpriseBasicDto basic : pageResult.getRecords()) {
            basic.setRegcap(basic.getRegcap().split("\\.")[0]);
            basic.setTActualControllerList(itActualControllerMapper.selectList(
                    new QueryWrapper<TActualController>().eq("uniscid",basic.getUniscid())
            ));
            basicList.add(basic);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total",pageResult.getTotal());
        jsonObject.put("pages",pageResult.getPages());
        jsonObject.put("pageNum",pageResult.getCurrent());
        jsonObject.put("pageSize",pageResult.getSize());
        jsonObject.put("item",basicList);
        return AjaxResult.success(jsonObject);
    }

    public AjaxResult createDataSet(JSONObject paramsBody, Long userId) {

        TEnterpriseDataset tEnterpriseDataset = new TEnterpriseDataset();
        String id = UUID.randomUUID().toString().replace("-","").toUpperCase().substring(0,29);
        tEnterpriseDataset.setId(id);
        tEnterpriseDataset.setCreateUserId(userId.intValue());
        tEnterpriseDataset.setDatasetName(paramsBody.getString("datasetName"));
        tEnterpriseDataset.setVisibleDataset(paramsBody.getIntValue("visibleDataset"));
        itEnterpriseDatasetMapper.insert(tEnterpriseDataset);

        // 插入数据集-企业关系表
        JSONArray uniscidList = paramsBody.getJSONArray("uniscidList");
        for (int i = 0; i < uniscidList.size(); i++) {
            TDatasetEnterprise itDatasetEnterprise = new TDatasetEnterprise();
            itDatasetEnterprise.setDatasetId(id);
            itDatasetEnterprise.setUniscid(uniscidList.getString(i));
            itDatasetEnterpriseMapper.insert(itDatasetEnterprise);
        }
        return AjaxResult.success();
    }

    public AjaxResult deleteDataSet(String datasetId) {
        itDatasetEnterpriseMapper.delete(new QueryWrapper<TDatasetEnterprise>().eq("dataset_id",datasetId));
        itEnterpriseDatasetMapper.deleteById(datasetId);
        return AjaxResult.success();
    }

    public AjaxResult getDataSetList(long userId, JSONObject paramsBody, int pageNum, int pageSize) {
        QueryWrapper<TEnterpriseDataset> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("create_user_id",userId);
        if (paramsBody.containsKey("datasetName") && StringUtils.isNotBlank(paramsBody.getString("datasetName"))) {
            queryWrapper.like("dataset_name",paramsBody.getString("datasetName"));
        }
        Page<TEnterpriseDataset> page = new Page<>(pageNum,pageSize);
        Page<TEnterpriseDataset> pageResult = itEnterpriseDatasetMapper.selectPage(page, queryWrapper);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total",pageResult.getTotal());
        jsonObject.put("pages",pageResult.getPages());
        jsonObject.put("pageNum",pageResult.getCurrent());
        jsonObject.put("pageSize",pageResult.getSize());
        jsonObject.put("item",pageResult.getRecords());
        return AjaxResult.success(jsonObject);
    }

    public AjaxResult getRiskList(JSONObject paramsBody, int pageNum, int pageSize) {
        QueryWrapper<TEnterpriseBasicDto> queryWrapper = new QueryWrapper<>();
        if (paramsBody.containsKey("keyword") && StringUtils.isNotBlank(paramsBody.getString("keyword"))) {
            String keyword = paramsBody.getString("keyword");
            queryWrapper.like("entname",keyword)
                    .or().eq("uniscid",keyword);
        }

        Page<TEnterpriseBasicDto> page = new Page<>(pageNum,pageSize);
        Page<TEnterpriseBasicDto> pageResult = itEnterpriseBasicMapper.selectPage(page, queryWrapper);
        List<TEnterpriseBasicDto> basicList = Lists.newArrayList();
        for (TEnterpriseBasicDto basic : pageResult.getRecords()) {
            basic.setRegcap(basic.getRegcap().split("\\.")[0]);
            basic.setTGreylistList(itGreylistMapper.selectList(
                    new QueryWrapper<TGreylist>().eq("uniscid",basic.getUniscid())
            ));
            basic.setTBlacklistList(itBlacklistMapper.selectList(
                    new QueryWrapper<TBlacklist>().eq("uniscid",basic.getUniscid())
            ));
            basicList.add(basic);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total",pageResult.getTotal());
        jsonObject.put("pages",pageResult.getPages());
        jsonObject.put("pageNum",pageResult.getCurrent());
        jsonObject.put("pageSize",pageResult.getSize());
        jsonObject.put("item",basicList);
        return AjaxResult.success(jsonObject);
    }

    public AjaxResult getDataSetDetail(String datasetId) {
        List<TDatasetEnterprise> tDatasetEnterpriseList = itDatasetEnterpriseMapper.selectList(
                new QueryWrapper<TDatasetEnterprise>().eq("dataset_id",datasetId)
        );
        List<String> uniscidList = Lists.newArrayList();
        tDatasetEnterpriseList.forEach(tDatasetEnterprise -> {
            uniscidList.add(tDatasetEnterprise.getUniscid());
        });
        QueryWrapper<TEnterpriseBasicDto> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("uniscid",uniscidList);
        List<TEnterpriseBasicDto> basicList = itEnterpriseBasicMapper.selectList(queryWrapper);
        return AjaxResult.success(basicList);
    }

    public AjaxResult updateDataSetDetail(String datasetId, JSONObject paramsBody) {

        // 更新数据集
        TEnterpriseDataset tEnterpriseDataset = new TEnterpriseDataset();
        tEnterpriseDataset.setId(datasetId);
        tEnterpriseDataset.setDatasetName(paramsBody.getString("datasetName"));
        tEnterpriseDataset.setVisibleDataset(paramsBody.getIntValue("visibleDataset"));
        tEnterpriseDataset.setUpdateTime(new Date());
        itEnterpriseDatasetMapper.updateById(tEnterpriseDataset);
        return AjaxResult.success();
    }
}
