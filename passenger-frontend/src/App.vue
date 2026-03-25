<template>
  <div class="layout">
    <header v-if="showHeader" class="header">
      <div class="logo">Taxi 乘客端</div>
      <nav class="topnav">
        <router-link class="toplink" to="/">呼叫</router-link>
        <router-link class="toplink" to="/orders">订单</router-link>
        <router-link class="toplink" to="/feedback">反馈</router-link>
        <router-link class="toplink" to="/me">我的</router-link>
      </nav>
    </header>
    <main class="main" :class="{ 'main-auth': !showHeader }">
      <router-view />
    </main>
  </div>
</template>

<script setup lang="ts">
import { computed } from 'vue'
import { useRoute } from 'vue-router'

const route = useRoute()
const showHeader = computed(() =>
  route.path !== '/login' &&
  route.path !== '/register' &&
  route.path !== '/reset-password'
)
</script>

<style scoped>
.main-auth {
  padding: 0;
}
</style>

