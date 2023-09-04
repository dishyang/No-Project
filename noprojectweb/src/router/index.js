import {createRouter, createWebHistory} from "vue-router";

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
            ]
        },
        {
            path:"/main",
            name:"main",
            component:()=>import("@/components/MainPage")
        }
    ]
})


export default router