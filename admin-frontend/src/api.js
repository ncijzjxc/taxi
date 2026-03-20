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
// 后端返回 { code, message, data }，code != 0 时走失败分支
api.interceptors.response.use(
  res => {
    const body = res.data
    if (body && typeof body.code === 'number') {
      if (body.code !== 0) {
        const msg = body.message || '请求失败'
        return Promise.reject(new Error(msg))
      }
      return body.data
    }
    return body
  },
  err => {
    if (err.response && err.response.status === 401) {
      localStorage.removeItem('token')
      window.location.href = '/login'
    }
    return Promise.reject(err)
  }
)

export default api
