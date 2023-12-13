<template>
  <div style="margin: auto;background-color: #f9f9f9;padding-top: 15px;min-height: 700px">
    <div style="background-color: white;line-height: 70px;height: 70px;text-align: center">
      <el-autocomplete
        v-model="dataGrid.listQuery.keywords"
        :fetch-suggestions="querySearchAsync"
        placeholder="请输入公司名称/统一社会信用代码"
        clearable
        style="width: 610px;"
        class="filter-item"
        @input="handleInput"
        @select="handleSelect"
        @keyup.enter.native="handleFilter"
      >
        <el-button slot="append" icon="el-icon-search" @click="getList" />
      </el-autocomplete>
    </div>
    <div style="padding-left: 20px;line-height: 40px;height: 40px;background-color: white;font-size: 12px;margin-top: 15px">
      为您找到 <span style="color: #2E4E8F;font-weight: bolder">{{ dataGrid.total }}</span> 家符合条件的企业，此处最多展示10000条。
    </div>
    <div style="clear: both;background-color: white;margin-top: 15px;min-height: 300px;padding:15px">
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
          prop="grpname"
          label="集团名称"
          align="left"
        >
          <template slot-scope="scope">
            <div v-html="grpnameHtml(scope.row)" />
          </template>
        </el-table-column>
        <el-table-column
          width="110"
          prop="grpembrs"
          :align="textCenter"
          label="集团成员总数"
        />
        <el-table-column
          width="110"
          prop="fdcmbrs"
          :align="textCenter"
          label="龙头企业数量"
        />
        <el-table-column
          width="110"
          prop="listedmbrs"
          :align="textCenter"
          label="核心成员数量"
        />
        <el-table-column
          width="110"
          prop="gxjsmbrs"
          :align="textCenter"
          label="一般成员数量"
        />
        <el-table-column
          prop="mbrlist"
          label="集团成员列表"
        >
          <template slot-scope="scope">
            <div style="word-break: break-all;white-space: normal;line-height: 17px">
              <span v-html="groupMemberHtml(scope.row)" />
              <el-link v-show=" (scope.row.tgroupRelationList !== undefined && scope.row.tgroupRelationList.length>3)" type="primary" :underline="false" @click="showMoreEnt(scope.row)">更多</el-link>
            </div>
          </template>
        </el-table-column>
        <el-table-column
          fixed="right"
          label="操作"
          width="150"
          :align="textCenter"
        >
          <template slot-scope="scope">
            <el-link type="primary" :underline="false" @click="viewDetailInfo(scope.row)" style="margin-right: 10px">详情</el-link>
            <el-link type="primary" :underline="false" @click="downLoadExcel(scope.row)">下载集团成员</el-link>
          </template>
        </el-table-column>
      </el-table>
      <pagination :total="100" :page.sync="dataGrid.listQuery.page" :limit.sync="dataGrid.listQuery.limit" @pagination="getList" />
    </div>
    <el-dialog title="集团企业列表（此处最多展示100条集团成员企业）" :visible.sync="dialogTableVisible" width="800px" style="height: 800px">
      <el-table :data="gridMemberData" style="font-size: 14px;" height="400">
        <el-table-column
          prop="toname"
          label="集团成员名称"
        >
          <template slot-scope="scope">
            <div v-html="scope.row.toname" />
          </template>
        </el-table-column>
        <el-table-column
          prop="reltype"
          label="集团成员角色"
        />
      </el-table>
    </el-dialog>
  </div>
</template>
<script>
import Pagination from "@/components/Pagination";
import request from "@/utils/request";
export default({
  name: "SupplyGroupList",
  components: { Pagination },
  data() {
    return {
      tableData: [],
      textCenter: "center",
      dialogTableVisible: false,
      gridMemberData: [],
      membersCurrentPage: 1,
      groupMembersTotal: 0,
      grpname: "",
      dataGrid:{
        total: 0,
        listQuery: {
          page: 1,
          limit: 10,
          keywords: undefined
        }
      }
    }
  },
  mounted() {
    this.getList()
  },
  methods: {
    groupMemberHtml(row) {
      const memberHtml = []

      if (row.tgroupRelationList === null || row.tgroupRelationList === undefined || row.tgroupRelationList.length <= 0) {
        return ''
      }

      if (row.tgroupRelationList.length <= 3) {
        row.tgroupRelationList.forEach(function(thisItem, index) {
          if (thisItem.toname !== undefined) {
            memberHtml.push(thisItem.toname)
          } else {
            memberHtml.push(thisItem.mbrname)
          }
        })
        return memberHtml.join(';').toLocaleString()
      }
      row.tgroupRelationList.forEach(function(thisItem, index) {
        if (index < 3) {
          if (thisItem.toname !== undefined) {
            memberHtml.push(thisItem.toname)
          } else {
            memberHtml.push(thisItem.mbrname)
          }
        }
      })
      return memberHtml.join(';').toLocaleString() + '...'
    },
    handleSizeChange(val) {
      this.dataGrid.listQuery.limit = val
      this.getList()
    },
    handleCurrentChange(val) {
      this.dataGrid.listQuery.page = val
      this.getList()
    },
    getList() {
      request({
        url: '/entInfo/getGroupList',
        method: 'post',
        params: {
          pageNum: this.dataGrid.listQuery.page,
          pageSize: this.dataGrid.listQuery.limit,
        },
        data: { keyword : this.dataGrid.listQuery.keywords }
      }).then(res => {
        this.tableData = res.data.item
        this.dataGrid.total = res.data.total
      }).catch(err => {
        console.log(err)
      })
    },
    grpnameHtml(row) {
      return `<span>${row.grpname}</span>`
    },
    handleMembersChange(val) {
      this.membersCurrentPage = val
    },
    showMoreEnt(row) {
      this.gridMemberData = row.tgroupRelationList
      this.dialogTableVisible = true
    },
    viewDetailInfo(row) {
      sessionStorage.setItem("grpInfo", JSON.stringify(row))
      this.$router.push({
        path: "/supplier_graph/supplyGroupDetail",
        query: {
          grpid: row.grpid
        }
      });
    },
    downLoadExcel(row) {
      this.$router.push({
        path: '/supplyGroupDetail',
        query: {
          grpid: row.grpid
        }
      })
    },
    handleFilter() {
      this.dataGrid.listQuery.page = 1;
      this.getList();
    },
    handleInput() {
      this.dataGrid.listQuery.page = 1;
      this.getList();
    },
    handleSelect() {
      this.dataGrid.listQuery.page = 1;
      this.getList();
    },
    querySearchAsync(queryString, cb) {
    },

  }
})
</script>
<style scoped>

</style>
