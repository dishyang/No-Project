<template>
  <div>
    <el-container style="height: 100vh">
      <!--头部-->
      <el-header style="background: #4d5b76">
        <main-header/>
      </el-header>
      <!--内容-->
      <el-container style="max-height: 100%;min-height: 10%;">
        <el-aside class="aside_main" :class="{aside_main_show:!asideStatus}">
          <el-scrollbar>
            <main-menu/>
          </el-scrollbar>
        </el-aside>
        <!--主体-->
        <el-main class="main_cont">
          <el-scrollbar>
            <!-- aside侧边栏按钮 -->
            <div class="aside_open_close" @click="asideChange">
              <i class="el-icon-arrow-left" v-if="aside_open_close"><el-icon><ArrowLeft /></el-icon></i>
              <i class="el-icon-arrow-right" v-else><el-icon><ArrowRight /></el-icon></i>
            </div>
            <router-view>
              <el-empty description="No Data"/>
            </router-view>
          </el-scrollbar>
        </el-main>
      </el-container>
      <!--腿部-->
      <el-footer style="background: #f5f5f5;">
        <main-footer/>
      </el-footer>
    </el-container>
  </div>
</template>


<script>
import MainHeader from "@/components/MainPage/MainHeader.vue";
import {ref} from 'vue'
import MainFooter from "@/components/MainPage/MainFooter.vue";
import MainMenu from "@/components/MainPage/MainMenu.vue";
import {ArrowRight,ArrowLeft} from '@element-plus/icons-vue'
export default {
  name: "MainPage",
  components: {MainMenu, MainFooter, MainHeader,ArrowRight,ArrowLeft},
  setup() {
    const asideStatus = ref(true)
    const aside_open_close = ref(true)

    function asideChange(){
      asideStatus.value = !asideStatus.value;
      if(asideStatus.value){
        setTimeout(()=>{
          aside_open_close.value = true
        },500)
      }else{
        setTimeout(()=>{
          aside_open_close.value = false
        },500)
      }
    }
    return {
      asideChange,
      asideStatus,
      aside_open_close
    }
  }
}
</script>

<style scoped>
.el-header,.el-main {
  padding: 0;
  height: auto;
}

/* 侧边栏样式 */
.aside_main{
  width: 300px !important;
  transition: width 0.5s;
}
.aside_main_show{
  width: 0px !important;
}
.main_cont{
  position: relative;
  margin: 0;
  padding: 0;
  background-color: #E9EEF3;
}
/* 侧边栏按钮样式 */
.aside_open_close{
  position: absolute;
  left: 0;
  top: 50%;
  width: 16px;
  height: 52px;
  line-height: 60px;
  color: #fff;
  background-color: #2A333A;
  border-radius: 0 6px 6px 0;
  z-index: 1000;
  cursor: pointer;
}
.aside_open_close:hover{
  background-color: #FF8E2B;
  color: #fff;
}
</style>