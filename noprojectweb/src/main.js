import { createApp } from 'vue'
import App from './App.vue'
//引入ElementPlus
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
//引入路由
import router from "@/router";
//引入Vuex
import store from '@/store'

const app = createApp(App)
app.use(ElementPlus)
app.use(router)
app.use(store)
app.mount('#app')
