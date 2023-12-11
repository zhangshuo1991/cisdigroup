<template>
  <div ref="graphPanel" class="c-my-graph1" style="height:800px;">
    <div v-if="graph1_loading" v-loading="graph1_loading" element-loading-text="拼命加载关联方图谱中..." style="text-align: center;height: 500px;" />
    <div v-if="emptyGroupGraph1Data" style="height:300px;line-height: 200px;font-size: 16px;text-align: center;color: #888888;background-color: #ffffff;">
      无关联方图谱数据
    </div>
    <RelationGraph v-if="graph1_loading===false&&!emptyGroupGraph1Data" ref="seeksRelationGraph1" :user-graph-setting="userGraphSetting1" :on-node-click="onNodeClick">
      <template slot="node" slot-scope="{node}">
        <div class="my-node-content">
          <span style="color: #ffffff;font-size: 25px;">
            <i v-if="node.myicon !== 'fengwang-'" :class="node.myicon" />
            <svg-icon v-if="node.myicon === 'fengwang-'" icon-class="fengwang-" />
          </span>
          <div :style="{color:node.color}" class="c-node-label">{{ node.text }}</div>
        </div>
      </template>
      <template slot="bottomPanel">
        <div class="my-bottom-panel">
          节点筛选：
          <template v-for="(item) in orgTypeColorMap">
            <span v-if="item.isNode===true" :key="item.text" :style="{color: item.fontColor || item.color}" :class="{'c-type-item-disabled':item.checked===false}" class="c-type-item" @click="doTypeFilter(item)">
              <i v-if="item.isNode===false" class="el-icon-minus" />
              <i v-else class="el-icon-info" />
              {{ item.text }}
            </span>
          </template>
          <br>
          关系筛选：
          <template v-for="(item) in orgTypeColorMap">
            <span v-if="item.isNode===false" :key="item.text" :style="{color: item.fontColor || item.color}" :class="{'c-type-item-disabled':item.checked===false}" class="c-type-item" @click="doTypeFilter(item)">
              <i v-if="item.isNode===false" class="el-icon-minus" />
              <i v-else class="el-icon-info" />
              {{ item.text }}
            </span>
          </template>
        </div>
      </template>
    </RelationGraph>
  </div>
</template>

<script>
import RelationGraph from "relation-graph";

