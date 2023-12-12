<template>
  <div id="app" style="margin: auto;padding-top: 15px;background-color: #F2F2F2;min-height: 1200px;padding-bottom: 15px">
    <div style="margin-left: 15px;background-color: white;padding-left: 10px;margin-right: 15px;padding-top: 3px;padding-right: 10px;padding-bottom: 10px;height: 300px">
      <div class="c-title-div">
        企业业务往来信息
      </div>
      <div style="height: 300px">
        <el-table
          height="200"
          :data = "entWarrantyList"
          border
        >
          <el-table-column label="企业ID" prop="entname"></el-table-column>
          <el-table-column label="企业名称" prop="waramount"></el-table-column>
          <el-table-column label="交易金额" prop="b_waramount"></el-table-column>
          <el-table-column label="交易类型"></el-table-column>
          <el-table-column label="交易币种"></el-table-column>
          <el-table-column label="交易对手名称"></el-table-column>
          <el-table-column label="交易对手ID"></el-table-column>
          <el-table-column label="交易日期"></el-table-column>
        </el-table>
      </div>
    </div>
    <div style="margin-left: 15px;background-color: white;padding-left: 10px;margin-right: 15px;padding-bottom: 20px;padding-top: 3px;margin-top: 15px">
      <div class="c-title-div">
        资金往来图谱
      </div>
      <div style="height:calc(100vh - 60px);" class="c-my-graph1">
        <RelationGraph
          ref="graphRef"
          :options="graphOptions"
          :on-node-click="onNodeClick"
          :on-line-click="onLineClick"
        >
        <template slot="node" slot-scope="{node}">
            <div class="my-node-content">
              <div v-if="node.data.spcType === 'ctrler'" style="width:300px;background-color: #409eff;font-size: 16px;color: #fff;height:40px;line-height: 40px;">
                交易对手
              </div>
              <div v-else-if="node.data.spcType === 'ctrled'" 
              style="width: 300px; background-color: #d9001b;color: #ffffff;height:40px;line-height: 40px;font-size: 16px;
              border-top-left-radius: 3px;border-top-right-radius: 3px;">
                被查询企业
              </div>
              <div v-else style="height:10px;" />
              <div style="padding:2px; height:50px;">
                <span :style="{color:node.fontColor}" class="c-node-label">{{ node.text }}</span>
              </div>
            </div>
            <div v-if="node.data.spcType === 'ctrled' " class="c-node-desc" style="line-height: 15px;">
               企业ID: <span>{{ node.data.textId }}</span>
            </div>
            <div v-if="node.data.spcType === 'ctrler' " class="c-node-desc" style="line-height: 15px;">
               交易对手ID: <span>{{ node.data.textId }}</span>
            </div>
          </template>
      </RelationGraph>
      </div>
    </div>
  </div>
</template>
<script>
import RelationGraph from 'relation-graph'

export default ({
  name: "SupplyGraphDetail",
  components: { RelationGraph },
  data() {
    return {
      entWarrantyList: [],
      graphOptions: {
        defaultNodeColor: '#fff',
        allowSwitchLineShape: true,
        allowSwitchJunctionPoint: true,
        // nodeShape: 1,
        defaultJunctionPoint: 'border',
        allowShowMiniToolBar: true
        // 这里可以参考"Graph 图谱"中的参数进行设置 https://seeksdream.github.io/#/docs/graph
      }
    }
  },
  mounted() {
    this.showGraph()
  },
  methods: {
    showGraph() {
      const jsonData = {
        rootId: 'a',
        nodes: [
          { id: 'a', text: '供应商1', nodeShape: 1,data:{spcType: 'ctrled' ,textId:'33423412341234s'}},
          { id: 'b', text: '供应商2', nodeShape: 1,data:{spcType: 'ctrler',textId:'33423412341234s'} },
          { id: 'c', text: '供应商3', nodeShape: 1,data:{spcType: 'ctrler',textId:'33423412341234s'} },
          { id: 'd', text: '供应商4', nodeShape: 1,data:{spcType: 'ctrler',textId:'33423412341234s'}},
          { id: 'e', text: '供应商5', nodeShape: 1,data:{spcType: 'ctrler',textId:'33423412341234s'}},
          { id: 'f', text: '北京阿里巴巴企业有限公司', nodeShape: 1,data:{spcType: 'ctrler',textId:'33423412341234s'} }
        ],
        lines: [
          { from: 'a', to: 'b', text: '关系1', fontColor: '#3399ff' },
          { from: 'a', to: 'c', text: '关系2', fontColor: '#3399ff' },
          { from: 'a', to: 'd', text: '关系3', fontColor: '#3399ff' },
          { from: 'a', to: 'e', text: '关系4', fontColor: '#3399ff' },
          { from: 'a', to: 'f', text: '关系5', fontColor: '#3399ff' }
        ]
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
.c-title-div {
  border-left: 10px solid #002060;
  padding-left: 10px;
  height: 30px;
  line-height: 30px;
  font-weight: bolder;
  font-size: 18px;
  color: #002060;
  margin-top: 10px;
  margin-bottom: 20px;
}
.c-my-graph1 ::v-deep .c-node-label{
  font-size: 14px;
  line-height: 50px;

}
.c-my-graph1 ::v-deep .my-node-content{
 
  color: #333 !important;
  
}
::v-deep .rel-node-shape-1{
  padding-top: 0px;
  padding-left: 0px;
  padding-right: 0px;
  height:125px;
  width:300px;
  text-align: center;
  border-radius: 3px;
  border:#cccccc solid 1px !important;
  /*display: flex;*/
  /*align-items: center;*/
  /*justify-content: center;*/
}
::v-deep .c-node-desc{
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
</style>
