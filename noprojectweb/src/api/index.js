import {getAction, postAction} from '@/api/manage'
//登录管理
const register = (params) => postAction("/api/user/register", params)
const login = (params) => postAction("/api/user/login", params)
const getUserList = (params) => postAction("/api/user/getUserList",params)
const updateUser = (params) => postAction("/api/user/updataUser",params)
const validateLogin = () => getAction("/api/user/validateLogin")
const getUserInfo = () => getAction("/api/user/userInfo")
const logout = () => getAction("/api/user/logout");
const createSupplier = (params) => postAction("/api/supplier/createSupplier", params);
const getSupplierList = (params) => getAction("/api/supplier/getSupplierList", params);
const updateSupplier = (params) => postAction("/api/supplier/updateSupplier", params);
const deleteSupplier = (params) => getAction("/api/supplier/deleteSupplier", params);
const batchDeleteSupplier = (params) => postAction("/api/supplier/batchDeleteSupplier", params)
const batchEnableSupplier = (params) => postAction("/api/supplier/batchEnableSupplier", params)
const batchDisableSupplier = (params) => postAction("/api/supplier/batchDisableSupplier", params)
const createMenuItem = (params) => postAction("/api/menu/createMenuItem", params)
const getMenuList = () => getAction("/api/menu/menuList")
export {
    register,
    login,
    getUserList,
    validateLogin,
    getUserInfo,
    updateUser,
    logout,
    createSupplier,
    getSupplierList,
    updateSupplier,
    deleteSupplier,
    batchDeleteSupplier,
    batchEnableSupplier,
    batchDisableSupplier,
    createMenuItem,
    getMenuList
}