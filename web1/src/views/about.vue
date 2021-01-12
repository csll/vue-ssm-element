<template>
  <el-container>
    <el-header  style="background-color: #373F41">
      <div class="footer">
        <div class="userlogo"></div>
        <div class="welcome">欢迎{{this.$route.params.username}}进入电商管理系统</div>
        <div class="exit">
          <router-link :to="{name:'login'}">
            <el-button type="info" >退出</el-button>
          </router-link>
        </div>
        </div>
    </el-header>
    <el-container >
      <el-aside width="200px"  style="background-color: #545C64">
        <div class="aside">
        <el-col>
          <el-menu
            default-active="2"
            class="el-menu-vertical-demo"
            @open="handleOpen"
            @close="handleClose"
            background-color="#545c64"
            text-color="#fff"
            active-text-color="#ffd04b">
            <el-submenu index="1">
              <template slot="title">
                <i class="el-icon-user-solid"></i>
                <span>用户管理</span>
              </template>
              <el-menu-item-group><router-link :to="{name: 'userAdmin'}" style="text-decoration: none">
                <el-menu-item index="1-1">
                    <i class="el-icon-s-unfold"></i>
                    <span>用户列表</span>
                </el-menu-item></router-link>
              </el-menu-item-group>
            </el-submenu>
            <el-submenu index="2">
              <template slot="title">
                <i class="el-icon-menu"></i>
                <span>权限管理</span>
              </template>
              <el-menu-item-group><router-link :to="{name: 'powerAdmin'}" style="text-decoration: none">
                <el-menu-item index="1-1">
                  <i class="el-icon-setting"></i>
                  <span>设置权限</span>
                </el-menu-item></router-link>
              </el-menu-item-group>
            </el-submenu>
            <el-submenu index="3">
              <template slot="title">
                <i class="el-icon-s-goods"></i>
                <span>商品管理</span>
              </template>
              <el-menu-item-group><router-link :to="{name: 'goodsAdmin'}" style="text-decoration: none">
                <el-menu-item index="1-1">
                  <i class="el-icon-s-home"></i>
                  <span>库存管理</span>
                </el-menu-item></router-link>
              </el-menu-item-group>
            </el-submenu>
            <router-link :to="{name: 'orderAdmin'}" style="text-decoration: none;">
              <el-menu-item index="4">
                <i class="el-icon-shopping-cart-full"></i>
                <span slot="title">订单管理</span>
              </el-menu-item>
            </router-link>
            <el-submenu index="5">
              <template slot="title">
                <i class="el-icon-coin"></i>
                <span>数据管理</span>
              </template>
              <el-menu-item-group><router-link :to="{name: 'dateAdmin'}" style="text-decoration: none">
                <el-menu-item index="1-1">
                  <i class="el-icon-s-unfold"></i>
                  <span>数据报表</span>
                </el-menu-item></router-link>
              </el-menu-item-group>
            </el-submenu>
            </el-menu>
        </el-col>
        </div>
      </el-aside>
      <el-main style="background-color: #EAEDF1">
        <router-view/>
      </el-main>
    </el-container>
  </el-container>

</template>

<script>
export default {
  name: 'about',
  methods: {
    handleOpen (key, keyPath) {
      console.log(key, keyPath)
    },
    handleClose (key, keyPath) {
      console.log(key, keyPath)
    }
  }
}
</script>

<style>
  * {
    padding: 0;
    margin: 0;
  }
  .footer {
    height: 60px;
    background-color: #373F41;
  }
  .userlogo {
    margin-top: 5px;
    float: left;
    width: 50px;
    height: 50px;
    background-color: white;
    border-radius: 30px;
    background-image: url("../assets/userlogo.jpg");
    background-size: 50px 50px;
  }
  .welcome {
    float: left;
    margin-left: 20px;
    text-align: left;
    font-size: 18px;
    line-height: 60px;
    color: white;
  }
  .exit {
    margin-top: 8px;
    float: right;
    margin-right: 30px;
  }
  .aside {
    height: 800px;
  }

</style>
