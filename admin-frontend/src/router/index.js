import { createRouter, createWebHistory } from 'vue-router'
import Layout from '../views/Layout.vue'
import Login from '../views/Login.vue'
import PassengerList from '../views/PassengerList.vue'
import DriverList from '../views/DriverList.vue'
import VehicleList from '../views/VehicleList.vue'
import OrderList from '../views/OrderList.vue'
import CityList from '../views/CityList.vue'
import FeedbackList from '../views/FeedbackList.vue'
import Stats from '../views/Stats.vue'
import LoginLogList from '../views/LoginLogList.vue'

const routes = [
 { path: '/login', component: Login },
 {
 path: '/',
 component: Layout,
 children: [
 { path: '', component: PassengerList },
 { path: 'passengers', component: PassengerList },
 { path: 'drivers', component: DriverList },
 { path: 'vehicles', component: VehicleList },
 { path: 'orders', component: OrderList },
 { path: 'cities', component: CityList },
 { path: 'feedback', component: FeedbackList },
 { path: 'stats', component: Stats },
 { path: 'login-logs', component: LoginLogList }
 ]
 }
]

const router = createRouter({
 history: createWebHistory(),
 routes
})

router.beforeEach((to, from, next) => {
 const token = localStorage.getItem('token')
 if (to.path !== '/login' && !token) return next('/login')
 next()
})

export default router
