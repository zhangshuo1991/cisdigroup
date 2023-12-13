<template>
  <div id="app" style="margin: auto;padding-top: 10px;background-color: #F2F2F2;min-height: 900px">
    <div v-loading="baseInfoLoading" style="margin-left: 20px;background-color: white;padding-left: 10px;margin-right: 20px;padding-top: 3px;padding-bottom: 20px;padding-right: 15px">
      <div class="c-title-div">
        实际控制人相关信息
      </div>
      <table class="c-base-table" style="background-color: white;" width="100%">
        <tbody>
        <tr>
          <td class="c-td-title">目标企业名称：</td>
          <td class="c-td-text">{{ actualControllerGraph.entname }}</td>
          <td class="c-td-title">统一社会信用代码/工商注册号 ：</td>
          <td class="c-td-text">
            <span>{{ actualControllerGraph.uniscid }}</span>
          </td>
        </tr>
        <tr>
          <td class="c-td-title">目标企业经营状态：</td>
          <td class="c-td-text">
            <span>
              {{ actualControllerGraph.entstatusCn }}
            </span>
          </td>
          <td class="c-td-title">控制目标企业类型：</td>
          <td class="c-td-text" style="width: 30%">
            {{ actualControllerGraph.tactualControllerList[0].isidrctrl }}
          </td>
        </tr>
        <tr>
          <td class="c-td-title">是否为上市公司 ：</td>
          <td class="c-td-text">
            {{ ctrlers.length>0?ctrlers[0].islist_cn: '' }}
            <span v-if="ctrlers.length>0" v-show="ctrlers[0].islist_cn === '是'">({{ ctrlers.length>0?ctrlers[0].skcode: '' }})</span>
          </td>
          <td class="c-td-title">上市公司自行披露实际控制人 ：</td>
          <td class="c-td-text">
            <span v-if="ctrlers.length>0">{{ ctrlers[0].publishCtrler === null? '':ctrlers[0].publishCtrler }}</span>
          </td>
        </tr>
        </tbody>
      </table>
      <div id="s-info-ctrlList" style="width: 100%;height: 50px;padding-top: 10px">
        <div v-for="item in actualControllerGraph.tactualControllerList" :key="item.ctrlid" class="c-ctrler-info">
          <div style="float: left">
            <svg-icon icon-class="shijikongzhiren" style="font-size: 28px;color: #0071C2" />
          </div>
          <div style="float: left;margin-left: 5px;">
            <div style="font-size: 12px;font-weight: bolder;color: black">
              {{ item.ctrlname }}
              <span style="color: red;font-weight: bolder">({{ item.shratio+'%' }})</span>
            </div>
            <div style="font-size: 10px;font-weight: bolder;color: #808080;margin-top: 3px;">
              <span>类型:{{ item.ctrltype }}</span>
              <span>控制方式:{{ item.isidrctrl }}</span>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div style="margin-left: 20px;background-color: white;padding-left: 10px;margin-right: 20px;padding-top: 3px;margin-top: 15px;padding-right: 15px">
      <div class="c-title-div">
        控制路径图谱
      </div>
      <div style="height:calc(100vh - 160px);" class="c-my-graph1">
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
              <div v-if="node.data.spcType === 'ctrler'" style="background-color: #FFF838;color: #333333;height:40px;line-height: 40px;border-top-left-radius: 3px;border-top-right-radius: 3px;border-bottom: #cccccc solid 1px;">
               实际控制人
              </div>
              <div v-else-if="node.data.spcType === 'ctrled'" style="background-color: #128bed;color: #ffffff;height:40px;line-height: 40px;border-top-left-radius: 3px;border-top-right-radius: 3px;">
               被查询企业
              </div>
              <div v-else style="height:10px;" />
              <div style="padding:5px;height:50px;padding-top:10px;">
                <span :style="{color:node.fontColor}" class="c-node-label">{{ node.text }}</span>
              </div>
            </div>
            <div v-if="node.data.regcap" class="c-node-desc" style="line-height: 15px;">
              注册资本:<span>{{ node.data.regcap }}{{ node.data.regcapcur_cn }}</span>
              状态:<span>{{ node.data.entstatusCn }}</span>
            </div>
          </template>
        </RelationGraph>
         <div class="imgstyle" style="width:100%;font-size: 14px;height: 80px;line-height: 80px; display: flex;justify-content: center; position: absolute;bottom:0;z-index: 600;">
              图例：<div style="color: #FFF838; display: flex;align-items: center;padding-right: 20px;"><img style="width: 22px;" src="@/assets/images/sjkzr.png" alt="">实际控制人 </div>
              <div  style="color: #999;display: flex;align-items: center;padding-right: 20px;"><img style="width: 22px;" src="@/assets/images/b.png" alt="">中间企业</div>
              <div style="color: #3399ff;display: flex;align-items: center;padding-right: 20px;"><img style="width: 22px;" src="@/assets/images/c.png" alt="">被查询企业</div>
          </div>
      </div>
    </div>
  </div>