export default {
  name: 'Graph4RelatedParty',
  components: { RelationGraph },
  props: {
    entid: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      drawer: false,
      emptyGroupGraph1Data: false,
      orgTypeColorMap: {
        'n-green': { color: '#67C23A', text: '被查询企业', checked: true, isNode: true },
        'n-blue': { color: '#409EFF', text: '关联方', checked: true, isNode: true },
        'n-yellow': { color: '#E6A23C', text: '疑似关联方', checked: true, isNode: true },
        '01': { color: '#EC6941', text: '母公司', checked: true, isNode: false },
        '02': { color: '#FF9E00', text: '兄弟公司', checked: true, isNode: false },
        '03': { color: '#4EA2F0', text: '合营公司', checked: true, isNode: false },
        '04': { color: '#D1C0A5', text: '联营公司', checked: true, isNode: false },
        '05': { color: 'red', text: '主要投资者个人', checked: true, isNode: false },
        '06': { color: '#3D6EBF', text: '关键管理人员', checked: true, isNode: false },
        '07': { color: '#5FB878', text: '关系密切家族成员', checked: true, isNode: false },
        '08': { color: '#efefef', color11: '#ffeecf', fontColor: '#D1C0A5', text: '重要自然人关联企业', prevDesc: '', suffixDesc: '', checked: true, isNode: false },
        '09': { color: '#efefef', color11: '#ffeecf', fontColor: '#D1C0A5', text: '其他', prevDesc: '', suffixDesc: '', checked: true, isNode: false }
      },
      graph1_loading: true,
      nameFilter4TableView: '',
      innerDrawer: false,
      groupInfo: {},
      groupMemberList: [],
      currentTab4LeftPanel: '图谱统计',
      currentTab4GraphPanel: 'g-view',
      currentTab4NodeInfoPanel: 'basic',
      allData: {},
      pageSize: 20,
      showNodeInfoPanel: false,
      currentEntId: '',
      checkedEntId: '',
      userGraphSetting1: {
        fullTools: true,
        heightByContent: false,
        isShowMiniView: true,
        nodeShape: 0,
        lineShape: 1,
        layoutName: 'center',
        layoutDirection: 'v',
        hideTextByZoom: true,
        layoutConfig: { from: 'left' },
        viewSize: { width: 1600, height: 1 }
      }
    }
  },
  // computed: mapState({
  //   graphSetting: () => SeeksRGStore.state.graphSetting
  // }),
  created() {
  },
  mounted() {
    if (this.entid) {
      this.refresh(this.entid)
    }
  },
  methods: {
    transGroupData: function(data) {
      console.log('组装图谱数据：', data)
      var __nodes = []
      var __links = []
      data.nodes.forEach(thisNode => {
        var _preDesc = ''
        if (thisNode.entstatus === '1') {
          _preDesc = ''
        } else if (thisNode.entstatus === '2') {
          _preDesc = '[已吊销]'
        } else if (thisNode.entstatus === '3') {
          _preDesc = '[已注销]'
        }
        var _nodetype4filter = ''
        if (thisNode.tagnode === true) {
          _nodetype4filter = 'n-green'
        } else if (thisNode.isSus === true) {
          _nodetype4filter = 'n-yellow'
        } else {
          _nodetype4filter = 'n-blue'
        }
        var _color
        var _fontColor = '#ffffff'
        var _size = 50
        var colorConfig = this.orgTypeColorMap[_nodetype4filter]
        if (colorConfig) {
          _color = colorConfig.color
          _fontColor = colorConfig.fontColor
        }
        __nodes.push({
          styleClass: 'c-graph-node-' + (thisNode.nodetype === '01' ? 'person' : 'ent'),
          color: _color,
          fontColor: _fontColor,
          nodeShape: 0,
          border: 5,
          data: { nodetype: _nodetype4filter },
          myicon: (thisNode.nodetype === '01' ? 'el-icon-s-custom' : 'el-icon-office-building'),
          clickId: thisNode.nodeid,
          text: _preDesc + thisNode.nodename,
          width: _size,
          allowClick: thisNode.nodetype === '02',
          height: _size,
          id: thisNode.nodeid
        })
      })
      data.links.forEach(thisLink => {
        var __text = thisLink.relpartyp_cn || ''
        var __color
        var _fontColor
        if (thisLink.relpartyp) {
          var colorConfig = this.orgTypeColorMap[thisLink.relpartyp]
          if (colorConfig) {
            __color = colorConfig.color
            _fontColor = colorConfig.fontColor
          }
        }
        // if (thisLink.fromtype === '1' || thisLink.totype === '1') {
        //   return
        // }
        __links.push({
          from: thisLink.fromid,
          to: thisLink.toid,
          color: __color,
          fontColor: _fontColor,
          text: __text,
          data: {
            reltype: thisLink.relpartyp
          }
        })
      })
      return {
        rootId: this.currentEntId,
        nodes: __nodes,
        links: __links
      }
    },
    refresh(entid) {
      this.g_loading = true
      this.SeeksHttp.get('/smart-api/aff-enrelpar-ws/enrelpar/getEnrelparGraph', { entid: entid }).then(res => {
        var response = res.data
        this.graph1_loading = false
        if (!response.item) {
          this.emptyGroupGraph1Data = true
          this.$message({
            message: '找不到该集团的相关信息,已为您切换到股权视图！',
            type: 'warning'
          })
          return
        }
        this.groupInfo = response.item.groupInfo
        this.$nextTick(() => {
          this.$refs.seeksRelationGraph1.setJsonData(this.transGroupData(response.item.graph), function(seeksRGGraph) {
          })
        })
      })
    },
    checkNode(nodeData) {
      this.checkedEntId = nodeData.clickId
      this.$refs.entCoreInfoView.show(this.checkedEntId)
    },
    onNodeClick(nodeData) {
      console.log('click node:', nodeData)
      if (nodeData.allowClick === false) {
        return
      }
      this.checkNode(nodeData)
    },
    doTypeFilter(item) {
      item.checked = !item.checked
      // this.$message({
      //   message: '暂时没有提供筛选功能'
      // })
      var __checked_arr = []
      Object.keys(this.orgTypeColorMap).forEach(thisKey => {
        if (this.orgTypeColorMap[thisKey].checked)__checked_arr.push(thisKey)
      })
      console.error(__checked_arr)
      if (item.isNode !== false) {
        this.$refs.seeksRelationGraph1.nodeViewList.forEach(thisNode => {
          if (thisNode.data && thisNode.data.nodetype && __checked_arr.indexOf(thisNode.data.nodetype) === -1) {
            thisNode.isHide = true
          } else {
            thisNode.isHide = false
          }
        })
        this.$refs.seeksRelationGraph1.refresh()
      } else {
        this.$refs.seeksRelationGraph1.lineViewList.forEach(thisLine => {
          thisLine.relations.forEach(thisRelation => {
            if (thisRelation.data.reltype && __checked_arr.indexOf(thisRelation.data.reltype) === -1) {
              thisRelation.isHide = true
              console.error('hide relation:', thisRelation.data.reltype, thisRelation)
            } else {
              console.log('show relation:', thisRelation.data.reltype, thisRelation)
              thisRelation.isHide = false
            }
          })
        })
      }
    }
  }
}
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
  .el-tabs__nav-scroll{
    padding-left:30px;
  }
  .el-tabs{
    background-color: #ffffff;
  }
  html,body{
    background-color: #ffffff !important;
  }
  .c-right-panel .el-tabs__header{
    margin-bottom: 0px;
  }
