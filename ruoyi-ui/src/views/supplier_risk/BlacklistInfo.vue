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
              <div style="font-size: 30px;font-weight: bolder;color: #464c5b">100/10</div>
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
              <div style="font-size: 30px;font-weight: bolder;color: #464c5b">120 / 5</div>
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
              <div style="font-size: 30px;font-weight: bolder;color: #464c5b">120 / 5</div>
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
              <span style="font-weight: bolder;font-size: 15px">供应商预警黑名单（12）</span>
              <el-button style="float: right; padding: 3px 0" type="text">查看更多</el-button>
            </div>
            <div style="width: 100%;height: 250px">
              <table class="c-base-table" style="width: 100%">
                <tr>
                  <th class="c-th-header">企业名称</th>
                  <th class="c-th-header">纳入黑名单原因</th>
                  <th class="c-th-header">认定部门</th>
                  <th class="c-th-header">是否移除</th>
                  <th class="c-th-header">移除原因</th>
                  <th class="c-th-header">数据日期</th>
                </tr>
                <tr v-for="(item, index) in dataGrid.list" :key="index">
                  <td class="c-td-text">{{item.enterpriseName}}</td>
                  <td class="c-td-text">{{item.registeredCapital}}</td>
                  <td class="c-td-text">{{item.establishmentDate}}</td>
                  <td class="c-td-text">{{item.legalRepresentative}}</td>
                  <td class="c-td-text">{{item.blacklistType}}</td>
                  <td class="c-td-text">{{item.blacklistType}}</td>
                </tr>
              </table>
            </div>
          </el-card>
        </el-col>
        <el-col :span="12">
          <el-card style="margin-left: 10px">
            <div slot="header" class="clearfix">
              <span style="font-weight: bolder;font-size: 15px">隐形关系名单预警（12）</span>
              <el-button style="float: right; padding: 3px 0" type="text">查看更多</el-button>
            </div>
            <div style="width: 100%;height: 250px">
              <table class="c-base-table" style="width: 100%">
                <tr>
                  <th class="c-th-header">企业名称</th>
                  <th class="c-th-header">关联黑名单名称</th>
                  <th class="c-th-header">关联类型</th>
                </tr>
                <tr v-for="(item, index) in dataGrid.list" :key="index">
                  <td class="c-td-text">{{item.enterpriseName}}</td>
                  <td class="c-td-text">{{item.registeredCapital}}</td>
                  <td class="c-td-text">{{item.establishmentDate}}</td>
                </tr>
              </table>
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
                  <el-button style="float: right; padding: 3px 0" type="text">查看更多</el-button>
                </div>
                <div style="width: 100%;height: 250px">
                  <table class="c-base-table" style="width: 100%">
                    <tr>
                      <th class="c-th-header">决定文书号</th>
                      <th class="c-th-header">违法行为类型</th>
                      <th class="c-th-header">行政处罚内容</th>
                      <th class="c-th-header">决定机关</th>
                    </tr>
                    <tr v-for="(item, index) in dataGrid.list" :key="index">
                      <td class="c-td-text">{{item.enterpriseName}}</td>
                      <td class="c-td-text">{{item.registeredCapital}}</td>
                      <td class="c-td-text">{{item.establishmentDate}}</td>
                      <td class="c-td-text">{{item.legalRepresentative}}</td>
                    </tr>
                  </table>
                </div>
              </el-card>
            </div>
            <div style="float: left;width: 49%;margin-left: 10px">
              <el-card>
                <div slot="header" class="clearfix">
                  <span style="font-weight: bolder;font-size: 15px">风险事件2：司法负面</span>
                  <el-button style="float: right; padding: 3px 0" type="text">查看更多</el-button>
                </div>
                <div style="width: 100%;height: 250px">
                  <table class="c-base-table" style="width: 100%">
                    <tr>
                      <th class="c-th-header">案件名称</th>
                      <th class="c-th-header">案件类型</th>
                      <th class="c-th-header">案由</th>
                      <th class="c-th-header">案号</th>
                    </tr>
                    <tr v-for="(item, index) in dataGrid.list" :key="index">
                      <td class="c-td-text">{{item.enterpriseName}}</td>
                      <td class="c-td-text">{{item.registeredCapital}}</td>
                      <td class="c-td-text">{{item.establishmentDate}}</td>
                      <td class="c-td-text">{{item.legalRepresentative}}</td>
                    </tr>
                  </table>
                </div>
              </el-card>
            </div>
          </div>
          <div style="width: 100%;height: 340px">
            <div style="float: left;width: 49%">
              <el-card>
                <div slot="header" class="clearfix">
                  <span style="font-weight: bolder;font-size: 15px">风险事件3：重大变更</span>
                  <el-button style="float: right; padding: 3px 0" type="text">查看更多</el-button>
                </div>
                <div style="width: 100%;height: 250px">
                  <table class="c-base-table" style="width: 100%">
                    <tr>
                      <th class="c-th-header">决定文书号</th>
                      <th class="c-th-header">违法行为类型</th>
                      <th class="c-th-header">行政处罚内容</th>
                      <th class="c-th-header">决定机关</th>
                    </tr>
                    <tr v-for="(item, index) in dataGrid.list" :key="index">
                      <td class="c-td-text">{{item.enterpriseName}}</td>
                      <td class="c-td-text">{{item.registeredCapital}}</td>
                      <td class="c-td-text">{{item.establishmentDate}}</td>
                      <td class="c-td-text">{{item.legalRepresentative}}</td>
                    </tr>
                  </table>
                </div>
              </el-card>
            </div>
            <div style="float: left;width: 49%;margin-left: 10px">
              <el-card>
                <div slot="header" class="clearfix">
                  <span style="font-weight: bolder;font-size: 15px">风险事件4：欠税信息</span>
                  <el-button style="float: right; padding: 3px 0" type="text">查看更多</el-button>
                </div>
                <div style="width: 100%;height: 250px">
                  <table class="c-base-table" style="width: 100%">
                    <tr>
                      <th class="c-th-header">案件名称</th>
                      <th class="c-th-header">案件类型</th>
                      <th class="c-th-header">案由</th>
                      <th class="c-th-header">案号</th>
                    </tr>
                    <tr v-for="(item, index) in dataGrid.list" :key="index">
                      <td class="c-td-text">{{item.enterpriseName}}</td>
                      <td class="c-td-text">{{item.registeredCapital}}</td>
                      <td class="c-td-text">{{item.establishmentDate}}</td>
                      <td class="c-td-text">{{item.legalRepresentative}}</td>
                    </tr>
                  </table>
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
                <el-button style="float: right; padding: 3px 0" type="text">查看更多</el-button>
              </div>
            </el-card>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>
