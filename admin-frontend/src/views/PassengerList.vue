<template>
 <el-card>
 <div class="toolbar">
 <el-input v-model="query.name" placeholder="姓名" style="width:200px" />
 <el-button type="primary" @click="load">查询</el-button>
 <el-button type="success" @click="openDialog()">新增</el-button>
 </div>
 <el-table :data="list" style="width:100%">
 <el-table-column prop="id" label="ID" width="90"/>
 <el-table-column prop="username" label="账号" width="120"/>
 <el-table-column prop="name" label="姓名"/>
 <el-table-column prop="phone" label="手机"/>
 <el-table-column prop="gender" label="性别" width="80"/>
 <el-table-column prop="birthday" label="生日" width="120"/>
 <el-table-column prop="createTime" label="创建时间"/>
 <el-table-column prop="updateTime" label="更新时间"/>
 <el-table-column prop="status" label="状态"/>
 <el-table-column label="操作" width="160">
 <template #default="scope">
 <el-button size="small" @click="openDialog(scope.row)">编辑</el-button>
 <el-button size="small" type="danger" @click="remove(scope.row.id)">删除</el-button>
 </template>
 </el-table-column>
 </el-table>
 <el-pagination v-model:current-page="page" :page-size="size" :total="total" @current-change="load" />

 <el-dialog v-model="dialogVisible" title="乘客">
 <el-form :model="form">
 <el-form-item label="账号"><el-input v-model="form.username"/></el-form-item>
 <el-form-item label="姓名"><el-input v-model="form.name"/></el-form-item>
 <el-form-item label="手机"><el-input v-model="form.phone"/></el-form-item>
 <el-form-item label="性别">
  <el-select v-model="form.gender" style="width:100%">
   <el-option label="男" value="男" />
   <el-option label="女" value="女" />
  </el-select>
 </el-form-item>
 <el-form-item label="生日"><el-input v-model="form.birthday" placeholder="YYYY-MM-DD"/></el-form-item>
 <el-form-item label="状态"><el-input v-model="form.status"/></el-form-item>
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
const query = reactive({ name:'' })
const dialogVisible = ref(false)
const form = reactive({ id:null, username:'', name:'', phone:'', gender:'', birthday:'', status:'' })

const load = async ()=>{
 const res = await api.get('/passengers', { params:{ page:page.value, size:size.value, name: query.name } })
 list.value = res.records
 total.value = res.total
}

const openDialog = (row)=>{
 if (row){ Object.assign(form,row) } else { Object.assign(form,{id:null,username:'',name:'',phone:'',gender:'',birthday:'',status:''}) }
 dialogVisible.value=true
}

const save = async ()=>{
 if (form.id){ await api.put(`/passengers/${form.id}`, form) } else { await api.post('/passengers', form) }
 dialogVisible.value=false
 load()
}

const remove = async (id) => {
  try {
    await ElMessageBox.confirm('确定要删除该乘客吗？删除后不可恢复。', '删除确认', {
      confirmButtonText: '确定删除',
      cancelButtonText: '取消',
      type: 'warning'
    })
  } catch {
    return
  }
  try {
    await api.delete(`/passengers/${id}`)
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
