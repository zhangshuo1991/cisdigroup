<template>
  <div id="app" style="margin: auto;background-color: #f9f9f9;padding: 10px;min-height: 700px">
    <div style="background-color: white;line-height: 70px;height: 70px;text-align: center">
      <el-autocomplete
        v-model="keywords"
        class="input-with-select"
        style="width: 710px;margin-left: 48px;"
        placeholder="请输入企业名称、统⼀社会信⽤代码、⼯商注册号"
        :fetch-suggestions="querySearchAsync"
        :trigger-on-focus="false"
        @select="handleSelect"
        @input="handleInput"
        @keyup.enter.native="searchGroupList"
      >
        <el-button slot="append" icon="el-icon-search" @click="searchGroupList" />
      </el-autocomplete>
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
        <el-table-column
          prop="entname"
          label="企业名称"
          width="200"
        >
          <template slot-scope="scope">
            <div
              v-html="(scope.row.entname_hlf === undefined
                  || scope.row.entname_hlf === null
                  || scope.row.entname_hlf === '')?scope.row.entname: scope.row.entname_hlf"
            />
          </template>
        </el-table-column>
        <el-table-column
          prop="uniscid"
          label="统一社会信用代码"
        />
        <el-table-column
          label="企业经营状态"
        >
          <template slot-scope="scope">
            <span :style="( statusStyle(scope.row.entstatus))">{{ scope.row.entstatus_cn }}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="注册资本"
        >
          <template slot-scope="scope">
            {{ scope.row.regcap+scope.row.regcapcur_cn }}
          </template>
        </el-table-column>
        <el-table-column
          prop="esdate"
          label="成立日期"
        />
        <el-table-column
          prop="regorg_cn"
          label="注册地"
        />
        <el-table-column
          prop="actgrpCount"
          label="一致行动人组（数量）"
        />
        <el-table-column
          label="⼀致⾏动⼈列表"
        >
          <template slot-scope="scope">
            <span v-html="convertPartyList(scope)" />
            <el-link v-show=" (scope.row.actlist !== undefined && scope.row.actlist.length>3)" type="primary" :underline="false" @click="showMoreEnt(scope)">更多</el-link>
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
      searchList: [],
      searchListLoading: false,
      totalSize: 0,
      tableData: [],
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
    searchGroupList() {
      this.searchListQuery.keywords = this.keywords
      this.searchListQuery.page = 1
      this.searchListQuery.limit = 10
      this.searchListLoading = true
      this.$http({
        url: "/actionGraph/searchGroupList",
        method: "post",
        data: this.searchListQuery
      }).then(res => {
        this.searchListLoading = false
        this.searchList = res.data.rows
      })
    },
    handleSelect(item) {
      this.keywords = item.entname
      this.searchList = []
    },
    handleInput() {
      this.searchList = []
    },
    querySearchAsync(queryString, cb) {
    },
    getList() {
      request({
        url: "/entInfo/getActualController",
        method: "post",
        data: { keyword: this.searchListQuery.keywords },
        params: { pageNum: this.searchListQuery.page,pageSize: this.searchListQuery.limit }
      }).then(res => {
        this.tableData = res.data.rows
        this.totalSize = res.data.total
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
      if (scope.row.actlist !== null && scope.row.actlist !== undefined && scope.row.actlist !== '') {
        const partyname_list = []
        if (scope.row.actlist.length <= 3) {
          scope.row.actlist.forEach(thisItem => {
            if (thisItem.partyname_hlf !== null && thisItem.partyname_hlf !== undefined && thisItem.partyname_hlf !== '') {
              partyname_list.push(thisItem.partyname_hlf)
            } else {
              partyname_list.push(thisItem.partyname)
            }
          })
          return partyname_list.join(';').toLocaleString()
        } else {
          scope.row.actlist.forEach(function(thisItem, index) {
            if (index < 3) {
              if (thisItem.partyname_hlf !== null && thisItem.partyname_hlf !== undefined && thisItem.partyname_hlf !== '') {
                partyname_list.push(thisItem.partyname_hlf)
              } else {
                partyname_list.push(thisItem.partyname)
              }
            }
          })
          return partyname_list.join(';').toLocaleString() + '...'
        }
      }
      return ''
    },
    showMoreEnt(scope) {
      this.$router.push({
        path: '/actionGraph/moreEnt',
        query: {
          entname: scope.row.entname,
          uniscid: scope.row.uniscid,
          entid: scope.row.entid
        }
      })
    },
    viewDetailInfo(row) {
      this.$router.push({ path: "/supplier_graph/actionGraphDetail" });
    }
  }
})
</script>
<style scoped lang="scss">

</style>
