<template>
  <div class="stats-page">
    <el-row :gutter="20" class="kpi-row">
      <el-col :span="6">
        <el-card shadow="hover" class="kpi-card">
          <div class="kpi-title">总订单</div>
          <div class="kpi-value">{{ overview.totalOrders ?? '-' }}</div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover" class="kpi-card">
          <div class="kpi-title">有效订单</div>
          <div class="kpi-value">{{ overview.validOrders ?? '-' }}</div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover" class="kpi-card">
          <div class="kpi-title">总收入</div>
          <div class="kpi-value">{{ formatCurrency(overview.totalIncome) }}</div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover" class="kpi-card">
          <div class="kpi-title">活跃司机</div>
          <div class="kpi-value">{{ overview.activeDrivers ?? '-' }}</div>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="20" class="chart-row">
      <el-col :span="12">
        <el-card shadow="hover" class="chart-card">
          <template #header>
            <span class="card-title">按城市总订单（Top 8）</span>
          </template>
          <div ref="ordersChartRef" class="chart" />
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card shadow="hover" class="chart-card">
          <template #header>
            <span class="card-title">收入与活跃司机（Top 8）</span>
          </template>
          <div ref="incomeChartRef" class="chart" />
        </el-card>
      </el-col>
    </el-row>

    <el-card shadow="hover" class="table-card" style="margin-top: 20px;">
      <template #header>
        <span class="card-title">城市明细</span>
      </template>
      <el-table
        :data="tableList"
        style="width: 100%;"
        border
        max-height="420"
        :header-cell-style="{ background: '#f5f7fa' }"
        v-loading="loading"
      >
        <el-table-column prop="city" label="城市" />
        <el-table-column prop="totalOrders" label="总订单" />
        <el-table-column prop="totalIncome" label="总收入" :formatter="formatCurrencyCell" />
        <el-table-column prop="activeDrivers" label="活跃司机" />
      </el-table>
    </el-card>
  </div>
</template>

<script setup>
import { computed, onBeforeUnmount, onMounted, ref, watch } from 'vue'
import { ElMessage } from 'element-plus'
import * as echarts from 'echarts'
import api from '../api'

const loading = ref(false)
const overview = ref({})
const list = ref([])

const ordersChartRef = ref(null)
const incomeChartRef = ref(null)
let ordersChart = null
let incomeChart = null

const chartList = computed(() => {
  const arr = [...(list.value || [])]
  arr.sort((a, b) => (b.totalOrders ?? 0) - (a.totalOrders ?? 0))
  return arr.slice(0, 8)
})

const tableList = computed(() => {
  const arr = [...(list.value || [])]
  arr.sort((a, b) => (b.totalOrders ?? 0) - (a.totalOrders ?? 0))
  return arr
})

const formatCurrency = (value) => {
  if (value === null || value === undefined || value === '') return '-'
  const num = typeof value === 'string' ? Number(value) : value
  if (Number.isNaN(num)) return String(value)
  return `¥${num.toLocaleString()}`
}

const formatCurrencyCell = (_row, _column, cellValue) => formatCurrency(cellValue)

const renderOrdersChart = () => {
  if (!ordersChart) return
  const xData = chartList.value.map((i) => i.city)
  const yData = chartList.value.map((i) => i.totalOrders ?? 0)

  ordersChart.setOption({
    backgroundColor: 'transparent',
    tooltip: { trigger: 'axis', axisPointer: { type: 'shadow' } },
    grid: { left: 40, right: 20, bottom: 45, top: 50 },
    xAxis: {
      type: 'category',
      data: xData,
      axisLabel: { rotate: 30, interval: 0 }
    },
    yAxis: {
      type: 'value',
      name: '订单',
      axisLabel: { formatter: (v) => v.toLocaleString() }
    },
    series: [
      {
        name: '总订单',
        type: 'bar',
        data: yData,
        barMaxWidth: 28,
        itemStyle: {
          borderRadius: [6, 6, 0, 0],
          color: '#409EFF'
        }
      }
    ]
  })
}

const renderIncomeDriversChart = () => {
  if (!incomeChart) return
  const xData = chartList.value.map((i) => i.city)
  const incomeData = chartList.value.map((i) => i.totalIncome ?? 0)
  const driversData = chartList.value.map((i) => i.activeDrivers ?? 0)

  incomeChart.setOption({
    backgroundColor: 'transparent',
    tooltip: { trigger: 'axis', axisPointer: { type: 'cross' } },
    legend: { top: 24, data: ['总收入', '活跃司机'] },
    grid: { left: 40, right: 20, bottom: 45, top: 70 },
    xAxis: {
      type: 'category',
      data: xData,
      axisLabel: { rotate: 30, interval: 0 }
    },
    yAxis: [
      {
        type: 'value',
        name: '收入',
        axisLabel: { formatter: (v) => `¥${v.toLocaleString()}` }
      },
      {
        type: 'value',
        name: '司机',
        axisLabel: { formatter: (v) => v.toLocaleString() }
      }
    ],
    series: [
      {
        name: '总收入',
        type: 'bar',
        data: incomeData,
        yAxisIndex: 0,
        barMaxWidth: 28,
        itemStyle: {
          borderRadius: [6, 6, 0, 0],
          color: '#67C23A'
        }
      },
      {
        name: '活跃司机',
        type: 'line',
        data: driversData,
        yAxisIndex: 1,
        smooth: true,
        symbolSize: 8,
        lineStyle: { width: 2, color: '#E6A23C' },
        itemStyle: { color: '#E6A23C' }
      }
    ]
  })
}

const initCharts = () => {
  if (ordersChartRef.value) {
    ordersChart = echarts.init(ordersChartRef.value)
    renderOrdersChart()
  }
  if (incomeChartRef.value) {
    incomeChart = echarts.init(incomeChartRef.value)
    renderIncomeDriversChart()
  }
}

const resizeCharts = () => {
  ordersChart?.resize()
  incomeChart?.resize()
}

const loadData = async () => {
  loading.value = true
  try {
    const [ov, cities] = await Promise.all([api.get('/stats/overview'), api.get('/stats/cities')])
    overview.value = ov || {}
    list.value = cities || []
  } catch (e) {
    ElMessage.error(e?.message || '加载统计数据失败')
  } finally {
    loading.value = false
  }
}

onMounted(async () => {
  await loadData()
  initCharts()
  window.addEventListener('resize', resizeCharts)
})

watch(list, () => {
  renderOrdersChart()
  renderIncomeDriversChart()
}, { deep: true })

onBeforeUnmount(() => {
  window.removeEventListener('resize', resizeCharts)
  ordersChart?.dispose()
  incomeChart?.dispose()
  ordersChart = null
  incomeChart = null
})
</script>

<style scoped>
.stats-page {
  padding: 20px 10px;
}

.kpi-card {
  border-radius: 12px;
}

.kpi-title {
  color: #909399;
  font-size: 14px;
  margin-bottom: 8px;
}

.kpi-value {
  font-size: 26px;
  font-weight: 700;
  color: #303133;
  line-height: 1.2;
}

.chart-card :deep(.el-card__header) {
  padding: 12px 18px;
}

.chart {
  height: 360px;
}

.card-title {
  font-weight: 600;
}

.table-card :deep(.el-card__header) {
  padding: 12px 18px;
}
</style>
