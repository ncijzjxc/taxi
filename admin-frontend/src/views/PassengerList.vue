<template>
 <el-card>
 <el-table :data="list" style="width:100%">
 <el-table-column prop="id" label="ID" width="80"/>
 <el-table-column prop="name" label="姓名"/>
 <el-table-column prop="phone" label="手机"/>
 <el-table-column prop="registerTime" label="注册时间"/>
 <el-table-column prop="status" label="状态"/>
 </el-table>
 </el-card>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import api from '../api'
const list = ref([])

onMounted(async ()=>{
 list.value = await api.get('/passengers')
})
</script>
