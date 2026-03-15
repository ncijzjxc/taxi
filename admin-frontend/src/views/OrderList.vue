<template>
 <el-card>
 <div class="toolbar">
 <el-select v-model="status" placeholder="状态" style="width:160px">
 <el-option label="全部" value="all" />
 <el-option label="进行中" value="ongoing" />
 <el-option label="完成" value="completed" />
 <el-option label="取消" value="canceled" />
 </el-select>
 <el-button type="primary" @click="load">查询</el-button>
 <el-button type="success" @click="openDialog()">新增</el-button>
 </div>
 <el-table :data="list" style="width:100%">
 <el-table-column prop="id" label="ID" width="80"/>
 <el-table-column prop="passengerId" label="乘客"/>
 <el-table-column prop="driverId" label="司机"/>
 <el-table-column prop="vehicleId" label="车辆"/>
 <el-table-column prop="orderStatus" label="状态"/>
 <el-table-column prop="amount" label="金额"/>
 <el-table-column label="操作" width="160">
 <template #default="scope">
 <el-button size="small" @click="openDialog(scope.row)">编辑</el-button>
 <el-button size="small" type="danger" @click="remove(scope.row.id)">删除</el-button>
 </template>
 </el-table-column>
 </el-table>
 <el-pagination v-model:current-page="page" :page-size="size" :total="total" @current-change="load" />

 <el-dialog v-model="dialogVisible" title="订单">
 <el-form :model="form">
 <el-form-item label="乘客ID"><el-input v-model="form.passengerId"/></el-form-item>
 <el-form-item label="司机ID"><el-input v-model="form.driverId"/></el-form-item>
 <el-form-item label="车辆ID"><el-input v-model="form.vehicleId"/></el-form-item>
 <el-form-item label="状态"><el-input v-model="form.orderStatus"/></el-form-item>
 <el-form-item label="金额"><el-input v-model="form.amount"/></el-form-item>
 </el-form>
 <template #footer>
 <el-button @click="dialogVisible=false">取消</el-button>
 <el-button type="primary" @click="save">保存</el-button>
 </template>
 </el-dialog>
 </el-card>
</template>

<script setup>
import { onMounted, reactive, ref } from 'vue'
import api from '../api'
const list = ref([])
const total = ref(0)
const page = ref(1)
const size = ref(10)
const status = ref('all')
const dialogVisible = ref(false)
const form = reactive({ id:null, passengerId:'', driverId:'', vehicleId:'', orderStatus:'', amount:'' })

const load = async ()=>{
 const res = await api.get('/orders', { params:{ page:page.value, size:size.value, status: status.value } })
 list.value = res.records
 total.value = res.total
}

const openDialog = (row)=>{
 if (row){ Object.assign(form,row) } else { Object.assign(form,{id:null,passengerId:'',driverId:'',vehicleId:'',orderStatus:'',amount:''}) }
 dialogVisible.value=true
}

const save = async ()=>{
 if (form.id){ await api.put(`/orders/${form.id}`, form) } else { await api.post('/orders', form) }
 dialogVisible.value=false
 load()
}

const remove = async (id)=>{ await api.delete(`/orders/${id}`); load() }

onMounted(load)
</script>

<style scoped>
.toolbar{margin-bottom:10px;display:flex;gap:8px;}
</style>
