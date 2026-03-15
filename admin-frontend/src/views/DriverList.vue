<template>
 <el-card>
 <div class="toolbar">
 <el-input v-model="query.name" placeholder="姓名" style="width:200px" />
 <el-button type="primary" @click="load">查询</el-button>
 <el-button type="success" @click="openDialog()">新增</el-button>
 </div>
 <el-table :data="list" style="width:100%">
 <el-table-column prop="id" label="ID" width="80"/>
 <el-table-column prop="name" label="姓名"/>
 <el-table-column prop="phone" label="手机"/>
 <el-table-column prop="licenseNo" label="驾照号"/>
 <el-table-column prop="onlineStatus" label="上线状态"/>
 <el-table-column prop="auditStatus" label="审核状态"/>
 <el-table-column label="操作" width="160">
 <template #default="scope">
 <el-button size="small" @click="openDialog(scope.row)">编辑</el-button>
 <el-button size="small" type="danger" @click="remove(scope.row.id)">删除</el-button>
 </template>
 </el-table-column>
 </el-table>
 <el-pagination v-model:current-page="page" :page-size="size" :total="total" @current-change="load" />

 <el-dialog v-model="dialogVisible" title="司机">
 <el-form :model="form">
 <el-form-item label="姓名"><el-input v-model="form.name"/></el-form-item>
 <el-form-item label="手机"><el-input v-model="form.phone"/></el-form-item>
 <el-form-item label="驾照号"><el-input v-model="form.licenseNo"/></el-form-item>
 <el-form-item label="上线状态"><el-input v-model="form.onlineStatus"/></el-form-item>
 <el-form-item label="审核状态"><el-input v-model="form.auditStatus"/></el-form-item>
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
const query = reactive({ name:'' })
const dialogVisible = ref(false)
const form = reactive({ id:null, name:'', phone:'', licenseNo:'', onlineStatus:'', auditStatus:'' })

const load = async ()=>{
 const res = await api.get('/drivers', { params:{ page:page.value, size:size.value, name: query.name } })
 list.value = res.records
 total.value = res.total
}

const openDialog = (row)=>{
 if (row){ Object.assign(form,row) } else { Object.assign(form,{id:null,name:'',phone:'',licenseNo:'',onlineStatus:'',auditStatus:''}) }
 dialogVisible.value=true
}

const save = async ()=>{
 if (form.id){ await api.put(`/drivers/${form.id}`, form) } else { await api.post('/drivers', form) }
 dialogVisible.value=false
 load()
}

const remove = async (id)=>{ await api.delete(`/drivers/${id}`); load() }

onMounted(load)
</script>

<style scoped>
.toolbar{margin-bottom:10px;display:flex;gap:8px;}
</style>
