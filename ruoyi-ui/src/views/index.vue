<template>
  <div id="app" style="text-align: center;">
    <div style="margin:auto;width: 900px;height: 80px">
      <h2>主要功能模块</h2>
      <span style="color: #7F7F7F;font-size: 14px">基于现有数据源搭建一套基于大数据平台框架体系下的供应商知识图谱平台，满足业务应用需求。</span>
    </div>
    <div style="margin:auto;width: 1000px;height: 250px;margin-top: 30px">
      <div style="float: left;width: 495px;height: 220px;background-color: #F8F9FA">
        <div style="float: left;width: 40%;padding-top: 50px">
          <svg-icon icon-class="laxinhuoke" style="color: #5099F8;width: 100px;height: 100px"></svg-icon>
        </div>
        <div style="float: left;width: 40%;text-align: left">
          <div>
            <h2>供应商寻源</h2>
          </div>
          <div style="height: 100px;line-height: 30px;">
            <span style="color: #7F7F7F;font-size: 14px">
              帮助用户在几千万家在营企业中找到适合自己的潜在供应商，以方便自己询价，竞价谈判等。
            </span>
          </div>
          <div>
            <el-link type="primary" href="/supplier_sourcing/potential_search">进入功能模块>></el-link>
          </div>
        </div>
      </div>
      <div style="float: left;width: 495px;height: 220px;background-color: #F8F9FA;margin-left: 10px">
        <div style="float: left;width: 40%;padding-top: 50px">
          <svg-icon icon-class="fengxianchuandaofenxi" style="color: #F19837;width: 100px;height: 100px"></svg-icon>
        </div>
        <div style="float: left;width: 40%;text-align: left">
          <div>
            <h2>供应商图谱分析</h2>
          </div>
          <div style="height: 100px;line-height: 30px;">
            <span style="color: #7F7F7F;font-size: 14px">
              了解目标企业的关系网是什么样子的？以及他们是否存在潜在风险，可能对供应商的影响。
            </span>
          </div>
          <div>
            <el-link type="primary" href="/supplier_graph/equity_graph">进入功能模块>></el-link>
          </div>
        </div>
      </div>
    </div>
    <div style="margin:auto;width: 1000px;height: 300px;">
      <div style="float: left;width: 495px;height: 220px;background-color: #F8F9FA">
        <div style="float: left;width: 40%;padding-top: 50px">
          <svg-icon icon-class="eye" style="color: #2566F5;width: 100px;height: 100px"></svg-icon>
        </div>
        <div style="float: left;width: 40%;text-align: left">
          <div>
            <h2>供应商画像</h2>
          </div>
          <div style="height: 100px;line-height: 30px;">
            <span style="color: #7F7F7F;font-size: 14px">
              全面展示供应商的360度全景视图，帮助用户充分了解目标企业的各方面信息，一目了然。
            </span>
          </div>
          <div>
            <el-link type="primary" href="/supplier_info">进入功能模块>></el-link>
          </div>
        </div>
      </div>
      <div style="float: left;width: 495px;height: 220px;background-color: #F8F9FA;margin-left: 10px">
        <div style="float: left;width: 40%;padding-top: 50px">
          <svg-icon icon-class="jianguanfumian" style="color: #71D67B;width: 100px;height: 100px"></svg-icon>
        </div>
        <div style="float: left;width: 40%;text-align: left">
          <div>
            <h2>供应商风险信息</h2>
          </div>
          <div style="height: 100px;line-height: 30px;">
            <span style="color: #7F7F7F;font-size: 14px">
              在与供应商合作的全生命周期中能够及时了解其风险情况，以便及时采取措施，防范风险。
            </span>
          </div>
          <div>
            <el-link type="primary" href="/supplier_risk/blacklist_info">进入功能模块>></el-link>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Index",
  data() {
    return {
      // 版本号
      version: "3.8.6"
    };
  },
  methods: {
    goTarget(href) {
      window.open(href, "_blank");
    }
  }
};
</script>

<style scoped lang="scss">
.home {
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }
  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }
  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }
}
</style>
