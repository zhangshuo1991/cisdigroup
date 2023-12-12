<template>
  <div id="app" style="margin: auto;padding-bottom: 30px;background-color: #F2F2F2;padding-top: 10px;">
    <div style="padding: 15px 15px 15px 15px;background-color: white;margin-left: 15px;margin-right: 15px">
      <div class="c-title-div">
        企业基本信息
      </div>
      <table class="c-base-table" style="width: 100%;margin-bottom: 10px">
        <tbody>
        <tr>
          <td class="c-td-title" style="width: 12%">企业名称：</td>
          <td class="c-td-text" style="width: 20%">{{ baseinfo.entname }}</td>
          <td class="c-td-title" style="width: 12%">统一社会信用代码：</td>
          <td class="c-td-text" style="width: 20%">{{ baseinfo.uniscid }}</td>
          <td class="c-td-title">企业经营状态：</td>
          <td class="c-td-text">{{ baseinfo.entstatusCn }}</td>
        </tr>
        <tr>
          <td class="c-td-title">是否为上市公司：</td>
          <td class="c-td-text">{{ baseinfo.islist_cn }}</td>
          <td class="c-td-title">股票代码：</td>
          <td class="c-td-text">{{ baseinfo.skcode }}</td>
        </tr>
        </tbody>
      </table>
    </div>
    <div style="padding: 15px 15px 15px 15px;background-color: white;margin-left: 15px;margin-right: 15px;margin-top: 15px">
      <div class="c-title-div">
        企业关联方信息
      </div>
      <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
        <el-tab-pane label="企业关联方列表" name="relaList">
          <el-table :data="enrelpar.slice((currentPage-1)*10,currentPage*10)" border style="width: 100%;margin-bottom: 10px" :header-cell-style="{backgroundColor:'#f9f9f9'}">
            <el-table-column
              type="index"
              width="50"
              label="序号"
            />
            <el-table-column
              prop="partyname"
              label="关联⽅名称"
            />
            <el-table-column
              prop="partytyp"
              label="关联⽅类型"
            />
            <el-table-column
              prop="relpartyp"
              label="关联关系类型"
            />
<!--            <el-table-column-->
<!--              prop="partyid"-->
<!--              label="统一社会信用代码"-->
<!--            />-->
            <el-table-column
              prop="partystatus"
              label="关联⽅经营状态"
            />
          </el-table>
          <div class="block" style="text-align: right;width: 100%;margin-top: 10px;">
            <el-pagination
              :current-page="currentPage"
              :page-size="10"
              :total="enrelpar.length"
              layout="total, prev,pager, next"
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
            />
          </div>
        </el-tab-pane>
        <el-tab-pane label="上市公司自行披露的关联方" name="stockRelaList">
          <el-table :data="listEnrelpar" border style="width: 100%;margin-bottom: 10px" :header-cell-style="{backgroundColor:'#f9f9f9'}">
            <el-table-column
              type="index"
              label="序号"
            />
            <el-table-column
              prop="partyname"
              label="关联⽅名称"
            />
            <el-table-column
              prop="partytyp_cn"
              label="关联⽅类型"
            />
            <el-table-column
              prop="relpartyp_cn"
              label="关联关系类型"
            />
            <el-table-column
              prop="uniscid"
              label="统一社会信用代码"
            />
            <el-table-column
              prop="partystatus_cn"
              label="关联⽅经营状态"
            />
          </el-table>
        </el-tab-pane>
      </el-tabs>
    </div>
    <div style="padding: 15px;background-color: white;margin-left: 15px;margin-right: 15px;margin-top: 15px">
      <div class="c-title-div">
        企业关联方图谱
      </div>
      <div style="height:calc(100vh - 60px);"  class="c-my-graph1">
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
            
              <img v-if="node.data.partytyp=='企业'" src="@/assets/images/qiye.png" alt="">
              <img v-else src="@/assets/images/ger.png" alt="">
             
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
import request from "@/utils/request";
export default({
  name: "RelaDetail",
  components: { RelationGraph },
  data() {
    return {
      activeName: 'relaList',
      graphOptions: {
        allowSwitchLineShape: true,
        allowSwitchJunctionPoint: true,
       // defaultJunctionPoint: 'border',
        allowShowMiniToolBar: true
      },
      baseinfo: {},
      listEnrelpar: [],
      enrelpar: [],
      currentPage: 1,
    }
  },
  mounted() {

    this.uniscid = this.$route.query.uniscid
    this.getRelaList()
    this.$nextTick(() => {
      this.baseinfo = JSON.parse(sessionStorage.getItem("baseRelaInfo"))
      this.showSeeksGraph();
    });
  },
  methods: {
    getRelaList() {
      request({
        url: "/entInfo/getRelaEntList/" + this.uniscid,
        method: "get"
      }).then(res => {

        console.log('res======>>>', this.baseinfo.partytyp)
        this.enrelpar = res.data
        const nodes = []
        const lines = []
        nodes.push({
          id: this.baseinfo.uniscid,
          text: this.baseinfo.entname,
         // data: this.baseinfo,
          data: {
          spcType: 'ctrled',
          text: this.baseinfo.entname, 
        },
        })
        this.enrelpar.forEach(item => {
          console.log('reitems======>>>', item)
          nodes.push({
            id: item.partyid,
            // text: item.partyname,
            //data: item,
            data: {
              spcType: 'ctrler',
              partytyp: item.partytyp,
            },

          })
          lines.push({
            from: this.baseinfo.uniscid,
            to: item.partyid,
            text: item.relpartyp,
            data: item,
            fontColor:'#3399ff',
          })
        })
        this.showSeeksGraph(this.baseinfo.uniscid, nodes, lines)
      })
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
    handleClick(tab, event) {
      console.log(tab, event);
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      this.currentPage = val
      console.log(`当前页: ${val}`);
    }
  }
})
</script>
<style scoped lang="scss">
.c-td-title{
  background:#f9f9f9;
  border:1px solid #D0CECE;
  padding-left: 10px;
  font-size:14px;
  font-family:Microsoft YaHei;
  font-weight:bolder;
  height: 40px;
  color:rgba(96,96,96,1);
}
.c-base-table,.c-base-table tr th, .c-base-table tr td {
  border-bottom:1px solid #D0CECE;
  border-top:1px solid #D0CECE;
}
.c-base-table { border-collapse: collapse;}
.c-title-div{
  border-left: 10px solid #002060;
  padding-left: 10px;
  height: 30px;
  line-height: 30px;
  font-weight: bolder;
  font-size: 18px;
  color: #002060;
  margin-bottom: 20px;
}
.c-td-text{
  padding-left: 10px;
  text-align: left;
  height: 40px;
  font-size:14px;
  line-height: 40px;
  font-family:Microsoft YaHei;
  font-weight:400;
  color:rgba(96,96,96,1);
  background:white;
  border:1px solid #D0CECE;
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
.c-my-graph1 ::v-deep .rel-node{
overflow: hidden;
background: #fff;
}
.c-my-graph1 ::v-deep .c-node-label{
  position: absolute;
  z-index: 999;
width:400px;
left: 0;
  color: #4eb548;
  margin-left: -150px;
  margin-top: 8px;
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
