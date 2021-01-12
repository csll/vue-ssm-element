<template>
    <div>
      <div class="footer">首页 > 数据管理 > 数据报表</div>
      <el-progress :text-inside="true" :stroke-width="26" :percentage="70"></el-progress>
      <el-progress :text-inside="true" :stroke-width="24" :percentage="100" status="success"></el-progress>
      <el-progress :text-inside="true" :stroke-width="22" :percentage="80" status="warning"></el-progress>
      <el-progress :text-inside="true" :stroke-width="20" :percentage="50" status="exception"></el-progress>
    </div>
</template>

<script>

</script>

<style scoped>
  .footer {
    height: 30px;
    font-size: 18px;
    background-color: white;
    text-align: left;
  }
</style>
