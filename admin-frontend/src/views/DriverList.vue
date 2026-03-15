<template>
 <el-card>
 <el-table :data="list" style="width:100%">
 <el-table-column prop="id" label="ID" width="80"/>
 <el-table-column prop="name" label="姓名"/>
 <el-table-column prop="phone" label="手机"/>
 <el-table-column prop="licenseNo" label="驾照号"/>
 <el-table-column prop="onlineStatus" label="上线状态"/>
 <el-table-column prop="auditStatus" label="审核状态"/>
 </el-table>
 </el-card>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import api from '../api'
const list = ref([])

onMounted(async ()=>{
 list.value = await api.get('/drivers')
})
</script>
