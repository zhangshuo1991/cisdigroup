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
import com.ruoyi.web.utils.CheckUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.compress.utils.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
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

    @Autowired
    private ITSoftwareCopyrightMapper itSoftwareCopyrightMapper;

    @Autowired
    private ITEntWebsiteMapper itEntWebsiteMapper;

    @Autowired
    private ITBiddingsallMapper itBiddingsallMapper;

    @Autowired
    private ITActionPersonMapper itActionPersonMapper;

    @Autowired
    private ITAdministrativePunishmentMapper itAdministrativePunishmentMapper;

    @Autowired
    private ITOverduetaxsMapper itOverduetaxsMapper;

    @Autowired
    private ITLawsuitsRelationsMapper itLawsuitsRelationsMapper;

    @Autowired
    private ITActionPersonGraphDetailMapper itActionPersonGraphDetailMapper;

    @Autowired
    private ITEnterpriseLabelMapper itEnterpriseLabelMapper;

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
                        .like("appinfo",keyword)
        );

        List<TSoftwareCopyright> tSoftwareCopyrightList = itSoftwareCopyrightMapper.selectList(
                new QueryWrapper<TSoftwareCopyright>()
                        .like("frj_rjjc",keyword)
        );


        List<TEntWebsite> tEntWebsiteList = itEntWebsiteMapper.selectList(
                new QueryWrapper<TEntWebsite>()
                        .like("fba_wz_mc",keyword)
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
        tSoftwareCopyrightList.forEach(tSoftwareCopyright ->
            allSearchUniscid.add(tSoftwareCopyright.getUniscid())
        );
        tEntWebsiteList.forEach(tEntWebsite -> {
            allSearchUniscid.add(tEntWebsite.getUniscid());
        });
        JSONArray entscoreChecks = searchParams.getJSONArray("entscore_checks");

        Set<String> newResult = allSearchUniscid;
        if(entscoreChecks.size()>0) {
            Set<String> scoreSelectList = searchScore(searchParams);
            // 两个集合是否有交集
            newResult = com.google.common.collect.Sets.intersection(allSearchUniscid,scoreSelectList);
        }
        if (newResult.size() == 0) {
            return AjaxResult.success(Lists.newArrayList());
        }
        // 根据查询到的信息，查询企业基本信息
        QueryWrapper<TEnterpriseBasicDto> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("uniscid",newResult);
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
            ));
            tEnterpriseBasicDto.setTTrademarksList(itTrademarksMapper.selectList(
                    new QueryWrapper<TTrademarks>()
                            .eq("uniscid",tEnterpriseBasicDto.getUniscid())
                            .like("name",keyword)
            ));
            tEnterpriseBasicDto.setTNewJobsList(itNewJobsMapper.selectList(
                    new QueryWrapper<TNewJobs>()
                            .eq("uniscid",tEnterpriseBasicDto.getUniscid())
                            .like("title",keyword)
            ));
            tEnterpriseBasicDto.setTEnterpriseDescList(itEnterpriseDescMapper.selectList(
                    new QueryWrapper<TEnterpriseDesc>()
                            .eq("uniscid",tEnterpriseBasicDto.getUniscid())
                            .like("ent_desc",keyword)
            ));
            tEnterpriseBasicDto.setTSoftwareCopyrightList(
                    itSoftwareCopyrightMapper.selectList(
                            new QueryWrapper<TSoftwareCopyright>()
                                    .eq("uniscid",tEnterpriseBasicDto.getUniscid())
                                    .like("frj_rjjc",keyword)
                    ));

            tEnterpriseBasicDto.setTEnterpriseAppList(itEnterpriseAppMapper.selectList(
                    new QueryWrapper<TEnterpriseApp>()
                            .eq("uniscid",tEnterpriseBasicDto.getUniscid())
                            .like("appinfo",keyword)
            ));

            tEnterpriseBasicDto.setTEntWebsiteList(itEntWebsiteMapper.selectList(
                    new QueryWrapper<TEntWebsite>()
                            .eq("uniscid",tEnterpriseBasicDto.getUniscid())
                            .like("fba_wz_mc",keyword)
            ));
            tEnterpriseBasicDto.setTEnterpriseLabelList(itEnterpriseLabelMapper.selectList(
                    new QueryWrapper<TEnterpriseLabel>()
                            .eq("uniscid",tEnterpriseBasicDto.getUniscid())
            ));
        });
        return AjaxResult.success(tempResultList);
    }

    private Set<String> searchScore(JSONObject searchParams) {
        JSONArray entscoreChecks = searchParams.getJSONArray("entscore_checks");
        if(entscoreChecks.size()<=0) {
            return Sets.newHashSet();
        }

        Set<String> uniscidSet = Sets.newHashSet();
        for (int i=0;i<entscoreChecks.size();i++) {
            String score = CheckUtils.scoreMap.get((String)entscoreChecks.get(i));
            String start = score.split("-")[0];
            String end = score.split("-")[1];
            QueryWrapper<TEvaluatingIndex> queryWrapper = new QueryWrapper<>();
            queryWrapper.between("overall",start,end);
            List<TEvaluatingIndex> tempList = itEvaluatingIndexMapper.selectList(queryWrapper);
            for(TEvaluatingIndex item: tempList) {
                uniscidSet.add(item.getUniscid());
            }
        }
        return uniscidSet;
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
//            basic.setTBiddingsallCacheList(itBiddingsallCacheMapper.selectList(
//                    new QueryWrapper<TBiddingsallCache>().eq("uniscid",basic.getUniscid())
//            ));
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
                    new QueryWrapper<TGroupRelation>().eq("groupid",group.getGrpid()).last(" limit 20 ")
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

    public AjaxResult getBlackList() {
        return AjaxResult.success(itBlacklistMapper.selectList(new QueryWrapper<TBlacklist>()));
    }

    public AjaxResult getEntActGraph(String uniscid) {

        TActionPersonGraphDetail tActionPersonGraphDetail= itActionPersonGraphDetailMapper.selectList(
                new QueryWrapper<TActionPersonGraphDetail>()
                        .select("graph_detail")
                        .eq("uniscid",uniscid)).get(0);
        JSONObject graphDetailJson = JSONObject.parseObject(tActionPersonGraphDetail.getGraphDetail());
        return AjaxResult.success(graphDetailJson);
    }

    public AjaxResult getEntActionLineDetail(String uniscid,String actrelid) {
        List<TActionPersonGraphDetail> actionPersonGraphDetailsList= itActionPersonGraphDetailMapper.selectList(
                new QueryWrapper<TActionPersonGraphDetail>()
                        .select("line_detail")
                        .eq("uniscid",uniscid)
                        .eq("actrelid",actrelid)
        );
        if(actionPersonGraphDetailsList.size()<=0) {
            return AjaxResult.error("未找到相关的数据！");
        }
        TActionPersonGraphDetail tActionPersonGraphDetail = actionPersonGraphDetailsList.get(0);
        JSONObject graphDetailJson = JSONObject.parseObject(tActionPersonGraphDetail.getLineDetail());
        return AjaxResult.success(graphDetailJson);
    }

    public AjaxResult getTGreylist(int pageNum, int pageSize) {
        Page<TGreylist> page = new Page<>(pageNum,pageSize);
        Page<TGreylist> pageResult = itGreylistMapper.selectPage(page, new QueryWrapper<TGreylist>());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total",pageResult.getTotal());
        jsonObject.put("pages",pageResult.getPages());
        jsonObject.put("pageNum",pageResult.getCurrent());
        jsonObject.put("pageSize",pageResult.getSize());
        jsonObject.put("item",pageResult.getRecords());
        return AjaxResult.success(jsonObject);
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
//        log.info("getRecords:{}", pageResult.getRecords());
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

    public AjaxResult getActionGraph(JSONObject searchParams, int pageNum, int pageSize) {
        QueryWrapper<TEnterpriseBasicDto> queryWrapper = new QueryWrapper<>();
        if (searchParams.containsKey("keyword") && StringUtils.isNotBlank(searchParams.getString("keyword"))) {
            String keyword = searchParams.getString("keyword");
            queryWrapper.like("entname",keyword)
                    .or().eq("uniscid",keyword);
        }
        List<TActionPerson>  tActionPersonList = itActionPersonMapper.selectList(
                new QueryWrapper<TActionPerson>()
                        .select("distinct uniscid")
        );
        Set<String> uniscidSet = Sets.newHashSet();
        for (TActionPerson tActionPerson: tActionPersonList) {
            uniscidSet.add(tActionPerson.getUniscid());
        }
        queryWrapper.in("uniscid",uniscidSet);
        Page<TEnterpriseBasicDto> page = new Page<>(pageNum,pageSize);
        Page<TEnterpriseBasicDto> pageResult = itEnterpriseBasicMapper.selectPage(page, queryWrapper);
        List<TEnterpriseBasicDto> basicList = Lists.newArrayList();
        for (TEnterpriseBasicDto basic : pageResult.getRecords()) {
            basic.setRegcap(basic.getRegcap().split("\\.")[0]);
            basic.setActionPersonNums(
                itActionPersonMapper.selectList(
                        new QueryWrapper<TActionPerson>()
                                .select("distinct actgid")
                                .eq("uniscid",basic.getUniscid())
                ).size()
            );
            basic.setTActionPersonList(
                itActionPersonMapper.selectList(
                        new QueryWrapper<TActionPerson>()
                                .select("distinct fromname")
                                .eq("uniscid",basic.getUniscid())
                )
            );
            basic.setTEnterpriseStockholderList(itEnterpriseStockholderMapper.selectList(
                    new QueryWrapper<TEnterpriseStockholder>().eq("uniscid",basic.getUniscid())
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
        queryWrapper.orderByDesc("create_time");
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

    public AjaxResult getAdministrativePunishment(int pageNum, int pageSize) {
        QueryWrapper<TAdministrativePunishment> queryWrapper = new QueryWrapper<>();
        Page<TAdministrativePunishment> page = new Page<>(pageNum,pageSize);
        Page<TAdministrativePunishment> pageResult = itAdministrativePunishmentMapper.selectPage(page, queryWrapper);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total",pageResult.getTotal());
        jsonObject.put("pages",pageResult.getPages());
        jsonObject.put("pageNum",pageResult.getCurrent());
        jsonObject.put("pageSize",pageResult.getSize());
        jsonObject.put("item",pageResult.getRecords());
        return AjaxResult.success(jsonObject);
    }

    public AjaxResult getOverduetaxs(int pageNum, int pageSize) {
        QueryWrapper<TOverduetaxs> queryWrapper = new QueryWrapper<>();
        Page<TOverduetaxs> page = new Page<>(pageNum,pageSize);
        Page<TOverduetaxs> pageResult = itOverduetaxsMapper.selectPage(page, queryWrapper);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total",pageResult.getTotal());
        jsonObject.put("pages",pageResult.getPages());
        jsonObject.put("pageNum",pageResult.getCurrent());
        jsonObject.put("pageSize",pageResult.getSize());
        jsonObject.put("item",pageResult.getRecords());
        return AjaxResult.success(jsonObject);
    }

    public AjaxResult getLawsuitsRelations(int pageNum, int pageSize) {
        QueryWrapper<TLawsuitsRelations> queryWrapper = new QueryWrapper<>();
        Page<TLawsuitsRelations> page = new Page<>(pageNum,pageSize);
        Page<TLawsuitsRelations> pageResult = itLawsuitsRelationsMapper.selectPage(page, queryWrapper);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total",pageResult.getTotal());
        jsonObject.put("pages",pageResult.getPages());
        jsonObject.put("pageNum",pageResult.getCurrent());
        jsonObject.put("pageSize",pageResult.getSize());
        jsonObject.put("item",pageResult.getRecords());
        return AjaxResult.success(jsonObject);
    }

    public AjaxResult getTBiddingsallCache(JSONObject paramsBody, int pageNum, int pageSize) {
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
            basic.setTBiddingsallList(
                itBiddingsallMapper.selectList(
                        new QueryWrapper<TBiddingsall>().eq("bidder_creditno",basic.getUniscid())
                )
            );
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
}
