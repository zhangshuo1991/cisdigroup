<template>
  <section>
    <div style="margin: auto;padding-left: 15px;padding-top: 15px;min-height: 780px;background-color: #F6F7FA;padding-bottom: 20px">
      <div id="s-ent-header" style="background-color: white;margin-right: 15px">
        <!--<img :src="background_image" style="width: 100%;height: 380px;">-->
        <div style="color: black;width: 100%;">
          <table style="width: 100%;">
            <tbody>
              <tr>
                <td style="width: 100%;">
                  <div style="padding-top: 10px">
                      <span class="c-ent-title">
                        {{ entinfo.entname }}
                        <span v-show="entinfo.uniscid">{{ '（'+ entinfo.uniscid +'）' }}</span>
                        <span style="color: #8AD8A9;font-weight: bolder">
                          （{{ entinfo.entstatusCn }}）
                        </span>
                      </span>
                  </div>
                  <div class="c-ent-basic" style="width: 100%;padding-left: 2px;padding-top: 24px;">
                    <div style="min-height: 119px;margin-bottom: 5px;background-color: #f7f7f7;margin-top: 10px;padding-top: 5px;padding-left: 10px">
                      <table style="width: 100%;padding-left: 5px;">
                        <tbody>
                        <tr>
                          <td class="c-table-basic-title">企业法人</td>
                          <td class="c-table-basic-text">{{ entinfo.lerepname }}</td>
                          <td class="c-table-basic-title">注册资金</td>
                          <td class="c-table-basic-text">
                            <span>{{ entinfo.regcap }}&nbsp{{ entinfo.regcapcurCn }}</span>
                          </td>
                          <td class="c-table-basic-title">所属地区</td>
                          <td class="c-table-basic-text">
                            {{ enterpriseTag.enprovCn }}-{{ enterpriseTag.encityCn }}-{{ enterpriseTag.domdistrictCn }}
                          </td>
                        </tr>
                        <tr>
                          <td class="c-table-basic-title">经营年限</td>
                          <td class="c-table-basic-text">{{ enterpriseTag.enages }}年</td>
                          <td class="c-table-basic-title">是否上市</td>
                          <td class="c-table-basic-text">{{ enterpriseTag.islist === 1 ? '是':'否' }}</td>
                          <td class="c-table-basic-title">是否高新</td>
                          <td class="c-table-basic-text">否</td>
                        </tr>
                        <tr>
                          <td class="c-table-basic-title">主营行业</td>
                          <td class="c-table-basic-text">{{ entinfo.industryname }}</td>
                          <td class="c-table-basic-title">组织形式</td>
                          <td class="c-table-basic-text">{{ entinfo.orgtypeCn }}</td>
                          <td class="c-table-basic-title">企业规模</td>
                          <td class="c-table-basic-text">{{ enterpriseTag.entscaleCn }}</td>
                        </tr>
                        <tr>
                          <td class="c-table-basic-title">企业简介</td>
                          <td colspan="5" class="c-table-basic-text">
                            <div style="line-height: 20px">
                              {{ entinfo.opscope.length>47?entinfo.opscope.substr(0,47)+'...':entinfo.opscope }}
                              <el-button v-show="entinfo.opscope.length>47" type="text" style="color: #0070C5" @click="viewIntro(entinfo.opscope)">更多</el-button>
                            </div>
                          </td>
                        </tr>
                        </tbody>
                      </table>
                    </div>
                  </div>
                </td>
              </tr>
              <tr>
                <td id="s-footer-td">
                  <div v-if="enterpriseTag.ctrlid" class="c-other-info" style="width: 170px;padding-right: 4px">
                    <div style="float: left;width: 30%;text-align: center;padding-top: 5px">
                      <svg-icon icon-class="shijikongzhiren" class="c-other-info-icon" /><br>
                      <div style="font-size: 7px;color: #0070C4;font-weight: bolder;margin-top: 1px">实控人</div>
                    </div>
                    <div style="float: left;width: 70%;">
                      <div style="height: 31px;">
                        <div
                          class="c-ent-taginfo"
                          style="font-size: 12px;line-height: 15px;padding-top: 4px"
                          @click="viewCtrlGraph"
                        >
                          {{ ctrlInfo.ctrlname.length>17?ctrlInfo.ctrlname.substr(0,17)+'...':ctrlInfo.ctrlname }}
                        </div>
                      </div>
                      <div style="height: 30px;">
                        <span style="font-size: 7px;">类型：{{ ctrlInfo.isidrctrl }}</span>
                      </div>
                    </div>
                  </div>
                  <div v-else class="c-other-info-gray" style="width: 85px;">
                    <div style="width: 100%;text-align: center;padding-top: 7px;color: #BFBFBF">
                      <div style="height: 32px;">
                        <svg-icon icon-class="shijikongzhiren" class="c-other-info-icon" style="font-size: 27px" />
                        <div style="font-size: 7px;font-weight: bolder">实控人</div>
                      </div>
                    </div>
                  </div>
                  <div v-if="enterpriseTag.groupid" class="c-other-info" style="margin-left: 25px;padding-right: 4px;width: 15%">
                    <div style="float: left;width: 30%;text-align: center;padding-top: 7px">
                      <svg-icon icon-class="jituankehu_one" class="c-other-info-icon" style="font-size: 27px" /><br>
                      <div style="font-size: 7px;color: #0070C4;font-weight: bolder;margin-top: 1px">集团</div>
                    </div>
                    <div style="float: left;width: 70%;">
                      <div style="height: 31px;">
                        <div
                          v-if="grpid"
                          class="c-ent-taginfo"
                          style="font-size: 12px;line-height: 15px;padding-top: 4px"
                          @click="viewGroupGraph"
                        >
                          {{ grpname }}
                        </div>
                      </div>
                      <div style="height: 30px;">
                          <span
                            style="font-size: 7px;"
                          >成员：{{ mbrCount }}</span>
                      </div>
                    </div>
                  </div>
                  <div v-else class="c-other-info-gray" style="width: 85px;margin-left: 25px">
                    <div style="width: 100%;text-align: center;padding-top: 7px;color: #BFBFBF">
                      <div style="height: 32px;">
                        <svg-icon icon-class="jituankehu_one" class="c-other-info-icon" style="font-size: 27px" />
                        <div style="font-size: 7px;font-weight: bolder">集团</div>
                      </div>
                    </div>
                  </div>
                  <div class="c-other-info" style="width:85px;margin-left: 25px;">
                    <div style="width: 100%;text-align: center;padding-top: 5px">
                      <div style="height: 32px;">
                        <svg-icon icon-class="guquanchuantou_one" class="c-other-info-icon" />
                        <div style="font-size: 7px;color: #0070C4;font-weight: bolder;" class="c-ent-taginfo" @click="viewStockGraph">股权穿透</div>
                      </div>
                    </div>
                  </div>
                  <div class="c-other-info" style="width:85px;margin-left: 25px;">
                    <div style="width: 100%;text-align: center;padding-top: 5px">
                      <div style="height: 32px;">
                        <svg-icon icon-class="guanlianfang_one" class="c-other-info-icon" />
                        <div style="font-size: 7px;color: #0070C4;font-weight: bolder;" class="c-ent-taginfo"
                             @click="viewRelaDetailInfo">关联方</div>
                      </div>
                    </div>
                  </div>
                  <div class="c-other-info-gray" style="width: 85px;margin-left: 25px">
                    <div style="width: 100%;text-align: center;padding-top: 7px;color: #BFBFBF">
                      <div style="height: 32px;">
                        <svg-icon icon-class="yizhixingdongren" class="c-other-info-icon" style="font-size: 27px" />
                        <div style="font-size: 7px;font-weight: bolder">一致行动人</div>
                      </div>
                    </div>
                  </div>
                  <div class="c-other-info-gray" style="width: 85px;margin-left: 25px">
                    <div style="width: 100%;text-align: center;padding-top: 7px;color: #BFBFBF">
                      <div style="height: 32px;">
                        <svg-icon icon-class="gongyinglian" class="c-other-info-icon" style="font-size: 27px" />
                        <div style="font-size: 7px;font-weight: bolder">供应链</div>
                      </div>
                    </div>
                  </div>
                  <div class="c-other-info-gray" style="width: 85px;margin-left: 25px">
                    <div style="width: 100%;text-align: center;padding-top: 7px;color: #BFBFBF">
                      <div style="height: 32px;">
                        <svg-icon icon-class="chanyelian" class="c-other-info-icon" style="font-size: 27px" />
                        <div style="font-size: 7px;font-weight: bolder">资金链</div>
                      </div>
                    </div>
                  </div>
                  <div class="c-other-info-gray" style="width: 85px;margin-left: 25px">
                    <div style="width: 100%;text-align: center;padding-top: 7px;color: #BFBFBF">
                      <div style="height: 32px;">
                        <svg-icon icon-class="yichangdanbao" class="c-other-info-icon" style="font-size: 27px" />
                        <div style="font-size: 7px;font-weight: bolder">异常担保</div>
                      </div>
                    </div>
                  </div>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
      <div style="margin-top: 10px;min-height: 355px;margin-right: 15px;background-color: white;border: 1px solid #F2F2F2">
        <el-tabs v-model="activeName" type="card" @tab-click="handleClick" style="background-color: #F2F2F2">
          <el-tab-pane label="业务信息" name="yewu">
            <div style="background-color: white;width: 100%;padding: 15px;">
                <div class="c-data-h1">
                  合同信息表
                </div>
                <div style="padding: 12px;overflow-y: auto">
                  <table class="c-base-table" style="width: 120%;background-color: white;">
                    <thead>
                      <tr>
                        <th class="c-th-header">供应商ID</th>
                        <th class="c-th-header">供应商名称</th>
                        <th class="c-th-header">合同编号</th>
                        <th class="c-th-header">合同名称</th>
                        <th class="c-th-header">合同签订时间</th>
                        <th class="c-th-header">合同到期时间</th>
                        <th class="c-th-header">合同金额</th>
                        <th class="c-th-header">交付时间</th>
                        <th class="c-th-header">付款方式</th>
                        <th class="c-th-header">合作状态</th>
                        <th class="c-th-header">服务形式</th>
                        <th class="c-th-header">产品规格</th>
                        <th class="c-th-header">产品数量</th>
                        <th class="c-th-header">是否延期</th>
                        <th class="c-th-header">数据日期</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr v-for="item in 10" :key="item">
                        <td class="c-td-text">1000000001</td>
                        <td class="c-td-text">北京中科三方网络技术有限公司</td>
                        <td class="c-td-text">HT202001010001</td>
                        <td class="c-td-text">北京中科三方网络技术有限公司</td>
                        <td class="c-td-text">2020-01-01</td>
                        <td class="c-td-text">2020-12-31</td>
                        <td class="c-td-text">1000000</td>
                        <td class="c-td-text">2020-01-01</td>
                        <td class="c-td-text">现金</td>
                        <td class="c-td-text">正常</td>
                        <td class="c-td-text">软件开发</td>
                        <td class="c-td-text">软件开发</td>
                        <td class="c-td-text">100</td>
                        <td class="c-td-text">否</td>
                        <td class="c-td-text">2020-01-01</td>
                      </tr>
                    </tbody>
                  </table>
                </div>
            </div>
          </el-tab-pane>
          <el-tab-pane label="履约信息" name="lvyue">
            <div style="background-color: white;width: 100%;padding: 15px;">
              <div class="c-data-h1">
                履约信息汇总表
              </div>
              <div style="padding: 12px;">
                <div style="padding: 12px;overflow-y: auto">
                  <table class="c-base-table" style="width: 120%;background-color: white;">
                    <thead>
                      <tr>
                        <th class="c-th-header">供应商ID</th>
                        <th class="c-th-header">供应商名称</th>
                        <th class="c-th-header">历史是否出现过延期交付</th>
                        <th class="c-th-header">延期交付次数</th>
                        <th class="c-th-header">产品质量不合格次数</th>
                        <th class="c-th-header">产品或服务被投诉次数</th>
                        <th class="c-th-header">是否出现过安全生产事故</th>
                        <th class="c-th-header">数据日期</th>
                        <th class="c-th-header">操作</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr v-for="item in 10" :key="item">
                        <td class="c-td-text">1000000001</td>
                        <td class="c-td-text">中国五矿集团有限公司</td>
                        <td class="c-td-text">否</td>
                        <td class="c-td-text">0</td>
                        <td class="c-td-text">0</td>
                        <td class="c-td-text">0</td>
                        <td class="c-td-text">否</td>
                        <td class="c-td-text">2020-01-01</td>
                        <td class="c-td-text">
                          <el-button type="text" style="color: #0070C5">详情</el-button>
                        </td>
                      </tr>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </el-tab-pane>
          <el-tab-pane label="绩效评价" name="jixiao">
            <div style="background-color: white;width: 100%;padding: 15px;">
              <div class="c-data-h1">
                绩效评价信息
              </div>
              <div style="padding: 12px;">
                <div style="padding: 12px;overflow-y: auto">
                  <table class="c-base-table" style="width: 120%;background-color: white;">
                    <thead>
                      <tr>
                        <th class="c-th-header">供应商ID</th>
                        <th class="c-th-header">供应商名称</th>
                        <th class="c-th-header">内部管理评价</th>
                        <th class="c-th-header">质量管理评价</th>
                        <th class="c-th-header">进度管理评价</th>
                        <th class="c-th-header">成本管理评价</th>
                        <th class="c-th-header">安全文明评价</th>
                        <th class="c-th-header">沟通协调评价</th>
                        <th class="c-th-header">售后服务评价</th>
                        <th class="c-th-header">绩效评价分数</th>
                        <th class="c-th-header">评价日期</th>
                        <th class="c-th-header">操作</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr v-for="item in 4" :key="item">
                        <td class="c-td-text">1000000001</td>
                        <td class="c-td-text">中国五矿集团有限公司</td>
                        <td class="c-td-text">优秀</td>
                        <td class="c-td-text">良好</td>
                        <td class="c-td-text">合格</td>
                        <td class="c-td-text">优秀</td>
                        <td class="c-td-text">优秀</td>
                        <td class="c-td-text">优秀</td>
                        <td class="c-td-text">优秀</td>
                        <td class="c-td-text">优秀</td>
                        <td class="c-td-text">2020-01-01</td>
                        <td class="c-td-text">
                          <el-button type="text" style="color: #0070C5" size="mini">详情</el-button>
                        </td>
                      </tr>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </el-tab-pane>
          <el-tab-pane label="合作信息" name="hezuo">
            <div style="background-color: white;width: 100%;padding: 15px;">
              <div class="c-data-h1">
                合作信息表
              </div>
              <div style="padding: 12px;">
                <div style="padding: 12px;overflow-y: auto">
                  <table class="c-base-table" style="width: 120%;background-color: white;">
                    <thead>
                      <tr>
                        <th class="c-th-header">供应商ID</th>
                        <th class="c-th-header">供应商名称</th>
                        <th class="c-th-header">首次申请时间</th>
                        <th class="c-th-header">首次合作时间</th>
                        <th class="c-th-header">首次纳入供应商名单时间</th>
                        <th class="c-th-header">最后一次纳入供应商名单时间</th>
                        <th class="c-th-header">当前合作时长</th>
                        <th class="c-th-header">合作主要项目(内容)</th>
                        <th class="c-th-header">合作总金额(人民币)</th>
                        <th class="c-th-header">最近三年合作总金额(人民币)</th>
                        <th class="c-th-header">当前合作状态</th>
                        <th class="c-th-header">数据日期</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr v-for="item in 4" :key="item">
                        <td class="c-td-text">1000000001</td>
                        <td class="c-td-text">中国五矿集团有限公司</td>
                        <td class="c-td-text">2020-01-01</td>
                        <td class="c-td-text">2020-01-01</td>
                        <td class="c-td-text">2020-01-01</td>
                        <td class="c-td-text">2020-01-01</td>
                        <td class="c-td-text">1年</td>
                        <td class="c-td-text">软件开发</td>
                        <td class="c-td-text">1000000</td>
                        <td class="c-td-text">1000000</td>
                        <td class="c-td-text">正常</td>
                        <td class="c-td-text">2020-01-01</td>
                      </tr>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </el-tab-pane>
          <el-tab-pane label="财务信息" name="caiwu">
            <div style="background-color: white;width: 100%;padding: 15px;">
              <el-tabs v-model="activeNameTwo">
                <el-tab-pane label="资产负债表（单位:人民币元）" name="zichan">
                  <table v-if="tlistedCompanyBalanceList.length>0" class="c-base-table" style="width: 100%;margin-top: 5px">
                    <thead>
                    <tr>
                      <td class="c-th-header">序号</td>
                      <td class="c-th-header"></td>
                      <td v-for="(item,index) in tlistedCompanyBalanceList" :key="index" class="c-th-header">
                        {{ item.rptDate }}
                      </td>
                    </tr>
                    <tr>
                      <td class="c-th-header">1</td>
                      <td class="c-th-header">企业id</td>
                      <td class="c-th-header">{{ entinfo.uniscid }}</td>
                      <td class="c-th-header">{{ entinfo.uniscid }}</td>
                      <td class="c-th-header">{{ entinfo.uniscid }}</td>
                      <td class="c-th-header">{{ entinfo.uniscid }}</td>
                      <td class="c-th-header">{{ entinfo.uniscid }}</td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                      <td class="c-td-text">2</td>
                      <td class="c-td-text">企业名称</td>
                      <td class="c-td-text">{{ entinfo.entname }}</td>
                      <td class="c-td-text">{{ entinfo.entname }}</td>
                      <td class="c-td-text">{{ entinfo.entname }}</td>
                      <td class="c-td-text">{{ entinfo.entname }}</td>
                      <td class="c-td-text">{{ entinfo.entname }}</td>
                    </tr>
                    <tr v-for="(index_item,index) in balance_listed_index" :key="index">
                      <td class="c-td-text">
                        {{ index+3 }}
                      </td>
                      <td class="c-td-text">
                        {{ index_item.label }}
                      </td>
                      <td v-for="(item,index) in tlistedCompanyBalanceList" :key="index" class="c-td-text">
                        {{ convertIndex(item[index_item.index],index_item.label) }}
                      </td>
                    </tr>
                    </tbody>
                  </table>
                  <h4 v-else>暂无数据</h4>
                </el-tab-pane>
                <el-tab-pane label="利润表（单位:人民币元）" name="lirun">
                  <table v-if="tlistedCompanyProfitsInfoList.length>0" class="c-base-table" style="width: 100%;margin-top: 10px">
                    <thead>
                    <tr>
                      <td class="c-th-header">序号</td>
                      <td class="c-th-header"></td>
                      <td v-for="(item,index) in tlistedCompanyProfitsInfoList" :key="index" class="c-th-header">
                        {{ item.rptDate }}
                      </td>
                    </tr>
                    <tr>
                      <td class="c-th-header">1</td>
                      <td class="c-th-header">企业id</td>
                      <td class="c-th-header">{{ entinfo.uniscid }}</td>
                      <td class="c-th-header">{{ entinfo.uniscid }}</td>
                      <td class="c-th-header">{{ entinfo.uniscid }}</td>
                      <td class="c-th-header">{{ entinfo.uniscid }}</td>
                      <td class="c-th-header">{{ entinfo.uniscid }}</td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                      <td class="c-td-text">2</td>
                      <td class="c-td-text">企业名称</td>
                      <td class="c-td-text">{{ entinfo.entname }}</td>
                      <td class="c-td-text">{{ entinfo.entname }}</td>
                      <td class="c-td-text">{{ entinfo.entname }}</td>
                      <td class="c-td-text">{{ entinfo.entname }}</td>
                      <td class="c-td-text">{{ entinfo.entname }}</td>
                    </tr>
                    <tr v-for="(index_item,index) in profits_info_index" :key="index">
                      <td class="c-td-text">
                        {{ index+3 }}
                      </td>
                      <td class="c-td-text">
                        {{ index_item.label }}
                      </td>
                      <td v-for="(item,index) in tlistedCompanyProfitsInfoList" :key="index" class="c-td-text">
                        {{ convertIndex(item[index_item.index],index_item.label) }}
                      </td>
                    </tr>
                    </tbody>
                  </table>
                  <h4 v-else>暂无数据</h4>
                </el-tab-pane>
                <el-tab-pane label="现金流量表（单位:人民币元）" name="xianjin">
                  <table v-if="tlistedCompanyCashFlowList.length>0" class="c-base-table" style="width: 100%;margin-top: 10px">
                    <thead>
                    <tr>
                      <td class="c-th-header">序号</td>
                      <td class="c-th-header"></td>
                      <td v-for="(item,index) in tlistedCompanyCashFlowList" :key="index" class="c-th-header">
                        {{ item.rptDate }}
                      </td>
                    </tr>
                    <tr>
                      <td class="c-th-header">1</td>
                      <td class="c-th-header">企业id</td>
                      <td class="c-th-header">{{ entinfo.uniscid }}</td>
                      <td class="c-th-header">{{ entinfo.uniscid }}</td>
                      <td class="c-th-header">{{ entinfo.uniscid }}</td>
                      <td class="c-th-header">{{ entinfo.uniscid }}</td>
                      <td class="c-th-header">{{ entinfo.uniscid }}</td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                      <td class="c-td-text">2</td>
                      <td class="c-td-text">企业名称</td>
                      <td class="c-td-text">{{ entinfo.entname }}</td>
                      <td class="c-td-text">{{ entinfo.entname }}</td>
                      <td class="c-td-text">{{ entinfo.entname }}</td>
                      <td class="c-td-text">{{ entinfo.entname }}</td>
                      <td class="c-td-text">{{ entinfo.entname }}</td>
                    </tr>
                    <tr v-for="(index_item,index) in cash_flow_index" :key="index">
                      <td class="c-td-text">
                        {{ index+3 }}
                      </td>
                      <td class="c-td-text">
                        {{ index_item.label }}
                      </td>
                      <td v-for="(item,index) in tlistedCompanyCashFlowList" :key="index" class="c-td-text">
                        {{ convertIndex(item[index_item.index],index_item.label) }}
                      </td>
                    </tr>
                    </tbody>
                  </table>
                  <h4 v-else>暂无数据</h4>
                </el-tab-pane>
              </el-tabs>
            </div>
          </el-tab-pane>
          <el-tab-pane label="企业评价" name="zhuti">
            <div v-if="tevaluatingIndex" style="background-color: white;height: 350px">
              <div style="height: 40px;padding: 10px">
                <span style="font-weight: bolder;color: black;font-size: 15px">{{ entinfo.entname }}</span>
              </div>
              <div style="height: 300px;padding: 15px">
                <div class="c-data-h1">企业综合评分</div>
                <el-row>
                  <el-col :span="12">
                    <div style="text-align: center;padding-top: 90px">
                      <div style="font-weight: bolder;font-size: 30px;color: #1ab394">{{ tevaluatingIndex.overall }}分</div>
                      <div>企业评分</div>
                    </div>
                  </el-col>
                  <el-col :span="12">
                    <div ref="radarStats" style="height:300px;width:500px;" />
                  </el-col>
                </el-row>
              </div>
              <div style="clear: both;background-color: white;padding: 15px;margin-top: 15px">
                <div class="c-data-h1">企业分项评价</div>
                <el-row>
                  <el-col :span="4" style="padding: 5px">
                    <el-popover
                      placement="top"
                      width="400"
                      trigger="hover"
                      content="表征企业开展经营活动的天然优势，体现市场和社会公众对企业的基础认知情况。身份特征相关的属性往往会对企业的行业准入、
                        融资成本和商业模式等产生较大影响，也将潜移默化地影响企业的经营与发展。身份特征指标包括企业经过多年经营积累积淀下的身份特征和股东、
                        法定代表人特征等各类优质要素为企业增加的商誉特征。"
                    >
                      <div slot="reference" style="text-align: center;font-size: 14px;border: 1px solid gainsboro;height: 70px;margin-top: 5px">
                        <div style="font-weight: bolder;color: white;background-color: #3A71A8;height: 35px;line-height: 35px">身份特征</div>
                        <div style="color: #1ab394;height: 35px;line-height: 35px">
                          <i class="el-icon-star-off"></i>{{ tevaluatingIndex.entIdentity }}
                        </div>
                      </div>
                    </el-popover>
                  </el-col>
                  <el-col :span="4" style="padding: 5px">
                    <el-popover
                      placement="top"
                      width="200"
                      trigger="hover"
                      content="表征企业生产经营中隐含的风险大小以及对风险的抵抗能力，反映企业的经营稳定性。经营风险指标主要包括严重事件风险、
                        财务风险、合规风险、关联风险和频繁变更风险五个方面。此外，经营风险侧重考虑企业的负面的信息，
                        根据一段时间内的风险事件发生的严重程度、数量、事件时间来构建模型，进行评分。"
                    >
                      <div slot="reference" style="text-align: center;font-size: 14px;border: 1px solid gainsboro;height: 70px;margin-top: 5px">
                        <div style="font-weight: bolder;color: white;background-color: #3A71A8;height: 35px;line-height: 35px">经营风险</div>
                        <div style="color: #1ab394;height: 35px;line-height: 35px">
                          <i class="el-icon-star-off"></i>{{ tevaluatingIndex.risk }}
                        </div>
                      </div>
                    </el-popover>
                  </el-col>
                  <el-col :span="4" style="padding: 5px">
                    <el-popover
                      placement="top"
                      width="200"
                      trigger="hover"
                      content="表征企业在国内行业中的竞争状况，体现了企业的行业地位。行业实力指标的纠正了行业差异形成的竞争差异，
                        更全面和准确的评价企业的经营情况。行业实力指标主要包括企业财务指标相对行业的分位数情况、
                        非财务类指标相对行业的中位数情况以及企业收入增速相对行业平均增速情况。"
                    >
                      <div slot="reference" style="text-align: center;font-size: 14px;border: 1px solid gainsboro;height: 70px;margin-top: 5px">
                        <div style="font-weight: bolder;color: white;background-color: #3A71A8;height: 35px;line-height: 35px">行业实力</div>
                        <div style="color: #1ab394;height: 35px;line-height: 35px">
                          <i class="el-icon-star-off"></i>{{ tevaluatingIndex.competitiveness }}
                        </div>
                      </div>
                    </el-popover>
                  </el-col>
                  <el-col :span="4" style="padding: 5px">
                    <el-popover
                      placement="top"
                      width="200"
                      trigger="hover"
                      content="表征企业近期各个指标的变化方向和速度，体现企业的成长性和稳定性。
            发展速度指标主要包括财务类、市场类、人才类和创新类四个方面。根据企业在两个时间段内的数据项的同比变化情况进行评分。"
                    >
                      <div slot="reference" style="text-align: center;font-size: 14px;border: 1px solid gainsboro;height: 70px;margin-top: 5px">
                        <div style="font-weight: bolder;color: white;background-color: #3A71A8;height: 35px;line-height: 35px">发展速度</div>
                        <div style="color: #1ab394;height: 35px;line-height: 35px">
                          <i class="el-icon-star-off"></i>{{ tevaluatingIndex.development }}
                        </div>
                      </div>
                    </el-popover>
                  </el-col>
                  <el-col :span="4" style="padding: 5px">
                    <el-popover
                      placement="top"
                      width="200"
                      trigger="hover"
                      content="表征企业开展经营活动的活跃程度，体现企业的生存状态。活跃度并不考虑事件的好与坏，更侧重考虑企业事件的影响大小。
            活跃程度包括企业经营活动活跃程度、创新活跃程度、投融资活跃程度等方面。评价体系将经营事件数据按照影响程度的强弱分为三类，
            分别是与经营活动直接相关的经营事件、与经营活动强相关的创新和投融资事件和与经营活动弱相关的企业成立和变更事件。"
                    >
                      <div slot="reference" style="text-align: center;font-size: 14px;border: 1px solid gainsboro;height: 70px;margin-top: 5px">
                        <div style="font-weight: bolder;color: white;background-color: #3A71A8;height: 35px;line-height: 35px">活跃程度</div>
                        <div style="color: #1ab394;height: 35px;line-height: 35px">
                          <i class="el-icon-star-off"></i>{{ tevaluatingIndex.vigor }}
                        </div>
                      </div>
                    </el-popover>
                  </el-col>
                  <el-col :span="4" style="padding: 5px">
                    <el-popover
                      placement="top"
                      width="200"
                      trigger="hover"
                      content="表征企业在技术、品牌和商业模式上的创新程度，体现企业的创新性。创新能力指标包括创新投入、技术创新程度、
            品牌创新四个方面。技术创新不仅包括知识产权的创造，还包括新技术和新模式的应用。从数据项看，
            一方面体现在企业知识产权的数量、类型、申请时间和授权时间等，另一方面体现在外部投资机构的地位、投资次数、时间等。"
                    >
                      <div slot="reference" style="text-align: center;font-size: 14px;border: 1px solid gainsboro;height: 70px;margin-top: 5px">
                        <div style="font-weight: bolder;color: white;background-color: #3A71A8;height: 35px;line-height: 35px">创新能力</div>
                        <div style="color: #1ab394;height: 35px;line-height: 35px">
                          <i class="el-icon-star-off"></i>{{ tevaluatingIndex.creativity }}
                        </div>
                      </div>
                    </el-popover>
                  </el-col>
                </el-row>
              </div>
            </div>
            <div style="background-color: white;height: 150px;padding: 15px" v-else>
              <h3>暂无数据</h3>
            </div>
          </el-tab-pane>
        </el-tabs>

      </div>
      <el-dialog
        title="企业简介"
        :visible.sync="dialogVisible"
        width="800px"
      >
        <div style="line-height: 40px">{{ entIntro }}</div>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
        </span>
      </el-dialog>
    </div>
  </section>
