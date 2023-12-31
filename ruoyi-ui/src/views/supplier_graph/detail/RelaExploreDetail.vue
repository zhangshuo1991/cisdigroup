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
            <div v-else-if="node.data.spcType === 'invest'"
                 style="width: 100%; background: #324154;color: white ;height:80px;font-size: 16px;
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
    const relaExplore = JSON.parse(sessionStorage.getItem("relaExplore"))
    this.$nextTick(() => {
      this.showGraph(relaExplore);
    })
  },
  methods: {
    showGraph(relaExplore_detail) {
      const setEntId = new Set()
      const nodes = []
      const links = []
      if (relaExplore_detail.sameListShareholder && relaExplore_detail.sameListShareholder.length>0) {
        let sameInvId = relaExplore_detail.sameListShareholder[0].invid
        nodes.push(
          {
            id:  relaExplore_detail.sameListShareholder[0].invid,
            text:  relaExplore_detail.sameListShareholder[0].invname,
            data: {
              spcType: 'inv'
            }
          }
        )
        relaExplore_detail.sameListShareholder.forEach(thisItem => {
          if (setEntId.has(thisItem.uniscid1)) {
            return
          }
          setEntId.add(thisItem.uniscid1)
          nodes.push({
            id: thisItem.uniscid1,
            text: thisItem.enterprise1,
            myicon: 'el-icon-setting',
            data: {
              spcType: 'ent'
            }
          })
          links.push({
            from: sameInvId,
            to: thisItem.uniscid1,
            text: '持股：' + parseFloat(thisItem.subconprop).toFixed(2) + '%',
            color: '#ff0000'
          })
        })
      }
      if (relaExplore_detail.sameListInvestment && relaExplore_detail.sameListInvestment.length>0) {
        let sameInvId = relaExplore_detail.sameListInvestment[0].invest_eid
        nodes.push(
          {
            id:  relaExplore_detail.sameListInvestment[0].invest_eid,
            text:  relaExplore_detail.sameListInvestment[0].invest_name,
            data: {
              spcType: 'invest'
            }
          }
        )
        const linksSet = new Set()
        relaExplore_detail.sameListInvestment.forEach(thisItem => {
          if (linksSet.has(thisItem.uniscid1)) {
            return
          }
          linksSet.add(thisItem.uniscid1)
          links.push({
            from: thisItem.uniscid1,
            to: sameInvId,
            text: '投资：' + parseFloat(thisItem.stock_percent).toFixed(2) + '%',
            color: '#324154'
          })
          if (setEntId.has(thisItem.uniscid1)) {
            return
          }
          setEntId.add(thisItem.uniscid1)
          nodes.push({
            id: thisItem.uniscid1,
            text: thisItem.enterprise1,
            myicon: 'el-icon-setting',
            data: {
              spcType: 'ent'
            }
          })


        })
      }
      const __graph_json_data = {
        rootId: nodes[0].id,
        nodes: nodes,
        links: links
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
