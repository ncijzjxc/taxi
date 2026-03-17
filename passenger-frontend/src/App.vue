<template>
  <div class="layout">
    <header class="header">
      <div class="logo">Taxi 乘客端</div>
      <nav class="topnav">
        <router-link class="toplink" to="/">呼叫</router-link>
        <router-link class="toplink" to="/orders">订单</router-link>
        <router-link class="toplink" to="/feedback">反馈</router-link>
        <router-link class="toplink" to="/me">我的</router-link>
      </nav>
    </header>
    <main class="main">
      <router-view />
    </main>
  </div>
</template>

<script setup lang="ts">
</script>

