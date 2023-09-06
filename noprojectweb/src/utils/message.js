import {ElMessage} from "element-plus";


const errorMessage = (error)=>{
    ElMessage({
        message: (error.code || error.status)  +" : "+ (error.message || error.statusText),
        type:"error"
    })
}

export {
    errorMessage
}