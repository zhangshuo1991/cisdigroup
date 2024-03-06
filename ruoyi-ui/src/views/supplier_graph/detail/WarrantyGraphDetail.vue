<template>
  <div id="app" style="margin: auto;padding-top: 15px;background-color: #F2F2F2;min-height: 1200px;padding-bottom: 15px">
    <div style="margin-left: 15px;background-color: white;padding-left: 10px;margin-right: 15px;padding-top: 3px;padding-right: 10px;padding-bottom: 10px;height: 300px">
      <div class="c-title-div">
        企业担保信息
      </div>
      <div style="height: 300px">
        <el-table
          height="200"
          :data = "entWarrantyList"
          border
        >
          <el-table-column label="企业名称" prop="entname"></el-table-column>
          <el-table-column label="担保金额" prop="waramount"></el-table-column>
          <el-table-column label="被担保金额" prop="b_waramount"></el-table-column>
          <el-table-column label="担保企业"></el-table-column>
          <el-table-column label="担保日期"></el-table-column>
          <el-table-column label="担保期限"></el-table-column>
          <el-table-column label="主债权人名称"></el-table-column>
          <el-table-column label="债务人名称"></el-table-column>
          <el-table-column label="主债权数额"></el-table-column>
          <el-table-column label="主债权到期日"></el-table-column>
          <el-table-column label="数据来源"></el-table-column>
          <el-table-column label="数据日期"></el-table-column>
        </el-table>
      </div>
    </div>
    <div style="margin-left: 15px;background-color: white;padding-left: 10px;margin-right: 15px;padding-bottom: 20px;padding-top: 3px;margin-top: 15px">
      <div class="c-title-div">
        担保关系图谱
      </div>
      <div style="height:calc(100vh - 60px);">
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
              @input="handleFilter"
            ></el-input>
          </div>
        </template>
        <template slot="node" slot-scope="{node}">
            <div class="my-node-content">
              <div v-if="node.data.spcType === 'ctrler'" style="width:300px;background-color: #4eb548;font-size: 16px;color: #fff;height:40px;line-height: 40px;">
                被担保公司
              </div>
              <div v-else-if="node.data.spcType === 'ctrled'"
              style="width:300px; background-color: #d9001b;color: #ffffff;height:40px;line-height: 40px;font-size: 16px;
              border-top-left-radius: 3px;border-top-right-radius: 3px;">
                被查询企业
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
  name: "WarrantyGraphDetail",
  components: { RelationGraph },
  data(){
    return {
      graphOptions: {
        defaultNodeBorderWidth: 0,
        defaultNodeColor: '#fff',
        allowSwitchLineShape: true,
        allowSwitchJunctionPoint: true,
        defaultLineShape: 1,
        nodeShape: 1,
        allowShowMiniToolBar: true,
        'layouts': [
          {
            'label': '中心',
            'layoutName': 'center',
            'layoutClassName': 'seeks-layout-center'
          }
        ],
        defaultJunctionPoint: 'border'
      },
      entWarrantyList: [],
      searchText: ''
    }
  },
  mounted() {
    this.showSeeksGraph();
  },
  methods: {
    handleFilter(){
      const allNodes = this.$refs.graphRef.getNodes()
      for (let i = 0; i < allNodes.length; i++) {
        // 模糊匹配
        if (allNodes[i].text.indexOf(this.searchText) !== -1) {
          this.$refs.graphRef.focusNode(allNodes[i].id)
          break
        }
      }
    },
    showSeeksGraph() {
      const __graph_json_data = {
        rootId: '2',
        nodes: [
          { id: '1', text: '被担保公司1', myicon: 'el-icon-star-on' ,data:{spcType: 'ctrler'} },
          { id: '2', text: '渝商投资集团股分有限公司', myicon: 'el-icon-setting', data:{spcType: 'ctrled'}},
          { id: '4', text: '被担保公司1', myicon: 'el-icon-star-on',data:{spcType: 'ctrler'}  },
          { id: '6', text: '被担保公司1', myicon: 'el-icon-setting',data:{spcType: 'ctrler'}  },
          { id: '7', text: '被担保公司1', myicon: 'el-icon-setting',data:{spcType: 'ctrler'}  },

        ],
        lines: [
          { from: '1', to: '2', text: '被担保', fontColor: '#3399ff',},
          { from: '4', to: '2', text: '被担保' ,fontColor: '#3399ff', },
          { from: '6', to: '2', text: '被担保',fontColor: '#3399ff', },
          { from: '7', to: '2', text: '被担保' ,fontColor: '#3399ff', },
          { from: '8', to: '2', text: '被担保' ,fontColor: '#3399ff', }
        ]
      };
      this.$refs.graphRef.setJsonData(__graph_json_data, (graphInstance) => {
        // 这些写上当图谱初始化完成后需要执行的代码
      });
    },
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
::v-deep .rel-node-shape-1{
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
::v-deep .my-node-content{
  border:#cccccc solid 1px !important;
  color: #333 !important;
}
::v-deep .relation-graph .rel-node-shape-0{
  width: 300px;
  height: 95px;
  border-radius: 0px;
}
::v-deep .c-node-label{
  font-size: 14px;
  line-height: 50px;

}
</style>
