<template>
  <div id="app" style="margin: auto;background-color: #f9f9f9;padding-top: 15px;min-height: 700px">
    <div style="background-color: white;line-height: 70px;height: 70px;text-align: center">
      <el-autocomplete
        placeholder="请输入公司名称/统一社会信用代码"
        clearable
        style="width: 610px;"
        class="filter-item"
      >
        <el-button slot="append" icon="el-icon-search" />
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
        <el-table-column label="法人" prop="lerepname"></el-table-column>
        <el-table-column label="注册资本" prop="regcap"></el-table-column>
        <el-table-column label="注册时间" prop="esdate"></el-table-column>
        <el-table-column label="交易笔数" prop="tradeNums">
          <template slot-scope="scope">
            <span>0</span>
          </template>
        </el-table-column>
        <el-table-column label="交易金额（人民币）" prop="tradeAmount">
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
      <pagination style="text-align: right;" :total="dataGrid.total" :page.sync="dataGrid.listQuery.page" :limit.sync="dataGrid.listQuery.limit" @pagination="getList" />
    </div>

  </div>
</template>
<script>
import request from "@/utils/request";
export default({
  name: "TransactionList",
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
    viewDetail() {
      this.$router.push({
        path: "/supplier_graph/transactionGraphDetail"
      });
    },
  }
})
</script>
<style scoped lang="scss">

</style>
