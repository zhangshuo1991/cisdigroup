<template>
  <div style="margin: auto;background-color: #f9f9f9;padding-top: 15px;min-height: 700px">
    <div style="background-color: white;padding: 15px">
      <div class="c-title-div">
        关系探查
      </div>
      <div style="width: 800px;margin:auto">
        <div style="font-size: 14px;text-align: left">
          在一个基于公司股权 、高管任职、专利、招投标、涉诉等关系信息形成的企业复杂关系网络中，探查任意多个企业之
          间是否存在关联关系，并根据关联路径挖掘目标企业谱系中是存在异常关联。
        </div>
        <div style="margin-top: 10px">
          <el-input
            type="textarea"
            :autosize="{ minRows: 2, maxRows: 4}"
            placeholder="请输入企业名称，输入多个换行输入"
            v-model="textarea2">
            <template slot="prepend">企业名称：</template>
          </el-input>
        </div>
        <div style="margin-top: 10px">
          <el-form :label-position="labelPosition"  label-width="80px">
            <el-form-item label="查询层级">
              <el-radio-group v-model="level_radio" placeholder="请选择">
                <el-radio :label="1">1</el-radio>
                <el-radio :label="2">2</el-radio>
                <el-radio :label="3">3</el-radio>
                <el-radio :label="4">4</el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="企业关系">
              <el-checkbox-group v-model="check_relation"  placeholder="请选择">
                <el-checkbox :label="1">企业股东</el-checkbox>
                <el-checkbox :label="2">企业对外投资</el-checkbox>
                <el-checkbox :label="3" disabled>历史企业股东</el-checkbox>
                <el-checkbox :label="4" disabled>历史企业对外投资</el-checkbox>
              </el-checkbox-group>
            </el-form-item>
            <el-form-item label="人员关系">
              <el-checkbox-group v-model="check_person_relation" placeholder="请选择" style="width: 100%">
                <el-checkbox :label="1">自然人股东</el-checkbox>
                <el-checkbox :label="2">自然人对外投资</el-checkbox>
                <el-checkbox :label="3">公司管理人员</el-checkbox>
                <el-checkbox :label="4">管理人员其他公司任职</el-checkbox>
                <el-checkbox :label="5" disabled>历史自然人股东</el-checkbox>
                <el-checkbox :label="6" disabled>历史自然人对外投资</el-checkbox>
                <el-checkbox :label="7" disabled>历史公司管理人员</el-checkbox>
                <el-checkbox :label="8" disabled>历史管理人员其他公司任职</el-checkbox>
              </el-checkbox-group>
            </el-form-item>
            <el-form-item label="商业关系">
              <el-checkbox-group v-model="check_buss_relation" placeholder="请选择" style="width: 100%" >
                <el-checkbox :label="1">共同投标</el-checkbox>
                <el-checkbox :label="2">共同专利</el-checkbox>
                <el-checkbox :label="3">诉讼关系</el-checkbox>
              </el-checkbox-group>
            </el-form-item>
            <el-form-item label="潜在关系">
              <el-checkbox-group v-model="check_potential_relation" placeholder="请选择" style="width: 100%">
                <el-checkbox :label="1">共同联系方式</el-checkbox>
                <el-checkbox :label="2">共同注册地址</el-checkbox>
              </el-checkbox-group>
            </el-form-item>
          </el-form>
        </div>
        <div style="margin-top: 10px">
          <el-button type="primary" @click="handleSearch">查询</el-button>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import request from "@/utils/request";
export default({
  name: "RelaExplore",
  data() {
    return {
      textarea2: '',
      check_relation: [],
      level_radio: 1,
      check_person_relation: [],
      labelPosition: 'left',
      check_buss_relation: [],
      // 潜在关系
      check_potential_relation: [],
    }
  },
  methods:{
    handleSearch() {
      const loading = this.$loading({
        lock: true,
        text: '正在查询中',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
      });
      request({
        url: '/entInfo/relaExplore',
        method: 'post',
        data: {
          textarea2: this.textarea2,
          check_relation: this.check_relation,
          level_radio: this.level_radio,
          check_person_relation: this.check_person_relation,
          check_buss_relation: this.check_buss_relation,
          check_potential_relation: this.check_potential_relation
        }
      }).then(res => {
          loading.close();
          sessionStorage.setItem('relaExplore', JSON.stringify(res.data))
          this.$router.push({
            path: '/supplier_graph/RelaExploreDetail'
          })
        }
      )
      // this.$router.push({
      //   path: '/supplier_graph/RelaExploreDetail',
      //   query: {textarea2: this.textarea2, radio: this.radio, checkList: this.checkList}
      // })
    }
  }
})
</script>
<style scoped lang="scss">
.c-title-div{
  border-left: 3px solid #5297F7;
  padding-left: 10px;
  height: 30px;
  line-height: 30px;
  font-weight: bolder;
  font-size: 14px;
  color: #002060;
  margin-top: 10px;
  margin-bottom: 20px;
}
</style>