</style>
<style scoped>
  .c-right-panel /deep/ .el-tabs__nav-scroll{
    padding-left:400px;
  }
  .c-my-graph1 /deep/ .c-node-text{
    color: #ffffff;
  }
  /** person **/
  .c-my-graph1 /deep/ .c-graph-node-person .c-node-text{
    font-size: 18px;
  }
  /** ent **/
  .c-my-graph1 /deep/ .c-graph-node-ent .c-node-text{
    font-size: 22px;
  }
  .c-my-graph1 /deep/ .c-node-label{
    position: absolute;
    width:150px;
    margin-top:15px;
    margin-left:-61px;
    text-align: center;
    justify-content: left;
    overflow: hidden;
    text-overflow:ellipsis;
    white-space: nowrap;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 1;
  }
  .c-my-graph1 /deep/ .c-node-label:hover{
    width:300px;
    margin-left:-136px;
  }
  .c-my-graph1 /deep/ .c-graph-node-person .c-node-label{
    margin-top:10px;
    color: #D1C0A5;
  }
  .c-my-graph1 /deep/ .rel-node-shape-0{
    padding: 0px;
    height:100%;
    width:100%;
    display: flex;
    align-items: center;
    justify-content: center;
  }
  @keyframes myACTRGNodeInit {
    from {
      box-shadow: 0px 0px 10px rgb(58, 91, 178);
    }
    15% {
      box-shadow: 0px 0px 1px #aaaaaa;
    }
    30% {
      box-shadow: 0px 0px 10px rgb(58, 91, 178);
    }
    45% {
      box-shadow: 0px 0px 1px #aaaaaa;
    }
    60% {
      box-shadow: 0px 0px 10px rgb(58, 91, 178);
    }
    to {
      box-shadow: 0px 0px 1px #aaaaaa;
    }
  }
  .c-my-graph1 /deep/ .my-bottom-panel{
    position: fixed;
    margin-top: 0px;
    bottom:0px;
    left:0px;
    background-color: red;
    height:100px;
    width:100%;
    z-index: 3333;
    width: 100%;
    height:50px;border-top: #efefef solid 1px;background-color: #ffffff;
    line-height: 25px;
    font-size: 14px;
    text-align: left;
    padding-left:300px;
  }
  .c-table-view{
    width: 400px;
    height:calc(100vh - 250px);
    position: absolute;left:0px;z-index: 2222;background-color: #ffffff;
    border-right:#efefef solid 1px;
    border-bottom:#efefef solid 1px;
    overflow: auto;
  }
  .c-row{
    clear: both;
    border-top:#efefef solid 1px;
    font-size: 14px;
  }
  .c-row-col{
    float: left;
    border-left:#efefef solid 1px;
    padding-left:5px;
    padding-right:5px;
    line-height: 25px;
  }
  .c-row-header{
    display: flex;
    align-items: center;
    justify-content: center;
    text-align: center;
    line-height: 16px;
  }
  .c-type-item{
    margin-left:20px;
    cursor: pointer;
    font-size: 14px;
    opacity: 1;
  }
  .c-type-item-disabled {
    opacity: 0.5;
    color: #cccccc !important;
  }

  .c-left-penal-button{
    height:75px;
    width:75px;
    font-size: 40px;
    line-height: 75px;
    text-align: center;
    border-radius: 50%;
    position: fixed;
    left:25px;
    top:calc(50vh - 60px);
    background-color: rgb(58, 91, 178);
    color: #ffffff;
    cursor: pointer;
    z-index: 1000;
    box-shadow: 0px 0px 8px rgb(46, 78, 143);
  }
  .c-left-penal-button:hover{
    box-shadow: 0px 0px 20px #FFA20A;
    border:#ffffff solid 1px;
    color: #FFA20A;
    -moz-transform: rotate(-89deg) translateX(-190px);
    animation-timing-function:linear;
    animation: flashButton 2s infinite;
  }
  @keyframes flashButton {
    from {
      box-shadow: 0px 0px 8px rgb(46, 78, 143);
    }
    30% {
      box-shadow: 0px 0px 20px #FFA20A;
    }
    to {
      box-shadow: 0px 0px 8px rgb(46, 78, 143);
    }
  }
  .c-longtou{
    border-bottom: #efefef solid 1px;
    padding:10px;
  }
  .c-longtou-skr{
    padding-top:5px;
    font-size: 12px;
    color: #888888;
  }
</style>
