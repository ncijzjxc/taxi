<template>
 <div class="login">
 <el-card class="card">
 <h3>管理员登录</h3>
 <el-form :model="form">
 <el-form-item label="用户名">
 <el-input v-model="form.username" />
 </el-form-item>
 <el-form-item label="密码">
 <el-input type="password" v-model="form.password" />
 </el-form-item>
 <el-button type="primary" @click="submit">登录</el-button>
 </el-form>
 </el-card>
 </div>
</template>

<script setup>
import { reactive } from 'vue'
import api from '../api'

const form = reactive({ username: 'admin', password: '123456' })
const submit = async () => {
 const res = await api.post('/auth/login', form)
 localStorage.setItem('token', res.data.token)
 location.href = '/'
}
</script>

<style scoped>
.login{display:flex;justify-content:center;align-items:center;height:100vh;background:#f5f5f5;}
.card{width:360px;}
</style>
