<template>
  <div id="app" style="margin: auto;background-color: #f9f9f9;padding: 15px;min-height: 700px">
    <div style="background-color: white;height: 100%;">
      <div class="detlbos">
        <div class="detitle">{{entinfo.entname}}（{{entinfo.uniscid}}）</div>
        <div class="detTag">
          <div style="width: 200px">{{entinfo.entstatusCn}}</div>
          <div>联系方式<i class="el-icon-arrow-down"></i></div>
          <div>官方网址<i class="el-icon-arrow-down"></i></div>
          <div>曾用名<i class="el-icon-arrow-down"></i></div>
          <div>同义词<i class="el-icon-arrow-down"></i></div>
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
          <el-table-column label="企业ID" prop="uniscid"></el-table-column>
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
          <el-table-column label="企业ID" prop="uniscid"></el-table-column>
          <el-table-column label="企业名称" prop="ename"></el-table-column>
          <el-table-column label="风险信息类型" prop="blacklistType"></el-table-column>
          <el-table-column label="认定部门" prop="department"></el-table-column>
          <el-table-column label="关联黑名单名称" prop="entname"></el-table-column>
          <el-table-column label="关联类型" prop="relType"></el-table-column>
        </el-table>
      </div>
    </div>

    <blackList :visible="visibleblackList" @visibleFlag="blckFlag"></blackList>
    <RisksummaryDetail :visible="visiblesummary" @visibleFlag="blckFlag"/>
    <greyListDetail :visible="greyListvisible" @visibleFlag="blckFlag"></greyListDetail>

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
      currentIndex:0,
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
      this.visiblesummary = true
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
::v-deep .el-descriptions__body .el-descriptions__table {
  border-collapse: collapse;
  width: 100%;
  table-layout: fixed;
  color: #333;
  background-color: rgba(242, 242, 242, 1);
}
.detlbos{
  .detitle{
    font-size: 26px;


  }
  .detTag{
    display: flex;
    padding: 20px 0px;
    div{
      width: 120px;
      text-align: center;
      height: 35px;
      line-height: 35px;
      margin-right: 18px;
      border:1px solid #ccc;
      .el-icon-arrow-down{
        font-size: 18px;
        margin-left: 8px;
      }
    }
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
