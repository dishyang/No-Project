<template>
  <div class="login-title">
    <el-form
        label-width="80xp"
        :model="form"
        :rules="rules"
        ref="ruleForm"
    >
      <h3>欢迎登录</h3>
      <el-form-item label="账号" prop="userName">
        <el-input placeholder="请输入账号" type="text" v-model="form.userName"/>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input placeholder="请输入密码" type="password" v-model="form.password"/>
      </el-form-item>
      <el-form-item class="login-button">
        <el-button type="primary" @click="handleSubmit(ruleForm)">登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>


<script>
import {reactive, ref} from 'vue'
import md5 from "md5"
import {useStore} from 'vuex'
import {useRouter} from "vue-router"
import {errorMessage} from "@/utils/message"

export default {
  name: "Login",
  setup() {
    //变量============================
    const store = useStore();
    const router = useRouter();
    const form = reactive({
      userName: "",
      password: ""
    })
    const ruleForm = ref()
    const rules = reactive({
      userName: [
        {type: "string", required: true, whitespace: true, message: "用户名必填", trigger: "change"},
        {
          pattern: /^[a-zA-Z0-9_]{4,16}$/,
          message: "用户名格式不正确,最少需要4位,最多16位,只能包含字母数字下划线",
          trigger: "change"
        }
      ],
      password: [{type: "string", required: true, whitespace: true, message: "密码必填", trigger: "change"}]
    })

    //方法==============================
    function handleSubmit(formRef) {
      formRef.validate(async (valid) => {
        if (valid) {
          //表单校验正确
          const response = await store.dispatch("userInfo/login", {
            userName: form.userName,
            password: md5(form.password)
          });
          //console.log("Login.vue->handleSubmit() response:", response)
          if (response.code == 200) {
            //登录成功
            await router.push("/")
          } else {
            errorMessage(response)
          }
        }
      })
    }

    //return=============================
    return {
      form,
      ruleForm,
      rules,
      handleSubmit
    }
  }
}
</script>

<style scoped>
.login-title {
  text-align: center;
  margin: 0 auto 40px auto;
  color: #303133;
  width: 300px;
}

.login-button {
  margin-left: 40%;
}
</style>