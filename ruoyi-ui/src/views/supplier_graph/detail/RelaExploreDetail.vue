<template>
  <div id="app">
    <div style="height:calc(100vh - 60px);" class="c-my-graph1">
      <RelationGraph
        :options="graphOptions"
        ref="relationGraph"
      >
        <template slot="node" slot-scope="{node}">
          <div class="my-node-content">
            <div v-if="node.data.spcType === 'ent'"  style="width: 100%;
               background: #1980df;color: #1a0404;height:80px;font-size: 16px;
             border-radius: 150px;border:6px solid #3399ff;" class="imgqy">
              <img src="@/assets/images/qiye.png" alt="">
            </div>
            <div v-else-if="node.data.spcType === 'inv'"
                 style="width: 100%; background: #4eb548;color: #1a0404;height:80px;font-size: 16px;
               border-radius: 150px;border:6px solid #76dd64;" class="imgqy">
              <img src="@/assets/images/qiye.png" alt="">
            </div>
            <div v-else style="height:10px;" />
            <div style="padding:2px; height:50px;">
              <span :style="{color:node.fontColor}" class="c-node-label">{{ node.text }}</span>
            </div>
          </div>
          <!-- <div v-if="node.data.regcap" class="c-node-desc" style="line-height: 15px;">
            认缴金额:<span>{{ node.data.regcap }}{{ node.data.regcapcur_cn }}</span>
             状态:<span>{{ node.data.entstatusCn }}</span>
          </div> -->
        </template>
      </RelationGraph>
    </div>
  </div>
</template>
<script>
import RelationGraph from "relation-graph";
export default ({
  name: "RelaExploreDetail",
  components: {
    RelationGraph
  },
  data() {
    return {
      graphOptions: {
        defaultNodeBorderWidth: 0,
        allowSwitchLineShape: true,
        allowSwitchJunctionPoint: true,
        defaultLineShape: 1,
        defaultJunctionPoint: 'border',
        layouts: [
          {
            label: '中心',
            layoutName: 'center',
            layoutClassName: 'seeks-layout-center'
          }
        ],
      },
    }
  },
  mounted() {
    this.showGraph();
  },
  methods: {
    showGraph() {
      const __graph_json_data = {
        rootId: '6F274BC721AE7C57E0539601A8C0ACD65',
        nodes: [
          {
            id: '91110000105520221M', text: '中国华冶科工集团有限公司',
            myicon: 'el-icon-setting',
            data: {
              spcType: 'ent'
            }
          },
          {
            id: '91510100201906490X', text: '中国五冶集团有限公司',
            myicon: 'el-icon-star-on',
            data: {
              spcType: 'ent'
            }          },
          {
            id: '91210300765442193F', text: '中冶焦耐工程技术有限公司',
            myicon: 'el-icon-setting',
            data: {
              spcType: 'ent'
            }
          },
          {
            id: '91500000202801842U', text: '中冶赛迪集团有限公司',
            myicon: 'el-icon-setting',
            data: {
              spcType: 'ent'
            }
          },
          {
            id: '91110302757700076U', text: '中冶京诚工程技术有限公司',
            myicon: 'el-icon-star-on',
            data: {
              spcType: 'ent'
            }
          },
          {
            id: '912101007695618516', text: '中冶沈勘工程技术有限公司',
            myicon: 'el-icon-headset',
            data: {
              spcType: 'ent'
            }
          },
          {
            id: '6F274BC721AE7C57E0539601A8C0ACD65', text: '中国冶金科工股份有限公司',
            data: {
              spcType: 'inv'
            }
          }
        ],
        lines: [
          { from: '6F274BC721AE7C57E0539601A8C0ACD65', to: '91110000105520221M', text: '持股：100%',color:'#ff0000' },
          { from: '6F274BC721AE7C57E0539601A8C0ACD65', to: '91510100201906490X', text: '持股：98.58%',color:'#ff0000' },
          { from: '6F274BC721AE7C57E0539601A8C0ACD65', to: '91210300765442193F', text: '持股：87.81%',color:'#ff0000' },
          { from: '6F274BC721AE7C57E0539601A8C0ACD65', to: '91500000202801842U', text: '持股：100%',color:'#ff0000' },
          { from: '6F274BC721AE7C57E0539601A8C0ACD65', to: '91110302757700076U', text: '持股：88.72%',color:'#ff0000' },
          { from: '6F274BC721AE7C57E0539601A8C0ACD65', to: '912101007695618516', text: '持股：100%',color:'#ff0000' }
        ]
      };
      this.$refs.relationGraph.setJsonData(__graph_json_data, (graphInstance) => {
        // 这些写上当图谱初始化完成后需要执行的代码
      });
    },
  }
})
</script>
<style scoped lang="scss">
.c-my-graph1 ::v-deep .c-node-label{
  font-size: 12px;
  // line-height: 50px;
  color: #3399ff;
  position: absolute;
  z-index: 999;
  width: 400px;
  left:0;
  margin-left: -150px;
  margin-top: 5px;
}
.imgqy{
  display: flex;
  align-items: center;
  justify-content: center;
  img{
    width: 40px;
  }
}
</style>
