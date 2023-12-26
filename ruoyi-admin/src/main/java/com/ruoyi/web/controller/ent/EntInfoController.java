package com.ruoyi.web.controller.ent;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.web.domain.*;
import com.ruoyi.web.service.EntDetailService;
import com.ruoyi.web.service.EntInfoService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("企业信息管理")
@RestController
@RequestMapping("/entInfo")
public class EntInfoController extends BaseController {


    @Autowired
    private EntInfoService entInfoService;

    @Autowired
    private EntDetailService entDetailService;


    /**
     * 根据关键字搜索企业信息
     * @param searchParams
     * @param pageNum
     * @param pageSize
     * @return
     */
    @PostMapping("/searchInfoByKeyword")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "searchParams", value = "搜索参数", dataType = "JSONObject"),
                    @ApiImplicitParam(name = "pageNum", value = "页码", required = false, dataType = "int"),
                    @ApiImplicitParam(name = "pageSize", value = "每页数量", required = false, dataType = "int")
            }
    )
    @ApiResponse(code = 200, message = "success", response = TEnterpriseBasicDto.class)
    @ApiOperation("根据关键字搜索企业信息")
    public AjaxResult searchInfoByKeyword(@RequestBody JSONObject searchParams,
                                          @RequestParam(required = false,defaultValue = "1") int pageNum,
                                          @RequestParam(required = false,defaultValue = "10") int pageSize) {
        return entInfoService.searchInfoByKeyword(searchParams,pageNum,pageSize);
    }

    /**
     * 根据关键字搜索企业信息简单版，只查询基本信息
     * @param searchParams
     * @param pageNum
     * @param pageSize
     * @return
     */

    @PostMapping("/searchInfoByKeywordSimple")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "searchParams", value = "搜索参数", dataType = "JSONObject"),
                    @ApiImplicitParam(name = "pageNum", value = "页码", required = false, dataType = "int"),
                    @ApiImplicitParam(name = "pageSize", value = "每页数量", required = false, dataType = "int")
            }
    )
    @ApiResponse(code = 200, message = "success", response = TEnterpriseBasicDto.class)
    @ApiOperation("根据关键字搜索企业信息简单版，只查询基本信息")
    public AjaxResult searchInfoByKeywordSimple(@RequestBody JSONObject searchParams,
                                          @RequestParam(required = false,defaultValue = "1") int pageNum,
                                          @RequestParam(required = false,defaultValue = "10") int pageSize) {
        return entInfoService.searchInfoByKeywordSimple(searchParams,pageNum,pageSize);
    }

    /**
     * 根据uniscid查询企业企业股东和对外投资
     * @param uniscid
     * @return
     */
    @GetMapping("/getShareHolderAndInvestment/{uniscid}")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "uniscid", value = "uniscid", dataType = "String"),
            }
    )
    @ApiResponse(code = 200, message = "success", response = TEnterpriseStockholder.class)
    @ApiOperation("根据uniscid查询企业企业股东和对外投资")
    public AjaxResult getShareHolderAndInvestment(@PathVariable String uniscid) {
        return entInfoService.getShareHolderAndInvestment(uniscid);
    }

    /**
     * 根据uniscid查询企业供应商关系
     * @param uniscid
     * @return
     */
    @GetMapping("/getSupplierRelation/{uniscid}")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "uniscid", value = "uniscid", dataType = "String"),
            }
    )
    @ApiResponse(code = 200, message = "success", response = TEnterpriseStockholder.class)
    @ApiOperation("根据uniscid查询企业供应商关系")
    public AjaxResult getSupplierRelation(@PathVariable String uniscid) {
        return entInfoService.getSupplierRelation(uniscid);
    }

    /**
     * 查询企业实际控制人
     */
    @PostMapping("/getActualController")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "searchParams", value = "搜索参数", dataType = "JSONObject"),
                    @ApiImplicitParam(name = "pageNum", value = "页码", required = false, dataType = "int"),
                    @ApiImplicitParam(name = "pageSize", value = "每页数量", required = false, dataType = "int")
            }
    )
    @ApiResponse(code = 200, message = "success", response = TEnterpriseBasicDto.class)
    @ApiOperation("查询企业实际控制人")
    public AjaxResult getActualController(@RequestBody JSONObject searchParams,
                                          @RequestParam(required = false,defaultValue = "1") int pageNum,
                                          @RequestParam(required = false,defaultValue = "10") int pageSize) {
        return entInfoService.getActualController(searchParams,pageNum,pageSize);
    }

    @GetMapping("/getActualControllerDetail/{uniscid}")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "uniscid", value = "uniscid", dataType = "String"),
            }
    )
    @ApiResponse(code = 200, message = "success", response = TEnterpriseBasicDto.class)
    @ApiOperation("查询企业实际控制人")
    public AjaxResult getActualControllerDetail(@PathVariable String uniscid) {
        return entInfoService.getActualControllerDetail(uniscid);
    }

    @PostMapping("/getActionGraph")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "searchParams", value = "搜索参数", dataType = "JSONObject"),
                    @ApiImplicitParam(name = "pageNum", value = "页码", required = false, dataType = "int"),
                    @ApiImplicitParam(name = "pageSize", value = "每页数量", required = false, dataType = "int")
            }
    )
    @ApiResponse(code = 200, message = "success", response = TEnterpriseBasicDto.class)
    @ApiOperation("查询企业一致行动人")
    public AjaxResult getActionGraph(@RequestBody JSONObject searchParams,
                                          @RequestParam(required = false,defaultValue = "1") int pageNum,
                                          @RequestParam(required = false,defaultValue = "10") int pageSize) {
        return entInfoService.getActionGraph(searchParams,pageNum,pageSize);
    }

    @GetMapping("/getEntActGraph/{uniscid}")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "uniscid", value = "uniscid", dataType = "String"),
            }
    )
    @ApiResponse(code = 200, message = "success", response = TActionPerson.class)
    @ApiOperation("查询企业一致行动人")
    public AjaxResult getEntActGraph(@PathVariable String uniscid) {
        return entInfoService.getEntActGraph(uniscid);
    }

    @GetMapping("/getEntActionLineDetail/{uniscid}/{actrelid}")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "uniscid", value = "uniscid", dataType = "String"),
                    @ApiImplicitParam(name = "actrelid", value = "actrelid", dataType = "String"),
            }
    )
    @ApiResponse(code = 200, message = "success", response = TActionPerson.class)
    @ApiOperation("查询企业一致行动人")
    public AjaxResult getEntActionLineDetail(@PathVariable String uniscid,@PathVariable String actrelid) {
        return entInfoService.getEntActionLineDetail(uniscid,actrelid);
    }

    /**
     * 查询企业关联企业
     */
    @PostMapping("/getRelatedEnterprise")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "searchParams", value = "搜索参数", dataType = "JSONObject"),
                    @ApiImplicitParam(name = "pageNum", value = "页码", required = false, dataType = "int"),
                    @ApiImplicitParam(name = "pageSize", value = "每页数量", required = false, dataType = "int")
            }
    )
    @ApiResponse(code = 200, message = "success", response = TEnterpriseBasicDto.class)
    @ApiOperation("查询企业关联企业")
    public AjaxResult getRelatedEnterprise(@RequestBody JSONObject searchParams,
                                          @RequestParam(required = false,defaultValue = "1") int pageNum,
                                          @RequestParam(required = false,defaultValue = "10") int pageSize) {
        return entInfoService.getRelatedEnterprise(searchParams,pageNum,pageSize);
    }

    @GetMapping("/getRelaEntList/{uniscid}")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "uniscid", value = "uniscid", dataType = "String"),
            }
    )
    @ApiResponse(code = 200, message = "success", response = TSupplierRelevance.class)
    @ApiOperation("查询企业关联企业")
    public AjaxResult getRelaEntList(@PathVariable String uniscid) {
        return entInfoService.getRelaEntList(uniscid);
    }

    @PostMapping("/getGroupList")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "searchParams", value = "搜索参数", dataType = "JSONObject"),
                    @ApiImplicitParam(name = "pageNum", value = "页码", required = false, dataType = "int"),
                    @ApiImplicitParam(name = "pageSize", value = "每页数量", required = false, dataType = "int")
            }
    )
    @ApiResponse(code = 200, message = "success", response = TEnterpriseBasicDto.class)
    @ApiOperation("查询企业集团信息")
    public AjaxResult getGroupList(@RequestBody JSONObject searchParams,
                                           @RequestParam(required = false,defaultValue = "1") int pageNum,
                                           @RequestParam(required = false,defaultValue = "10") int pageSize) {
        return entInfoService.getGroupList(searchParams,pageNum,pageSize);
    }

    @PostMapping("/getGuaranteeList")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "searchParams", value = "搜索参数", dataType = "JSONObject"),
                    @ApiImplicitParam(name = "pageNum", value = "页码", required = false, dataType = "int"),
                    @ApiImplicitParam(name = "pageSize", value = "每页数量", required = false, dataType = "int")
            }
    )
    @ApiResponse(code = 200, message = "success", response = TEnterpriseBasicDto.class)
    @ApiOperation("查询企业担保信息")
    public AjaxResult getGuaranteeList(@RequestBody JSONObject searchParams,
                                   @RequestParam(required = false,defaultValue = "1") int pageNum,
                                   @RequestParam(required = false,defaultValue = "10") int pageSize) {
        return entInfoService.getGuaranteeList(searchParams,pageNum,pageSize);
    }

    @PostMapping("/getRiskList")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "searchParams", value = "搜索参数", dataType = "JSONObject"),
                    @ApiImplicitParam(name = "pageNum", value = "页码", required = false, dataType = "int"),
                    @ApiImplicitParam(name = "pageSize", value = "每页数量", required = false, dataType = "int")
            }
    )
    @ApiResponse(code = 200, message = "success", response = TEnterpriseBasicDto.class)
    @ApiOperation("查询企业风险信息")
    public AjaxResult getRiskList(@RequestBody JSONObject searchParams,
                                       @RequestParam(required = false,defaultValue = "1") int pageNum,
                                       @RequestParam(required = false,defaultValue = "10") int pageSize) {
        return entInfoService.getRiskList(searchParams,pageNum,pageSize);
    }

    @GetMapping("/getBlackList")
    @ApiResponse(code = 200, message = "success", response = TBlacklist.class)
    @ApiOperation("查询企业黑名单信息")
    public AjaxResult getBlackList() {
        return entInfoService.getBlackList();
    }

    @GetMapping("/getTGreylist")
    @ApiResponse(code = 200, message = "success", response = TGreylist.class)
    @ApiOperation("查询企业灰名单信息")
    public AjaxResult getTGreylist(
            @RequestParam(required = false,defaultValue = "1") int pageNum,
            @RequestParam(required = false,defaultValue = "10") int pageSize) {
        return entInfoService.getTGreylist(pageNum,pageSize);
    }

    @GetMapping("/getEntDetail/{uniscid}")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "uniscid", value = "uniscid", dataType = "String"),
            }
    )
    @ApiResponse(code = 200, message = "success", response = TEnterpriseBasicDto.class)
    @ApiOperation("查询企业详情")
    public AjaxResult getEntDetail(@PathVariable String uniscid) {
        return entInfoService.getEntDetail(uniscid);
    }


    /**
     * 创建数据集
     * @return
     */
    @PostMapping("/createDataSet")
    @ApiOperation("创建数据集")
    public AjaxResult createDataSet(@RequestBody JSONObject paramsBody) {

        long userId = getUserId();
        return entInfoService.createDataSet(paramsBody,userId);
    }

    /**
     * 删除数据集
     * @param datasetId
     * @return
     */
    @DeleteMapping("/deleteDataSet/{datasetId}")
    @ApiOperation("删除数据集")
    public AjaxResult deleteDataSet(@PathVariable String datasetId) {
        return entInfoService.deleteDataSet(datasetId);
    }

    /**
     * 获取数据集列表
     * @return
     */
    @PostMapping("/getDataSetList")
    @ApiOperation("获取数据集列表")
    public AjaxResult getDataSetList(@RequestBody JSONObject paramsBody,
                                     @RequestParam(required = false,defaultValue = "1") int pageNum,
                                     @RequestParam(required = false,defaultValue = "10") int pageSize) {
        long userId = getUserId();
        return entInfoService.getDataSetList(userId,paramsBody,pageNum,pageSize);
    }

    /**
     * 获取数据集详情
     * @param datasetId
     */
    @GetMapping("/getDataSetDetail/{datasetId}")
    @ApiOperation("获取数据集详情")
    public AjaxResult getDataSetDetail(@PathVariable String datasetId) {
        return entInfoService.getDataSetDetail(datasetId);
    }

    /**
     * 更新数据集详情
     * @param datasetId
     */
    @PostMapping("/updateDataSetDetail/{datasetId}")
    @ApiOperation("更新数据集详情")
    public AjaxResult updateDataSetDetail(@PathVariable String datasetId,@RequestBody JSONObject paramsBody) {
        return entInfoService.updateDataSetDetail(datasetId,paramsBody);
    }

    /**
     * 获取数据集列表
     * @return
     */
    @GetMapping("/getAdministrativePunishment")
    @ApiOperation("获取行政处罚信息")
    public AjaxResult getDataSetList(
                                     @RequestParam(required = false,defaultValue = "1") int pageNum,
                                     @RequestParam(required = false,defaultValue = "10") int pageSize) {
        return entInfoService.getAdministrativePunishment(pageNum,pageSize);
    }

    /**
     * 获取数据集列表
     * @return
     */
    @GetMapping("/getOverduetaxs")
    @ApiOperation("获取欠税信息")
    public AjaxResult getOverduetaxs(
            @RequestParam(required = false,defaultValue = "1") int pageNum,
            @RequestParam(required = false,defaultValue = "10") int pageSize) {
        return entInfoService.getOverduetaxs(pageNum,pageSize);
    }

    /**
     * 获取数据集列表
     * @return
     */
    @GetMapping("/getLawsuitsRelations")
    @ApiOperation("获取涉诉信息")
    public AjaxResult getLawsuitsRelations(
            @RequestParam(required = false,defaultValue = "1") int pageNum,
            @RequestParam(required = false,defaultValue = "10") int pageSize) {
        return entInfoService.getLawsuitsRelations(pageNum,pageSize);
    }

    /**
     * 获取数据集列表
     * @return
     */
    @GetMapping("/getChangeRecords")
    @ApiOperation("获取变更信息")
    public AjaxResult getChangeRecords(
            @RequestParam(required = false,defaultValue = "1") int pageNum,
            @RequestParam(required = false,defaultValue = "10") int pageSize) {
        return entInfoService.getChangeRecords(pageNum,pageSize);
    }

    @PostMapping("/getTBiddingsallCache")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "paramsBody", value = "搜索参数", dataType = "JSONObject"),
                    @ApiImplicitParam(name = "pageNum", value = "页码", required = false, dataType = "int"),
                    @ApiImplicitParam(name = "pageSize", value = "每页数量", required = false, dataType = "int")
            }
    )
    @ApiOperation("获取招投标信息")
    @ApiResponse(code = 200, message = "success", response = TBiddingsall.class)
    public AjaxResult getTBiddingsallCache(
            @RequestBody JSONObject paramsBody,
            @RequestParam(required = false,defaultValue = "1") int pageNum,
            @RequestParam(required = false,defaultValue = "10") int pageSize) {
        return entInfoService.getTBiddingsallCache(paramsBody,pageNum,pageSize);
    }


    @GetMapping("/getOtherBlackEvent")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "paramsBody", value = "搜索参数", dataType = "JSONObject"),
                    @ApiImplicitParam(name = "pageNum", value = "页码", required = false, dataType = "int"),
                    @ApiImplicitParam(name = "pageSize", value = "每页数量", required = false, dataType = "int")
            }
    )
    @ApiOperation("获取其他黑名单信息")
    @ApiResponse(code = 200, message = "success", response = TBlacklist.class)
    public AjaxResult getOtherBlackEvent(
            @RequestParam(required = false,defaultValue = "1") int pageNum,
            @RequestParam(required = false,defaultValue = "10") int pageSize) {
        return entInfoService.getOtherBlackEvent(pageNum,pageSize);
    }

    @GetMapping("/getBlackEvent/{uniscid}")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "uniscid", value = "uniscid", dataType = "String"),
            }
    )
    @ApiOperation("获取企业黑名单信息")
    @ApiResponse(code = 200, message = "success", response = TBlacklist.class)
    public AjaxResult getBlackEvent(@PathVariable String uniscid) {
        return entInfoService.getBlackEvent(uniscid);
    }

    @PostMapping("/relaExplore")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "paramsBody", value = "搜索参数", dataType = "JSONObject"),
            }
    )
    @ApiOperation("关联探索")
    @ApiResponse(code = 200, message = "success", response = JSONObject.class)
    public AjaxResult relaExplore(@RequestBody JSONObject paramsBody) {
        return entInfoService.relaExplore(paramsBody);
    }
}
