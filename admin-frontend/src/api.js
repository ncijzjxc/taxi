import axios from 'axios'

const api = axios.create({
 baseURL: 'http://localhost:8083/api',
 timeout:10000
})

api.interceptors.request.use(config => {
 const token = localStorage.getItem('token')
 if (token) config.headers.Authorization = `Bearer ${token}`
 return config
})

// 统一把后端 ApiResponse<T> 的 data 字段解包出来
// 后端返回 { code, message, data }，这里直接返回 data，前端拿到的就是业务数据
api.interceptors.response.use(res => res.data.data, err => {
 if (err.response && err.response.status ===401) {
 localStorage.removeItem('token')
 window.location.href = '/login'
 }
 return Promise.reject(err)
})

export default api
