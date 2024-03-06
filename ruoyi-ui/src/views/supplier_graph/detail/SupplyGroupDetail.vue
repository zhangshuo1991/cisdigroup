<template>
  <div style="margin: auto;background-color: #f9f9f9;padding: 15px;min-height: 700px">
    <div v-loading="labelLoading" style="background-color: white;padding:15px">
      <table style="font-size: 15px;font-family: 'Microsoft YaHei';width: 100%">
        <tbody>
          <tr>
            <td style="padding-left: 10px;width: 130px;padding-right: 10px" rowspan="2">
              <i class="el-icon-office-building" style="font-size: 80px;color: gainsboro;border: 1px solid gainsboro" />
            </td>
            <td style="font-size: 24px;font-weight: 800;font-family: 'Microsoft YaHei';height: 60px;width: 83%;">
              <div style="float: left">集团名称：{{ grpInfo.grpname }}</div><br>
              <div style="margin-top: 14px">
                <div class="c-count-div">集团成员：{{ grpInfo.grpembrs }}</div>
                <div class="c-count-div">龙头企业：{{ grpInfo.hysl }}</div>
                <div class="c-count-div">核心企业：{{ grpInfo.licnums }}</div>
                <div class="c-count-div">一般企业：{{ grpInfo.listedmbrs }}</div>
                <div class="c-count-div">上市公司：{{ grpInfo.statembrs }}</div>
              </div>
            </td>
            <td>
            </td>
        </tr>
        </tbody>
      </table>
    </div>
    <div
      style="background-color: white;
      margin-top: 10px;
      border-bottom: 1px solid #e3e8ee;
      padding-top: 2px;
      padding-left: 15px"
    >
      <div class="c-title-div">
        集团客户特征
      </div>
      <div v-loading="labelLoading" style="margin-top: 10px;;margin-right: 20px">
        <table class="c-base-table" style="width: 100%">
          <tbody>
            <tr>
              <td class="c-td-title" style="width: 25%;">集团成员企业数量：</td>
              <td class="c-td-text c-td-text-padding" style="width: 25%;">{{ grpInfo.grpembrs }}</td>
              <td class="c-td-title" style="width: 25%;">集团内注册资本总额：</td>
              <td class="c-td-text c-td-text-padding" style="width: 25%;">{{ grpInfo.grpregcaps }} 万元</td>
            </tr>
            <tr>
              <td class="c-td-title" style="width: 25%;">集团内房地产行业企业数量：</td>
              <td class="c-td-text c-td-text-padding" style="width: 25%;">{{ grpInfo.fdcmbrs }}</td>
              <td class="c-td-title" style="width: 25%;">集团内上市公司数量：</td>
              <td class="c-td-text c-td-text-padding" style="width: 25%;">{{ grpInfo.listedmbrs }}</td>
            </tr>
            <tr>
              <td class="c-td-title" style="width: 25%;">集团内高新技术企业数量：</td>
              <td class="c-td-text c-td-text-padding" style="width: 25%;">{{ grpInfo.gxjsmbrs }}</td>
              <td class="c-td-title" style="width: 25%;">集团内国有企业数量：</td>
              <td class="c-td-text c-td-text-padding" style="width: 25%;">{{ grpInfo.licnums }}</td>
            </tr>
            <tr>
              <td class="c-td-title" style="width: 25%;">集团内国有企业占比：</td>
              <td class="c-td-text c-td-text-padding" style="width: 25%;">{{  ( grpInfo.qyjzd * 100 ).toLocaleString()+'%' }}</td>
              <td class="c-td-title" style="width: 25%;">集团内行业数量：</td>
              <td class="c-td-text c-td-text-padding" style="width: 25%;">{{ grpInfo.hysl }}</td>
            </tr>
            <tr>
              <td class="c-td-title" style="width: 25%;">行业集中度：</td>
              <td class="c-td-text c-td-text-padding" style="width: 25%;">{{ ( grpInfo.hyjzd * 100).toLocaleString()+'%' }}</td>
              <td class="c-td-title" style="width: 25%;">集团内成员注册省份数量：</td>
              <td class="c-td-text c-td-text-padding" style="width: 25%;">{{ grpInfo.zcsfsl }}</td>
            </tr>
            <tr>
              <td class="c-td-title" style="width: 25%;">区域集中度：</td>
              <td class="c-td-text c-td-text-padding" style="width: 25%;">{{ ( grpInfo.qyjzd * 100 ).toLocaleString() + '%' }}</td>
              <td class="c-td-title" style="width: 25%;">集团拥有金融牌照种类数量：</td>
              <td class="c-td-text c-td-text-padding" style="width: 25%;">{{ grpInfo.zcsfsl }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
    <div
      style="background-color: white;
      margin-top: 10px;
      border-bottom: 1px solid #e3e8ee;
      padding-top: 2px;
      padding-left: 15px"
    >
      <div class="c-title-div">
        集团成员列表
      </div>
      <div v-loading="membersLoading" style="margin-top: 10px;background-color: white;padding-bottom: 20px;padding-right: 30px;">

        <el-tabs v-model="activeName" @tab-click="handleClick">
          <el-tab-pane label="集团成员列表" name="second">
            <div class="demo-input-suffix" style="padding-bottom: 10px;padding-top: 10px">
              <el-input
                v-model="groupInput"
                placeholder="请输入集团成员名称"
                prefix-icon="el-icon-search"
                style="width: 270px"
                @input="handleGroup"
              />
            </div>
            <table class="c-base-table" style="width: 100%">
              <thead>
              <tr>
                <th class="c-th-header" style="width: 4%;">序号</th>
                <th class="c-th-header" style="width: 23%;">集团成员名称</th>
<!--                <th class="c-th-header" style="width: 7%;">经营状态</th>-->
<!--                <th class="c-th-header" style="width: 7%;">成员角色</th>-->
<!--                <th class="c-th-header" style="width: 8%;">是否上市</th>-->
<!--                <th class="c-th-header" style="width: 18%;">关联龙头</th>-->
                <th class="c-th-header" style="width: 7%;">穿透比例</th>
                <th class="c-th-header" style="width: 18%;">穿透详情</th>
              </tr>
              </thead>
              <tbody>
              <tr v-for="(item,index) in groupMembers.slice((currentPage-1)*10,currentPage*10) " :key="index">
                <td class="c-td-text" style="text-align: center">{{ index+1 }}</td>
                <td class="c-td-text" style="padding-left: 10px">{{ item.toname }}</td>
                <td class="c-td-text" style="text-align: center">
                  <span v-if="item.property">{{ parseFloat(item.property).toFixed(2) }} %</span>
                </td>
                <td class="c-td-text" style="font-size: 10px;padding-left: 10px">
                  {{ handleRelPath(item.relpath) }} <br>
                </td>
              </tr>
              </tbody>
            </table>
            <div class="block" style="text-align: right;width: 100%;">
              <el-pagination
                :current-page="currentPage"
                :page-size="10"
                :total="groupMembers.length"
                layout="total, prev,pager, next"
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
              />
            </div>
          </el-tab-pane>
          <el-tab-pane label="龙头企业的实际控制人" name="first">
            <table class="c-base-table" style="width: 98%">
              <thead>
              <tr>
                <th class="c-th-header">序号</th>
                <th class="c-th-header">龙头企业名称</th>
                <th class="c-th-header">实际控制人</th>
                <!--                  <th class="c-th-header">集团成员经营状态</th>-->
                <!--                  <th class="c-th-header">是否为上市公司</th>-->
              </tr>
              </thead>
              <tbody>
              <tr v-for="(item,index) in leadingMembers.slice((currentLeadPage-1)*10,currentLeadPage*10)" :key="index">
                <td class="c-td-text" style="text-align: center">{{ index+1 }}</td>
                <td class="c-td-text  c-td-text-padding">{{ item.memberName }}</td>
                <td class="c-td-text  c-td-text-padding">{{ item.controllers }}</td>
                <!--                  <td class="c-td-text"><span :style="( statusStyle(item.entstatus))">{{ item.entstatus_cn }}</span></td>-->
                <!--                  <td class="c-td-text">{{ item.islist_cn }}<span v-show="(item.islist_cn==='是')">({{ item.skcode }})</span></td>-->
              </tr>
              </tbody>
            </table>
            <div class="block" style="text-align: right;width: 100%;">
              <el-pagination
                :current-page="currentLeadPage"
                :page-size="10"
                :total="leadingMembers.length"
                layout="total, prev,pager, next"
                @current-change="handleCurrentLeadChange"
              />
            </div>
          </el-tab-pane>
        </el-tabs>
      </div>
    </div>
    <div style="padding: 15px;background-color: white;margin-top: 15px">
      <div class="c-title-div">
        集团知识图谱
      </div>
      <div style="height:calc(100vh - 60px);" class="c-my-graph1">
        <RelationGraph
          ref="graphRef"
          :options="graphOptions"
        >
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
              <div v-if="node.data.spcType === 'ctrler'"  style="width: 100%;
               background: #1980df;color: #1a0404;height:80px;font-size: 16px;
             border-radius: 150px;border:6px solid #3399ff;" class="imgqy">
              <img src="@/assets/images/qiye.png" alt="">
              </div>
              <div v-else-if="node.data.spcType === 'ctrled'"
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
  </div>
</template>
<script>
import RelationGraph from "relation-graph";
export default({
  name: "SupplyGroupDetail",
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

      },
      searchText:'',
      labelLoading: false,
      membersLoading: false,
      activeName: 'second',
      groupInput: '',
      currentPage: 1,
      currentLeadPage: 1,
      groupMembersTotal: 0,
      groupMembers: [],
      leadingMembers: [],
      grpInfo: {},
      relaInfoHidden: false,
      relaListDataLength: 0,
    }
  },
  mounted() {
    this.$nextTick(()=> {
      this.grpInfo = JSON.parse(sessionStorage.getItem('grpInfo'))
      const toIdSet = new Set()
      // this.groupMembers = this.grpInfo.tgroupRelationList
      this.grpInfo.tgroupRelationList.forEach(item => {
        if (item.toid && !toIdSet.has(item.toid)) {
          toIdSet.add(item.toid)
          this.groupMembers.push(item)
        }
      })

      const nodes = []
      const lines = []
      const rootId = this.grpInfo.grpid
      nodes.push({
        id: rootId,
        text: this.grpInfo.grpname,
        data: {
          spcType: 'ctrled'
        },
      })

      this.groupMembers.forEach((item, index) => {
        nodes.push({
          id: item.toid,
          text: item.toname,
          data: {
              spcType: 'ctrler',

            },
        })
        lines.push({
          from: rootId,
          to: item.toid,
          text:' 持股 '+ this.convertedStrToNumber(item.property),
          data: item,
          fontColor:'#4eb548',
        })
      })

      this.showSeeksGraph(rootId, nodes, lines)
    })
    this.showSeeksGraph();
  },

  methods: {
    convertedStrToNumber(str) {
      if (str === null || str === undefined) return ''
      return parseFloat(str).toFixed(2) + '%'
    },
    changeSelected(itemId) {
        console.log('changeSelected:', itemId);
        const graphInstance = this.$refs.graphRef.getInstance();
        // const node = graphInstance.getNodeById(itemId);
         graphInstance.focusNodeById(itemId)
      },
    showSeeksGraph(rootId, nodes, lines) {
      const __graph_json_data = {
        rootId: rootId,
        nodes: nodes,
        lines: lines
      };
      this.$refs.graphRef.setJsonData(__graph_json_data, (graphInstance) => {
        // 这些写上当图谱初始化完成后需要执行的代码
      });
    },
    downLoadReport() {
      const url = '/smart-api/aff-enrelpar-ws/enrelpar/getEnrelpars?entid='
    },
    handleSizeChange(val) {
      this.currentPage = val
    },
    handleCurrentChange(val) {
      this.currentPage = val
    },
    handleCurrentLeadChange(val) {
      this.currentLeadPage = val
    },
    handleClick(tab, event) {
      console.log(tab, event)
    },
    handleGroup() {
      this.groupMembers = this.grpInfo.tgroupRelationList.filter(item => item.fromname.indexOf(this.groupInput) !== -1)
      this.groupMembersTotal = this.groupMembers.length
    },
    handleRelPath(relPath) {
      if (relPath === null || relPath === undefined) return ''
       // "[\"6F274D22E0857C57E0539601A8C0ACD65|福建省福能新型建材有限责任公司->51.0000:6F274D3379B67C57E0539601A8C0ACD65|福建省钢源粉体材料有限公司\"]"
      const relPathStr = relPath.replace(/\"/g, '').replace(/\[/g, '').replace(/\]/g, '').replace(/\\/g, '').replace(/\|/g, '->')
      return relPathStr
    },
    showMorePath(item) {
      this.$router.push({
        path: '/nrzs/relationInfo',
        query: {
          queryId: item.mbrid
        }
      })
    }
  }
})
</script>
<style scoped lang="scss">
.c-td-title{
  background:#fcfcfc;
  border:1px solid #fcfcfc;
  padding-left: 10px;
  font-size:14px;
  font-family:Microsoft YaHei;
  font-weight:bolder;
  height: 40px;
  color:rgba(96,96,96,1);
}
.c-popper-class{
  height: 20px;
}
.c-base-table,.c-base-table tr th, .c-base-table tr td {
  border:1px solid #D0CECE;
}
.c-base-table { border-collapse: collapse;}
.c-th-header{
  text-align: left;
  font-size:14px;
  height: 40px;
  padding-left: 10px;
  background:#f9f9f9;
  font-family:Microsoft YaHei;
  font-weight:bolder;
  color:rgba(96,96,96,1);
  border:1px solid #fcfcfc;
}
.c-td-text{
  text-align: left;
  height: 40px;
  line-height: 25px;
  font-size: 14px;
  font-family:Microsoft YaHei;
  font-weight:400;
  color:rgba(96,96,96,1);
  background:white;
  border:1px solid #fcfcfc;
}
.c-td-text-padding{
  padding-left: 10px;
}
.c-count-div{
  border-radius: 6px;
  border: 1px solid #01B050;
  color: #01B050;
  margin-right: 5px;
  height: 30px;
  min-width: 85px;
  padding-left: 5px;
  padding-right: 5px;
  float: left;
  font-size: 11px;
  line-height: 30px;
  text-align: center;
}
.c-title-div{
  border-left: 10px solid #002060;
  padding-left: 10px;
  height: 30px;
  line-height: 30px;
  font-weight: bolder;
  font-size: 18px;
  color: #002060;
  margin-top: 20px;
  margin-bottom: 20px;
}
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
.c-my-graph1 ::v-deep .rel-node{
overflow: hidden;
background: #fff;
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
