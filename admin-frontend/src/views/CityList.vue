<template>
 <el-card>
 <div class="toolbar">
 <el-input v-model="query.name" placeholder="城市" style="width:200px" />
 <el-button type="primary" @click="load">查询</el-button>
 <el-button type="success" @click="openDialog()">新增</el-button>
 </div>
 <el-table :data="list" style="width:100%">
 <el-table-column prop="id" label="ID" width="80"/>
 <el-table-column prop="name" label="城市"/>
 <el-table-column prop="openStatus" label="是否开通"/>
 <el-table-column prop="operateStatus" label="运营状态"/>
 <el-table-column prop="createTime" label="创建时间"/>
 <el-table-column prop="updateTime" label="更新时间"/>
 <el-table-column label="操作" width="240">
 <template #default="scope">
 <el-button size="small" @click="openDialog(scope.row)">编辑</el-button>
 <el-button size="small" @click="openPrice(scope.row)">计价管理</el-button>
 <el-button size="small" type="danger" @click="remove(scope.row.id)">删除</el-button>
 </template>
 </el-table-column>
 </el-table>
 <el-pagination v-model:current-page="page" :page-size="size" :total="total" @current-change="load" />

 <el-dialog v-model="dialogVisible" title="城市">
 <el-form :model="form">
 <el-form-item label="城市"><el-input v-model="form.name"/></el-form-item>
 <el-form-item label="开通状态"><el-input v-model="form.openStatus"/></el-form-item>
 <el-form-item label="运营状态"><el-input v-model="form.operateStatus"/></el-form-item>
 </el-form>
 <template #footer>
 <el-button @click="dialogVisible=false">取消</el-button>
 <el-button type="primary" @click="save">保存</el-button>
 </template>
 </el-dialog>

 <!--计价管理 -->
 <el-dialog v-model="priceDialog" title="计价管理" width="900px">
 <div class="toolbar">
 <el-select v-model="priceQuery.carType" placeholder="车型" style="width:160px">
 <el-option label="全部" value="" />
 <el-option label="经济" value="economy" />
 <el-option label="高端" value="premium" />
 <el-option label="豪车" value="luxury" />
 </el-select>
 <el-button type="primary" @click="loadPrice">查询</el-button>
 <el-button type="success" @click="openPriceDialog()">新增</el-button>
 </div>
 <el-table :data="priceList" style="width:100%">
 <el-table-column prop="carType" label="车型"/>
 <el-table-column prop="startPrice" label="起步价"/>
 <el-table-column prop="startKm" label="起步公里"/>
 <el-table-column prop="pricePerKm" label="每公里"/>
 <el-table-column prop="pricePerMin" label="每分钟"/>
 <el-table-column prop="version" label="版本"/>
 <el-table-column prop="status" label="状态"/>
 <el-table-column prop="effectiveTime" label="生效时间"/>
 <el-table-column label="操作" width="200">
 <template #default="scope">
 <el-button size="small" @click="openPriceDialog(scope.row)">编辑</el-button>
 <el-button size="small" type="danger" @click="removePrice(scope.row.id)">删除</el-button>
 </template>
 </el-table-column>
 </el-table>
 <el-pagination v-model:current-page="pricePage" :page-size="priceSize" :total="priceTotal" @current-change="loadPrice" />
 </el-dialog>

 <el-dialog v-model="priceFormVisible" title="计价规则">
 <el-form :model="priceForm">
 <el-form-item label="车型">
 <el-select v-model="priceForm.carType" placeholder="车型">
 <el-option label="经济" value="economy" />
 <el-option label="高端" value="premium" />
 <el-option label="豪车" value="luxury" />
 </el-select>
 </el-form-item>
 <el-form-item label="起步价"><el-input v-model="priceForm.startPrice"/></el-form-item>
 <el-form-item label="起步公里"><el-input v-model="priceForm.startKm"/></el-form-item>
 <el-form-item label="每公里"><el-input v-model="priceForm.pricePerKm"/></el-form-item>
 <el-form-item label="每分钟"><el-input v-model="priceForm.pricePerMin"/></el-form-item>
 <el-form-item label="版本"><el-input v-model="priceForm.version"/></el-form-item>
 <el-form-item label="状态">
 <el-select v-model="priceForm.status">
 <el-option label="启用" value="enabled" />
 <el-option label="停用" value="disabled" />
 </el-select>
 </el-form-item>
 <el-form-item label="生效时间"><el-date-picker v-model="priceForm.effectiveTime" type="datetime" /></el-form-item>
 </el-form>
 <template #footer>
 <el-button @click="priceFormVisible=false">取消</el-button>
 <el-button type="primary" @click="savePrice">保存</el-button>
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
const form = reactive({ id:null, name:'', openStatus:'', operateStatus:'' })

