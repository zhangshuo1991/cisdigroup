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
        @keyup.enter.native="getList"
      >
        <el-button slot="append" icon="el-icon-search" @click="getList" />
      </el-autocomplete>
    </div>
    <div style="padding-left: 20px;line-height: 40px;height: 40px;background-color: white;font-size: 12px;margin-top: 15px">
      为您找到 <span style="color: #2E4E8F;font-weight: bolder">{{ dataGrid.total }}</span> 家符合条件的企业，此处最多展示10000条。
    </div>
    <div style="clear: both;background-color: white;margin-top: 15px;min-height: 300px;padding:15px">
      <el-table
        v-loading="dataGrid.listLoading"
        :data="dataGrid.list"
        border
        fit
        highlight-current-row
        :header-row-style="{height:'40px',fontSize: '13px'}"
        :header-cell-style="{padding:'0px'}"
        :row-style="{height:'40px',fontSize: '13px'}"
        :cell-style="{padding:'0px'}"
        style="width: 100%;"
      >
        <el-table-column label="序号" width="60" align="center">
          <template slot-scope="scope">{{ scope.$index+1 }}</template>
        </el-table-column>
        <el-table-column label="企业名称" prop="entname"></el-table-column>
        <el-table-column label="统一社会信用代码" prop="uniscid"></el-table-column>
        <el-table-column label="实际控制人" prop="lerepname">
          <template slot-scope="scope">
            {{ convertActualController(scope.row) }}
          </template>
        </el-table-column>
        <el-table-column label="注册资本">
          <template slot-scope="scope">{{ scope.row.regcap }}万元人民币</template>
        </el-table-column>
        <el-table-column label="详情">
          <template slot-scope="scope">
            <el-link size="mini" type="primary" @click="viewDetail(scope.row)">详情</el-link>
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
import store from "@/store";
export default({
  name: "SupplyActualController",
  components: { Pagination },
  data() {
    return {
      dataGrid: {
        listQuery: {
          keywords: null,
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
    }
  },
  mounted() {
    this.getList();
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
    convertActualController(row) {
      if (!row.tactualControllerList || row.tactualControllerList.length === 0) {
        return "";
      }
      let actualController = "";
      for (let i = 0; i < row.tactualControllerList.length; i++) {
        if (i > 0) {
          actualController += "，";
        }
        actualController += row.tactualControllerList[i].ctrlname;
      }
      return actualController;
    },
    getList() {
      this.dataGrid.listLoading = true
      request({
        url: "/entInfo/getActualController",
        method: "post",
        data: {
          keyword: this.dataGrid.listQuery.keywords,
        },
        params: { pageNum: this.dataGrid.listQuery.page, pageSize: this.dataGrid.listQuery.limit }
      }).then(res => {
        this.dataGrid.list = res.data.item
        this.dataGrid.total = res.data.total
        this.dataGrid.listLoading = false
      })
    },
    viewDetail(row) {
      if (!row.tactualControllerList || row.tactualControllerList.length === 0) {
        return "";
      }
      sessionStorage.setItem("actualControllerGraph", JSON.stringify(row));
      this.$router.push({ path: "/supplier_graph/actualControllerGraph" });
    }
  }
})
</script>
<style scoped lang="scss">

</style>
