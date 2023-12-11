<template>
  <el-dialog
    title="风险信息详细信息"
    :visible.sync="visible"
    width="76%"
    :before-close="onCancel"
  >
    <div class="mybos">
      <div>行政处罚</div>
      <el-table
        :data="[]"
        border
        fit
        highlight-current-row
        :header-row-style="{ height: '40px', fontSize: '13px' }"
        :header-cell-style="{ padding: '0px', textAlign: 'center' }"
        :row-style="{ height: '40px', fontSize: '13px' }"
        :cell-style="{ padding: '0px', 'text-align': 'center' }"
        style="width: 100%"
      >
        <el-table-column label="企业ID" prop="entname"></el-table-column>
        <el-table-column label="企业名称" prop="creditCode"></el-table-column>
        <el-table-column
          label="处罚编号"
          prop="legalPerson"
        ></el-table-column>
        <el-table-column label="违法类型" prop="regCapital"></el-table-column>
        <el-table-column
          label="处罚机关"
          prop="regTime"
        ></el-table-column>
        <el-table-column label="处罚结果" prop="tradeNums"></el-table-column>
        <el-table-column
          label="处罚决定日期"
          min-width="100px"
          prop="tradeAmount"
        ></el-table-column>
        <el-table-column
          label="处罚内容"
          prop="regTime"
        ></el-table-column>
        <el-table-column
          label="处罚依据"
          prop="regTime"
        ></el-table-column>
        <el-table-column
          label="公示日期"
          prop="regTime"
        ></el-table-column>
        <el-table-column
          label="处罚事由"
          prop="regTime"
        ></el-table-column>
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
::v-deep
.el-descriptions__body
.el-descriptions__table
.el-descriptions-item__cell {
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
