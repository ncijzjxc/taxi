<template>
 <el-container class="layout">
 <el-aside width="200px">
 <div class="logo">Taxi Admin</div>
 <el-menu :default-active="active" router>
 <el-menu-item index="/passengers">乘客管理</el-menu-item>
 <el-menu-item index="/drivers">司机管理</el-menu-item>
 <el-menu-item index="/vehicles">车辆管理</el-menu-item>
 <el-menu-item index="/orders">订单管理</el-menu-item>
 <el-menu-item index="/cities">城市管理</el-menu-item>
 <el-menu-item index="/feedback">建议/投诉/表扬</el-menu-item>
 <el-menu-item index="/login-logs">登录日志</el-menu-item>
 <el-menu-item index="/stats">数据分析</el-menu-item>
 </el-menu>
 </el-aside>
 <el-container>
 <el-header>
 <div class="header">
 <span class="header-title">网约车管理系统</span>
 <el-button size="small" class="header-logout" @click="logout">退出</el-button>
 </div>
 </el-header>
 <el-main>
 <router-view />
 </el-main>
 </el-container>
 </el-container>
</template>

<script setup>
import { computed } from 'vue'
import { useRoute } from 'vue-router'
const route = useRoute()
const active = computed(() => route.path)
const logout = () => {
 localStorage.removeItem('token')
 location.href = '/login'
}
</script>

<style scoped>
.layout{height:100vh;}
.logo{color:#fff;font-weight:bold;padding:16px;text-align:center;background:#409EFF;}
.header{
  position:relative;
  display:flex;
  align-items:center;
  justify-content:flex-end;
  min-height:56px;
  padding:0 8px;
}
.header-title{
  position:absolute;
  left:50%;
  transform:translateX(-50%);
  font-size:18px;
  font-weight:600;
  color:#303133;
  pointer-events:none;
}
.header-logout{position:relative;z-index:1;}
</style>
