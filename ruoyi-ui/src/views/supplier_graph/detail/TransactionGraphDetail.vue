<template>
  <div id="app" style="margin: auto;padding-top: 15px;background-color: #F2F2F2;min-height: 1200px;padding-bottom: 15px">
    <div style="margin-left: 15px;background-color: white;
    padding-left: 10px;margin-right: 15px;padding-top: 3px;padding-right: 10px;padding-bottom: 10px;height: 400px">
      <div class="c-title-div">
        企业业务往来信息
      </div>
      <div style="height: 300px">
        <el-table
          height="320"
          :data = "entWarrantyList"
          :cell-style="{fontSize: '10px',height: '30px'}"
          :row-style="{height: '30px'}"
          :header-cell-style="{fontSize: '12px', height: '30px'}"
          border
        >
          <el-table-column label="企业ID" prop="credit_no"></el-table-column>
          <el-table-column label="企业名称" prop="name"></el-table-column>
          <el-table-column label="交易金额" prop="trans_amount"></el-table-column>
          <el-table-column label="交易类型" prop="trans_type"></el-table-column>
          <el-table-column label="交易币种" prop="trans_cur"></el-table-column>
          <el-table-column label="交易对手名称" prop="counterparty"></el-table-column>
          <el-table-column label="交易对手ID" prop="counterparty_id"></el-table-column>
          <el-table-column label="交易日期" prop="trans_date"></el-table-column>
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
        <template #graph-plug>
          <div style="position: absolute;width:350px; right:0;top:0;z-index: 600;
              padding:10px;border-radius: 5px;color: #ffffff;font-size: 12px;">
            <el-input
              v-model="searchText"
              placeholder="图谱节点定位，请输入节点名称"  suffix-icon="el-icon-search"
              @click="searchNode"
            ></el-input>
          </div>
        </template>
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
      searchText: '',
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
    this.$axios.get('/js/t_supplierfund_info_txt_202312301705.json').then(res => {
      this.entWarrantyList = res.data.t_supplierfund_info_txt
    })
    this.showGraph()
  },
  methods: {
    showGraph() {
      const jsonData = {
        rootId: 'a',
        nodes: [
          { id: 'd', text: '湖北晋控气体有限公司', nodeShape: 1,data:{spcType: 'ctrler',textId:'91420582MA49MT6K3R'}},
          { id: 'e', text: '中国华能集团清洁能源技术研究院有限公司', nodeShape: 1,data:{spcType: 'ctrler',textId:'9111000071782666XW'}},
          { id: 'a', text: '沈阳透平机械股份有限公司', nodeShape: 1,data:{spcType: 'ctrled',textId:'912101007157146053'} }
        ],
        lines: [
          { from: 'a', to: 'd', text: '交易对手', fontColor: '#3399ff' },
          { from: 'a', to: 'e', text: '交易对手', fontColor: '#3399ff' },
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
    },
    searchNode() {
      const allNodes = this.$refs.graphRef.getNodes()
      for (let i = 0; i < allNodes.length; i++) {
        // 模糊匹配
        if (allNodes[i].text.indexOf(this.searchText) !== -1) {
          this.$refs.graphRef.focusNode(allNodes[i].id)
          break
        }
      }
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
