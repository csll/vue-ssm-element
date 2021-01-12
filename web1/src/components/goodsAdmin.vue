<template>
    <div>
      <div class="footer">首页 > 商品管理 > 库存管理</div>
      <el-progress type="circle" :percentage="0"></el-progress>
      <el-progress type="circle" :percentage="25"></el-progress>
      <el-progress type="circle" :percentage="100" status="success"></el-progress>
      <el-progress type="circle" :percentage="70" status="warning"></el-progress>
      <el-progress type="circle" :percentage="50" status="exception"></el-progress>
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
