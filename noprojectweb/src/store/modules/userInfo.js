import {login,parseResponse} from "@/api"
import config from "@/config";

//准备actions对象——响应组件中用户的动作
const actions = {
    async login({commit},params){
        const response = parseResponse(await login(params));
        //console.log("store->action>-login() response",response)
        if(response.code == 200){
            //保存token
            localStorage.setItem(config.localStorageTokenKey,response.data.token)
        }

        return response;
    }
}

//准备mutations对象——修改state中的数据
const mutations = {

}


//准备state对象——保存具体的数据
const state = {

}

//准备getters对象——当state中的数据需要经过加工后再使用时，可以使用gettters加工
const getters = {}
export default {
    namespaced: true,
    actions,
    mutations,
    state,
    getters
}