<template>
 <el-row :gutter="20">
 <el-col :span="6"><el-card>总订单：{{stats.totalOrders}}</el-card></el-col>
 <el-col :span="6"><el-card>有效订单：{{stats.validOrders}}</el-card></el-col>
 <el-col :span="6"><el-card>总收入：{{stats.totalIncome}}</el-card></el-col>
 <el-col :span="6"><el-card>活跃司机：{{stats.activeDrivers}}</el-card></el-col>
 </el-row>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import api from '../api'
const stats = ref({})

onMounted(async ()=>{
 stats.value = await api.get('/stats/overview')
})
</script>
