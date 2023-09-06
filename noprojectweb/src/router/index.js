import {createRouter, createWebHistory} from "vue-router";
import config from "@/config";
import {validateLogin,parseResponse} from "@/api";

const router = createRouter({
    history:createWebHistory(),
    routes:[
        {
            path:"/",
            redirect:"/main"
        },
        {
            path:"/user",
            name:"user",
            component:()=>import("@/components/LoginRegistrationPage"),
            redirect:"/user/login",
            children: [
                {
                    path: "login",
                    component: () => import('@/components/LoginRegistrationPage/Login.vue'),
                    meta: {keepAlive: true}
                },
                {
                    path: "register",
                    component: () => import('@/components/LoginRegistrationPage/Register.vue'),
                    meta: {keepAlive: true}
                }
            ],
            beforeEnter(to,from,next){
                if(localStorage.getItem(config.localStorageTokenKey) == null){
                    next("user")
                    return;
                }
                validateLogin().then(value => {
                    const responseData =  parseResponse(value)
                    if(responseData.code == 200){
                        next("main")
                        return;
                    }
                    next()
                })
            }
        },
        {
            path:"/main",
            name:"main",
            component:()=>import("@/components/MainPage"),
            beforeEnter(to,from,next){
                if(localStorage.getItem(config.localStorageTokenKey) == null){
                    next("user")
                    return;
                }
                validateLogin().then(value => {
                    //console.log("router.js->main->beforeEnter()->value",value)
                    const responseData =  parseResponse(value)
                    //console.log("router.js->main->beforeEnter()->responseData",responseData)
                    if(responseData.code != 200){
                        next("user")
                        return;
                    }
                    next()
                })
            }
        }
    ]
})


export default router