</template>

<script>
import request from "@/utils/request";
import * as echarts from "echarts";
export default {
  name: 'EntDetail',
  data() {
    return {
      activeNameTwo: 'zichan',
      radarStats: null,
      entid: '',
      divHeight: '',
      tempRate: 3.6,
      evamkt: 0,
      evaviality: 0,
      activeName: 'yewu',
      newsShow: false,
      groupInfo: {},
      is_monitor: false,
      monitor_tab_names: [],
      grpid: null,
      grpname: null,
      relaNums: 0,
      newsWidth: {
        width: '0px'
      },
      balance_listed_index: [
        { label: '报表类型', index: 'rptType' },
        { label: '货币资金', index: 'curfds' },
        { label: '交易性金融资产', index: 'tradfinasset' },
        { label: '应收票据', index: 'notesrece' },
        { label: '预付款项', index: 'prep' },
        { label: '其他流动资产', index: 'othercurrasse' },
        { label: '流动资产合计', index: 'totcurrasset' },
        { label: '可供出售金融资产', index: 'avaisellasse' },
        { label: '投资性房地产', index: 'inveprop' },
        { label: '固定资产原值', index: 'fixedasseimmo' },
        { label: '在建工程', index: 'consprog' },
        { label: '固定资产清理', index: 'fixedasseclea' },
        { label: '无形资产', index: 'intaasset' },
        { label: '商誉', index: 'goodwill' },
        { label: '长期待摊费用', index: 'logprepexpe' },
        { label: '递延所得税资产', index: 'defetaxasset' },
        { label: '非流动资产合计', index: 'totalnoncassets' },
        { label: '资产总计', index: 'totasset' },
        { label: '短期借款', index: 'shorttermborr' },
        { label: '应付票据', index: 'notespaya' },
        { label: '应付账款', index: 'accopaya' },
        { label: '预收款项', index: 'advapaym' },
        { label: '应付职工薪酬', index: 'copeworkersal' },
        { label: '应交税费', index: 'taxespaya' },
        { label: '应付利息', index: 'intepaya' },
        { label: '应付股利', index: 'divipaya' },
        { label: '其他应付款', index: 'otherpay' },
        { label: '一年内到期的非流动负债', index: 'duenoncliab' },
        { label: '其他流动负债', index: 'othercurreliabi' },
        { label: '流动负债合计', index: 'totalcurrliab' },
        { label: '长期借款', index: 'longborr' },
        { label: '应付债券', index: 'bdspaya' },
        { label: '长期应付款', index: 'longpaya' },
        { label: '专项应付款', index: 'specpaya' },
        { label: '递延所得税负债', index: 'defeincotaxliab' },
        { label: '非流动负债合计', index: 'totalnoncliab' },
        { label: '负债合计', index: 'totliab' },
        { label: '实收资本(或股本)', index: 'paidincapi' },
        { label: '资本公积', index: 'capisurp' },
        { label: '盈余公积', index: 'rese' },
        { label: '未分配利润', index: 'undiprof' },
        { label: '外币报表折算差额', index: 'curtrandiff' },
        { label: '归属于母公司股东权益合计', index: 'paresharrigh' },
        { label: '少数股东权益', index: 'minysharrigh' },
        { label: '所有者权益(或股东权益)合计', index: 'righaggr' },
        { label: '负债和所有者权益(或股东权益)总计', index: 'totliabsharequi' },
        { label: '应收股利', index: 'dividrece' },
        { label: '应收利息', index: 'interece' },
        { label: '应收账款', index: 'accorece' },
        { label: '其他应收款', index: 'otherrece' },
        { label: '应收补贴款', index: 'subsrece' },
        { label: '内部应收款', index: 'intelrece' },
        { label: '存货', index: 'inve' },
        { label: '待摊费用', index: 'prepexpe' },
        { label: '待处理流动资产损益', index: 'unseg' },
        { label: '一年内到期的非流动资产', index: 'expinoncurrasset' },
        { label: '股权分置流通权', index: 'tradshartrad' },
        { label: '持有至到期投资', index: 'holdinvedue' },
        { label: '长期应收款', index: 'longrece' },
        { label: '长期股权投资', index: 'equiinve' },
        { label: '其他长期投资', index: 'otherlonginve' },
        { label: '累计折旧', index: 'accudepr' },
        { label: '固定资产净值', index: 'fixedassenetw' },
        { label: '固定资产减值准备', index: 'fixedasseimpa' },
        { label: '固定资产净额', index: 'fixedassenet' },
        { label: '工程物资', index: 'engimate' },
        { label: '生产性生物资产', index: 'prodasse' },
        { label: '油气资产', index: 'hydrasset' },
        { label: '开发支出', index: 'deveexpe' },
        { label: '其他非流动资产', index: 'othernoncasse' },
        { label: '交易性金融负债', index: 'tradfinliab' },
        { label: '其他应交款', index: 'otherfeepaya' },
        { label: '内部应付款', index: 'intelpay' },
        { label: '应付短期债券', index: 'shorttermbdspaya' },
        { label: '预提费用', index: 'accrexpe' },
        { label: '国内票证结算', index: 'dometicksett' },
        { label: '国际票证结算', index: 'inteticksett' },
        { label: '其他非流动负债', index: 'othernoncliabi' },
        { label: '减：库存股', index: 'treastk' },
        { label: '公益性生物资产', index: 'comasse' },
        { label: '未确定的投资损失', index: 'unreinveloss' },
        { label: '专项储备', index: 'specrese' },
        { label: '长期递延收益', index: 'longdefeinco' },
        { label: '结算备付金', index: 'settresedepo' },
        { label: '拆出资金', index: 'plac' },
        { label: '衍生金融资产', index: 'derifinaasset' },
        { label: '应收保费', index: 'premrece' },
        { label: '应收分保账款', index: 'reinrece' },
        { label: '应收分保合同准备金', index: 'reincontrese' },
        { label: '应收出口退税', index: 'expotaxrebarece' },
        { label: '应收保证金', index: 'margrece' },
        { label: '买入返售金融资产', index: 'purcresaasset' },
        { label: '发放贷款及垫款', index: 'lendandloan' },
        { label: '向中央银行借款', index: 'cenbankborr' },
        { label: '吸收存款及同业存放', index: 'deposit' },
        { label: '拆入资金', index: 'fdsborr' },
        { label: '衍生金融负债', index: 'deriliab' },
        { label: '卖出回购金融资产款', index: 'sellrepasse' },
        { label: '应付手续费及佣金', index: 'copepoun' },
        { label: '应付保证金', index: 'margrequ' },
        { label: '预计流动负债', index: 'expecurrliab' },
        { label: '应付分保账款', index: 'copewithreinrece' },
        { label: '保险合同准备金', index: 'insucontrese' },
        { label: '代理买卖证券款', index: 'actitradsecu' },
        { label: '代理承销证券款', index: 'actiundesecu' },
        { label: '一年内的递延收益', index: 'defereve' },
        { label: '预计非流动负债', index: 'expenoncliab' },
        { label: '一般风险准备', index: 'generiskrese' },
        { label: '拟分配现金股利', index: 'topaycashdivi' },
        { label: '发布日期', index: 'rptDate' },
        { label: '截止日期', index: 'enddate' }
      ],
      profits_info_index: [
        { label: '营业总收入', index: 'biztotinco' },
        { label: '营业收入', index: 'bizinco' },
        { label: '营业总成本', index: 'biztotcost' },
        { label: '营业成本', index: 'bizcost' },
        { label: '营业利润', index: 'perprofit' },
        { label: '投资收益', index: 'inveinco' },
        { label: '资产减值损失', index: 'asseimpaloss' },
        { label: '管理费用', index: 'manaexpe' },
        { label: '销售费用', index: 'salesexpe' },
        { label: '财务费用', index: 'finexpe' },
        { label: '营业外收入', index: 'nonoreve' },
        { label: '营业外支出', index: 'nonoexpe' },
        { label: '营业税金及附加', index: 'biztax' },
        { label: '利润总额', index: 'totprofit' },
        { label: '所得税费用', index: 'incotaxexpe' },
        { label: '综合收益总额', index: 'compincoamt' },
        { label: '归属于母公司所有者的综合收益总额', index: 'parecompincoamt' },
        { label: '归属于少数股东的综合收益总额', index: 'minysharincoamt' },
        { label: '主营业务收入', index: 'mainbizinco' },
        { label: '其他业务利润', index: 'otherbizprof' },
        { label: '汇兑收益', index: 'exchggain' },
        { label: '公允价值变动收益', index: 'valuechgloss' },
        { label: '期货损益', index: 'futuloss' },
        { label: '补贴收入', index: 'subsidyincome' },
        { label: '少数股东损益', index: 'minysharrigh' },
        { label: '未确认投资损失', index: 'unreinveloss' },
        { label: '净利润', index: 'netprofit' },
        { label: '归属于母公司所有者的净利润', index: 'parenetp' },
        { label: '被合并方在合并前实现净利润', index: 'mergeformnetprof' },
        { label: '年初未分配利润', index: 'earlyundiprof' },
        { label: '减少注册资本减少的未分配利润', index: 'rundisprobyrregcap' },
        { label: '外币报表折算差额', index: 'tdiffforcur' },
        { label: '可供分配的利润', index: 'avaidistprof' },
        { label: '提取法定盈余公积', index: 'legalsurp' },
        { label: '提取法定公益金', index: 'statextrundi' },
        { label: '提取职工奖福基金', index: 'extstafffund' },
        { label: '提取储备基金', index: 'trustloss' },
        { label: '提取企业发展基金', index: 'pextcdevefd' },
        { label: '可供股东分配的利润', index: 'avaidistshareprof' },
        { label: '应付优先股股利', index: 'prefstockdivi' },
        { label: '提取任意盈余公积', index: 'extrarbirese' },
        { label: '应付普通股股利', index: 'comdivpaybable' },
        { label: '转作资本股本的普通股股利', index: 'turncapsdivi' },
        { label: '未分配利润', index: 'undiprof' },
        { label: '其他', index: 'othercpltohinco' },
        { label: '基本每股收益', index: 'basiceps' },
        { label: '稀释每股收益', index: 'dilutedeps' },
        { label: '其他综合收益', index: 'othercompinco' },
        { label: '其中:对联营企业和合营企业的投资收益', index: 'assoinveprof' },
        { label: '利息收入', index: 'inteinco' },
        { label: '已赚保费', index: 'earnprem' },
        { label: '手续费及佣金收入', index: 'pouninco' },
        { label: '房地产销售收入', index: 'realsale' },
        { label: '其他业务收入', index: 'otherbizinco' },
        { label: '利息支出', index: 'inteexpe' },
        { label: '手续费及佣金支出', index: 'pounexpe' },
        { label: '房地产销售成本', index: 'realsalecost' },
        { label: '研发费用', index: 'deveexpe' },
        { label: '退保金', index: 'surrgold' },
        { label: '赔付支出净额', index: 'compnetexpe' },
        { label: '提取保险合同准备金净额', index: 'contress' },
        { label: '保单红利支出', index: 'polidiviexpe' },
        { label: '分保费用', index: 'reinexpe' },
        { label: '其他业务成本', index: 'otherbizcost' },
        { label: '托管收益', index: 'custinco' },
        { label: '非流动资产处置损失', index: 'noncassetsdisl' },
        { label: '归属于母公司所有者的其他综合收益', index: 'parecompinco' },
        { label: '归属于少数股东的其他综合收益', index: 'minysharinco' },
        { label: '报表日期', index: 'rptDate' },
        { label: '数据期间开始日期', index: 'startdate' },
        { label: '数据期间截止日期', index: 'enddate' },
        { label: '报表合并类型', index: 'rptType' }
      ],
      cash_flow_index: [
        { label: '发布时期', index: 'rptDate' },
        { label: '报表起始日期', index: 'startdate' },
        { label: '截止日期', index: 'enddate' },
        { label: '报表类型', index: 'rptType' },
        { label: '销售商品、提供劳务收到的现金', index: 'laborgetcash' },
        { label: '支付的各项税费', index: 'paytax' },
        { label: '支付给职工以及为职工支付的现金', index: 'payworkcash' },
        { label: '经营活动现金流入小计', index: 'bizcashinfl' },
        { label: '经营活动现金流出小计', index: 'bizcashoutf' },
        { label: '经营活动产生现金流量净额', index: 'biznetcflow' },
        { label: '处置固定资产、无形资产和其他长期资产所收回的现金净额', index: 'fixedassetnetc' },
        { label: '处置子公司及其他营业单位收到的现金净额', index: 'subsnetc' },
        { label: '购建固定资产、无形资产和其他长期资产所支付的现金', index: 'acquassetcash' },
        { label: '投资所支付的现金', index: 'invpayc' },
        { label: '取得子公司及其他营业单位支付的现金净额', index: 'subspaynetcash' },
        { label: '支付的其他与投资活动有关的现金', index: 'payinvecash' },
        { label: '投资活动现金流入小计', index: 'invcashinfl' },
        { label: '投资活动现金流出小计', index: 'invcashoutf' },
        { label: '投资活动产生的现金流量净额', index: 'invnetcashflow' },
        { label: '吸收投资收到的现金', index: 'invrececash' },
        { label: '取得借款收到的现金', index: 'recefromloan' },
        { label: '收到其他与筹资活动有关的现金', index: 'recefincash' },
        { label: '偿还债务支付的现金', index: 'debtpaycash' },
        { label: '分配股利、利润或偿付利息所支付的现金', index: 'diviprofpaycash' },
        { label: '支付其他与筹资活动有关的现金', index: 'finrelacash' },
        { label: '筹资活动现金流入小计', index: 'fincashinfl' },
        { label: '筹资活动现金流出小计', index: 'fincashoutf' },
        { label: '筹资活动产生的现金流量净额', index: 'finnetcflow' },
        { label: '现金及现金等价物的净增加额', index: 'cashneti' },
        { label: '收到的其他与经营活动有关的现金', index: 'receotherbizcash' },
        { label: '购买商品、接受劳务支付的现金', index: 'labopayc' },
        { label: '支付的其他与经营活动有关的现金', index: 'payacticash' },
        { label: '取得投资收益收到的现金', index: 'inveretugetcash' },
        { label: '收到的其他与投资活动有关的现金', index: 'receinvcash' },
        { label: '其中：子公司吸收少数股东投资收到的现金', index: 'subsrececash' },
        { label: '发行债券收到的现金', index: 'issbdrececash' },
        { label: '期初现金及现金等价物余额', index: 'inicashbala' },
        { label: '债务转为资本', index: 'debtintocapi' },
        { label: '一年内到期的可转换公司债券', index: 'expiconvbd' },
        { label: '融资租入固定资产', index: 'finfixedasset' },
        { label: '净利润', index: 'netprofit' },
        { label: '少数股东权益', index: 'minysharrigh' },
        { label: '未确认的投资损失', index: 'unreinveloss' },
        { label: '固定资产折旧、油气资产折耗、生产性物资折旧', index: 'assedepr' },
        { label: '无形资产摊销', index: 'intaasseamor' },
        { label: '长期待摊费用摊销', index: 'longdefeexpenamor' },
        { label: '待摊费用的减少', index: 'prepexpedecr' },
        { label: '预提费用的增加', index: 'accrexeincr' },
        { label: '固定资产报废损失', index: 'fixedassescraloss' },
        { label: '公允价值变动损失', index: 'valuechgloss' },
        { label: '财务费用', index: 'finexpe' },
        { label: '投资损失', index: 'inveloss' },
        { label: '递延所得税资产减少', index: 'defetaxassetdecr' },
        { label: '递延所得税负债增加', index: 'defetaxliabincr' },
        { label: '递延收益增加（减：减少）', index: 'defeincoincr' },
        { label: '存货的减少', index: 'inveredu' },
        { label: '经营性应收项目的减少', index: 'receredu' },
        { label: '经营性应付项目的增加', index: 'payaincr' },
        { label: '现金等价物的期末余额', index: 'equfinalbala' },
        { label: '现金等价物的期初余额', index: 'equopenbala' },
        { label: '收到的税费返还', index: 'taxrefd' },
        { label: '收回投资所收到的现金', index: 'withinvgetcash' },
        { label: '客户存款和同业存放款项净增加额', index: 'deponetr' },
        { label: '向中央银行借款净增加额', index: 'bankloannetincr' },
        { label: '向其他金融机构拆入资金净增加额', index: 'fininstnetr' },
        { label: '收到原保险合同保费取得的现金', index: 'inspremcash' },
        { label: '收到再保险业务现金净额', index: 'insnetc' },
        { label: '保户储金及投资款净增加额', index: 'savinetr' },
        { label: '处置交易性金融资产净增加额', index: 'disptradnetincr' },
        { label: '收取利息、手续费及佣金的现金', index: 'charintecash' },
        { label: '拆入资金净增加额', index: 'fdsborrnetr' },
        { label: '回购业务资金净增加额', index: 'repnetincr' },
        { label: '客户贷款及垫款净增加额', index: 'loansnetr' },
        { label: '存放中央银行和同业款项净增加额', index: 'tradepaymnetr' },
        { label: '支付原保险合同赔付款项的现金', index: 'paycompgold' },
        { label: '支付利息、手续费及佣金的现金', index: 'payintecash' },
        { label: '支付保单红利的现金', index: 'paydivicash' },
        { label: '一、经营活动产生的现金流量净额', index: 'mananetr' },
        { label: '减少质押和定期存款所收到的现金', index: 'reducashpled' },
        { label: '质押贷款净增加额', index: 'loannetr' },
        { label: '增加质押和定期存款所支付的现金', index: 'incrcashpled' },
        { label: '其中：子公司支付给少数股东的股利、利润', index: 'subspaydivid' },
        { label: '四、汇率变动对现金及现金等价物的影响', index: 'chgexchgchgs' },
        { label: '五、现金及现金等价物净增加额', index: 'cashnetr' },
        { label: '六、期末现金及现金等价物余额', index: 'finalcashbala' },
        { label: '资产减值准备', index: 'asseimpa' },
        { label: '处置固定资产、无形资产和其他长期资产的损失', index: 'dispfixedassetloss' },
        { label: '预计负债', index: 'estidebts' },
        { label: '已完工尚未结算款的减少(减:增加)', index: 'unseparachg' },
        { label: '已结算尚未完工款的增加(减:减少)', index: 'unfiparachg' },
        { label: '其他', index: 'other' },
        { label: '现金的期末余额', index: 'cashfinalbala' },
        { label: '现金的期初余额', index: 'cashopenbala' }
      ],
      entinfo: {},
      used_name: [],
      tels: [],
      websites: [],
      syncWords: [],
      entname: '',
      location: '',
      ctrlInfo: {
        ctrlname: ''
      },
      entIntro: '',
      dialogVisible: false,
      mbrCount: 0,
      groupStats: {},
      radar_options: {
        title: {
          text: ''
        },
        tooltip: {},
        radar: {
          radius: '50%',
          // shape: 'circle',
          center: ['50%', '50%'],
          name: {
            textStyle: {
              color: '#fff',
              backgroundColor: '#999',
              padding: [1, 2]
            }
          },
          indicator: [
            { name: '财务', max: 6500 },
            { name: '行业', max: 16000 },
            { name: '融资', max: 30000 },
            { name: '创新', max: 38000 }
          ]
        },
        series: [{
          name: '企业评分',
          type: 'radar',
          label: {
            show: false
          },
          // areaStyle: {normal: {}},
          data: [
            {
              value: [4300, 10000, 28000, 35000],
              name: '企业评分',
              label: {
                show: false
              }
            }
          ]
        }]
      },
      monitorDialogVisible: false,
      monitor_id_checked: [],
      activeNameMonitor: 'mfg_entbase',
      tlistedCompanyBalanceList: [],
      tlistedCompanyCashFlowList: [],
      tlistedCompanyProfitsInfoList: [],
      tevaluatingIndex: null,
      defaultProps: {
        children: 'children',
        label: 'label'
      },
      enterpriseTag: {},
      enterpriseDetail: {}
    }
  },
  created() {
    this.entinfo = JSON.parse(sessionStorage.getItem('supplyOneDetail'));
  },
  mounted() {
    this.uniscid = this.$route.query.uniscid;
    this.getEntDetail()
  },
  methods: {
    handleClick(val) {

    },
    convertIndex(val,label) {
      if (label === '发布时期' ||
        label === '报表起始日期' ||
        label === '截止日期' ||
        label === '报表类型') {
        return val
      }
      if (!val) {
        return ''
      }
      return parseFloat(val).toLocaleString()
    },
    initRadarStats(values) {
      this.radarStats.setOption({
        title: {
          text: 'Basic Radar Chart',
          show: false
        },
        legend: {
          data: ['综合实力评分'],
          show: false
        },
        tooltip: {},
        radar: {
          radius: '60%',
          // shape: 'circle',
          center: ['50%', '40%'],
          // shape: 'circle',
          indicator: [
            { name: '身份特征', max: 1000 },
            { name: '经营风险', max: 1000 },
            { name: '行业实力', max: 1000 },
            { name: '发展速度', max: 1000 },
            { name: '活跃程度', max: 1000 },
            { name: '创新能力', max: 1000 }
          ]
        },
        series: [
          {
            name: 'Budget vs spending',
            type: 'radar',
            data: [
              {
                value: values,
                name: '综合实力评分'
              }
            ]
          }
        ]
      });
    },
    getEntDetail() {
      request({
        url: '/entInfo/getEntDetail/' + this.uniscid,
        method: 'get',
        params: { uniscid: this.uniscid }
      }).then(res => {
        this.enterpriseDetail = res.data
        if (res.data.tactualControllerList && res.data.tactualControllerList.length>0) {
          this.ctrlInfo = res.data.tactualControllerList[0];
        }
        this.grpid = res.data.grpid;
        this.grpname = res.data.grpname;
        this.mbrCount = res.data.groupInfoNums;
        this.tlistedCompanyBalanceList = res.data.tlistedCompanyBalanceList;
        this.tlistedCompanyCashFlowList = res.data.tlistedCompanyCashFlowList;
        this.tlistedCompanyProfitsInfoList = res.data.tlistedCompanyProfitsInfoList;
        this.tevaluatingIndex = res.data.tevaluatingIndex;
        this.enterpriseTag = res.data.tenterpriseTag;
        if (this.tevaluatingIndex) {
          const values = [
            this.tevaluatingIndex.entIdentity,
            this.tevaluatingIndex.risk,
            this.tevaluatingIndex.competitiveness,
            this.tevaluatingIndex.development,
            this.tevaluatingIndex.vigor,
            this.tevaluatingIndex.creativity
          ]
          this.$nextTick(() => {
            this.radarStats = echarts.init(this.$refs.radarStats, "macarons");
            this.initRadarStats(values)
          })
        }
      });
    },
    viewIntro(entIntro) {
      this.entIntro = entIntro;
      this.dialogVisible = true;
    },
    viewStockGraph() {
      this.$router.push({
        path: "/supplier_graph/equity_graph_detail",
        query: { uniscid: this.uniscid }
      });
    },
    viewCtrlGraph() {
      sessionStorage.setItem("actualControllerGraph", JSON.stringify(this.enterpriseDetail));
      this.$router.push({
        path: "/supplier_graph/actualControllerGraph",
        query: { uniscid: this.uniscid }
      });
    },
    viewGroupGraph() {
      let grpInfoTemp = this.enterpriseDetail.tgroupTag;
      grpInfoTemp.tgroupRelationList = this.enterpriseDetail.tgroupRelationList;
      sessionStorage.setItem("grpInfo", JSON.stringify(grpInfoTemp));
      this.$router.push({
        path: "/supplier_graph/supplyGroupDetail",
        query: { grpid: this.grpid }
      });
    },
    viewRelaDetailInfo(row) {
      sessionStorage.setItem("baseRelaInfo", JSON.stringify(this.enterpriseDetail))
      this.$router.push({
        path: "/supplier_graph/relaDetailGraph",
        query: { uniscid: this.uniscid }
      });
    }
  }
};
</script>
<style scoped>
/deep/ .el-rate{
  height: 14px;
  line-height: 14px;
}
/deep/ .el-rate__icon {
  font-size: 10px;
}
/deep/ .el-tabs__nav-wrap::after{
  margin-left: 10px;
  margin-right: 10px;
  width: 98%;
}
/deep/ .el-button--medium{
  padding-bottom: 4px;
  padding-top: 4px;
}
/deep/ .el-dialog__body{
  padding: 0px 25px;
}
.c-table-basic-title{
  color: black;
  font-weight: bolder;
  width: 3.3%;
  height: 20px;
  line-height: 20px;
  font-size: 14px;
}
.c-table-basic-text{
  color: black;
  width: 10%;
  height: 22px;
  line-height: 22px;
  font-size: 14px;
}
.c-data-h1{
  border-left: 7px solid #009EFF;
  padding-left: 5px;
  height: 25px;
  line-height: 25px;
  font-size: 14px;
  color: #464c5b;
  font-weight: bolder;
}
.c-div-tab{
  min-width: 10.4%;
  float: left;
  height: 40px;
  padding-left: 2px;
  padding-right: 2px;
  text-align: center;
  line-height: 40px;
  font-size: 13px;
  border: 1px solid #EAEAEA;
  background-color: #f2f2f2;
  cursor: pointer
}
.c-ent-title{
  font-size: 20px;
  color: black;
  font-family: "Microsoft YaHei";
  font-weight: 800;
}
.c-div-tab-checked{
  background-color: white;
}
.c-ent-label{
  float: left;
  min-width: 100px;
  height: 30px;
  margin-right: 5px;
  padding-right: 5px;
  padding-left: 5px;
  line-height: 30px;
  color: white;
  text-align: center;
  font-size: 13px;
  margin-bottom: 8px;
  background-color: #3663CB;
}
.c-other-info{
  height: 56px;
  float: left;
  width: 180px;
  padding-top: 4px;
  padding-bottom: 4px;
  border-radius: 4px;
  color: #0070C4;
  background-color: #D3E1F3;
}
.c-other-info:hover{
  box-shadow: #e2e2e2 0px 0px 10px;

}
.c-other-info-icon{
  font-size: 29px;
}
.c-other-info-gray{
  height: 56px;
  float: left;
  width: 15%;
  width: 200px;
  padding-top: 4px;
  padding-bottom: 4px;
  border-radius: 4px;
  background-color: #F2F2F2;
}
.c-ent-taginfo{
  cursor: pointer;
}
.c-ent-taginfo:hover{
  font-weight: bolder;
}
/deep/ .el-tabs__header {
  margin: 0px;
}
/deep/ .is-active{
  background-color: white;
}
.c-td-title{
  background:#f7f7f7;
  border:1px solid #D0CECE;
  padding-left: 10px;
  font-size:14px;
  font-weight:400;
  height: 40px;
  color:rgba(96,96,96,1);
}
.c-base-table,.c-base-table tr th, .c-base-table tr td {
  border:1px solid #D0CECE;
}
.c-base-table { border-collapse: collapse;}
.c-th-header{
  text-align: left;
  font-size:12px;
  height: 30px;
  padding-left: 10px;
  font-weight:400;
  color:rgba(96,96,96,1);
  background:#f7f7f7;
  border:1px solid #D0CECE;
}
.c-td-text{
  padding-left: 10px;
  text-align: left;
  height: 30px;
  font-size:12px;
  font-weight:400;
  color:rgba(96,96,96,1);
  background:white;
  border:1px solid #D0CECE;
}
</style>
