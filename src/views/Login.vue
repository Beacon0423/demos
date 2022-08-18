<template>
  <body id="poster">
  <el-form class="login-container" label-position="left"
           label-width="0px">
    <h3 class="login_title">系统登录</h3>
    <el-form-item>
      <el-input type="text" v-model="loginForm.username"
                auto-complete="off" placeholder="账号"></el-input>
    </el-form-item>
    <el-form-item>
      <el-input type="password" v-model="loginForm.password"
                auto-complete="off" placeholder="密码"></el-input>
    </el-form-item>
    <el-form-item style="width: 100%">
      <el-button type="primary" style="width: 100%;background: #505458;border: none" v-on:click="login">登录</el-button>
    </el-form-item>
  </el-form>
  </body>
</template>

<script>

export default {
  name: 'Login',
  data () {
    return {
      loginForm: {
        username: '',
        password: ''
      },
      responseResult: [],
      dialogVisible: false
    }
  },
  methods: {
    login () {
      this.axios
        .post('/user/login', {
          username: this.loginForm.username,
          password: this.loginForm.password
        })
        .then(successResponse => {
          if (successResponse.data.code === 200) {
            this.$router.replace({path: '/users'})
            this.$message({
              type: 'success',
              message: `欢迎${this.loginForm.username}`
            })
          } else {
            this.$message({
              type: 'error',
              message: '用户名或密码错误!'
            })
          }
        })
        .catch(failResponse => {
          this.$message({
            type: 'error',
            message: '网络错误!'
          })
        })
    }
  }
}
</script>
<style>
  @import "../assets/css/Login.css";
</style>
