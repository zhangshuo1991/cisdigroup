<template>
  <div id="app" style="margin: auto;background-color: #f9f9f9;padding: 15px;min-height: 700px">
    <div style="background-color: white;height: 100%;">
      <div class="detlbos">
        <div class="detitle">
          {{entinfo.entname}}
          （{{entinfo.uniscid}}）
          <span style="color:rgba(78, 181, 72, 1)">（{{entinfo.entstatusCn}}）</span>
        </div>
        <div class="eldes">
          <el-descriptions class="margin-top" :column="3">
            <el-descriptions-item label="企业法人">{{entinfo.lerepname}} </el-descriptions-item>
            <el-descriptions-item label="注册资金">{{entinfo.regcap}}{{entinfo.regcapcurCn}}</el-descriptions-item>
            <el-descriptions-item label="所属地区">{{entinfo.domdistrictCn}}</el-descriptions-item>
            <el-descriptions-item label="经营年限">{{entinfo.tenterpriseTag.enages}} 年</el-descriptions-item>
            <el-descriptions-item label="是否上市">{{entinfo.listed === 0 ? '否': '是'}}</el-descriptions-item>
            <el-descriptions-item label="是否高新">否</el-descriptions-item>
            <el-descriptions-item label="主营行业">{{entinfo.industryname}}</el-descriptions-item>
            <el-descriptions-item label="组织形式">{{entinfo.orgtypeCn}}</el-descriptions-item>
            <el-descriptions-item label="企业规模">{{entinfo.tenterpriseTag.entscaleCn}}</el-descriptions-item>
            <el-descriptions-item label="企业简介">
              {{entinfo.opscope}}
            </el-descriptions-item>
          </el-descriptions>
        </div>
      </div>
    </div>
    <div style="background-color: white;margin-top: 15px;min-height: 300px;">
      <div class="activeTag">
        <div v-for="(item, index) in dataList" :key="item.id" :class="{'active': currentIndex == index }"
             @click="onGetIdx(index)">{{ item.name }}</div>
      </div>
      <div v-show="currentIndex === 0" style="padding:15px;min-height: 550px;">
        <div class="titleHock"><span>黑名单汇总信息</span></div>
        <el-table
          v-loading="dataGrid.listLoading"
          :data="dataGrid.list"
          border
          fit
          highlight-current-row
          :header-row-style="{height:'40px',fontSize: '13px'}"
          :header-cell-style="{padding:'0px',textAlign: 'center'}"
          :row-style="{height:'40px',fontSize: '13px'}"
          :cell-style="{padding:'0px','text-align':'center'}"
          style="width: 100%;"
          height="600"
        >
          <el-table-column label="统一社会信用代码" prop="uniscid"></el-table-column>
          <el-table-column label="企业名称" prop="ename"></el-table-column>
          <el-table-column label="黑名单类型" prop="blacklistType"></el-table-column>
          <el-table-column label="纳入黑名单原因" prop="reason"></el-table-column>
          <el-table-column label="认定部门" prop="department"></el-table-column>
          <el-table-column label="纳入黑名单原因" prop="reason"></el-table-column>
          <el-table-column label="是否移除" prop="del"></el-table-column>
          <el-table-column label="移除原因" prop="delReason"></el-table-column>
          <el-table-column label="数据日期" prop="datadate"></el-table-column>
        </el-table>
      </div>

      <div v-show="currentIndex == 1" style="padding:15px;min-height: 550px;">
        <div class="titleHock"><span>风险汇总信息</span></div>
        <el-table
          v-loading="dataGrid.listLoading"
          :data="riskSummary"
          border
          fit
          height="600"
          highlight-current-row
          :header-row-style="{height:'40px',fontSize: '13px'}"
          :header-cell-style="{padding:'0px',textAlign: 'center'}"
          :row-style="{height:'40px',fontSize: '13px'}"
          :cell-style="{padding:'0px','text-align':'center'}"
          style="width: 100%;"
        >
          <el-table-column label="企业名称" prop="entname"></el-table-column>
          <el-table-column label="风险信息类型" prop="blackType"></el-table-column>
          <el-table-column label="风险信息发生日期" prop="blackDate"></el-table-column>
          <el-table-column label="详情">
            <template slot-scope="scope">
              <el-link type="primary" @click="riskSumDelit(scope.row)">详情</el-link>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div v-show="currentIndex == 2" style="padding:15px;min-height: 550px;">
        <div class="titleHock"><span>灰名单信息</span></div>
        <el-table
          v-loading="dataGrid.listLoading"
          :data="GreyList"
          border
          fit
          height="600"
          highlight-current-row
          :header-row-style="{height:'40px',fontSize: '13px'}"
          :header-cell-style="{padding:'0px',textAlign: 'center'}"
          :row-style="{height:'40px',fontSize: '13px'}"
          :cell-style="{padding:'0px','text-align':'center'}"
          style="width: 100%;"
        >
          <el-table-column label="关联黑名单名称" prop="ename"></el-table-column>
          <el-table-column label="关联类型" prop="relType"></el-table-column>
          <el-table-column label="风险信息类型" prop="blacklistType"></el-table-column>
        </el-table>
      </div>
    </div>
    <el-dialog
      :visible.sync="dialogVisibleChange"
    >
     <div style="min-height: 100px;padding-bottom: 20px">
       <el-descriptions title="工商变更详情" border :column="1" :label-style="{'width': '100px'}">
         <el-descriptions-item label="变更前：">{{ changeReord.beforeContent }}</el-descriptions-item>
         <el-descriptions-item label="变更后：">{{ changeReord.afterContent }}</el-descriptions-item>
         <el-descriptions-item label="变更项：">{{ changeReord.changeItem }}</el-descriptions-item>
         <el-descriptions-item label="变更日期：">{{ changeReord.changeDate }}</el-descriptions-item>
       </el-descriptions>
     </div>
    </el-dialog>
    <el-dialog
      :visible.sync="dialogVisiblePunishment"
    >
      <div style="min-height: 100px;padding-bottom: 20px">
        <el-descriptions title="行政处罚详情" border :column="1" :label-style="{'width': '140px'}">
          <el-descriptions-item label="决定文书号：">{{ punishmentDetail.number }}</el-descriptions-item>
          <el-descriptions-item label="违法行为类型：">{{ punishmentDetail.illegalType }}</el-descriptions-item>
          <el-descriptions-item label="决定机关：">{{ punishmentDetail.department }}</el-descriptions-item>
          <el-descriptions-item label="社会信用代码：">{{ punishmentDetail.description }}</el-descriptions-item>
          <el-descriptions-item label="行政处罚内容：">{{ punishmentDetail.content }}</el-descriptions-item>
          <el-descriptions-item label="处罚依据：">{{ punishmentDetail.basedOn }}</el-descriptions-item>
          <el-descriptions-item label="决定日期：">{{ punishmentDetail.publicDate }}</el-descriptions-item>
        </el-descriptions>
      </div>
    </el-dialog>
    <el-dialog
      :visible.sync="dialogVisibleLawsuits"
    >
      <div style="min-height: 100px;padding-bottom: 20px">
        <el-descriptions title="裁判文书详情" border :column="1" :label-style="{'width': '140px'}">
          <el-descriptions-item label="案件名称：">{{ lawsuitDetail.title }}</el-descriptions-item>
          <el-descriptions-item label="案号：">{{ lawsuitDetail.caseNo }}</el-descriptions-item>
          <el-descriptions-item label="案由：">{{ lawsuitDetail.causeAction }}</el-descriptions-item>
          <el-descriptions-item label="案件类型：">{{ lawsuitDetail.type }}</el-descriptions-item>
          <el-descriptions-item label="判决法院：">{{ lawsuitDetail.court }}</el-descriptions-item>
          <el-descriptions-item label="案件原告/被告：">{{ lawsuitDetail.role }}</el-descriptions-item>
          <el-descriptions-item label="判决结果：">{{ lawsuitDetail.judgeresult }}</el-descriptions-item>
          <el-descriptions-item label="判决日期：">{{ lawsuitDetail.pubDate }}</el-descriptions-item>
        </el-descriptions>
      </div>
    </el-dialog>
    <el-dialog
      :visible.sync="dialogVisibleOverduetaxs"
    >
      <div style="min-height: 100px;padding-bottom: 20px">
        <el-descriptions title="欠税信息详情" border :column="1" :label-style="{'width': '140px'}">
          <el-descriptions-item label="发布时间：">{{ overduetaxsDetail.overdueTime }}</el-descriptions-item>
          <el-descriptions-item label="税务机关：">{{ overduetaxsDetail.pubDepartment }}</el-descriptions-item>
          <el-descriptions-item label="类型：">{{ overduetaxsDetail.overdueType }}</el-descriptions-item>
          <el-descriptions-item label="金额：">{{ overduetaxsDetail.overdueAmount }}元人民币</el-descriptions-item>
        </el-descriptions>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import blackList from './blackList.vue'
