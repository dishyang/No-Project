import {getAction, postAction} from '@/api/manage'
//登录管理
const register = (params) => postAction("/api/user/register", params)
const login = (params) => postAction("/api/user/login", params)
const validateLogin = () => getAction("/api/user/validateLogin")
export {
    parseResponse,
    register,
    login,
    validateLogin
}

const parseResponse = (response) =>{
    if(response.status != 200){
        return {code:response.status,data:"",message:response.statusText}
    }
    return response.data
}