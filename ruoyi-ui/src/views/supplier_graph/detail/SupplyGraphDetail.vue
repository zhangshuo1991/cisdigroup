<template>
  <div>
    <div style="height:calc(100vh - 60px);" class="c-my-graph1">
      <RelationGraph ref="graphRef" :options="graphOptions" :on-node-click="onNodeClick" :on-line-click="onLineClick">
        <template #graph-plug>
            <div style="position: absolute;width:350px; right:0;top:0;z-index: 600;
            padding:10px;border-radius: 5px;color: #ffffff;font-size: 12px;"> 
              <el-input
                  v-model="searchText"
                  placeholder="图谱节点定位，请输入节点名称"  suffix-icon="el-icon-search"
                ></el-input>
            </div>
          </template>
        <template slot="node" slot-scope="{node}">
            <div class="my-node-content">
              <div v-if="node.data.spcType === 'ctrler'" style="background-color: #4eb548;width: 100%; color: #fff;height:40px;line-height: 40px;border-top-left-radius: 3px;border-top-right-radius: 3px;border-bottom: #cccccc solid 1px;">
                {{node.data.relpartyp }}
              </div>
              <div v-else-if="node.data.spcType === 'ctrled'" 
              style="width: 100%; background-color: #d9001b;color: #ffffff;height:40px;line-height: 40px;font-size: 16px;
              border-top-left-radius: 3px;border-top-right-radius: 3px;">
                被查询企业
              </div>
              <div v-else style="height:10px;" />
              <div style="padding:2px; height:50px;">
                <span :style="{color:node.fontColor}" class="c-node-label">{{ node.text }}</span>
              </div>
            </div>
          </template>
      </RelationGraph>
       <div class="imgstyle" style="width:100%;height: 80px;line-height: 80px; display: flex;justify-content: center; position: absolute;bottom:0;z-index: 600;">
              图例：<div style="color: #d9001b;"><img src="@/assets/images/ls.png" alt=""> 被查询企业</div>
              <div  style="color: #4eb548;"><img src="@/assets/images/tls.png" alt="">经销商</div>
              <div style="color: #3399ff;"><img src="@/assets/images/c.png" alt="">供应商</div>
          </div>
    </div>
  </div>
</template>
<script>
import RelationGraph from 'relation-graph'
import request from "@/utils/request";
export default ({
  name: "SupplyGraphDetail",
  components: { RelationGraph },
  data() {
    return {
      searchText:'',
      graphOptions: {
        allowShowMiniToolBar: true,
        defaultExpandHolderColor:'#3399ff',
        defaultExpandHolderPosition: "right",
        allowSwitchLineShape: true,
        allowSwitchJunctionPoint: true,
        defaultLineColor: 'rgba(255, 255, 255, 0.6)',
        defaultNodeColor: 'transparent',
        defaultNodeBorderWidth: 1,
        defaultNodeBorderColor: 'rgba(255, 255, 255, 0.3)',
        defaultNodeFontColor: '#ffffff',
        defaultPloyLineRadius: 10,
        defaultLineShape: 6,
        defaultJunctionPoint: 'lr',
        layout: {
          layoutName: 'tree',
          from: 'left',
          'min_per_width': 410, // 根据节点的宽度设置，这个是让图谱看起来偏亮的关键
          'min_per_height': 50,
        }
        // 这里可以参考"Graph 图谱"中的参数进行设置 https://seeksdream.github.io/#/docs/graph
      },
      uniscid: null
    }
  },
  mounted() {
    this.uniscid = this.$route.query.uniscid
    this.getGraphData()
    this.showGraph()
  },
  methods: {
    getGraphData() {
      request({
        url: '/entInfo/getSupplierRelation/'+this.uniscid,
        method: 'get',
      }).then(res => {
        const nodes = []
        nodes.push({
          id: res.data.baseEnterpriseInfo.uniscid,
          text: res.data.baseEnterpriseInfo.entname,
          data: res.data.baseEnterpriseInfo,
          nodeShape: 1,
          color: 'white',
          fontColor: 'black',
          width: 300,
          data: {
          spcType: 'ctrled'
        },
        })

        const lines = []

        res.data.supplierRelevanceList.forEach(thisItem => {
          nodes.push({
            id: thisItem.partyid,
            text: thisItem.partyname,
            // data: thisItem,
            nodeShape: 1,
            color: 'white',
            fontColor: 'black',
            width: 300,
            data: {
              spcType: 'ctrler',
              relpartyp: thisItem.relpartyp,
            },
          })

          lines.push({
            from: res.data.baseEnterpriseInfo.uniscid,
            to: thisItem.partyid,
            color: '#ccc',
            fontColor: 'black',
            width: 300,
          })
        })

        this.showGraph(res.data.baseEnterpriseInfo.uniscid,nodes,lines)
      })
    },
    showGraph(rootId,nodes,lines) {
      // list [node1,node2,]
      const jsonData = {
        rootId: rootId,
        nodes: nodes,
        lines: lines
      }
      // 以上数据中的node和link可以参考"Node节点"和"Link关系"中的参数进行配置
      this.$refs.graphRef.setJsonData(jsonData, (graphInstance) => {
        // Called when the relation-graph is completed
      })
    },
    onNodeClick(nodeObject, $event) {
      console.log('onNodeClick:', nodeObject)
    },
    onLineClick(lineObject, $event) {
      console.log('onLineClick:', lineObject)
    }
  }
})
</script>
<style scoped lang="scss">
.c-my-graph1 ::v-deep .rel-node-shape-1{
  padding-top: 0px;
  padding-left: 0px;
  padding-right: 0px;
  height:95px;
  width:300px;
  text-align: center;
  border-radius: 3px;
  border:#cccccc solid 1px !important;
  /*display: flex;*/
  /*align-items: center;*/
  /*justify-content: center;*/
}
.c-my-graph1 ::v-deep .c-node-label{
  font-size: 14px;
  line-height: 50px;
}
.c-my-graph1 ::v-deep .my-node-content{
  padding:0px;
display: flex;
align-items: center;
justify-content: center;
flex-direction: column;
  
}
.c-my-graph1 ::v-deep .c-node-desc{
  text-align: center;
  font-size: 14px;
  color: #888888;
  /*border-top: #dddddd solid 1px;*/
  margin-top:0px;
  padding-top:5px;
  /*background-color: #bbbbbb;*/
  // height:40px;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 300px;

}
.imgstyle{
  font-size: 14px;
  div{
    display: flex;
    align-items: center;
    padding-right: 20px;
    img{
    width: 22px;
    }
  }
}
</style>
