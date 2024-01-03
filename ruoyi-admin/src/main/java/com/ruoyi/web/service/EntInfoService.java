package com.ruoyi.web.service;


import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.DateUtils;
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

import java.util.*;

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
    private ITBiddingsAllNewMapper itBiddingsAllNewMapper;

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

    @Autowired
    private ITChangeRecordsMapper itChangeRecordsMapper;

    @Autowired
    private ITEnterpriseTagMapper itEnterpriseTagMapper;

    @Autowired
    private ITRestrictedConsumerMapper itRestrictedConsumerMapper;

    @Autowired
    private ITJudicialFreezesMapper itJudicialFreezesMapper;

    @Autowired
    private ITBiddingIndexMapper itBiddingIndexMapper;

    public AjaxResult searchInfoByKeyword(JSONObject searchParams, int pageNum, int pageSize) {
        if (!searchParams.containsKey("keywords") || StringUtils.isBlank(searchParams.getString("keywords"))) {
            return AjaxResult.error("请输入关键字");
        }
        String keyword = searchParams.getString("keywords");
        JSONArray checkBoxSearchType = searchParams.getJSONArray("checkBoxSearchType");
        Set<String> allSearchUniscid = Sets.newHashSet();
        if (checkBoxSearchType.contains("patentAbstract") ||
            checkBoxSearchType.contains("patentName") ) {
            List<TPatentsRelations> tPatentsRelationsList =itPatentsRelationsMapper.selectList(
                    new QueryWrapper<TPatentsRelations>().like("brief",keyword)
            );

            tPatentsRelationsList.forEach(tPatentsRelations -> {
                allSearchUniscid.add(tPatentsRelations.getUniscid());
            });
        }
        if (checkBoxSearchType.contains("trademarkName")) {
            List<TTrademarks> tTrademarksList = itTrademarksMapper.selectList(
                    new QueryWrapper<TTrademarks>()
                            .like("name",keyword)
            );
            tTrademarksList.forEach(tTrademarks -> {
                allSearchUniscid.add(tTrademarks.getUniscid());
            });
        }
        if (checkBoxSearchType.contains("jobName")) {
            List<TNewJobs> tNewJobsList = itNewJobsMapper.selectList(
                    new QueryWrapper<TNewJobs>().like("title",keyword)
            );
            tNewJobsList.forEach(tNewJobs -> {
                allSearchUniscid.add(tNewJobs.getUniscid());
            });
        }
        if (checkBoxSearchType.contains("entAppAbstract")) {
            List<TEnterpriseApp> tEnterpriseAppList = itEnterpriseAppMapper.selectList(
                    new QueryWrapper<TEnterpriseApp>().like("appinfo",keyword)
            );
            tEnterpriseAppList.forEach(tEnterpriseApp -> {
                allSearchUniscid.add(tEnterpriseApp.getUniscid());
            });
        }
        if (checkBoxSearchType.contains("entIntroduction")) {
            List<TEnterpriseDesc> tEnterpriseDescList = itEnterpriseDescMapper.selectList(
                    new QueryWrapper<TEnterpriseDesc>().like("ent_desc",keyword)
            );
            tEnterpriseDescList.forEach(tEnterpriseDesc -> {
                allSearchUniscid.add(tEnterpriseDesc.getUniscid());
            });
        }
        if (checkBoxSearchType.contains("websiteAbstract") ||
            checkBoxSearchType.contains("websiteName")) {
            List<TEntWebsite> tEntWebsiteList = itEntWebsiteMapper.selectList(
                    new QueryWrapper<TEntWebsite>().like("fba_wz_mc",keyword)
            );
            tEntWebsiteList.forEach(tEntWebsite -> {
                allSearchUniscid.add(tEntWebsite.getUniscid());
            });
        }
        if (checkBoxSearchType.contains("softwareName")) {
            List<TSoftwareCopyright> tSoftwareCopyrightList =
                    itSoftwareCopyrightMapper.selectList(
                            new QueryWrapper<TSoftwareCopyright>()
                                    .like("frj_rjjc",keyword)
                    );
            tSoftwareCopyrightList.
                    forEach(tSoftwareCopyright -> {
                        allSearchUniscid.add(tSoftwareCopyright
                                .getUniscid());
                    });
        }
        if (checkBoxSearchType.contains("entname")) {
            List<TEnterpriseBasicDto> tEnterpriseBasicDtoList = itEnterpriseBasicMapper.selectList(
                    new QueryWrapper<TEnterpriseBasicDto>()
                            .like("entname",keyword)
            );
            tEnterpriseBasicDtoList.forEach(tEnterpriseBasicDto -> {
                allSearchUniscid.add(tEnterpriseBasicDto.getUniscid());
            });
        }
        Set<String> scoreSelectList = searchScore(searchParams);

        Set<String> newResult = allSearchUniscid;
        if(scoreSelectList.size()>0) {
            // 两个集合是否有交集
            newResult = com.google.common.collect.Sets.intersection(allSearchUniscid,scoreSelectList);
        }
        if (newResult.size() == 0) {
            return AjaxResult.success(Lists.newArrayList());
        }

        // 查询企业评分
        JSONObject tEvaluatingIndexJson = new JSONObject();
        List<TEvaluatingIndex> tEvaluatingIndexList = itEvaluatingIndexMapper.selectList(
                new QueryWrapper<TEvaluatingIndex>()
                        .in("uniscid",newResult)
        );
        tEvaluatingIndexList.forEach(tEvaluatingIndex -> {
            tEvaluatingIndexJson.put(tEvaluatingIndex.getUniscid(),tEvaluatingIndex);
        });
        // 查询企业APP
        List<TEnterpriseApp> tEnterpriseAppList1 = itEnterpriseAppMapper.selectList(
                new QueryWrapper<TEnterpriseApp>()
                        .in("uniscid",newResult)
                        .like("appinfo",keyword)
        );
        JSONObject tEnterpriseAppJson = processJsonData(tEnterpriseAppList1);

        // 查询企业网站
        List<TEntWebsite> tEntWebsiteList1 = itEntWebsiteMapper.selectList(
                new QueryWrapper<TEntWebsite>()
                        .in("uniscid",newResult)
                        .like("fba_wz_mc",keyword)
        );
        JSONObject tEntWebsiteJson = processJsonData(tEntWebsiteList1);

        // 查询企业商标
        List<TTrademarks> tTrademarksList1 = itTrademarksMapper.selectList(
                new QueryWrapper<TTrademarks>()
                        .like("name",keyword)
                        .in("uniscid",newResult)
        );
        JSONObject tTrademarksJson = processJsonData(tTrademarksList1);

        List<TSoftwareCopyright> tSoftwareCopyrightList1 = itSoftwareCopyrightMapper.selectList(
                new QueryWrapper<TSoftwareCopyright>()
                        .like("frj_rjjc",keyword)
                        .in("uniscid",newResult)
        );
        JSONObject tSoftwareCopyrightJson = processJsonData(tSoftwareCopyrightList1);

        List<TNewJobs> tNewJobsList1 = itNewJobsMapper.selectList(
                new QueryWrapper<TNewJobs>()
                        .like("title",keyword)
                        .in("uniscid",newResult)
        );
        JSONObject tNewJobsListJson = processJsonData(tNewJobsList1);

        List<TPatentsRelations> tPatentsRelationsList1 =itPatentsRelationsMapper.selectList(
                new QueryWrapper<TPatentsRelations>()
                        .like("brief",keyword)
                        .in("uniscid",newResult)
        );
        JSONObject tPatentsRelationsJson = processJsonData(tPatentsRelationsList1);

        // 查询企业标签
        List<TEnterpriseLabel> tEnterpriseTagList = itEnterpriseLabelMapper.selectList(
                new QueryWrapper<TEnterpriseLabel>()
                        .in("uniscid",newResult)
        );
        JSONObject tEnterpriseTagJson = processJsonData(tEnterpriseTagList);

        List<TEnterpriseDesc> tEnterpriseDescList1 = itEnterpriseDescMapper.selectList(
                new QueryWrapper<TEnterpriseDesc>()
                        .in("uniscid",newResult)
                        .like("ent_desc",keyword)
        );
        JSONObject tEnterpriseDescJson = processJsonData(tEnterpriseDescList1);

        // 根据查询到的信息，查询企业基本信息
        QueryWrapper<TEnterpriseBasicDto> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("uniscid",newResult);
        List<TEnterpriseBasicDto> tempResultList = itEnterpriseBasicMapper.selectList(queryWrapper);

        List<JSONObject> basicJsonList = com.google.common.collect.Lists.newArrayList();
        tempResultList.forEach(tEnterpriseBasicDto -> {
            tEnterpriseBasicDto.setRegcap(tEnterpriseBasicDto.getRegcap().split("\\.")[0]);

            JSONObject basicJson = convertToJSONObject(tEnterpriseBasicDto);
            String uniscid = tEnterpriseBasicDto.getUniscid();
            basicJson.put("tenterpriseLabelList",tEnterpriseTagJson.get(uniscid) == null ? new JSONArray() : tEnterpriseTagJson.get(uniscid));
            basicJson.put("tevaluatingIndex",tEvaluatingIndexJson.get(uniscid) == null ? new JSONObject() : tEvaluatingIndexJson.get(uniscid));
            basicJson.put("tentWebsiteList",tEntWebsiteJson.get(uniscid) == null ? new JSONArray() : tEntWebsiteJson.get(uniscid));
            basicJson.put("tpatentsRelationsList",tPatentsRelationsJson.get(uniscid) == null ? new JSONArray() : tPatentsRelationsJson.get(uniscid));
            basicJson.put("tnewJobsList",tNewJobsListJson.get(uniscid) == null ? new JSONArray() : tNewJobsListJson.get(uniscid));
            basicJson.put("tsoftwareCopyrightList",tSoftwareCopyrightJson.get(uniscid) == null ? new JSONArray() : tSoftwareCopyrightJson.get(uniscid));
            basicJson.put("tenterpriseAppList",tEnterpriseAppJson.get(uniscid) == null ? new JSONArray() : tEnterpriseAppJson.get(uniscid));
            basicJson.put("tenterpriseDescList",tEnterpriseDescJson.get(uniscid) == null ? new JSONArray() : tEnterpriseDescJson.get(uniscid));
            basicJson.put("ttrademarksList",tTrademarksJson.get(uniscid) == null ? new JSONArray() : tTrademarksJson.get(uniscid));
            basicJsonList.add(basicJson);
        });
        return AjaxResult.success(basicJsonList);
    }

    public static JSONObject processJsonData(List<?> dataList) {
        JSONObject jsonObject = new JSONObject();
        dataList.forEach(data -> {
            JSONObject tempJson = convertToJSONObject(data);
            String uniscid = tempJson.getString("uniscid");
            if (jsonObject.containsKey(uniscid)) {
                JSONArray jsonArray = jsonObject.getJSONArray(uniscid);
                jsonArray.add(tempJson);
                jsonObject.put(uniscid,jsonArray);
            }else {
                JSONArray jsonArray = new JSONArray();
                jsonArray.add(tempJson);
                jsonObject.put(uniscid,jsonArray);
            }
        });
        return jsonObject;
    }

    private static JSONObject convertToJSONObject(Object object) {
        return JSONObject.parseObject(JSONObject.toJSONString(object));
    }

    private Set<String> searchScore(JSONObject searchParams) {
        JSONArray entscoreChecks = searchParams.getJSONArray("entscore_checks");
        Set<String> uniscidSetScore = Sets.newHashSet();
        for (int i=0;i<entscoreChecks.size();i++) {
            String score = CheckUtils.scoreMap.get((String)entscoreChecks.get(i));
            String start = score.split("-")[0];
            String end = score.split("-")[1];
            QueryWrapper<TEvaluatingIndex> queryWrapper = new QueryWrapper<>();
            queryWrapper.between("overall",start,end);
            List<TEvaluatingIndex> tempList = itEvaluatingIndexMapper.selectList(queryWrapper);
            for(TEvaluatingIndex item: tempList) {
                uniscidSetScore.add(item.getUniscid());
            }
        }
        Set<String> otherSet=searchTag(searchParams);
        if (uniscidSetScore.size()>0 && otherSet.size()>0) {
            return com.google.common.collect.Sets.intersection(uniscidSetScore, otherSet);
        }
        if (uniscidSetScore.size()>0) {
            return uniscidSetScore;
        }
        return otherSet;
    }

    private Set<String> searchTag(JSONObject searchParams) {
        JSONArray indusChecked = searchParams.getJSONArray("indus_checked");
        QueryWrapper<TEnterpriseTag> queryWrapper = new QueryWrapper<>();
        if(indusChecked.size()>0) {
            Set<String> indusCodeSet = new HashSet<>();
            for (int i=0;i<indusChecked.size();i++) {
                JSONArray singleJson = (JSONArray)indusChecked.get(i);
                indusCodeSet.add((String)singleJson.get(2));
            }
            queryWrapper.in("orgtype",indusCodeSet);
        }
        JSONArray aeraChecked = searchParams.getJSONArray("aera_checked");
        if(aeraChecked.size()>0) {
            Set<String> aeraCodeSet = new HashSet<>();
            for (int i=0;i<aeraChecked.size();i++) {
                JSONArray singleJson = (JSONArray)aeraChecked.get(i);
                aeraCodeSet.add((String)singleJson.get(2));
            }
            queryWrapper.in("domdistrict",aeraCodeSet);
        }
        JSONArray personNum = searchParams.getJSONArray("person_nums");
        if(personNum.size()>0) {
            Set<String> personNumSet = new HashSet<>();
            for (int i=0;i<personNum.size();i++) {
                String score = CheckUtils.personNumsMap.get((String) personNum.get(i));
                personNumSet.add(score);
            }
            queryWrapper.in("entscale",personNumSet);
        }
        JSONArray enttypeChecks = searchParams.getJSONArray("enttype_checks");
        if(enttypeChecks.size()>0) {
            Set<String> enttypeSet = new HashSet<>();
            for (int i=0;i<enttypeChecks.size();i++) {
                String score = (String) enttypeChecks.get(i);
                enttypeSet.add(score);
            }
            queryWrapper.in("orgtype",enttypeSet);
        }
        Set<String> uniscidSet = Sets.newHashSet();
        if(indusChecked.size()>0 || aeraChecked.size()>0
           || personNum.size()>0 || enttypeChecks.size()>0 ) {
            List<TEnterpriseTag> tEnterpriseTagList = itEnterpriseTagMapper.selectList(queryWrapper);
            tEnterpriseTagList.forEach(tEnterpriseTag -> {
                uniscidSet.add(tEnterpriseTag.getUniscid());
            });
        }


        JSONArray esdateChecks = searchParams.getJSONArray("esdate_checks");
        Set<String> uniscidSetSecond = Sets.newHashSet();
        for (int i=0;i<esdateChecks.size();i++) {
            String score = CheckUtils.enAgesMap.get((String)esdateChecks.get(i));
            String start = score.split("-")[0];
            String end = score.split("-")[1];
            queryWrapper = new QueryWrapper<>();
            queryWrapper.between("enages",start,end);
            List<TEnterpriseTag> tempList = itEnterpriseTagMapper.selectList(queryWrapper);
            for(TEnterpriseTag item: tempList) {
                uniscidSet.add(item.getUniscid());
            }
        }
        if (uniscidSet.size()>0 && uniscidSetSecond.size()>0) {
            return com.google.common.collect.Sets.intersection(uniscidSet, uniscidSetSecond);
        }
        if (uniscidSet.size()>0) {
            return uniscidSet;
        }
        return uniscidSetSecond;
    }

    public AjaxResult searchInfoByKeywordSimple(JSONObject searchParams, int pageNum, int pageSize) {

        QueryWrapper<TEnterpriseBasicDto> queryWrapper = new QueryWrapper<>();
        if (searchParams.containsKey("keyword") && StringUtils.isNotBlank(searchParams.getString("keyword"))) {
            String keyword = searchParams.getString("keyword");
            queryWrapper.like("entname",keyword)
                    .or().eq("uniscid",keyword);
        }
        queryWrapper.orderByDesc("regcap");
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
        queryWrapper.orderByDesc("regcap");
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
        queryWrapper.orderByDesc("regcap");
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
                new QueryWrapper<TSupplierRelevance>()
                        .select("distinct entname,uniscid,relpartyp,partyid,partyname,partytyp,partystatus")
                        .eq("uniscid",uniscid));
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
        basic.setTEnterpriseTag(itEnterpriseTagMapper.selectOne(
                new QueryWrapper<TEnterpriseTag>()
                        .eq("uniscid",uniscid)
        ));
        basic.setTActualControllerList(itActualControllerMapper.selectList(
                new QueryWrapper<TActualController>().eq("uniscid",uniscid)
        ));
        basic.setTGroupRelationList(itGroupRelationMapper.selectList(
                new QueryWrapper<TGroupRelation>().eq("groupid",basic.getGrpid())
        ));
        basic.setTGroupTag(itGroupTagMapper.selectOne(
                new QueryWrapper<TGroupTag>().eq("grpid",basic.getGrpid())
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
        queryWrapper.orderByDesc("regcap");
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

    public AjaxResult getActualControllerDetail(String uniscid) {
        QueryWrapper<TEnterpriseBasicDto> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uniscid",uniscid);
        TEnterpriseBasicDto basic = itEnterpriseBasicMapper.selectOne(queryWrapper);
        basic.setRegcap(basic.getRegcap().split("\\.")[0]);
        basic.setTActualControllerList(itActualControllerMapper.selectList(
                new QueryWrapper<TActualController>().eq("uniscid",basic.getUniscid())
        ));
        return AjaxResult.success(basic);
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
        // 转化create_time
        List<TEnterpriseDataset> tEnterpriseDatasetList = pageResult.getRecords();
        for (TEnterpriseDataset tEnterpriseDataset : tEnterpriseDatasetList) {
            tEnterpriseDataset.setCreateTimeStr(DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss",tEnterpriseDataset.getCreateTime()));
        }
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
        queryWrapper.orderByDesc("regcap");

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
            basic.setTEnterpriseTag(itEnterpriseTagMapper.selectOne(
                    new QueryWrapper<TEnterpriseTag>()
                            .select("uniscid,orgtype,domdistrict,entscale_cn,enages")
                            .eq("uniscid",basic.getUniscid())
            ));
            basic.setTEnterpriseDescList(itEnterpriseDescMapper.selectList(
                    new QueryWrapper<TEnterpriseDesc>()
                            .eq("uniscid",basic.getUniscid())
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

    public AjaxResult getChangeRecords(int pageNum, int pageSize) {
        QueryWrapper<TChangeRecords> queryWrapper = new QueryWrapper<>();
        Page<TChangeRecords> page = new Page<>(pageNum,pageSize);
        Page<TChangeRecords> pageResult = itChangeRecordsMapper.selectPage(page, queryWrapper);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total",pageResult.getTotal());
        jsonObject.put("pages",pageResult.getPages());
        jsonObject.put("pageNum",pageResult.getCurrent());
        jsonObject.put("pageSize",pageResult.getSize());
        jsonObject.put("item",pageResult.getRecords());
        return AjaxResult.success(jsonObject);
    }

    public AjaxResult getTBiddingsallCache(JSONObject paramsBody, int pageNum, int pageSize) {
        QueryWrapper<TBiddingIndex> queryWrapper = new QueryWrapper<>();
        if (paramsBody.containsKey("keyword") && StringUtils.isNotBlank(paramsBody.getString("keyword"))) {
            String keyword = paramsBody.getString("keyword");
            queryWrapper.like("entname",keyword)
                    .or().eq("uniscid",keyword);
        }
        queryWrapper.orderByDesc("total_purchase_amount");

        Page<TBiddingIndex> page = new Page<>(pageNum,pageSize);
        Page<TBiddingIndex> pageResult = itBiddingIndexMapper.selectPage(page,queryWrapper);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total",pageResult.getTotal());
        jsonObject.put("pages",pageResult.getPages());
        jsonObject.put("pageNum",pageResult.getCurrent());
        jsonObject.put("pageSize",pageResult.getSize());
        jsonObject.put("item",pageResult.getRecords());
        return AjaxResult.success(jsonObject);
    }

    public AjaxResult getTBiddingsallDetail(String uniscid) {

        String entname = itBiddingIndexMapper.selectById(uniscid).getEntname();

        QueryWrapper<TBiddingsAllNew> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("ename",entname)
                .eq("bidding_role","招标人");

        Set<String> dataIdSet = Sets.newHashSet();
        List<TBiddingsAllNew> listAllBidding = itBiddingsAllNewMapper.selectList(queryWrapper);
        for (TBiddingsAllNew item: listAllBidding) {
            dataIdSet.add(item.getDataId());
        }
        List<TBiddingsAllNew> biddingEntList = itBiddingsAllNewMapper.selectList(
                new QueryWrapper<TBiddingsAllNew>()
                        .eq("bidding_role", "中标人")
                        .in("data_id", dataIdSet)
        );
        return AjaxResult.success(biddingEntList);
    }

    public AjaxResult getTBiddingsallByDataId(String dataId) {
        QueryWrapper<TBiddingsAllNew> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .select(" notice_type, proj_num, area_code, area_name, data_id, bid_amount, bidding_role, eid, ename, dt ")
                .eq("data_id",dataId);
        return AjaxResult.success(itBiddingsAllNewMapper.selectList(queryWrapper));
    }

    public AjaxResult getOtherBlackEvent(int pageNum, int pageSize){

        List<JSONObject> allBlackList = Lists.newArrayList();
        itRestrictedConsumerMapper.selectList(new QueryWrapper<>()).forEach(item -> {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("uniscid",item.getUniscid());
            jsonObject.put("blackType","限制高消费");
            jsonObject.put("blackDate",item.getFilingDate());
            jsonObject.put("entname",item.getEname());
            allBlackList.add(jsonObject);
        });

        itJudicialFreezesMapper.selectList(new QueryWrapper<>()).forEach(item -> {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("uniscid",item.getUniscid());
            jsonObject.put("blackType","司法冻结");
            jsonObject.put("blackDate",item.getLoseEfficacyDate());
            jsonObject.put("entname",item.getEname());
            allBlackList.add(jsonObject);
        });
        return AjaxResult.success(allBlackList);
    }

    public AjaxResult getBlackEvent(String uniscid){

        List<JSONObject> allBlackList = Lists.newArrayList();
        itChangeRecordsMapper.selectList(
                new QueryWrapper<TChangeRecords>().eq("uniscid",uniscid)
        ).forEach(item -> {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("uniscid",item.getUniscid());
            jsonObject.put("blackType","工商变更");
            jsonObject.put("blackDate",item.getChangeDate());
            jsonObject.put("entname",item.getEname());
            jsonObject.put("detail",item);
            allBlackList.add(jsonObject);
        });

        itAdministrativePunishmentMapper.selectList(
                new QueryWrapper<TAdministrativePunishment>().eq("uniscid",uniscid)
        ).forEach(item -> {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("uniscid",item.getUniscid());
            jsonObject.put("blackType","行政处罚");
            jsonObject.put("blackDate",item.getPublicDate());
            jsonObject.put("entname",item.getEname());
            jsonObject.put("detail",item);
            allBlackList.add(jsonObject);
        });

        itOverduetaxsMapper.selectList(
                new QueryWrapper<TOverduetaxs>().eq("uniscid",uniscid)
        ).forEach(item -> {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("uniscid",item.getUniscid());
            jsonObject.put("blackType","欠税公告");
            jsonObject.put("blackDate",item.getOverdueTime());
            jsonObject.put("entname",item.getEname());
            jsonObject.put("detail",item);
            allBlackList.add(jsonObject);
        });

        itLawsuitsRelationsMapper.selectList(
                new QueryWrapper<TLawsuitsRelations>()
                        .eq("uniscid",uniscid)
                        .last(" limit 20 ")
        ).forEach(item -> {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("uniscid",item.getUniscid());
            jsonObject.put("blackType","法律诉讼");
            jsonObject.put("blackDate",item.getPubDate());
            jsonObject.put("entname",item.getEname());
            jsonObject.put("detail",item);
            allBlackList.add(jsonObject);
        });
        return AjaxResult.success(allBlackList);
    }

//    {"textarea2":"中冶京诚工程技术有限公司\n中冶赛迪集团有限公司","check_relation":[1],"level_radio":1,"check_person_relation":[2,6],"check_buss_relation":[1],"check_potential_relation":[1]}
    public AjaxResult relaExplore(JSONObject paramsBody) {
        String textarea2=paramsBody.getString("textarea2");
        String[] entnameList = textarea2.split("\n");
        if (entnameList.length<=0) {
            return AjaxResult.error("请输入企业名称！");
        }
        if (entnameList.length>10) {
            return AjaxResult.error("企业名称不能超过10个！");
        }
        JSONArray checkRelation=paramsBody.getJSONArray("check_relation");
        JSONArray checkPersonRelation=paramsBody.getJSONArray("check_person_relation");
        JSONArray checkBussRelation=paramsBody.getJSONArray("check_buss_relation");
        JSONArray checkPotentialRelation=paramsBody.getJSONArray("check_potential_relation");
        int levelRadio=paramsBody.getIntValue("level_radio");
        if (checkRelation.size()<=0 && checkPersonRelation.size()<=0 && checkBussRelation.size()<=0 && checkPotentialRelation.size()<=0) {
            return AjaxResult.error("请选择关系类型！");
        }
        if (levelRadio<=0) {
            return AjaxResult.error("请选择关系层级！");
        }
        JSONObject jsonObject = new JSONObject();
        for (int i=0;i<checkRelation.size();i++) {
            int label = (int)checkRelation.get(i);
            if (label==1) {
                List<Map<String, Object>> sameListShareholder = itEnterpriseStockholderMapper.getSameShareholder(entnameList);
                jsonObject.put("sameListShareholder",sameListShareholder);
            }
            if (label==2) {
                List<Map<String, Object>> sameListInvestment = itEnterpriseStockholderMapper.getSameInvestment(entnameList);
                jsonObject.put("sameListInvestment",sameListInvestment);
            }
        }

        return AjaxResult.success(jsonObject);
    }
}
