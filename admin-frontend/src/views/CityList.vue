<template>
 <el-card>
 <el-table :data="list" style="width:100%">
 <el-table-column prop="id" label="ID" width="80"/>
 <el-table-column prop="name" label="城市"/>
 <el-table-column prop="openStatus" label="是否开通"/>
 <el-table-column prop="operateStatus" label="运营状态"/>
 </el-table>
 </el-card>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import api from '../api'
const list = ref([])

onMounted(async ()=>{
 list.value = await api.get('/cities')
})
</script>
