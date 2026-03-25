<template>
  <div class="auth-page">
    <div class="auth-card">
      <div class="illustration">
        <div class="title">网约车管理后台</div>
        <div class="sub">数据运营，可视管理</div>
      </div>
      <div class="form-panel">
        <h2>管理员登录</h2>
        <label class="field">
          <span><em>*</em> 账号</span>
          <input class="input" v-model="username" placeholder="请输入账号" />
        </label>
        <label class="field">
          <span><em>*</em> 密码</span>
          <input class="input" v-model="password" type="password" placeholder="请输入密码" @keyup.enter="submit" />
        </label>
        <p v-if="error" class="error">{{ error }}</p>
        <p v-if="tip" class="success">{{ tip }}</p>
        <button class="primary full" :disabled="loading" @click="submit">{{ loading ? '登录中…' : '登录' }}</button>
        <div class="links footnote">
          <span class="muted">内部管理员账号登录</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import api from '../api'

const username = ref('')
const password = ref('')
const loading = ref(false)
const error = ref('')
const tip = ref('')
const router = useRouter()

async function submit() {
  error.value = ''
  tip.value = ''
  const u = username.value.trim()
  const pwd = password.value.trim()
  if (!u) return (error.value = '请输入账号')
  if (!pwd) return (error.value = '请输入密码')
  loading.value = true
  try {
    const res = await api.post('/auth/login', { username: u, password: pwd })
    const token = res?.token
    if (!token) throw new Error('登录未返回 token')
    localStorage.setItem('token', token)
    tip.value = '登录成功'
    setTimeout(() => router.replace('/'), 300)
  } catch (e) {
    error.value = e?.message || '登录失败'
  } finally {
    loading.value = false
  }
}
</script>

<style scoped>
.auth-page {
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 16px;
  background: #d8deea;
}

.auth-card {
  width: 920px;
  max-width: 100%;
  min-height: 460px;
  background: #fff;
  border-radius: 12px;
  overflow: hidden;
  display: grid;
  grid-template-columns: 1.6fr 1fr;
  box-shadow: 0 10px 28px rgba(22, 39, 77, 0.12);
}

.illustration {
  background: linear-gradient(135deg, #dff0ff 0%, #b5d8ff 100%);
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
  padding: 28px;
}

.title {
  font-size: 30px;
  font-weight: 700;
  color: #1f3c66;
}

.sub {
  margin-top: 8px;
  color: #2f588f;
}

.form-panel {
  padding: 36px 28px;
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.form-panel h2 {
  margin: 0 0 8px;
  text-align: center;
}

.field {
  display: flex;
  flex-direction: column;
  gap: 4px;
  font-size: 14px;
}

.field em {
  color: #f56c6c;
  font-style: normal;
}

.input {
  width: 100%;
  padding: 8px 10px;
  border-radius: 6px;
  border: 1px solid #dcdfe6;
  outline: none;
  font-size: 14px;
  background: #ffffff;
  color: #303133;
}

.input:focus {
  border-color: #409eff;
  box-shadow: 0 0 0 1px rgba(64, 158, 255, 0.15);
}

.primary {
  margin-top: 10px;
  padding: 10px 14px;
  border-radius: 6px;
  border: none;
  background: #409eff;
  color: #ffffff;
  font-size: 14px;
  cursor: pointer;
}

.primary:disabled {
  opacity: 0.6;
  cursor: not-allowed;
}

.full {
  width: 100%;
}

.success {
  margin: 0;
  font-size: 13px;
  color: #67c23a;
}

.error {
  margin: 0;
  font-size: 13px;
  color: #f56c6c;
}

.links {
  margin-top: 8px;
  display: flex;
  justify-content: space-between;
}

.links.footnote {
  justify-content: center;
}

.muted {
  color: #909399;
  font-size: 13px;
}

@media (max-width: 820px) {
  .auth-card {
    grid-template-columns: 1fr;
  }

  .illustration {
    min-height: 140px;
  }
}
</style>
