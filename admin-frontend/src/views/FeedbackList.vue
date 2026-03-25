<template>
 <el-card>
 <div class="toolbar">
 <el-select v-model="query.type" placeholder="类型" style="width:160px">
 <el-option label="全部" value="" />
 <el-option label="建议" value="suggestion" />
 <el-option label="投诉" value="complaint" />
 <el-option label="表扬" value="praise" />
 </el-select>
 <el-button type="primary" @click="load">查询</el-button>
 <el-button type="success" @click="openDialog()">新增</el-button>
 </div>
 <el-table :data="list" style="width:100%">
 <el-table-column prop="id" label="ID" width="80"/>
 <el-table-column prop="userType" label="用户类型"/>
 <el-table-column prop="userId" label="用户ID"/>
 <el-table-column prop="type" label="类型"/>
 <el-table-column prop="content" label="内容"/>
 <el-table-column prop="status" label="处理状态"/>
 <el-table-column label="操作" width="160">
 <template #default="scope">
 <el-button size="small" @click="openDialog(scope.row)">编辑</el-button>
 <el-button size="small" type="danger" @click="remove(scope.row.id)">删除</el-button>
 </template>
 </el-table-column>
 </el-table>
 <el-pagination v-model:current-page="page" :page-size="size" :total="total" @current-change="load" />

 <el-dialog v-model="dialogVisible" title="反馈">
 <el-form :model="form">
 <el-form-item label="用户类型"><el-input v-model="form.userType"/></el-form-item>
 <el-form-item label="用户ID"><el-input v-model="form.userId"/></el-form-item>
 <el-form-item label="类型"><el-input v-model="form.type"/></el-form-item>
 <el-form-item label="内容"><el-input v-model="form.content"/></el-form-item>
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
const query = reactive({ type:'' })
const dialogVisible = ref(false)
const form = reactive({ id:null, userType:'', userId:'', type:'', content:'', status:'' })

const load = async ()=>{
 const res = await api.get('/feedback', { params:{ page:page.value, size:size.value, type: query.type } })
 list.value = res.records
 total.value = res.total
}

const openDialog = (row)=>{
 if (row){ Object.assign(form,row) } else { Object.assign(form,{id:null,userType:'',userId:'',type:'',content:'',status:''}) }
 dialogVisible.value=true
}

const save = async ()=>{
 if (form.id){ await api.put(`/feedback/${form.id}`, form) } else { await api.post('/feedback', form) }
 dialogVisible.value=false
 load()
}

const remove = async (id) => {
  try {
    await ElMessageBox.confirm('确定要删除该反馈记录吗？删除后不可恢复。', '删除确认', {
      confirmButtonText: '确定删除',
      cancelButtonText: '取消',
      type: 'warning'
    })
  } catch {
    return
  }
  try {
    await api.delete(`/feedback/${id}`)
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
