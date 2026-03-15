<template>
 <el-card>
 <el-table :data="list" style="width:100%">
 <el-table-column prop="id" label="ID" width="80"/>
 <el-table-column prop="plateNo" label="车牌"/>
 <el-table-column prop="model" label="车型"/>
 <el-table-column prop="status" label="状态"/>
 <el-table-column prop="driverId" label="绑定司机"/>
 </el-table>
 </el-card>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import api from '../api'
const list = ref([])

onMounted(async ()=>{
 list.value = await api.get('/vehicles')
})
</script>
