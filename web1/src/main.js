// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import router from './router'

// 导入axios
import axios from 'axios'
import VueAxios from 'vue-axios'
// 引入cookies
import VueCookies from 'vue-cookies'

// 配置axios拦截器查看身份验证是否成功
axios.interceptors.request.use(config => {
  // console.log(config)
  // 为请求头对象，添加token验证的Authorization字段
  config.headers.token = window.$cookies.get('token')
  // 在最后必须 return config
  return config
})

// response 拦截器中,  隐藏进度条NProgress.done()
axios.interceptors.response.use(config => {
  if (config.data.status === 422) {
    window.$cookies.remove('token')
    window.alert('用户身份过期，请重新登录!!!')
    Vue.prototype.tokenAlert()
    router.push({name: 'login'})
  } else {
    return config
  }
})

// 导入全局组件

Vue.config.productionTip = false
Vue.use(ElementUI)
Vue.use(VueAxios, axios)
Vue.use(VueCookies)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  render: h => h(App),
  router,
  components: { App },
  template: '<App/>'

})

// 添加全局方法
Vue.prototype.tokenAlert = function () {
  this.$message.warning('用户身份过期，请重新登录')
}

Vue.prototype.updateError = function () {
  this.$notify.error({
    title: '错误',
    message: '操作失败'
  })
}

Vue.prototype.updateSuccess = function (msg) {
  this.$notify.success({
    title: '操作成功',
    message: msg,
    duration: 2000
  })
}
