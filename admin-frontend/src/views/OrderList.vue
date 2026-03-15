<template>
 <el-card>
 <el-tabs v-model="tab" @tab-change="load">
 <el-tab-pane label="全部" name="all" />
 <el-tab-pane label="进行中" name="ongoing" />
 <el-tab-pane label="完成" name="completed" />
 <el-tab-pane label="取消" name="canceled" />
 </el-tabs>
 <el-table :data="list" style="width:100%">
 <el-table-column prop="id" label="ID" width="80"/>
 <el-table-column prop="passengerId" label="乘客"/>
 <el-table-column prop="driverId" label="司机"/>
 <el-table-column prop="vehicleId" label="车辆"/>
 <el-table-column prop="orderStatus" label="状态"/>
 <el-table-column prop="amount" label="金额"/>
 </el-table>
 </el-card>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import api from '../api'
const list = ref([])
const tab = ref('all')

const load = async ()=>{
 list.value = await api.get('/orders', { params: { status: tab.value } })
}

onMounted(load)
</script>
