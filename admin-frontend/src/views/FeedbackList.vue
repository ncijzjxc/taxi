<template>
 <el-card>
 <el-table :data="list" style="width:100%">
 <el-table-column prop="id" label="ID" width="80"/>
 <el-table-column prop="userType" label="用户类型"/>
 <el-table-column prop="userId" label="用户ID"/>
 <el-table-column prop="type" label="类型"/>
 <el-table-column prop="content" label="内容"/>
 <el-table-column prop="status" label="处理状态"/>
 </el-table>
 </el-card>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import api from '../api'
const list = ref([])

onMounted(async ()=>{
 list.value = await api.get('/feedback')
})
</script>