</template>
<script>
import RelationGraph from 'relation-graph'

export default({
  name: "SupplyActualController",
  components: { RelationGraph },
  data() {
    return {
      baseInfoLoading: false,
      baseInfoHidden: false,
      graphOptions: {
        allowSwitchLineShape: true,
        allowSwitchJunctionPoint: true,
        nodeShape: 1,
        defaultJunctionPoint: 'border',
        allowShowMiniToolBar: true
        // 这里可以参考"Graph 图谱"中的参数进行设置 https://seeksdream.github.io/#/docs/graph
      },
      ctrlers: [],
      actualControllerGraph: {}
    }
  },
  mounted() {
    this.actualControllerGraph = JSON.parse(sessionStorage.getItem("actualControllerGraph"))
    this.$nextTick(() => {
      this.showGraph()
    })
  },
  methods:{
    showGraph() {
      const nodes = []
      const lines = []
      nodes.push({
        id: this.actualControllerGraph.uniscid,
        text: this.actualControllerGraph.entname,
        color: 'white',
        fontColor: 'black',
        width: 300,
        data: {
          regcap: this.actualControllerGraph.regcap,
          regcapcur_cn: this.actualControllerGraph.regcapcur_cn,
          entstatusCn: this.actualControllerGraph.entstatusCn,
          spcType: 'ctrled'
        },
        nodeShape: 1,
      })
      this.ctrlers = this.actualControllerGraph.tactualControllerList
      this.ctrlers.forEach((item) => {
        nodes.push({
          id: item.ctrlid,
          text: item.ctrlname,
          color: 'white',
          fontColor: 'black',
          width: 300,
          data: {
            spcType: 'ctrler'
          },
          nodeShape: 1,
        })
        lines.push({
          from: item.ctrlid,
          to: this.actualControllerGraph.uniscid,
          text: item.shratio+'%',
          data: item,
          fontColor:'#3399ff',
          lineShape: 1,
          lineStyle: this.statusStyle(item.isidrctrl)
        })
      })
      const jsonData = {
        rootId: this.actualControllerGraph.uniscid,
        nodes: nodes,
        lines: lines
      }
      console.log('jsonData:', jsonData)
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
    querySearchAsync(queryString, cb) {
    },
    handleSelect(item) {
    },
    handleInput(value) {
    },
    handleFilter() {
    },
    viewDetail() {
      this.$router.push({ path: "/supplier_graph/actualControllerGraph" });
    },
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
  }
})
</script>
<style scoped>
.c-title-div{
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
.c-td-title{
  background:#f9f9f9;
  border:1px solid #D0CECE;
  padding-left: 10px;
  font-size:13px;
  font-family:Microsoft YaHei;
  font-weight:400;
  height: 40px;
  color:rgba(96,96,96,1);
}
.c-base-table,.c-base-table tr th, .c-base-table tr td {
  border-bottom:1px solid #D0CECE;
  border-top:1px solid #D0CECE;
}
.c-base-table { border-collapse: collapse;}
.c-td-text{
  padding-left: 10px;
  text-align: left;
  height: 30px;
  font-size:13px;
  line-height: 25px;
  font-family:Microsoft YaHei;
  font-weight:400;
  color:rgba(96,96,96,1);
  background:white;
  border:1px solid #D0CECE;
}
.c-ctrler-info{
  margin-right: 5px;
  float: left;
  padding-top: 7px;
  padding-left: 5px;
  padding-right: 5px;
  min-width: 180px;
  height: 53px;
  font-size: 20px;
  font-family: "Microsoft YaHei";
  border-radius: 5px;
  border: 1px solid #BFBFBF;
}

.c-my-graph1 /deep/ .rel-node-shape-1{
  padding-top: 0px;
  padding-left: 0px;
  padding-right: 0px;
  height:95px;
  width:230px;
  text-align: center;
  border-radius: 3px;
  border:#cccccc solid 1px !important;
  /*display: flex;*/
  /*align-items: center;*/
  /*justify-content: center;*/
}
.c-my-graph1 /deep/ .c-node-label{
  font-size: 14px;
}
.c-my-graph1 /deep/ .my-node-content{
  padding:0px;
  height:70px;
  width: 300px;
}
.c-my-graph1 /deep/ .c-node-desc{
  text-align: center;
  font-size: 12px;
  color: #888888;
  /*border-top: #dddddd solid 1px;*/
  margin-top:0px;
  padding-top:5px;
  /*background-color: #bbbbbb;*/
  height:25px;
  width: 300px;
}
</style>
