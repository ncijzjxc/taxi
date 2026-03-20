<template>
  <div class="login">
    <el-card class="card">
      <h3 class="title">管理员登录</h3>
      <el-form :model="form" label-width="70px">
        <el-form-item label="用户名">
          <el-input v-model="form.username" placeholder="请输入用户名" />
        </el-form-item>
        <el-form-item label="密码">
          <el-input type="password" v-model="form.password" placeholder="请输入密码" @keyup.enter="submit" />
        </el-form-item>
        <div class="actions">
          <el-button type="primary" @click="submit">登录</el-button>
        </div>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import { reactive } from 'vue'
import api from '../api'

const form = reactive({ username: 'admin', password: '123456' })
const submit = async () => {
 try {
   const res = await api.post('/auth/login', form)
   // 经过拦截器解包后，这里的 res 就是后端 ApiResponse 的 data 字段
   // 后端登录返回的 data 里包含 token
   localStorage.setItem('token', res.token)
   location.href = '/'
 } catch (e) {
   window.alert(e?.message || '登录失败')
 }
}
</script>

<style scoped>
.login {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background: #f5f5f5;
}
.card {
  width: 360px;
}
.title {
  text-align: center;
  margin-bottom: 16px;
}
.actions {
  display: flex;
  justify-content: center;
  margin-top: 8px;
}
</style>
