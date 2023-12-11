<template>
  <div id="app" style="margin: auto;background-color: #f9f9f9;padding-top: 15px;padding-bottom: 30px;padding-right: 15px;padding-left: 15px;min-height: 900px">
    <div style="background-color: white;line-height: 70px;height: 70px;text-align: center">
      <el-autocomplete
        v-model="dataGrid.listQuery.keywords"
        placeholder="请输入公司名称/统一社会信用代码"
        clearable
        style="width: 610px;"
        class="filter-item"
      >
        <el-button slot="append" icon="el-icon-search" @click="getList"/>
      </el-autocomplete>
    </div>
    <div style="padding-left: 20px;line-height: 40px;height: 40px;background-color: white;font-size: 12px;margin-top: 15px">
      为您找到 <span style="color: #2E4E8F;font-weight: bolder">{{ dataGrid.total }}</span> 家符合条件的企业，此处最多展示10000条。
    </div>
    <div style="clear: both;background-color: white;margin-top: 15px;min-height: 300px;padding:15px">
      <div v-loading="dataGrid.listLoading" style="margin-top: 10px;">
        <div v-for="(item,index) in dataGrid.list" :key="index" style="background-color: white;">
          <el-card class="box-card" shadow="hover" style="cursor: pointer;margin-top: 8px" @click.native="viewDetailInfo(item)">
            <div slot="header" class="clearfix">
              <span style="height:30px;font-size:14px;font-weight:400;color:rgba(68,68,68,1);line-height:30px;">{{ item.entname }}</span>
              <span style="height:30px;font-size:14px;color: black;line-height:30px;"> （{{ item.entstatusCn }}） </span>
            </div>
            <div>
              <table style="font-size: 14px;">
                <tbody>
                <tr>
                  <td width="5%" align="left" style="height:14px;font-size:12px;font-weight:400;color:rgba(161,161,161,1);line-height:24px;">法定代表人：</td>
                  <td width="15%" style="height:14px;font-size:12px;font-weight:400;color:rgba(161,161,161,1);line-height:24px;"> {{ item.legal_name }}</td>
                  <td width="5%" align="left" style="height:14px;font-size:12px;font-weight:400;color:rgba(161,161,161,1);line-height:24px;">注册资本：</td>
                  <td width="30%" style="height:14px;font-size:12px;font-weight:400;color:rgba(161,161,161,1);line-height:24px;">{{ item.regcap.toLocaleString() }}{{ item.regcapcurCn }}</td>
                </tr>
                <tr>
                  <td align="left" style="height:14px;font-size:12px;font-weight:400;color:rgba(161,161,161,1);line-height:24px;">成立时间：</td>
                  <td style="height:14px;font-size:12px;font-weight:400;color:rgba(161,161,161,1);line-height:24px;">{{ item.esdate }}</td>
                  <td align="left" style="height:12px;font-size:12px;font-weight:400;color:rgba(161,161,161,1);line-height:24px;padding-top: 10px;">登记地址：</td>
                  <td style="height:14px;font-size:12px;font-weight:400;color:rgba(161,161,161,1);line-height:24px;padding-top: 10px;text-overflow:ellipsis;display:-webkit-box;-webkit-box-orient:vertical;-webkit-line-clamp:2;">{{ item.regAddr }}</td>
                </tr>
                </tbody>
              </table>
            </div>
          </el-card>
        </div>
        <pagination style="text-align: right;" :total="dataGrid.total" :page.sync="dataGrid.listQuery.page" :limit.sync="dataGrid.listQuery.limit" @pagination="getList" />
      </div>
    </div>
  </div>
</template>
<script>
import Pagination from "@/components/Pagination";
import request from "@/utils/request";
export default({
  name: "GraylistInfo",
  components: { Pagination },
  data(){
    return {
      dialogVisibleDetail: false,
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
        url: "/entInfo/getRiskList",
        method: "post",
        params: { pageNum: this.dataGrid.listQuery.page, pageSize: 10 },
        data: { keyword: this.dataGrid.listQuery.keywords }
      }).then(res => {
        this.dataGrid.listLoading = false;
        this.dataGrid.list = res.data.item;
        this.dataGrid.total = res.data.total;
      });
    },
    viewDetailInfo(item) {
      sessionStorage.setItem("riskInfo", JSON.stringify(item));
      this.$router.push({
        path: "/supplier_risk/GraylistInfoDetail",
        query: { entname: item.entname, uniscid: item.uniscid }
      });
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
