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
        <el-button slot="append" icon="el-icon-search" @click="handleFilter" />
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
        <el-table-column label="经营状态" prop="entstatusCn"></el-table-column>
        <el-table-column label="法人" prop="lerepname"></el-table-column>
        <el-table-column label="注册资本" prop="regcap"></el-table-column>
        <el-table-column label="注册时间" prop="esdate"></el-table-column>
        <el-table-column label="担保金额" prop="danbao_amount">
          <template slot-scope="scope">
            <span>0</span>
          </template>
        </el-table-column>
        <el-table-column label="被担保金额" prop="beidanbao_amount">
          <template slot-scope="scope">
            <span>0</span>
          </template>
        </el-table-column>
        <el-table-column label="详情">
          <template slot-scope="row">
            <el-link size="mini" type="primary" @click="viewDetail">详情</el-link>
          </template>
        </el-table-column>
      </el-table>
      <pagination :total="0" :page.sync="dataGrid.listQuery.page" :limit.sync="dataGrid.listQuery.limit" @pagination="getList" />
    </div>
  </div>
</template>
<script>
import Pagination from "@/components/Pagination";
import request from "@/utils/request";
export default({
  name: "WarrantyGraphList",
  components: { Pagination },
  data() {
    return {
      textCenter: "center",
      dialogTableVisible: false,
      gridMemberData: [],
      membersCurrentPage: 1,
      groupMembersTotal: 0,
      grpname: "",
      dataGrid:{
        total: 0,
        list: [],
        listQuery: {
          page: 1,
          limit: 10,
          keywords: undefined
        }
      }
    }
  },
  mounted() {
    this.getList();
  },
  methods: {
    getList() {
      this.dataGrid.listLoading = true;
      request({
        url: "/entInfo/getGuaranteeList",
        method: "post",
        params: { pageNum: this.dataGrid.listQuery.page,pageSize: this.dataGrid.listQuery.limit },
        data: { keyword: this.dataGrid.listQuery.keywords }
      }).then(res => {
        this.dataGrid.listLoading = false;
        this.dataGrid.list = res.data.item;
        this.dataGrid.total = res.data.total;
      });
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
      var restaurants = this.restaurants;
      var results = queryString
        ? restaurants.filter(this.createFilter(queryString))
        : restaurants;
      // 调用 callback 返回建议列表的数据
      cb(results);
    },
    viewDetail() {

      this.$router.push({
        path: "/supplier_graph/warrantyGraphDetail"
      });
    }
  }
})
</script>
<style scoped>

</style>