import RisksummaryDetail from './risksummaryDetail.vue'
import greyListDetail from './greyListDetail.vue'
import Pagination from "@/components/Pagination";
import request from "@/utils/request";
export default({
  name: "SupplyActualController",
  components: { Pagination,blackList,RisksummaryDetail,greyListDetail},
  data() {
    return {
      visibleblackList:false,
      visiblesummary:false,
      greyListvisible:false,
      dialogVisibleChange:false,
      dialogVisiblePunishment: false,
      dialogVisibleLawsuits: false,
      dialogVisibleOverduetaxs: false,
      currentIndex:0,
      changeReord: {},
      punishmentDetail: {},
      lawsuitDetail: {},
      overduetaxsDetail: {},
      dataList:[
        {id:1,name:'黑名单信息'},
        {id:2,name:'风险事件信息'},
        {id:3,name:'灰名单信息'}
      ],
      riskSummary: [],
      GreyList:[],
      dataGrid: {
        listQuery: {
          keywords: undefined,
          page: 1,
          limit: 10,
          sort: "entname",
          order: "asc"
        },
        total: 0,
        list: [],
        listLoading: false
      },
      search_text: '',
      options: [],
      selected: '',
      entinfo: {}
    }
  },
  computed: {
    bgColor () {
      return (status) => {
        console.log('status',status)
        let color = ''
        switch (status) {
          case '1':
            color = '#52C41A'
            break
          case '2':
            color = '#1890FF'
            break
        }
        return color
      }
    },
  },
  mounted() {
    this.$nextTick(() => {
      this.entinfo = JSON.parse(sessionStorage.getItem('riskInfo'))
      this.dataGrid.list = this.entinfo.tblacklistList
      this.GreyList = this.entinfo.tgreylistList
      this.getList(this.entinfo.uniscid)
    })
  },
  methods:{
    querySearchAsync(queryString, cb) {
    },
    handleSelect(item) {
    },
    handleInput(value) {
    },
    handleFilter() {
    },
    getList(uniscid) {
      request({
        url: '/entInfo/getBlackEvent/'+uniscid,
        method: 'get'
      }).then(response => {
        this.riskSummary = response.data
      })
    },
    viewDetail() {
      // this.$router.push({ path: "/supplier_graph/actualControllerGraph" });
      this.visibleblackList = true
    },
    blckFlag(){
      this.visibleblackList = false
      this.visiblesummary = false
      this.greyListvisible = false
    },
    onGetIdx(index){
      this.currentIndex = index
    },
    riskSumDelit(row){
      console.log('row',row)
      if (row.blackType === "法律诉讼") {
        this.lawsuitDetail = row.detail
        this.dialogVisibleChange = false
        this.dialogVisiblePunishment = false
        this.dialogVisibleLawsuits = true
      }else if (row.blackType === '工商变更') {
        this.changeReord = row.detail
        this.dialogVisibleChange = true
        this.dialogVisiblePunishment = false
        this.dialogVisibleLawsuits = false
      }else if (row.blackType === '行政处罚') {
        this.punishmentDetail = row.detail
        this.dialogVisibleChange = false
        this.dialogVisiblePunishment = true
        this.dialogVisibleLawsuits = false
      }else if (row.blackType === '欠税公告') {
        this.overduetaxsDetail = row.detail
        this.dialogVisibleChange = false
        this.dialogVisiblePunishment = false
        this.dialogVisibleLawsuits = false
        this.dialogVisibleOverduetaxs = true
      }
    },
    GreyListDetail(){
      this.greyListvisible = true
    }
  }
})
</script>
<style scoped lang="scss">
.eldes{
  background-color: rgba(242, 242, 242, 1);
  width: 100%;height: 100%;
  display: flex;
  justify-content: center;
  padding: 20px 20px 10px 20px;
}
::v-deep .el-dialog__body{
  padding: 10px 10px;
}
::v-deep .el-dialog__header{
  padding: 0px;
  padding-bottom: 0px;
}
::v-deep .el-descriptions__body .el-descriptions__table {
  border-collapse: collapse;
  width: 100%;
  table-layout: fixed;
  color: #333;
  background-color: rgba(242, 242, 242, 1);
}
.detlbos{
  .detitle{
    font-size: 20px;
    height: 50px;
    line-height: 50px;
  }
  .detTag{
    div:nth-child(1){
      border-color: rgba(78, 181, 72, 1);color:rgba(78, 181, 72, 1);
    }
    div:nth-child(2){
      border-color: rgba(255, 198, 43, 1);color:rgba(255, 198, 43, 1);
    }
    div:nth-child(3){
      border-color: rgba(255, 198, 43, 1);color:rgba(255, 198, 43, 1);
    }
    div:nth-child(4){
      border-color: rgba(49, 95, 202, 1);color:rgba(49, 95, 202, 1);
    }
    div:nth-child(5){
      border-color: rgba(49, 95, 202, 1);color:rgba(49, 95, 202, 1);
    }


  }
}
::v-deep.el-tabs__header{
  display: flex;
  justify-content: space-between;
}
::v-deep.el-tabs__item{
  width: 300px;

}
.activeTag{
  display: flex;
  justify-content: space-between;
  text-align: center;
  background-color: rgba(242, 242, 242, 1);
  border:1px solid rgba(215, 215, 215, 1);
  div{
    width: 100%;height: 50px;
    line-height: 50px;
    cursor: pointer;
    border-right: 1px solid rgba(215, 215, 215, 1);
  }
}
.active{
  background: #fff;
  color: #3399ff;

}
.titleHock{
  width: 100%;
  border-left: 4px solid #3399ff;
  margin-top: 10px;
  margin-bottom: 20px;
  span{
    display: inline-block;
    padding-left: 10px;
  }

}
</style>
