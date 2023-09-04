import axios from "axios";
//引入进度条
import nprogress from 'nprogress'
//引入进度条样式
import 'nprogress/nprogress.css'


const requests = axios.create({
    timeout: 60000  //超时时间5秒
})

//请求拦截器：在发送请求之前，做一些事情
requests.interceptors.request.use((config) => {
        //开启进度条
        nprogress.start()
        //携带token
        config.headers['X-Access-Token'] = localStorage.getItem("Access-Token")
        //config：配置对象，对象路有一个重要属性，headers请求头
        return config
    },
    (error) => {
        nprogress.start()
        return Promise.reject(error.request)
    })

//相应拦截器：响应信息回来后做一些处理
requests.interceptors.response.use((response) => {
        //关闭进度条
        nprogress.done()
        return response
    },
    (error) => {
        nprogress.done()
        if(error.response == undefined){
            return Promise.reject({code:error.code,message:error.message})
        }
        return Promise.reject(error.response)
    })

export default requests