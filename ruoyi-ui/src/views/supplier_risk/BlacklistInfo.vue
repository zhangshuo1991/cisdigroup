<template>
  <div id="app" style="margin: auto;background-color: #f2f2f2;padding-top: 15px;padding-bottom: 30px;padding-right: 15px;padding-left: 15px;min-height: 900px">
    <div class="c-data-h1">
      风险信息总体情况
    </div>
    <div style="margin-top: 10px;width: 100%">
      <el-row>
        <el-col :span="8">
          <div style="width: 100%;background-color: white;border-left: 5px solid #5099F8;height: 90px">
            <div style="float: left;height: 90px;padding-top: 20px;width: 30%;text-align: center">
              <svg-icon icon-class="laxinhuoke" style="width: 50px;height: 50px;color: #009EFF"></svg-icon>
            </div>
            <div style="float: left;height: 90px;padding-top: 15px;text-align: center">
              <div style="font-size: 30px;font-weight: bolder;color: #464c5b">1855 / 181</div>
              <div style="font-size: 14px;font-weight: bolder;color: #464c5b">合作供应商（家）/黑名单总数</div>
            </div>
          </div>
        </el-col>
        <el-col :span="8">
          <div style="width: 100%;background-color: white;border-left: 5px solid #F4BF43;height: 90px;margin-left: 10px">
            <div style="float: left;height: 90px;padding-top: 20px;width: 30%;text-align: center">
              <svg-icon icon-class="jinrongqizhafenxi" style="width: 50px;height: 50px;color: #F4BF43"></svg-icon>
            </div>
            <div style="float: left;height: 90px;padding-top: 15px;text-align: center">
              <div style="font-size: 30px;font-weight: bolder;color: #464c5b">73543 / 5</div>
              <div style="font-size: 14px;font-weight: bolder;color: #464c5b">风险事件数 / 事件类型数</div>
            </div>
          </div>
        </el-col>
        <el-col :span="8">
          <div style="width: 100%;background-color: white;border-left: 5px solid #54A452;height: 90px;margin-left: 20px">
            <div style="float: left;height: 90px;padding-top: 20px;width: 30%;text-align: center">
              <svg-icon icon-class="yizhixingdongren" style="width: 50px;height: 50px;color: #54A452"></svg-icon>
            </div>
            <div style="float: left;height: 90px;padding-top: 15px;text-align: center">
              <div style="font-size: 30px;font-weight: bolder;color: #464c5b">1855 / 173</div>
              <div style="font-size: 14px;font-weight: bolder;color: #464c5b">合作供应商（家）/ 灰名单 </div>
            </div>
          </div>
        </el-col>
      </el-row>
    </div>
    <div style="margin-top: 10px;width: 100%">
      <el-row>
        <el-col :span="12">
          <el-card>
            <div slot="header" class="clearfix">
              <span style="font-weight: bolder;font-size: 15px">供应商预警黑名单（181）</span>
              <el-button style="float: right; padding: 3px 0" type="text" @click="showMoreBlack">查看更多</el-button>
            </div>
            <div style="width: 100%;height: 300px;">
              <el-table
                :data="dataGrid.list"
                height="300"
                border
                :row-style="{fontSize: '10x'}"
                :header-row-style="{fontSize: '10px'}"
                :cell-style="{fontSize: '10px'}"
              >
                <el-table-column
                  prop="ename"
                  label="企业名称"
                  align="left"
                  width="200"
                >
                </el-table-column>
                <el-table-column
                  prop="reason"
                  label="纳入黑名单原因"
                  align="left"
                  width="500"
                >
                </el-table-column>
                <el-table-column
                  prop="department"
                  label="认定部门"
                  align="left"
                  width="200"
                >
                </el-table-column>
                <el-table-column
                  prop="del"
                  label="是否移除"
                  align="left"
                >
                </el-table-column>
                <el-table-column
                  prop="delReason"
                  label="移除原因"
                  align="left"
                  width="500"
                >
                </el-table-column>
                <el-table-column
                  prop="datadate"
                  label="数据日期"
                  align="left"
                >
                </el-table-column>
              </el-table>
            </div>
          </el-card>
        </el-col>
        <el-col :span="12">
          <el-card style="margin-left: 10px">
            <div slot="header" class="clearfix">
              <span style="font-weight: bolder;font-size: 15px">隐形关系名单预警（173）</span>
              <el-button style="float: right; padding: 3px 0" type="text" @click="dialogVisibleGrayDetail = true">查看更多</el-button>
            </div>
            <div style="width: 100%;height: 300px">
              <el-table
                :data="greyDataGrid.list"
                height="300"
                border
                :row-style="{fontSize: '10x'}"
                :header-row-style="{fontSize: '10px'}"
                :cell-style="{fontSize: '10px'}"
              >
                <el-table-column
                  prop="entname"
                  label="企业名称"
                  align="left"
                >
                </el-table-column>
                <el-table-column
                  prop="ename"
                  label="关联黑名单名称"
                  align="left"
                >
                </el-table-column>
                <el-table-column
                  prop="relType"
                  label="关联类型"
                  align="left"
                >
                </el-table-column>
              </el-table>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>
    <div style="margin-top: 10px;width: 100%">
      <el-row>
        <el-col :span="17">
          <div style="width: 100%;height: 340px;">
            <div style="float: left;width: 49%">
              <el-card>
                <div slot="header" class="clearfix">
                  <span style="font-weight: bolder;font-size: 15px">风险事件1：行政处罚</span>
                  <el-button style="float: right; padding: 3px 0" type="text" @click="dialogAdminunishment = true">查看更多</el-button>
                </div>
                <div style="width: 100%;height: 250px">
                  <el-table
                    :data="administrativePunishment.list"
                    height="250"
                    border
                    :row-style="{fontSize: '10x'}"
                    :header-row-style="{fontSize: '10px'}"
                    :cell-style="{fontSize: '10px'}"
                  >
                    <el-table-column
                      prop="ename"
                      label="企业名称"
                      align="left"
                    />
                    <el-table-column
                      prop="number"
                      label="决定文书号"
                      align="left"
                    >
                    </el-table-column>
                    <el-table-column
                      prop="illegalType"
                      label="违法行为类型"
                      align="left"
                    >
                    </el-table-column>
                    <el-table-column
                      prop="content"
                      label="行政处罚内容"
                      align="left"
                    >
                    </el-table-column>
                    <el-table-column
                      prop="department"
                      label="决定机关"
                      align="left"
                    >
                    </el-table-column>
                  </el-table>
                </div>
              </el-card>
            </div>
            <div style="float: left;width: 49%;margin-left: 10px">
              <el-card>
                <div slot="header" class="clearfix">
                  <span style="font-weight: bolder;font-size: 15px">风险事件2：司法负面</span>
                  <el-button style="float: right; padding: 3px 0" type="text" @click="dialogLawsuitsRelations = true">查看更多</el-button>
                </div>
                <div style="width: 100%;height: 250px">
                  <el-table
                    :data="lawsuitsRelations.list"
                    height="250"
                    border
                    :row-style="{fontSize: '10x'}"
                    :header-row-style="{fontSize: '10px'}"
                    :cell-style="{fontSize: '10px'}"
                  >
                    <el-table-column
                      prop="ename"
                      label="企业名称"
                      align="left"
                    >
                    </el-table-column>
                    <el-table-column
                      prop="title"
                      label="案件名称"
                      align="left"
                    >
                    </el-table-column>
                    <el-table-column
                      prop="type"
                      label="案件类型"
                      align="left"
                    >
                    </el-table-column>
                    <el-table-column
                      prop="causeAction"
                      label="案由"
                      align="left"
                    >
                    </el-table-column>
                    <el-table-column
                      prop="caseNo"
                      label="案号"
                      align="left"
                    >
                    </el-table-column>
                  </el-table>
                </div>
              </el-card>
            </div>
          </div>
          <div style="width: 100%;height: 340px">
            <div style="float: left;width: 49%">
              <el-card>
                <div slot="header" class="clearfix">
                  <span style="font-weight: bolder;font-size: 15px">风险事件3：重大变更</span>
                  <el-button style="float: right; padding: 3px 0" type="text" @click="dialogChangeDialog = true">查看更多</el-button>
                </div>
                <div style="width: 100%;height: 250px">
                  <el-table
                    :data="changeRecord.list"
                    height="250"
                    border
                    :row-style="{fontSize: '10x'}"
                    :header-row-style="{fontSize: '10px'}"
                    :cell-style="{fontSize: '10px'}"
                  >
                    <el-table-column
                      prop="ename"
                      label="企业名称"
                      align="left"
                    >
                    </el-table-column>
                    <el-table-column
                      prop="type"
                      label="变更项目"
                      align="left"
                    >
                    </el-table-column>
                    <el-table-column
                      prop="changeItem"
                      label="变更类型"
                      align="left"
                    >
                    </el-table-column>
                  </el-table>
                </div>
              </el-card>
            </div>
            <div style="float: left;width: 49%;margin-left: 10px">
              <el-card>
                <div slot="header" class="clearfix">
                  <span style="font-weight: bolder;font-size: 15px">风险事件4：欠税信息</span>
                  <el-button style="float: right; padding: 3px 0" type="text" @click="dialogOverduetaxs = true">查看更多</el-button>
                </div>
                <div style="width: 100%;height: 250px">
                  <el-table
                    :data="overduetaxs.list"
                    height="250"
                    border
                    :row-style="{fontSize: '10x'}"
                    :header-row-style="{fontSize: '10px'}"
                    :cell-style="{fontSize: '10px'}"
                    >
                    <el-table-column
                      type="index"
                      label="序号"
                      align="left">
                    </el-table-column>
                    <el-table-column
                      prop="ename"
                      label="企业名称"
                      align="left"/>
                    <el-table-column
                      prop="overdueTime"
                      label="发布时间"
                      align="left">
                    </el-table-column>
                    <el-table-column
                      prop="pubDepartment"
                      label="所属税务机关"
                      align="left">
                    </el-table-column>
                    <el-table-column
                      prop="overdueType"
                      label="案件性质"
                      align="left">
                    </el-table-column>
                  </el-table>
                </div>
              </el-card>
            </div>
          </div>
        </el-col>
        <el-col :span="7">
          <div style="width: 100%;height: 670px;background-color: white">
            <el-card style="height: 100%">
              <div slot="header" class="clearfix">
                <span style="font-weight: bolder;font-size: 15px">风险事件5：其他</span>
                <el-button style="float: right; padding: 3px 0" type="text" @click = "dialogOtherDialog = true">查看更多</el-button>
              </div>
              <div style="width: 100%;height: 670px">
                <el-table
                  :data="otherList.list.slice(0,20)"
                  height="670"
                  border
                  :row-style="{fontSize: '10x'}"
                  :header-row-style="{fontSize: '10px'}"
                  :cell-style="{fontSize: '10px'}"
                >
                  <el-table-column
                    type="index"
                    label="序号"
                    align="left">
                  </el-table-column>
                  <el-table-column
                    prop="entname"
                    label="企业名称"
                    align="left">
                  </el-table-column>
                  <el-table-column
                    prop="blackType"
                    label="风险信息类型"
                    align="left">
                  </el-table-column>
                  <el-table-column
                    prop="blackDate"
                    label="风险信息发生日期"
                    align="left">
                  </el-table-column>
                </el-table>
              </div>

            </el-card>
          </div>
        </el-col>
      </el-row>
    </div>
    <el-dialog
      :visible.sync="dialogVisibleDetail"
      title="供应商预警黑名单"
      width="80%"
    >
      <el-table :data="dataGrid.list.slice((dataGrid.listQuery.page - 1) * dataGrid.listQuery.limit, dataGrid.listQuery.page * dataGrid.listQuery.limit)"
                border fit highlight-current-row :header-row-style="{ height: '40px', fontSize: '13px' }"
                :header-cell-style="{ padding: '0px', textAlign: 'center' }" :row-style="{ height: '40px', fontSize: '13px' }"
                :cell-style="{ padding: '0px', 'text-align': 'center' }" style="width: 100%;">
        <el-table-column label="序号" width="60" align="center">
          <template slot-scope="scope">{{ scope.$index + 1 }}</template>
        </el-table-column>
        <el-table-column label="企业ID" prop="uniscid" width="150"></el-table-column>
        <el-table-column label="企业名称" prop="ename" width="300"></el-table-column>
        <el-table-column label="纳入黑名单原因" prop="reason" width="500"></el-table-column>
        <el-table-column label="认定部门" prop="department" width="300"></el-table-column>
        <el-table-column label="移除原因" prop="delReason" width="500"></el-table-column>
        <el-table-column label="是否移除" prop="del"></el-table-column>
        <el-table-column label="数据日期" prop="datadate"></el-table-column>
      </el-table>
      <pagination style="text-align: right;" :total="dataGrid.list.length" :page.sync="dataGrid.listQuery.page" :limit.sync="dataGrid.listQuery.limit" @pagination="getList" />
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogVisibleDetail = false">关 闭</el-button>
      </span>
    </el-dialog>
    <el-dialog
      :visible.sync="dialogVisibleGrayDetail"
      title="隐形关系名单预警"
      width="80%"
    >
      <el-table :data="greyDataGrid.list.slice((dataGrid.listQuery.page - 1) * greyDataGrid.listQuery.limit, greyDataGrid.listQuery.page * greyDataGrid.listQuery.limit)"
                border fit highlight-current-row :header-row-style="{ height: '40px', fontSize: '13px' }"
                :header-cell-style="{ padding: '0px', textAlign: 'center' }" :row-style="{ height: '40px', fontSize: '13px' }"
                :cell-style="{ padding: '0px', 'text-align': 'center' }" style="width: 100%;">
        <el-table-column label="序号" width="60" align="center">
          <template slot-scope="scope">{{ scope.$index + 1 }}</template>
        </el-table-column>
        <el-table-column label="企业ID" prop="uniscid"></el-table-column>
        <el-table-column label="企业名称" prop="entname"></el-table-column>
        <el-table-column label="关联黑名单名称" prop="ename"></el-table-column>
        <el-table-column label="关联类型" prop="relType"></el-table-column>
      </el-table>
      <pagination style="text-align: right;" :total="greyDataGrid.total" :page.sync="greyDataGrid.listQuery.page" :limit.sync="greyDataGrid.listQuery.limit" @pagination="getList" />
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogVisibleGrayDetail = false">关 闭</el-button>
      </span>
    </el-dialog>
    <el-dialog
      :visible.sync="dialogAdminunishment"
      title="行政处罚"
      width="80%"
    >
      <el-table :data="administrativePunishment.list"
                border fit highlight-current-row :header-row-style="{ height: '40px', fontSize: '13px' }"
                :header-cell-style="{ padding: '0px', textAlign: 'center' }" :row-style="{ height: '40px', fontSize: '13px' }"
                :cell-style="{ padding: '0px', 'text-align': 'center' }" style="width: 100%;">
        <el-table-column label="序号" width="60" align="center">
          <template slot-scope="scope">{{ scope.$index + 1 }}</template>
        </el-table-column>
        <el-table-column
          prop="ename"
          label="企业名称"
          align="left"
        >
        </el-table-column>
        <el-table-column
          prop="basedOn"
          label="处罚依据"
          align="left"
        >
        </el-table-column>
        <el-table-column
          prop="number"
          label="决定文书号"
          align="left"
        >
        </el-table-column>
        <el-table-column
          prop="illegalType"
          label="违法行为类型"
          align="left"
        >
        </el-table-column>
        <el-table-column
          prop="content"
          label="行政处罚内容"
          align="left"
        >
        </el-table-column>
        <el-table-column
          prop="department"
          label="决定机关"
          align="left"
        >
        </el-table-column>
        <el-table-column
          prop="publicDate"
          label="决定日期"
          align="left"
        >
        </el-table-column>
      </el-table>
      <pagination style="text-align: right;" :total="administrativePunishment.total" :page.sync="administrativePunishment.listQuery.page" :limit.sync="administrativePunishment.listQuery.limit" @pagination="getList" />
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogAdminunishment = false">关 闭</el-button>
      </span>
    </el-dialog>
    <el-dialog
      :visible.sync="dialogLawsuitsRelations"
      title="司法诉讼"
      width="80%"
    >
      <el-table :data="lawsuitsRelations.list"
                border fit highlight-current-row :header-row-style="{ height: '40px', fontSize: '13px' }"
                :header-cell-style="{ padding: '0px', textAlign: 'center' }" :row-style="{ height: '40px', fontSize: '13px' }"
                :cell-style="{ padding: '0px', 'text-align': 'center' }" style="width: 100%;">
        <el-table-column label="序号" width="60" align="center">
          <template slot-scope="scope">{{ scope.$index + 1 }}</template>
        </el-table-column>
        <el-table-column
          prop="title"
          label="案件名称"
          align="left"
        >
        </el-table-column>
        <el-table-column
          prop="role"
          label="案件原告/被告"
          align="left"
        >
        </el-table-column>
        <el-table-column
          prop="ename"
          label="企业名称"
          align="left"
        >
        </el-table-column>
        <el-table-column
          prop="type"
          label="案件类型"
          align="left"
        >
        </el-table-column>
        <el-table-column
          prop="causeAction"
          label="案由"
          align="left"
        >
        </el-table-column>
        <el-table-column
          prop="date"
          label="决定日期"
          align="left"
        >
        </el-table-column>
        <el-table-column
          prop="caseNo"
          label="案号"
          align="left"
        >
        </el-table-column>
        <el-table-column
          prop="court"
          label="判决法院"
          align="left"
        >
        </el-table-column>
      </el-table>
      <pagination style="text-align: right;" :total="lawsuitsRelations.total" :page.sync="lawsuitsRelations.listQuery.page" :limit.sync="lawsuitsRelations.listQuery.limit" @pagination="getList" />
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogLawsuitsRelations = false">关 闭</el-button>
      </span>
    </el-dialog>
    <el-dialog
      :visible.sync="dialogOverduetaxs"
      title="欠税信息"
      width="80%"
    >
      <el-table :data="overduetaxs.list"
                border fit highlight-current-row :header-row-style="{ height: '40px', fontSize: '13px' }"
                :header-cell-style="{ padding: '0px', textAlign: 'center' }" :row-style="{ height: '40px', fontSize: '13px' }"
                :cell-style="{ padding: '0px', 'text-align': 'center' }" style="width: 100%;">
        <el-table-column label="序号" width="60" align="center">
          <template slot-scope="scope">{{ scope.$index + 1 }}</template>
        </el-table-column>
        <el-table-column
          prop="ename"
          label="企业名称"
          align="left"/>
        <el-table-column
          prop="overdueAmount"
          label="欠税金额"
          align="left">
          <template slot-scope="scope">
            <span>{{ scope.row.overdueAmount }} 元人民币</span>
          </template>
        </el-table-column>
        <el-table-column
          prop="overdueTime"
          label="发布时间"
          align="left">
        </el-table-column>
        <el-table-column
          prop="pubDepartment"
          label="所属税务机关"
          align="left">
        </el-table-column>
        <el-table-column
          prop="overdueType"
          label="案件性质"
          align="left">
        </el-table-column>
      </el-table>
      <pagination style="text-align: right;" :total="overduetaxs.total" :page.sync="overduetaxs.listQuery.page" :limit.sync="overduetaxs.listQuery.limit" @pagination="getList" />
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogOverduetaxs = false">关 闭</el-button>
      </span>
    </el-dialog>
    <el-dialog
      :visible.sync="dialogChangeDialog"
      title="变更信息"
      width="80%"
    >
      <el-table :data="changeRecord.list"
                border fit highlight-current-row :header-row-style="{ height: '40px', fontSize: '13px' }"
                :header-cell-style="{ padding: '0px', textAlign: 'center' }" :row-style="{ height: '40px', fontSize: '13px' }"
                :cell-style="{ padding: '0px', 'text-align': 'center' }" style="width: 100%;">
        <el-table-column label="序号" width="60" align="center">
          <template slot-scope="scope">{{ scope.$index + 1 }}</template>
        </el-table-column>
        <el-table-column
          prop="ename"
          label="企业名称"
          align="left"
        >
        </el-table-column>
        <el-table-column
          prop="type"
          label="变更项目"
          align="left"
        >
        </el-table-column>
        <el-table-column
          prop="changeItem"
          label="变更类型"
          align="left"
        >
        </el-table-column>
        <el-table-column
          prop="beforeContent"
          label="变更前"
          align="left"
        >
        </el-table-column>
        <el-table-column
          prop="afterContent"
          label="变更后"
          align="left"
        >
        </el-table-column>
        <el-table-column
          prop="changeDate"
          label="变更日期"
          align="left"
        >
        </el-table-column>
      </el-table>
      <pagination style="text-align: right;" :total="changeRecord.total" :page.sync="changeRecord.listQuery.page" :limit.sync="changeRecord.listQuery.limit" @pagination="getList" />
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogChangeDialog = false">关 闭</el-button>
      </span>
    </el-dialog>
    <el-dialog
      :visible.sync="dialogOtherDialog"
      title="其他风险信息"
      width="80%"
    >
      <el-table :data="otherList.list.slice((otherList.listQuery.page - 1) * otherList.listQuery.limit, otherList.listQuery.page * otherList.listQuery.limit)"
                border fit highlight-current-row :header-row-style="{ height: '40px', fontSize: '13px' }"
                :header-cell-style="{ padding: '0px', textAlign: 'center' }" :row-style="{ height: '40px', fontSize: '13px' }"
                :cell-style="{ padding: '0px', 'text-align': 'center' }" style="width: 100%;">
        <el-table-column label="序号" width="60" align="center">
          <template slot-scope="scope">{{ scope.$index + 1 }}</template>
        </el-table-column>
        <el-table-column
          prop="entname"
          label="企业名称"
          align="left">
        </el-table-column>
        <el-table-column
          prop="blackType"
          label="风险信息类型"
          align="left">
        </el-table-column>
        <el-table-column
          prop="blackDate"
          label="风险信息发生日期"
          align="left">
        </el-table-column>
      </el-table>
      <pagination style="text-align: right;" :total="otherList.total" :page.sync="otherList.listQuery.page" :limit.sync="otherList.listQuery.limit"/>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogOtherDialog = false">关 闭</el-button>
      </span>
    </el-dialog>

  </div>
