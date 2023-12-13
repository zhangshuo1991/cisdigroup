<template>
  <div id="app">
    <div style="height: 300px;padding: 15px">
      <div class="c-data-h1">企业综合评分</div>
      <el-row>
        <el-col :span="12">
          <div style="text-align: center;padding-top: 90px">
            <div style="font-weight: bolder;font-size: 30px;color: #1ab394">{{ tevaluatingIndex.overall }}分</div>
            <div>企业评分</div>
          </div>
        </el-col>
        <el-col :span="12">
          <div ref="radarStats" style="height:300px;width:500px;" />
        </el-col>
      </el-row>
    </div>
    <div style="clear: both;background-color: white;padding: 15px;margin-top: 15px">
      <div class="c-data-h1">企业分项评价</div>
      <el-row>
        <el-col :span="4" style="padding: 5px">
          <div style="text-align: center;font-size: 14px;border: 1px solid gainsboro;height: 70px;margin-top: 5px">
            <div style="font-weight: bolder;color: white;background-color: #3A71A8;height: 35px;line-height: 35px">身份特征</div>
            <div style="color: #1ab394;height: 35px;line-height: 35px">
              <i class="el-icon-star-off"></i>{{ tevaluatingIndex.entIdentity }}
            </div>
          </div>
        </el-col>
        <el-col :span="4" style="padding: 5px">
          <div style="text-align: center;font-size: 14px;border: 1px solid gainsboro;height: 70px;margin-top: 5px">
            <div style="font-weight: bolder;color: white;background-color: #3A71A8;height: 35px;line-height: 35px">经营风险</div>
            <div style="color: #1ab394;height: 35px;line-height: 35px">
              <i class="el-icon-star-off"></i>{{ tevaluatingIndex.risk }}
            </div>
          </div>
        </el-col>
        <el-col :span="4" style="padding: 5px">
          <div style="text-align: center;font-size: 14px;border: 1px solid gainsboro;height: 70px;margin-top: 5px">
            <div style="font-weight: bolder;color: white;background-color: #3A71A8;height: 35px;line-height: 35px">行业实力</div>
            <div style="color: #1ab394;height: 35px;line-height: 35px">
              <i class="el-icon-star-off"></i>{{ tevaluatingIndex.competitiveness }}
            </div>
          </div>
        </el-col>
        <el-col :span="4" style="padding: 5px">
          <div style="text-align: center;font-size: 14px;border: 1px solid gainsboro;height: 70px;margin-top: 5px">
            <div style="font-weight: bolder;color: white;background-color: #3A71A8;height: 35px;line-height: 35px">发展速度</div>
            <div style="color: #1ab394;height: 35px;line-height: 35px">
              <i class="el-icon-star-off"></i>{{ tevaluatingIndex.development }}
            </div>
          </div>
        </el-col>
        <el-col :span="4" style="padding: 5px">
          <div style="text-align: center;font-size: 14px;border: 1px solid gainsboro;height: 70px;margin-top: 5px">
            <div style="font-weight: bolder;color: white;background-color: #3A71A8;height: 35px;line-height: 35px">活跃程度</div>
            <div style="color: #1ab394;height: 35px;line-height: 35px">
              <i class="el-icon-star-off"></i>{{ tevaluatingIndex.vigor }}
            </div>
          </div>
        </el-col>
        <el-col :span="4" style="padding: 5px">
          <div style="text-align: center;font-size: 14px;border: 1px solid gainsboro;height: 70px;margin-top: 5px">
            <div style="font-weight: bolder;color: white;background-color: #3A71A8;height: 35px;line-height: 35px">创新能力</div>
            <div style="color: #1ab394;height: 35px;line-height: 35px">
              <i class="el-icon-star-off"></i>{{ tevaluatingIndex.creativity }}
            </div>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>
<script>
import * as echarts from "echarts";
export default({
  name: "ScoreDetail",
  data() {
    return {
      radarStats: null,
    }
  },
  props:{
    tevaluatingIndex:{
      type: Object,
      default: {}
    }
  },
  mounted() {
    const values = [
      this.tevaluatingIndex.entIdentity,
      this.tevaluatingIndex.risk,
      this.tevaluatingIndex.competitiveness,
      this.tevaluatingIndex.development,
      this.tevaluatingIndex.vigor,
      this.tevaluatingIndex.creativity
    ]
    this.radarStats = echarts.init(this.$refs.radarStats, "macarons");
    this.initRadarStats(values);
  },
  methods: {
    initRadarStats(values) {
      this.radarStats.setOption({
        title: {
          text: 'Basic Radar Chart',
          show: false
        },
        legend: {
          data: ['综合实力评分'],
          show: false
        },
        tooltip: {},
        radar: {
          radius: '60%',
          // shape: 'circle',
          center: ['50%', '40%'],
          // shape: 'circle',
          indicator: [
            { name: '身份特征', max: 1000 },
            { name: '经营风险', max: 1000 },
            { name: '行业实力', max: 1000 },
            { name: '发展速度', max: 1000 },
            { name: '活跃程度', max: 1000 },
            { name: '创新能力', max: 1000 }
          ]
        },
        series: [
          {
            name: 'Budget vs spending',
            type: 'radar',
            data: [
              {
                value: values,
                name: '综合实力评分'
              }
            ]
          }
        ]
      });
    },

  }
})
</script>
<style scoped lang="scss">
.c-data-h1{
  height: 25px;
  line-height: 25px;
  font-size: 14px;
  color: #464c5b;
  font-weight: bolder;
}
</style>
