<template>
  <div class="login-box">
    <el-tabs :stretch="true" @tab-click="changeChoice">
      <el-tab-pane label="登录" name="login"/>
      <el-tab-pane label="注册" name="register"/>
    </el-tabs>
    <router-view></router-view>
  </div>
</template>

<script>
import {useRouter} from 'vue-router'
export default {
  name: "UserLayout",
  components: {},
  setup(){
    //数据==========================
    const router = useRouter()
    //方法=========================
    function changeChoice(tabsPaneContext,event){
      router.push(`/user/${tabsPaneContext.paneName}`)
    }

    return{
      changeChoice
    }
  }
}
</script>

<style scoped>
.login-box {
  border: 1px solid #DCDFE6;
  min-width: 300px;
  max-width: 600px;
  margin: 180px auto;
  padding: 35px 35px 15px 35px;
  border-radius: 5px;
  -webkit-border-radius: 5px;
  -moz-border-radius: 5px;
  box-shadow: 0 0 25px #909399;
}
</style>