</template>
<script>
import Pagination from "@/components/Pagination";
import request from "@/utils/request";
import BlackList from "@/views/supplier_risk/detail/blackList";
export default({
  name: "BlacklistInfo",
  components: { Pagination,BlackList },
  data(){
    return {
      dialogVisibleDetail: false,
      dialogVisibleGrayDetail: false,
      dialogAdminunishment: false,
      dialogLawsuitsRelations: false,
      dialogOverduetaxs: false,
      dialogChangeDialog: false,
      dialogOtherDialog: false,
      emptyData: [],
      dataGrid: {
        list: [],
        listLoading: false,
        listQuery: {
          page: 1,
          limit: 10,
          keywords: undefined
        },
        total: 0
      },
      greyDataGrid: {
        list: [],
        listLoading: false,
        listQuery: {
          page: 1,
          limit: 10,
          keywords: undefined
        },
        total: 0
      },
      administrativePunishment: {
        list: [],
        listLoading: false,
        listQuery: {
          page: 1,
          limit: 10,
          keywords: undefined
        },
        total: 0
      },
      overduetaxs: {
        list: [],
        listLoading: false,
        listQuery: {
          page: 1,
          limit: 10,
          keywords: undefined
        },
        total: 0
      },
      lawsuitsRelations: {
        list: [],
        listLoading: false,
        listQuery: {
          page: 1,
          limit: 10,
          keywords: undefined
        },
        total: 0
      },
      changeRecord: {
        list: [],
        listLoading: false,
        listQuery: {
          page: 1,
          limit: 10,
          keywords: undefined
        },
        total: 0
      },
      otherList: {
        list: [],
        listLoading: false,
        listQuery: {
          page: 1,
          limit: 10,
          keywords: undefined
        },
        total: 0
      }
    }
  },
  mounted() {
    this.getList();
  },
  methods: {
    showMoreBlack() {
      this.dialogVisibleDetail = true
    },
    getList() {
      const loading = this.$loading({
        lock: true,
        text: '正在加载中...',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
      });
      request({
        url: "/entInfo/getBlackList",
        method: "get"
      }).then(res => {
        this.dataGrid.list = res.data;
        loading.close();
      });
      request({
        url: "/entInfo/getTGreylist",
        method: "get",
        params: {pageNum: this.greyDataGrid.listQuery.page, pageSize: 10}
      }).then(res => {
        this.greyDataGrid.list = res.data.item;
        this.greyDataGrid.total = res.data.total;
      });

      request({
        url: "/entInfo/getAdministrativePunishment",
        method: "get",
        params: { pageNum: this.administrativePunishment.listQuery.page, pageSize: 10}
      }).then(res => {
        this.administrativePunishment.list = res.data.item;
        this.administrativePunishment.total = res.data.total;
      });

      request({
        url: "/entInfo/getOverduetaxs",
        method: "get",
        params: { pageNum: this.overduetaxs.listQuery.page, pageSize: 10}
      }).then(res => {
        this.overduetaxs.list = res.data.item;
        this.overduetaxs.total = res.data.total;
      });

      request({
        url: "/entInfo/getLawsuitsRelations",
        method: "get",
        params: { pageNum: this.lawsuitsRelations.listQuery.page, pageSize: 10}
      }).then(res => {
        this.lawsuitsRelations.list = res.data.item;
        this.lawsuitsRelations.total = res.data.total;
      });

      request({
        url: "/entInfo/getChangeRecords",
        method: "get",
        params: { pageNum: this.changeRecord.listQuery.page, pageSize: 10}
      }).then(res => {
        this.changeRecord.list = res.data.item;
        this.changeRecord.total = res.data.total;
      });

      request({
        url: "/entInfo/getOtherBlackEvent",
        method: "get",
        params: { pageNum: this.otherList.listQuery.page, pageSize: 10}
      }).then(res => {
        this.otherList.list = res.data;
        this.otherList.total = this.otherList.list.length;
      });
    },
  }
})
</script>
<style scoped lang="scss">
.c-data-h1{
  margin: 10px 0px 10px 0px;
  border-left: 7px solid #009EFF;
  padding-left: 5px;
  height: 25px;
  line-height: 25px;
  font-size: 14px;
  color: #464c5b;
  font-weight: bolder;
}

.c-td-title{
  background:#fcfcfc;
  border:1px solid #fcfcfc;
  padding-left: 10px;
  font-size:11px;
  font-weight:bolder;
  height: 30px;
  color:rgba(96,96,96,1);
}
.c-popper-class{
  height: 20px;
}
.c-base-table,.c-base-table tr th, .c-base-table tr td {
  border:1px solid #D0CECE;
}
.c-base-table { border-collapse: collapse;}
.c-th-header{
  text-align: left;
  font-size:11px;
  height: 30px;
  padding-left: 10px;
  background:#f9f9f9;
  font-weight:bolder;
  color:rgba(96,96,96,1);
  border:1px solid #fcfcfc;
}
.single-line {
  white-space: nowrap; /* 禁止换行 */
  overflow: hidden; /* 隐藏溢出内容 */
  text-overflow: ellipsis; /* 使用省略号显示多余字符 */
}
.c-td-text{
  text-align: left;
  height: 30px;
  line-height: 25px;
  font-size: 11px;
  padding-left: 10px;
  font-weight:400;
  color:rgba(96,96,96,1);
  background:white;
  border:1px solid #fcfcfc;
}
</style>