<script>
import Pagination from "@/components/Pagination";
export default({
  name: "BlacklistInfo",
  components: { Pagination },
  data(){
    return {
      dialogVisibleDetail: false,
      dataGrid: {
        list: [
            {
              enterpriseId: '1001',
              enterpriseName: '北京科技有限公司',
              registeredCapital: '1000万',
              establishmentDate: '2010-01-01',
              legalRepresentative: '张三',
              blacklistType: 'A级',
              blacklistReason: '违法违规',
              recognizingDepartment: '北京市工商局',
              dataSource: '公开信息',
              isRemoved: '否',
              removalReason: '',
              dataDate: '2021-10-01'
            },
            {
              enterpriseId: '1002',
              enterpriseName: '上海科技有限公司',
              registeredCapital: '2000万',
              establishmentDate: '2011-01-01',
              legalRepresentative: '李四',
              blacklistType: 'B级',
              blacklistReason: '欺诈行为',
              recognizingDepartment: '上海市工商局',
              dataSource: '公开信息',
              isRemoved: '否',
              removalReason: '',
              dataDate: '2021-10-02'
            },
            {
              enterpriseId: '1003',
              enterpriseName: '广州科技有限公司',
              registeredCapital: '3000万',
              establishmentDate: '2012-01-01',
              legalRepresentative: '王五',
              blacklistType: 'C级',
              blacklistReason: '环保问题',
              recognizingDepartment: '广州市工商局',
              dataSource: '公开信息',
              isRemoved: '否',
              removalReason: '',
              dataDate: '2021-10-03'
            },
            {
              enterpriseId: '1004',
              enterpriseName: '深圳科技有限公司',
              registeredCapital: '4000万',
              establishmentDate: '2013-01-01',
              legalRepresentative: '赵六',
              blacklistType: 'D级',
              blacklistReason: '质量问题',
              recognizingDepartment: '深圳市工商局',
              dataSource: '公开信息',
              isRemoved: '否',
              removalReason: '',
              dataDate: '2021-10-04'
            }
          ],
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
  methods: {
    getList() {
    }
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
