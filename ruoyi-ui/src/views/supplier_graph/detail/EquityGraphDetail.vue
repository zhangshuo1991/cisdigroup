<template>
  <div id="app">
    <div style="height:calc(100vh - 60px);border: 1px solid gray;margin: 20px" class="c-my-graph1">
      <RelationGraph
        ref="graphRef"
        :on-node-click="onNodeClick"
        :on-line-click="onLineClick"
        :options="graphOptions" 
        :style="{ width: '100%', height: '100%' }">
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
              <div v-if="node.data.spcType === 'ctrler'" style=""></div>
              <div v-else-if="node.data.spcType === 'ctrled'" 
              style="width: 100%; background-color: #128bed;color: #ffffff;height:40px;line-height: 40px;font-size: 16px;
              border-top-left-radius: 3px;border-top-right-radius: 3px;">
                被查询企业
              </div>
              <div v-else style="height:10px;" />
              <div style="padding:2px; height:50px;">
                <span :style="{color:node.fontColor}" class="c-node-label">{{ node.text }}</span>
              </div>
            </div>
            <div v-if="node.data.regcap" class="c-node-desc" style="line-height: 15px;">
              认缴金额:<span>{{ node.data.regcap }}{{ node.data.regcapcur_cn }}</span>  状态:<span>{{ node.data.entstatusCn }}</span>
            </div>
          </template>
      </RelationGraph>
    </div>

  </div>
</template>
<script>
import request from "@/utils/request";
import RelationGraph from "relation-graph";
export default ({
  name: "EquityGraphDetail",
  components: {
    RelationGraph
  },
  data() {
    return {
      uniscid: null,
      searchText:'',
      graphOptions: {
        allowSwitchLineShape: true,
        allowSwitchJunctionPoint: true,
        nodeShape: 1,
        defaultJunctionPoint: 'border',
        allowShowMiniToolBar: true,
        defaultExpandHolderColor:'#3399ff',
        defaultExpandHolderPosition: "bottom",
       
        'layouts': [
          {
            'label': '中心',
            'layoutName': 'tree',
            from: 'top',
 
          }
        ]
      },
    }
  },
  mounted() {
    this.uniscid = this.$route.query.uniscid
    this.getGraphDetail()
  },
  methods: {
    statusStyle(status) {
      if (status !== '1') {
        const style = {
          fontSize: '14px',
          fontFamily: 'Microsoft YaHei',
          fontWeight: '400',
          color: 'rgba(248,130,51,1)'
        }
        return style
      }
      const style = {
        fontSize: '14px',
        fontFamily: 'Microsoft YaHei',
        fontWeight: '400',
        color: '#19B94F'
      }
      return style
    },
    getGraphDetail() {
      request({
        url: '/entInfo/getShareHolderAndInvestment/'+this.uniscid,
        method: 'get',
      }).then(res => {

        console.log('8888888',res)
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
        res.data.investmentList.forEach(item => {
          nodes.push({
            id: item.investCreditNo,
            text: item.investName,
            data: item,
            nodeShape: 1,
            width: 300,
           
          })
        })
        res.data.stockholderList.forEach(item => {
          nodes.push({
            id: item.invid,
            text: item.invname,
            data: item,
            nodeShape: 1,
            color: 'white',
            fontColor: 'black',
            width: 300,
            data: {
              spcType: 'ctrler',
              regcap: item.subconam,
              regcapcur_cn: item.regcapcur_cn,
              entstatusCn: item.entstatusCn,
            },
          })
        })
        const lines = []
        res.data.investmentList.forEach(item => {
          lines.push({
            from: res.data.baseEnterpriseInfo.uniscid,
            to: item.investCreditNo,
            text: (parseFloat(item.stockPercent)*100).toLocaleString() + '%',
            color: 'green',
            fontColor:'#3399ff',
            width: 300,
          })
        })

        res.data.stockholderList.forEach(item => {
          lines.push({
            from: item.invid,
            to: res.data.baseEnterpriseInfo.uniscid,
            text: parseFloat(item.subconprop).toLocaleString() + '%',
            color: '#ccc',
            width: 300,
            fontColor:'#3399ff',
            lineShape: 1,
            lineStyle: this.statusStyle(item.isidrctrl)
          })
        })

        this.showGraph(res.data.baseEnterpriseInfo.uniscid, nodes, lines)
      })
    },
    onNodeClick(nodeObject, $event) {
      console.log('onNodeClick:', nodeObject)
    },
    onLineClick(lineObject, $event) {
      console.log('onLineClick:', lineObject)
    },
    showGraph(rootId, nodes, lines) {
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
.c-my-graph1 ::v-deep .c-collapsed{
// background: #3399ff !important;
}
</style>
