<template>
  <div class="login-title">
    <el-form label-width="80xp">
      <h3>欢迎登录</h3>
      <el-form-item>
        <span>账号</span>
        <el-input placeholder="请输入账号" type="text" v-model="loginForm.userName"/>
      </el-form-item>
      <el-form-item>
        <span>密码</span>
        <el-input placeholder="请输入密码" type="password" v-model="loginForm.userPassword"/>
      </el-form-item>
      <el-form-item class="login-button">
        <el-button type="primary" @click="handleSubmit">登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import {reactive,toRaw} from 'vue'
import md5 from "md5";
import {useStore} from 'vuex'
import {useRouter} from "vue-router";
export default {
  name: "Login",
  setup(){
    //数据==========================
    const store = useStore()
    const router = useRouter()
    const loginForm = reactive({
      userName:"",
      userPassword:""
    })

    //方法==============================
    async function handleSubmit(){
      const regUsername = /^[A-Za-z0-9]+$/
      const regPassWord = /^[\u4e00-\u9fa5]+/
      if(regUsername.test(loginForm.userName) && !regPassWord.test(loginForm.userPassword)){
        const userInfo = {
          userName:loginForm.userName,
          userPassword:md5(loginForm.userPassword)
        }
        try{
          const response = await store.dispatch("user/login",userInfo)
          const backendData = response.data
          if(backendData.code == 200){
            //添加动态路由
            store.state.user.menu.forEach((value, index, array)=>{
              addDynamicRouter(value,router)
            })
            //跳转
            router.push("/main")
          }else{
            //登录失败
            errorMessage(backendData)
          }
        }catch (e){
          //console.log("Login.vue-handleSubmit",e)
          errorMessage(e)
        }
      }
    }


    return{
      loginForm,
      handleSubmit
    }
  }
}
</script>

<style scoped>
.login-title{
  text-align: center;
  margin: 0 auto 40px auto;
  color: #303133;
  width: 300px;
}
.login-button{
  margin-left: 40%;
}
</style>