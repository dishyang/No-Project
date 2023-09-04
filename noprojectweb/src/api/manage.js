import request from "@/api/request"

//get
export function getAction(url,parameter) {
    return request({
        url: url,
        method: 'get',
        params: parameter
    })
}

//post
export function postAction(url,parameter) {
    return request({
        url: url,
        method: 'post',
        data: parameter,
        headers:{
            'Content-Type':'application/x-www-form-urlencoded;charset=utf-8'
        }
    })
}

//deleteAction
export function deleteAction(url,parameter) {
    return request({
        url: url,
        method: 'delete',
        params: parameter
    })
}

/**
 * 文件上传 用于富文本上传图片
 * @param url
 * @param parameter
 * @returns {*}
 */
export function uploadAction(url,parameter){
    return request({
        url: url,
        data: parameter,
        method:'post' ,
        headers: {
            'Content-Type': 'multipart/form-data',  // 文件上传
        },
    })
}