<template>
  <el-dialog title="黑名单详细信息" :visible.sync="visible" width="76%" :before-close="onCancel">
    <div class="mybos">
      <div>经营异常</div>
      <el-table :data="dataGridlist" border fit highlight-current-row :header-row-style="{ height: '40px', fontSize: '13px' }"
                :header-cell-style="{ padding: '0px', textAlign: 'center' }" :row-style="{ height: '40px', fontSize: '13px' }"
                :cell-style="{ padding: '0px', 'text-align': 'center' }" style="width: 100%;">
        <el-table-column label="序号" width="60" align="center">
          <template slot-scope="scope">{{ scope.$index + 1 }}</template>
        </el-table-column>
        <el-table-column label="企业ID" prop="entname"></el-table-column>
        <el-table-column label="企业名称" prop="creditCode"></el-table-column>
        <el-table-column label="列入经营异常名录原因" prop="legalPerson"></el-table-column>
        <el-table-column label="列入日期" prop="regCapital"></el-table-column>
        <el-table-column label="移出经营异常名录原因" prop="regTime"></el-table-column>
        <el-table-column label="移出日期" prop="tradeNums"></el-table-column>
        <el-table-column label="列入经营异常做出决定机关" min-width="100px" prop="tradeAmount"></el-table-column>
        <el-table-column label="移出经营异常做出决定机关" prop="regTime"></el-table-column>
        <el-table-column label="数据日期" prop="tradeNums"></el-table-column>
      </el-table>
    </div>
    <span slot="footer" class="dialog-footer">
      <el-button type="primary" @click="onCancel">关 闭</el-button>
    </span>
  </el-dialog>
</template>
<script>
export default {
  data() {
    return {
      rowlabel: {
        width: "200px",
        borderColor: "#ccc",
      },
      conentSty: {
        width: "200px",
        borderColor: "#ccc",
      },
      dataGridlist: [],
    };
  },
  props: {
    visible: {
      type: Boolean,
      default: false,
    },
  },
  watch: {},
  methods: {
    onCancel() {
      this.$emit("visibleFlag", false);
    },
    onSubmit(val) {
      // this.visible = false
      this.$emit("visible", val);
    },
  },
};
</script>
<style lang="scss" scoped>
.dialog-footer {
  display: flex;
  justify-content: center;
}

// ::v-deep .el-dialog{
//        display: flex;
//        flex-direction: column;
//        margin:0 !important;
//        position:absolute;
//        top:50%;
//        left:50%;
//        transform:translate(-50%,-50%);
//        max-height:calc(100% - 30px);
//        max-width:calc(100% - 30px);
//    }
//   ::v-deep  .el-dialog .el-dialog__body{
//        flex:1;
//        overflow: auto;
//    }
::v-deep .el-descriptions__body .el-descriptions__table .el-descriptions-item__cell {
  text-align: center;
}

.mybos ::v-deep .el-descriptions__body .el-descriptions__table {
  width: 100%;
  color: #333;
  text-align: center;
}

::v-deep .el-descriptions .el-descriptions-item__label {
  color: #333;
  background-color: rgba(242, 242, 242, 1);
}
</style>
