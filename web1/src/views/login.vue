<template>
  <div>
    <div>
      <div class="box">
        <div class="login">
          <div class="sign"></div>
          <div class="form">
            <div class="ipt">
              <div class="userid">
                <span style="color: red">*</span>
                账号：<input type="text" name="username" v-model="username">
              </div>
              <div class="pass">
                <span style="color: red">*</span>
                密码：<input type="password" name="password" v-model="password">
              </div>

            </div>
          </div>
          <div class="but">

              <el-button type="primary" plain style="width: 200px;position:absolute;right: 140px" @click.enter="login">提交</el-button>

          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'App',
  data () {
    return {
      username: '',
      password: ''
    }
  },

  methods: {
    login: function () {
      this.axios({
        method: 'post',
        url: 'http://127.0.0.1:8008/api/private/v1/login',
        data: {
          username: this.username,
          password: this.password
        }
      }).then((res) => {
        if (res.data === 'error') {
          window.alert('用户名或密码错误')
        } else {
          console.log(res.data.data.username)
          this.$cookies.set('token', res.data.data.token)
          this.$router.push({name: 'welcome', params: { username: res.data.data.username }})
        }
      })
    }
  }

}

</script>

<style>

  .box {
    position: relative;
    margin-top: 50px;
    background-color: #2B4B6B;
    width: 100%;
    height: 600px;
  }
  .login {
    position: relative;
    top: 120px;
    width: 600px;
    margin: 0 auto;
    height: 400px;
    background-color: aliceblue;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
    border: 1px solid aliceblue;
  }
  .sign {
    position: absolute;
    top: -90px;
    left: 210px;
    height: 180px;
    width: 180px;
    border-radius: 90px;
    background-image: url("../assets/logo1.jpg");
    background-size: 180px 180px;
  }
  .form {
    margin: 130px auto;
    width: 500px;
    height: 120px;
  }
  .but {
    position: absolute;
    top: 270px;
    left: 50px;
    width: 500px;
    height: 75px;
  }
  .ipt {
    position: relative;
    font-size: 18px;
    text-align: center;

  }
  input {
    text-align: center;
    width: 300px;
    height: 30px;
    border-radius: 5px;
  }
  .userid, .pass {

    margin-top: 30px;

  }
</style>
