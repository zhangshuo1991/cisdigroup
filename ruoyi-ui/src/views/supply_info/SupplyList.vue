<template>
  <section style="margin: auto;background-color: #f9f9f9;padding-top: 15px;padding-bottom: 30px;padding-right: 15px;">
    <div style="margin-left: 15px;background-color: white;line-height: 70px;height: 70px;text-align: center">

      <el-input
        v-model="keyword"
        style="width: 610px;margin-left: 58px;"
        placeholder="请输入公司名称/统一社会信用代码"
        @keyup.enter.native="getList"
      >
        <el-button slot="append" icon="el-icon-search" @click="getList"/>
      </el-input>
    </div>
    <div style="margin-left: 15px;">
      <div v-loading="loading" style="margin-top: 10px;">
        <div id="s-header-body">
          <div style="padding-left: 20px;line-height: 40px;height: 40px;background-color: white;font-size: 12px">
            为您找到 <span style="color: #2E4E8F;font-weight: bolder">{{ total }}</span> 家符合条件的企业，此处最多展示10000条。
          </div>
          <div v-for="(item,index) in top400List" :key="index" style="background-color: white;">
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
                    <td width="15%" style="height:14px;font-size:12px;font-weight:400;color:rgba(161,161,161,1);line-height:24px;"> {{ item.lerepname }}</td>
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
          <div class="block" style="text-align: right;width: 100%;">
            <el-pagination
              :current-page="currentPage"
              :page-size="pageSize"
              :total="total"
              layout="total, prev,pager, next"
              @current-change="handleCurrentChange"
            />
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import request from "@/utils/request";

export default ({
  name: "SupplyGraphDetail",
  data() {
    return {
      loading: false,
      total: 0,
      currentPage: 1,
      pageSize: 10,
      searchKey: '',
      top400List: [],
      searchType: 'entname',
      keyword: null,
      searchTypeOptions: [
        { value: 'entname', label: '企业名称' },
        { value: 'uniscid', label: '统一社会信用代码' }
      ]
    }
  },
  mounted() {
    this.getList()
  },
  methods: {
    getList() {
      this.loading = true;
      request({
        url: "/entInfo/getGuaranteeList",
        method: "post",
        params: { pageNum: this.currentPage, pageSize: this.pageSize },
        data: { keyword: this.keyword }
      }).then(res => {
        this.loading = false;
        this.top400List = res.data.item;
        this.total = res.data.total;
      });
    },
    viewDetailInfo(item) {
      sessionStorage.setItem('supplyOneDetail', JSON.stringify(item))
      this.$router.push({
        path: '/supplyOneDetail',
        query: { uniscid: item.uniscid }
      })
    },
    handleCurrentChange(val) {
      this.currentPage = val;
      this.getList();
    },
  }
})

</script>
<style>

</style>
<style scoped>
/deep/ .el-cascader-menu__wrap{
  height: 234px;
}
/deep/ .el-popper .el-input--medium .el-input__inner{
  height: 26px;
  line-height: 26px;
}
/deep/ .el-popover{
  height: auto;
}
/deep/ .el-card__header{
  padding: 5px 8px;
}
/deep/ .el-card__body{
  padding: 4px 8px;
}
/deep/ .el-loading-mask{
  z-index: 1998;
}
/deep/ .el-checkbox-button__inner{
  border: 0px white solid;
}

.c-title-td{
  height:22px;
  font-size:14px;

  font-weight:400;
  color:rgba(169,169,169,1);
  line-height:22px;
}
.c-title-option{
  height:22px;
  line-height:22px;
  font-size:14px;

  cursor: pointer;
  font-weight:400;
  padding-bottom: 5px;
  color:rgba(114,114,114,1);
}
.c-options-text{
  float: left;
  height:30px;
  font-size:14px;
  margin-left: 20px;
  padding-top: 2px;

  font-weight:400;
  color:rgba(114,114,114,1);
  line-height:30px;
}
.c-options-select{
  float: left;
  height:30px;
  font-size:14px;
  margin-left: 20px;
  padding-top: 2px;

  font-weight:bolder;
  color:#66b1ff;
  line-height:30px;
}
.c-self-selectd{
  color:#66b1ff;
}
.c-suggestion-name {
  width: 60%;
  float: left;

}
.c-suggestion-addr {
  text-overflow: ellipsis;
  overflow: hidden;
  width: 40%;
  font-size: 12px;
  float: left;
  text-align: right;
  color: #43a2f1;
}
.c-suggestion-uniscid{
  width: 60%;
  float: left;
  font-size: 12px;
  color: #b4b4b4;
}
</style>
