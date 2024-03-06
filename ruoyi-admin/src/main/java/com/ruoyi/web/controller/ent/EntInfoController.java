package com.ruoyi.web.controller.ent;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.quartz.domain.SysJobLog;
import com.ruoyi.web.domain.*;
import com.ruoyi.web.service.EntDetailService;
import com.ruoyi.web.service.EntInfoService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

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
    @Log(title = "根据关键字搜索企业信息", businessType = BusinessType.QUERY)
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
    @Log(title = "根据关键字搜索企业信息简单版，只查询基本信息", businessType = BusinessType.QUERY)
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
    @Log(title = "根据uniscid查询企业企业股东和对外投资", businessType = BusinessType.QUERY)
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
    @Log(title = "根据uniscid查询企业供应商关系", businessType = BusinessType.QUERY)
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
    @Log(title = "查询企业实际控制人", businessType = BusinessType.QUERY)
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
    @Log(title = "查询企业实际控制人", businessType = BusinessType.QUERY)
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
    @Log(title = "查询企业一致行动人", businessType = BusinessType.QUERY)
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
    @Log(title = "查询企业一致行动人", businessType = BusinessType.QUERY)
    public AjaxResult getEntActGraph(@PathVariable String uniscid) throws Exception {
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
    @Log(title = "查询企业一致行动人", businessType = BusinessType.QUERY)
    public AjaxResult getEntActionLineDetail(@PathVariable String uniscid,@PathVariable String actrelid) throws UnsupportedEncodingException {
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
    @Log(title = "查询企业关联企业", businessType = BusinessType.QUERY)
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
    @Log(title = "查询企业关联企业", businessType = BusinessType.QUERY)
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
    @Log(title = "查询企业集团信息", businessType = BusinessType.QUERY)
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
    @Log(title = "查询企业担保信息", businessType = BusinessType.QUERY)
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
    @Log(title = "查询企业风险信息", businessType = BusinessType.QUERY)
    public AjaxResult getRiskList(@RequestBody JSONObject searchParams,
                                       @RequestParam(required = false,defaultValue = "1") int pageNum,
                                       @RequestParam(required = false,defaultValue = "10") int pageSize) {
        return entInfoService.getRiskList(searchParams,pageNum,pageSize);
    }

    @GetMapping("/getBlackList")
    @ApiResponse(code = 200, message = "success", response = TBlacklist.class)
    @ApiOperation("查询企业黑名单信息")
    @Log(title = "查询企业黑名单信息", businessType = BusinessType.QUERY)
    public AjaxResult getBlackList() {
        return entInfoService.getBlackList();
    }

    @GetMapping("/getTGreylist")
    @ApiResponse(code = 200, message = "success", response = TGreylist.class)
    @ApiOperation("查询企业灰名单信息")
    @Log(title = "查询企业灰名单信息", businessType = BusinessType.QUERY)
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
    @Log(title = "查询企业详情", businessType = BusinessType.QUERY)
    public AjaxResult getEntDetail(@PathVariable String uniscid) {
        return entInfoService.getEntDetail(uniscid);
    }


    /**
     * 创建数据集
     * @return
     */
    @PostMapping("/createDataSet")
    @ApiOperation("创建数据集")
    @Log(title = "创建数据集", businessType = BusinessType.INSERT)
    public AjaxResult createDataSet(@RequestBody JSONObject paramsBody) {

        long userId = getUserId();
        return entInfoService.createDataSet(paramsBody,userId);
    }

    @PostMapping("/exportDataset")
    @Log(title = "导出数据集", businessType = BusinessType.EXPORT)
    public void exportDataset(HttpServletResponse response, @RequestParam Map paramsBody)
    {
        List<DataSetEnt> list = entInfoService.exportDataset(paramsBody);
        ExcelUtil<DataSetEnt> util = new ExcelUtil<DataSetEnt>(DataSetEnt.class);
        util.exportExcel(response, list, "数据集名称");
    }

    /**
     * 删除数据集
     * @param datasetId
     * @return
     */
    @DeleteMapping("/deleteDataSet/{datasetId}")
    @ApiOperation("删除数据集")
    @Log(title = "删除数据集", businessType = BusinessType.DELETE)
    public AjaxResult deleteDataSet(@PathVariable String datasetId) {
        return entInfoService.deleteDataSet(datasetId);
    }

    /**
     * 获取数据集列表
     * @return
     */
    @PostMapping("/getDataSetList")
    @ApiOperation("获取数据集列表")
    @Log(title = "获取数据集列表", businessType = BusinessType.QUERY)
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
    @Log(title = "获取数据集详情", businessType = BusinessType.QUERY)
    public AjaxResult getDataSetDetail(@PathVariable String datasetId) {
        return entInfoService.getDataSetDetail(datasetId);
    }

    /**
     * 更新数据集详情
     * @param datasetId
     */
    @PostMapping("/updateDataSetDetail/{datasetId}")
    @ApiOperation("更新数据集详情")
    @Log(title = "更新数据集详情", businessType = BusinessType.UPDATE)
    public AjaxResult updateDataSetDetail(@PathVariable String datasetId,@RequestBody JSONObject paramsBody) {
        return entInfoService.updateDataSetDetail(datasetId,paramsBody);
    }

    /**
     * 获取数据集列表
     * @return
     */
    @GetMapping("/getAdministrativePunishment")
    @ApiOperation("获取行政处罚信息")
    @Log(title = "获取行政处罚信息", businessType = BusinessType.QUERY)
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
    @Log(title = "获取欠税信息", businessType = BusinessType.QUERY)
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
    @Log(title = "获取涉诉信息", businessType = BusinessType.QUERY)
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
    @Log(title = "获取变更信息", businessType = BusinessType.QUERY)
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
    @Log(title = "获取招投标信息", businessType = BusinessType.QUERY)
    public AjaxResult getTBiddingsallCache(
            @RequestBody JSONObject paramsBody,
            @RequestParam(required = false,defaultValue = "1") int pageNum,
            @RequestParam(required = false,defaultValue = "10") int pageSize) {
        return entInfoService.getTBiddingsallCache(paramsBody,pageNum,pageSize);
    }

    @GetMapping("/getTBiddingsallDetail/{uniscid}")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "uniscid", value = "uniscid", dataType = "String"),
            }
    )
    @ApiOperation("获取招投标信息")
    @Log(title = "获取招投标信息", businessType = BusinessType.QUERY)
    @ApiResponse(code = 200, message = "success", response = TBiddingsall.class)
    public AjaxResult getTBiddingsallDetail(@PathVariable String uniscid) {
        return entInfoService.getTBiddingsallDetail(uniscid);
    }

    @GetMapping("/getTBiddingsallByDataId/{dataId}")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "dataId", value = "dataId", dataType = "String"),
            }
    )
    @ApiOperation("获取招投标信息")
    @ApiResponse(code = 200, message = "success", response = TBiddingsall.class)
    @Log(title = "获取招投标信息", businessType = BusinessType.QUERY)
    public AjaxResult getTBiddingsallByDataId(@PathVariable String dataId) {
        return entInfoService.getTBiddingsallByDataId(dataId);
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
    @Log(title = "获取其他黑名单信息", businessType = BusinessType.QUERY)
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
    @Log(title = "获取企业黑名单信息", businessType = BusinessType.QUERY)
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
    @Log(title = "关联探索", businessType = BusinessType.QUERY)
    public AjaxResult relaExplore(@RequestBody JSONObject paramsBody) {
        return entInfoService.relaExplore(paramsBody);
    }
}
