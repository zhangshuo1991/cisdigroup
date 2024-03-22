<template>
  <div id="app" style="margin: auto;background-color: #f9f9f9;padding: 10px;min-height: 700px">
    <div style="background-color: white;line-height: 70px;height: 70px;text-align: center">
      <el-input
        v-model="searchListQuery.keywords"
        class="input-with-select"
        style="width: 710px;margin-left: 48px;"
        placeholder="请输入企业名称搜索"
        @keyup.enter.native="handleSelect"
      >
        <el-button slot="append" icon="el-icon-search" @click="handleSelect" />
      </el-input>
    </div>
    <div style="padding-left: 15px;line-height: 40px;height: 40px;background-color: white;font-size: 12px;margin-top: 15px">
      为您找到 <span style="color: #2E4E8F;font-weight: bolder">{{ totalSize }}</span> 家符合条件的企业，此处最多展示10000条。
    </div>
    <div style="margin-top: 15px;background-color: white;min-height: 450px;padding: 15px">
      <el-table
        :data="tableData"
        border
        highlight-current-row
        :header-row-style="{height:'40px',fontSize: '13px'}"
        :header-cell-style="{padding:'0px'}"
        :row-style="{height:'40px',fontSize: '13px'}"
        :cell-style="{padding:'0px'}"
        style="width: 100%;background-color: white;"
      >
        <el-table-column label="序号" width="50">
          <template slot-scope="scope">
            {{ (scope.$index + 1) + (searchListQuery.page - 1) * searchListQuery.limit }}
          </template>
        </el-table-column>
        <el-table-column
          prop="entname"
          label="企业名称"
          width="200"
        >
        </el-table-column>
        <el-table-column
          prop="uniscid"
          label="统一社会信用代码"
        />
        <el-table-column
          label="企业经营状态"
        >
          <template slot-scope="scope">
            {{ scope.row.entstatusCn }}
          </template>
        </el-table-column>
        <el-table-column
          label="注册资本"
        >
          <template slot-scope="scope">
            {{ scope.row.regcap+scope.row.regcapcurCn }}
          </template>
        </el-table-column>
        <el-table-column
          prop="esdate"
          label="成立日期"
        />
        <el-table-column
          prop="regorgCn"
          label="注册地"
        />
        <el-table-column
          prop="actionPersonNums"
          label="一致行动人组（数量）"
        />
        <el-table-column
          label="⼀致⾏动⼈列表"
        >
          <template slot-scope="scope">
            <span v-html="convertPartyList(scope)" />
            <el-link v-show=" (scope.row.tactionPersonList !== undefined && scope.row.tactionPersonList.length>3)" type="primary" :underline="false" @click="showMoreEnt(scope)">更多</el-link>
          </template>
        </el-table-column>
        <el-table-column
          fixed="right"
          label="操作"
          width="70"
        >
          <template slot-scope="scope">
            <el-link type="primary" :underline="false" @click="viewDetailInfo(scope.row)">详情</el-link>
          </template>
        </el-table-column>
      </el-table>
      <pagination :total="totalSize" :page.sync="searchListQuery.page" :limit.sync="searchListQuery.limit" @pagination="getList" />
    </div>
    <el-dialog
      title="⼀致⾏动⼈组详情"
      :visible.sync="dialogVisible"
      width="30%"
    >
      <el-table
        :data = "tactionPersonListDetail"
        border
      >
        <el-table-column
          prop="fromname"
          label="企业名称"
        />
      </el-table>
      <el-button slot="footer" type="primary" @click="dialogVisible = false">关闭</el-button>
    </el-dialog>
  </div>
</template>
<script>
import Pagination from "@/components/Pagination";
import request from "@/utils/request";
export default({
  name: "ActionGraphList",
  components: { Pagination },
  data() {
    return {
      keywords: "",
      searchType: "1",
      dialogVisible: false,
      searchList: [],
      searchListLoading: false,
      totalSize: 0,
      tableData: [],
      tactionPersonListDetail: [],
      searchListQuery: {
        page: 1,
        limit: 10,
        keywords: ""
      }
    }
  },
  mounted() {
    this.getList()
  },
  methods:{
    handleSelect(item) {
      this.searchListQuery.page = 1
      this.getList()
    },
    handleInput() {
      this.searchList = []
    },
    querySearchAsync(queryString, cb) {
    },
    getList() {
      const loading = this.$loading({
        lock: true,
        text: "正在加载数据...",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)"
      })
      request({
        url: "/entInfo/getActionGraph",
        method: "post",
        data: { keyword: this.searchListQuery.keywords },
        params: { pageNum: this.searchListQuery.page,pageSize: this.searchListQuery.limit }
      }).then(res => {
        this.tableData = res.data.item
        this.totalSize = res.data.total
        loading.close()
      })
    },
    statusStyle(status) {
      if (status !== '1') {
        const style = {
          fontSize: '14px',
          fontFamily: 'Microsoft YaHei',
          fontWeight: '400',
          color: 'rgba(248,130,51,1)'
        }
        return style
      }
      const style = {
        fontSize: '14px',
        fontFamily: 'Microsoft YaHei',
        fontWeight: '400',
        color: '#19B94F'
      }
      return style
    },
    convertPartyList(scope) {
      if (scope.row.tactionPersonList !== null && scope.row.tactionPersonList !== undefined && scope.row.tactionPersonList !== '') {
        const partyname_list = []
        if (scope.row.tactionPersonList.length <= 3) {
          scope.row.tactionPersonList.forEach(thisItem => {
            if (thisItem.fromname !== null && thisItem.fromname !== undefined && thisItem.fromname !== '') {
              partyname_list.push(thisItem.fromname)
            }
          })
          return partyname_list.join(';').toLocaleString()
        } else {
          scope.row.tactionPersonList.forEach(function(thisItem, index) {
            if (index < 3) {
              if (thisItem.fromname !== null && thisItem.fromname !== undefined && thisItem.fromname !== '') {
                partyname_list.push(thisItem.fromname)
              }
            }
          })
          return partyname_list.join(';').toLocaleString() + '...'
        }
      }
      return ''
    },
    showMoreEnt(scope) {
      this.tactionPersonListDetail = scope.row.tactionPersonList
      this.dialogVisible = true
    },
    viewDetailInfo(row) {
      sessionStorage.setItem("actionOneDetail", JSON.stringify(row))
      this.$router.push({
        path: "/supplier_graph/actionGraphDetail",
        query: {
          uniscid: row.uniscid,
        }
      });
    }
  }
})
</script>
<style scoped lang="scss">

</style>