// price rule
const priceDialog = ref(false)
const currentCity = ref(null)
const priceList = ref([])
const priceTotal = ref(0)
const pricePage = ref(1)
const priceSize = ref(10)
const priceQuery = reactive({ carType:'' })
const priceFormVisible = ref(false)
const priceForm = reactive({ id:null, cityId:null, carType:'economy', startPrice:'', startKm:'', pricePerKm:'', pricePerMin:'', version:'v1', status:'enabled', effectiveTime:'' })

const load = async ()=>{
 const res = await api.get('/cities', { params:{ page:page.value, size:size.value, name: query.name } })
 list.value = res.records
 total.value = res.total
}

const openDialog = (row)=>{
 if (row){ Object.assign(form,row) } else { Object.assign(form,{id:null,name:'',openStatus:'',operateStatus:''}) }
 dialogVisible.value=true
}

const save = async ()=>{
 if (form.id){ await api.put(`/cities/${form.id}`, form) } else { await api.post('/cities', form) }
 dialogVisible.value=false
 load()
}

const remove = async (id) => {
  try {
    await ElMessageBox.confirm('确定要删除该城市吗？删除后不可恢复。', '删除确认', {
      confirmButtonText: '确定删除',
      cancelButtonText: '取消',
      type: 'warning'
    })
  } catch {
    return
  }
  try {
    await api.delete(`/cities/${id}`)
    ElMessage.success('已删除')
    load()
  } catch (e) {
    ElMessage.error(e?.message || '删除失败')
  }
}

const openPrice = (row)=>{
 currentCity.value = row
 priceDialog.value = true
 pricePage.value =1
 loadPrice()
}

const loadPrice = async ()=>{
 if (!currentCity.value) return
 const res = await api.get('/price-rules', { params:{ cityId: currentCity.value.id, carType: priceQuery.carType, page: pricePage.value, size: priceSize.value } })
 priceList.value = res.records
 priceTotal.value = res.total
}

const openPriceDialog = (row)=>{
 if (row){ Object.assign(priceForm,row) } else {
 Object.assign(priceForm,{ id:null, cityId: currentCity.value.id, carType:'economy', startPrice:'', startKm:'', pricePerKm:'', pricePerMin:'', version:'v1', status:'enabled', effectiveTime:'' })
 }
 priceFormVisible.value=true
}

const savePrice = async ()=>{
 if (priceForm.id){ await api.put(`/price-rules/${priceForm.id}`, priceForm) } else { await api.post('/price-rules', priceForm) }
 priceFormVisible.value=false
 loadPrice()
}

const removePrice = async (id) => {
  try {
    await ElMessageBox.confirm('确定要删除该计价规则吗？删除后不可恢复。', '删除确认', {
      confirmButtonText: '确定删除',
      cancelButtonText: '取消',
      type: 'warning'
    })
  } catch {
    return
  }
  try {
    await api.delete(`/price-rules/${id}`)
    ElMessage.success('已删除')
    loadPrice()
  } catch (e) {
    ElMessage.error(e?.message || '删除失败')
  }
}

onMounted(load)
</script>

<style scoped>
.toolbar{margin-bottom:10px;display:flex;gap:8px;}
</style>
