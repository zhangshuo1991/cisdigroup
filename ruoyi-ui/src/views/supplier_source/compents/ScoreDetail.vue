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
          <el-popover
            placement="top"
            width="400"
            trigger="hover"
            content="表征企业开展经营活动的天然优势，体现市场和社会公众对企业的基础认知情况。身份特征相关的属性往往会对企业的行业准入、
            融资成本和商业模式等产生较大影响，也将潜移默化地影响企业的经营与发展。身份特征指标包括企业经过多年经营积累积淀下的身份特征和股东、
            法定代表人特征等各类优质要素为企业增加的商誉特征。"
          >
            <div slot="reference" style="text-align: center;font-size: 14px;border: 1px solid gainsboro;height: 70px;margin-top: 5px">
              <div style="font-weight: bolder;color: white;background-color: #3A71A8;height: 35px;line-height: 35px">身份特征</div>
              <div style="color: #1ab394;height: 35px;line-height: 35px">
                <i class="el-icon-star-off"></i>{{ tevaluatingIndex.entIdentity }}
              </div>
            </div>
          </el-popover>
        </el-col>
        <el-col :span="4" style="padding: 5px">
          <el-popover
            placement="top"
            width="400"
            trigger="hover"
            content="表征企业生产经营中隐含的风险大小以及对风险的抵抗能力，反映企业的经营稳定性。经营风险指标主要包括严重事件风险、
            财务风险、合规风险、关联风险和频繁变更风险五个方面。此外，经营风险侧重考虑企业的负面的信息，
            根据一段时间内的风险事件发生的严重程度、数量、事件时间来构建模型，进行评分。"
          >
            <div slot="reference" style="text-align: center;font-size: 14px;border: 1px solid gainsboro;height: 70px;margin-top: 5px">
              <div style="font-weight: bolder;color: white;background-color: #3A71A8;height: 35px;line-height: 35px">经营风险</div>
              <div style="color: #1ab394;height: 35px;line-height: 35px">
                <i class="el-icon-star-off"></i>{{ tevaluatingIndex.risk }}
              </div>
            </div>
          </el-popover>

        </el-col>
        <el-col :span="4" style="padding: 5px">
          <el-popover
            placement="top"
            width="400"
            trigger="hover"
            content="表征企业在国内行业中的竞争状况，体现了企业的行业地位。行业实力指标的纠正了行业差异形成的竞争差异，
            更全面和准确的评价企业的经营情况。行业实力指标主要包括企业财务指标相对行业的分位数情况、
            非财务类指标相对行业的中位数情况以及企业收入增速相对行业平均增速情况。"
          >
            <div slot="reference" style="text-align: center;font-size: 14px;border: 1px solid gainsboro;height: 70px;margin-top: 5px">
              <div style="font-weight: bolder;color: white;background-color: #3A71A8;height: 35px;line-height: 35px">行业实力</div>
              <div style="color: #1ab394;height: 35px;line-height: 35px">
                <i class="el-icon-star-off"></i>{{ tevaluatingIndex.competitiveness }}
              </div>
            </div>
          </el-popover>
        </el-col>
        <el-col :span="4" style="padding: 5px">
          <el-popover
            placement="top"
            width="400"
            trigger="hover"
            content="表征企业近期各个指标的变化方向和速度，体现企业的成长性和稳定性。
            发展速度指标主要包括财务类、市场类、人才类和创新类四个方面。根据企业在两个时间段内的数据项的同比变化情况进行评分。"
          >
            <div slot="reference" style="text-align: center;font-size: 14px;border: 1px solid gainsboro;height: 70px;margin-top: 5px">
              <div style="font-weight: bolder;color: white;background-color: #3A71A8;height: 35px;line-height: 35px">发展速度</div>
              <div style="color: #1ab394;height: 35px;line-height: 35px">
                <i class="el-icon-star-off"></i>{{ tevaluatingIndex.development }}
              </div>
            </div>
          </el-popover>
        </el-col>
        <el-col :span="4" style="padding: 5px">
          <el-popover
            placement="top"
            width="400"
            trigger="hover"
            content="表征企业开展经营活动的活跃程度，体现企业的生存状态。活跃度并不考虑事件的好与坏，更侧重考虑企业事件的影响大小。
            活跃程度包括企业经营活动活跃程度、创新活跃程度、投融资活跃程度等方面。评价体系将经营事件数据按照影响程度的强弱分为三类，
            分别是与经营活动直接相关的经营事件、与经营活动强相关的创新和投融资事件和与经营活动弱相关的企业成立和变更事件。"
          >
            <div slot="reference" style="text-align: center;font-size: 14px;border: 1px solid gainsboro;height: 70px;margin-top: 5px">
              <div style="font-weight: bolder;color: white;background-color: #3A71A8;height: 35px;line-height: 35px">活跃程度</div>
              <div style="color: #1ab394;height: 35px;line-height: 35px">
                <i class="el-icon-star-off"></i>{{ tevaluatingIndex.vigor }}
              </div>
            </div>
          </el-popover>
        </el-col>
        <el-col :span="4" style="padding: 5px">
          <el-popover
            placement="top"
            width="400"
            trigger="hover"
            content="表征企业在技术、品牌和商业模式上的创新程度，体现企业的创新性。创新能力指标包括创新投入、技术创新程度、
            品牌创新四个方面。技术创新不仅包括知识产权的创造，还包括新技术和新模式的应用。从数据项看，
            一方面体现在企业知识产权的数量、类型、申请时间和授权时间等，另一方面体现在外部投资机构的地位、投资次数、时间等。"
          >
            <div slot="reference" style="text-align: center;font-size: 14px;border: 1px solid gainsboro;height: 70px;margin-top: 5px">
              <div style="font-weight: bolder;color: white;background-color: #3A71A8;height: 35px;line-height: 35px">创新能力</div>
              <div style="color: #1ab394;height: 35px;line-height: 35px">
                <i class="el-icon-star-off"></i>{{ tevaluatingIndex.creativity }}
              </div>
            </div>
          </el-popover>

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
