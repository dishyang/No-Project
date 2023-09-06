<template>
    <router-view/>
</template>

<script>
import ContentPage from './components/MainPage'
import LoginRegistrationPage from "@/components/LoginRegistrationPage/index.vue";

export default {
  name: 'App',
  components: {
    LoginRegistrationPage,
    ContentPage
  }
}
</script>

<style>
html, body, #app {
  padding: 0px;
  margin: 0px;
  padding: 0px;
}

* {
  font-family: 'Helvetica Neue', Helvetica, 'PingFang SC', 'Hiragino Sans GB',
  'Microsoft YaHei', '微软雅黑', Arial, sans-serif;
}
.el-form-item__error{
  position: unset !important;
}
.container-fluid {
  padding-right: 20px;
  padding-left: 20px;
}

.container-fluid-left {
  padding-left: 20px;
}

.container-fluid-right {
  padding-right: 20px;
}
</style>
