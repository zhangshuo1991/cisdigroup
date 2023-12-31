<template>
  <div>
    <div ref="myGraph" style="height:calc(100vh - 60px);" class="c-my-graph1">
      <RelationGraph ref="graphRef" :options="graphOptions" :on-node-click="onNodeClick" :on-line-click="onLineClick" :on-canvas-click="onCanvasClick">
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
              <div v-if="node.data.spcType === 'ctrler'"
                  style="font-size: 18px"
              >
                {{node.text }}
              </div>
              <div v-else-if="node.data.spcType === 'ctrled'"
                  style="font-size: 16px;line-height: 60px"
              >
                <span :style="{color:node.fontColor}" class="c-node-label">{{ node.text }}</span>
              </div>
            </div>
          </template>
      </RelationGraph>
      <transition name="fade">
        <div v-if="showDetailPanel"
             style="z-index: 999;padding:10px;background-color: #ffffff;width: 600px;
           border:#eeeeee solid 1px;right: 20px;top:100px;position: absolute;"
        >
          <el-table :data="biddingDetail"
                    style="font-size: 10px"
                    border
          >
            <el-table-column label="企业名称" prop="ename"></el-table-column>
            <el-table-column label="角色" prop="biddingRole"></el-table-column>
            <el-table-column label="中标金额" prop="bidAmount"></el-table-column>
            <el-table-column label="中标日期" prop="dt"></el-table-column>
            <el-table-column label="省份" prop="areaName"></el-table-column>
          </el-table>
        </div>
      </transition>

       <div class="imgstyle" style="width:100%;height: 80px;line-height: 80px; display: flex;justify-content: center; position: absolute;bottom:0;z-index: 600;">
              图例：<div style="color: #C0311E;"><img src="@/assets/images/ls.png" alt="">被查询企业</div>
              <div  style="color: #4ea2f0;"><img src="@/assets/images/tls.png" alt="">供应商</div>
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
      nodeMenuPanelPosition: { x: 0, y: 0 },
      showDetailPanel: false,
      biddingDetail: [],
      graphOptions: {
        layouts: [
          {
            label: '中心',
            layoutName: 'tree',
            layoutClassName: 'seeks-layout-tree',
            defaultJunctionPoint: 'border',
            defaultNodeShape: 0,
            defaultLineShape: 1,
            // 'centerOffset_x': -50,
            // 'centerOffset_y': 0,
            min_per_width: '60',
            min_per_height: '400'
          }
        ],
        defaultLineTextOffset_x: 35, // x 横向是相对于线条方向的
        defaultLineTextOffset_y: 20,
        showMaskWhenLayouting: true, // 在为节点分配好位置前，先让节点不可见，否则会出现短暂的节点挤在一团的显现，你可以去除此选线做一下对比
        defaultExpandHolderPosition: 'bottom',
        defaultLineShape: 4,
        defaultJunctionPoint: 'tb',
        defaultNodeShape: 1,
        defaultNodeWidth: 50,
        defaultNodeHeight: 250,
        defaultNodeBorderWidth: 0
        // 这里可以参考"Graph 图谱"中的参数进行设置 https://seeksdream.github.io/#/docs/graph
      },
      supplyDetail: {},
      uniscid: null
    }
  },
  mounted() {
    this.uniscid = this.$route.query.uniscid
    this.entname = this.$route.query.entname
    this.getGraphData()
  },
  methods: {
    getGraphData() {
      const loading = this.$loading({
        lock: true,
        text: '正在加载中...',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
      });
      request({
        url: '/entInfo/getTBiddingsallDetail/'+this.uniscid,
        method: 'get',
        params: {
          uniscid: this.uniscid
        }
      }).then(res => {
        const nodes = []
        nodes.push({
          id: this.uniscid,
          text: this.entname,
          nodeShape: 1,
          width: 300,
          height: 60,
          color: '#C0311E',
          data: {
            spcType: 'ctrled'
          },
        })
        const lines = []

        const setNodeId = new Set()
        res.data.forEach(thisItem => {
          if (setNodeId.has(thisItem.eid)) {
            return
          }
          setNodeId.add(thisItem.eid)
          nodes.push({
            id: thisItem.eid,
            text: thisItem.ename,
            nodeShape: 1,
            color: '#4ea2f0',
            data: {
              spcType: 'ctrler',
              dataId: thisItem.dataId
            },
          })
          lines.push({
            from: this.uniscid,
            to: thisItem.eid,
            color: '#ccc',
            fontColor: 'black',
            width: 300,
          })
        })
        this.showGraph(this.uniscid,nodes,lines)
        loading.close()
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
      this.showDetailPanel = false;
      const _base_position = this.$refs.myGraph.getBoundingClientRect();
      request({
        url: '/entInfo/getTBiddingsallByDataId/'+nodeObject.data.dataId,
        method: 'get',
      }).then(res => {
        this.biddingDetail = res.data
        this.showDetailPanel = true;
        this.nodeMenuPanelPosition.x = $event.clientX - _base_position.x + 10;
        this.nodeMenuPanelPosition.y = $event.clientY - _base_position.y + 10;
      })

    },
    onLineClick(lineObject, $event) {
      console.log('onLineClick:', lineObject)
    },
    onCanvasClick($event) {
      this.showDetailPanel = false
    }
  }
})
</script>
<style scoped lang="scss">
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
.fade-enter-active, .fade-leave-active {
  transition: opacity 0.5s;
}
.fade-enter, .fade-leave-to {
  opacity: 0;
}
</style>
