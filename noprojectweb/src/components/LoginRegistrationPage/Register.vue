<template>
  <div class="register-box" style="">
    <el-form>
      <h3>注册用户</h3>
      <el-form-item>
        <span>账号</span>
        <el-input placeholder="请输入用户名"  v-model="registerFrom.userName"/>
      </el-form-item>
      <el-form-item>
        <span>密码</span>
        <el-input maxlength="20" placeholder="请输入密码" v-model="registerFrom.userPassword"/>
      </el-form-item>
      <el-form-item>
        <span>确认密码</span>
        <el-input maxlength="20" placeholder="请确认密码" v-model="registerFrom.userPasswordConfirm"/>
        <span style="color: red" v-show="controlSubmit">密码不一致</span>
      </el-form-item>
      <el-form-item class="register-button">
        <el-button type="primary" :disabled="controlSubmit" @click="handleSubmit">注册</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import {reactive, toRaw,computed} from "vue";
import {useStore} from "vuex";
import {register} from "@/api";
import md5 from 'md5'
import {useRouter} from "vue-router";
import {ElMessage} from "element-plus";


export default {
  name: "Register",
  setup() {
    //数据============
    const store = useStore()
    const router = useRouter()
    const registerFrom = reactive({
      userName: "",
      password: "",
      userPasswordConfirm: ""
    })

    //方法=========================
    async function handleSubmit() {
      const reg = /^[A-Za-z0-9]+$/
      if(!reg.test(registerFrom.userName)){
        //账号不符合规则
        return
      }
      if(!(registerFrom.password === registerFrom.userPasswordConfirm)){
        //两次密码不一致
        return
      }
      const regPassWord = /^[\u4e00-\u9fa5]+/
      if(regPassWord.test(registerFrom.password)){
        //密码不符合规则
        return
      }
      const userInfo = toRaw(registerFrom);
      Reflect.deleteProperty(userInfo,"userPasswordConfirm")
      userInfo.password = md5(userInfo.password)
      try{
        const result = await register(userInfo)
        if(result.data.code == 200){
          router.push("/user/login")
        }else{
          ElMessage({
            message: result.data.code + result.data.message,
            type: 'error',
          })
        }
      }catch (e){
        ElMessage({
          message: e.message,
          type: 'error',
        })
      }
    }

    //计算属性============================
    const controlSubmit = computed(()=>{
      if(registerFrom.userName.length != null & registerFrom.password === registerFrom.userPasswordConfirm){
        return false
      }
      return true
    })

    return {
      registerFrom,
      handleSubmit,
      controlSubmit
    }
  }
}
</script>

<style scoped>
.register-box {
  text-align: center;
  width: 300px;
  margin: 0 auto 40px auto;
}

.register-button {
  margin-left: 40%;
}
</style>