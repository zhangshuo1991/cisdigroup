<template>
  <div id="app" style="margin: auto;background-color: #f9f9f9;padding: 15px;min-height: 700px">
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
        <el-button slot="append" icon="el-icon-search" @click="handleFilter" />
      </el-autocomplete>
    </div>
    <div style="clear: both;background-color: white;margin-top: 15px;min-height: 300px;padding:15px">
      <el-table
        :data="dataGrid.list"
        border
        :header-row-style="{height:'40px',fontSize: '13px'}"
        :header-cell-style="{padding:'0px'}"
        :row-style="{height:'40px',fontSize: '13px'}"
        :cell-style="{padding:'0px'}"
        style="width: 100%;"
      >
        <el-table-column type="index" label="序号"></el-table-column>
        <el-table-column
          prop="entname"
          label="企业名称"
          width="240"
        >
        </el-table-column>
        <el-table-column
          prop="uniscid"
          label="统一社会信用代码"
          width="190"
        />
        <el-table-column
          prop="entstatusCn"
          label="企业经营状态"
          style="text-align: left"
        >
        </el-table-column>
        <el-table-column
          prop="listed"
          label="是否上市"
        >
          <template slot-scope="scope">
            {{ scope.row.listed == 1 ? "是" : "否" }}
          </template>
        </el-table-column>
        <el-table-column
          prop="relaNums"
          label="关联企业数量"
        />
        <el-table-column
          prop="listedRelaNums"
          width="280"
          label="上市公司自行披露关联企业数量"
        />
        <el-table-column
          fixed="right"
          label="操作"
          width="100"
        >
          <template slot-scope="scope">
            <el-button type="text" size="small" @click="viewDetailInfo(scope.row)">详情</el-button>
          </template>
        </el-table-column>
      </el-table>
      <pagination style="text-align: right;" :total="dataGrid.total" :page.sync="dataGrid.listQuery.page" :limit.sync="dataGrid.listQuery.limit" @pagination="getList" />
    </div>
  </div>
</template>
<script>
import Pagination from "@/components/Pagination";
import request from "@/utils/request";
export default({
  name: "SupplyRelaList",
  components: { Pagination },
  data() {
    return {
      dataGrid: {
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
  methods:{
    handleInput(val) {
    },
    handleSelect(item) {
    },
    handleFilter() {
    },
    querySearchAsync(queryString, cb) {
    },
    getList() {
      this.dataGrid.listLoading = true
      request({
        url: "/entInfo/getRelatedEnterprise",
        method: "post",
        data: { keyword: this.dataGrid.listQuery.keywords },
        params: { pageNum: this.dataGrid.listQuery.page,pageSize: this.dataGrid.listQuery.limit }
      }).then(res => {
        this.dataGrid.list = res.data.item
        this.dataGrid.total = res.data.total
        this.dataGrid.listLoading = false
      })
    },
    viewDetailInfo(row) {
      sessionStorage.setItem("baseRelaInfo", JSON.stringify(row))
      this.$router.push({
        path: "/supplier_graph/relaDetailGraph",
        query: { uniscid: row.uniscid }
      });
    }
  }
})
</script>
<style scoped lang="scss">

</style>
