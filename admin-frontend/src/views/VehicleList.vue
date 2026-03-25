<template>
 <el-card>
 <div class="toolbar">
 <el-input v-model="query.plateNo" placeholder="车牌" style="width:200px" />
 <el-button type="primary" @click="load">查询</el-button>
 <el-button type="success" @click="openDialog()">新增</el-button>
 </div>
 <el-table :data="list" style="width:100%">
 <el-table-column prop="id" label="ID" width="80"/>
 <el-table-column prop="plateNo" label="车牌"/>
 <el-table-column prop="model" label="车型"/>
 <el-table-column prop="status" label="状态"/>
 <el-table-column prop="driverId" label="绑定司机"/>
 <el-table-column label="操作" width="160">
 <template #default="scope">
 <el-button size="small" @click="openDialog(scope.row)">编辑</el-button>
 <el-button size="small" type="danger" @click="remove(scope.row.id)">删除</el-button>
 </template>
 </el-table-column>
 </el-table>
 <el-pagination v-model:current-page="page" :page-size="size" :total="total" @current-change="load" />

 <el-dialog v-model="dialogVisible" title="车辆">
 <el-form :model="form">
 <el-form-item label="车牌"><el-input v-model="form.plateNo"/></el-form-item>
 <el-form-item label="车型"><el-input v-model="form.model"/></el-form-item>
 <el-form-item label="状态"><el-input v-model="form.status"/></el-form-item>
 <el-form-item label="司机ID"><el-input v-model="form.driverId"/></el-form-item>
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
import { ElMessageBox, ElMessage } from 'element-plus'
import api from '../api'
const list = ref([])
const total = ref(0)
const page = ref(1)
const size = ref(10)
const query = reactive({ plateNo:'' })
const dialogVisible = ref(false)
const form = reactive({ id:null, plateNo:'', model:'', status:'', driverId:'' })

const load = async ()=>{
 const res = await api.get('/vehicles', { params:{ page:page.value, size:size.value, plateNo: query.plateNo } })
 list.value = res.records
 total.value = res.total
}

const openDialog = (row)=>{
 if (row){ Object.assign(form,row) } else { Object.assign(form,{id:null,plateNo:'',model:'',status:'',driverId:''}) }
 dialogVisible.value=true
}

const save = async ()=>{
 if (form.id){ await api.put(`/vehicles/${form.id}`, form) } else { await api.post('/vehicles', form) }
 dialogVisible.value=false
 load()
}

const remove = async (id) => {
  try {
    await ElMessageBox.confirm('确定要删除该车辆吗？删除后不可恢复。', '删除确认', {
      confirmButtonText: '确定删除',
      cancelButtonText: '取消',
      type: 'warning'
    })
  } catch {
    return
  }
  try {
    await api.delete(`/vehicles/${id}`)
    ElMessage.success('已删除')
    load()
  } catch (e) {
    ElMessage.error(e?.message || '删除失败')
  }
}

onMounted(load)
</script>

<style scoped>
.toolbar{margin-bottom:10px;display:flex;gap:8px;}
</style>
