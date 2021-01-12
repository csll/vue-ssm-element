import Vue from 'vue'
import Router from 'vue-router'
import login from '../views/login'
import about from '../views/about'
// 引入组件
import userAdmin from '../components/userAdmin'
import powerAdmin from '../components/powerAdmin'
import orderAdmin from '../components/orderAdmin'
import goodsAdmin from '../components/goodsAdmin'
import dateAdmin from '../components/dateAdmin'
import welcome from '../components/welcome'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'login',
      component: login
    },
    {
      path: '/about/:userID',
      name: 'about',
      component: about,
      children: [
        {
          path: 'welcome', // 默认渲染的路由     匹配   /home
          name: 'welcome',
          component: welcome
        },
        {
          path: 'userAdmin', // 默认渲染的路由     匹配   /home
          name: 'userAdmin',
          component: userAdmin
        },
        {
          path: 'powerAdmin', // 默认渲染的路由     匹配   /home
          name: 'powerAdmin',
          component: powerAdmin
        },
        {
          path: 'orderAdmin', // 默认渲染的路由     匹配   /home
          name: 'orderAdmin',
          component: orderAdmin
        },
        {
          path: 'goodsAdmin', // 默认渲染的路由     匹配   /home
          name: 'goodsAdmin',
          component: goodsAdmin
        },
        {
          path: 'dateAdmin', // 默认渲染的路由     匹配   /home
          name: 'dateAdmin',
          component: dateAdmin
        }

      ]
    }

  ]
})
