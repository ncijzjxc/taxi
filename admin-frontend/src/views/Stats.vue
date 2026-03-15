<template>
 <el-card>
 <el-table :data="list" style="width:100%">
 <el-table-column prop="city" label="城市"/>
 <el-table-column prop="totalOrders" label="总订单"/>
 <el-table-column prop="totalIncome" label="总收入"/>
 <el-table-column prop="activeDrivers" label="活跃司机"/>
 </el-table>
 </el-card>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import api from '../api'
const list = ref([])

onMounted(async ()=>{
 list.value = await api.get('/stats/cities')
})
</script>
