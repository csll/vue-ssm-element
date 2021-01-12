<template>
  <div>
    <!-- 2.为Echarts准备一个Dom -->
    <el-card>
    <el-row>
      <el-col :span="16">
        <div id="main" style="width: 1000px;height:600px;"></div>
      </el-col>
    </el-row>
    </el-card>
  </div>
</template>

<script>
// 1.导入echarts
var echarts = require('echarts')

export default {
  // 此时,页面上的元素,已经被渲染完毕了
  async mounted () {
    // 3.基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'))
    var xAxisData = []
    var data1 = []
    var data2 = []
    for (var i = 0; i < 100; i++) {
      xAxisData.push('商品' + i)
      data1.push((Math.sin(i / 5) * (i / 5 - 10) + i / 6) * 5)
      data2.push((Math.cos(i / 5) * (i / 5 - 10) + i / 6) * 5)
    }

    var option = {
      title: {
        text: '电商管理后台数据'
      },
      legend: {
        data: ['bar', 'bar2']
      },
      toolbox: {
        // y: 'bottom',
        feature: {
          magicType: {
            type: ['stack', 'tiled']
          },
          dataView: {},
          saveAsImage: {
            pixelRatio: 2
          }
        }
      },
      tooltip: {},
      xAxis: {
        data: xAxisData,
        splitLine: {
          show: false
        }
      },
      yAxis: {
      },
      series: [{
        name: 'bar',
        type: 'bar',
        data: data1,
        animationDelay: function (idx) {
          return idx * 10
        }
      }, {
        name: 'bar2',
        type: 'bar',
        data: data2,
        animationDelay: function (idx) {
          return idx * 10 + 100
        }
      }],
      animationEasing: 'elasticOut',
      animationDelayUpdate: function (idx) {
        return idx * 5
      }
    }
    myChart.setOption(option)
  }
}
</script>

<style scoped>

</style>
