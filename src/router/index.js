import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/home/HomeView.vue'

import Layout from "@/views/Layout";
import Cookies from "js-cookie";

Vue.use(VueRouter)

const routes = [
    //登录
  {
    path:'/login',
    name:'Login',
    component: ()=>import('@/views/login/Login.vue')
  },
    // -----主页-------
  {
    path: '/',
    name: 'Layout',
    component: Layout,
    redirect:'/home',
    children:[//子路由
        //主页
      {
        path:'home',
        name: 'Home',
        component:()=>import ('@/views/home/HomeView')
      },
      //----user-----
      {
        path:'userList',
        name:'UserList',
        component: ()=>import('@/views/user/User.vue')
      },
      {
        path:'addUser',
        name:'AddUser',
        component: ()=>import('@/views/user/addUser.vue')
      },
      {
        path:'editUser',
        name:'EditUser',
        component: ()=>import('@/views/user/EditUser.vue')
      },
      // --- admin------
      {
        path:'adminList',
        name:'AdminList',
        component: ()=>import('@/views/admin/list.vue')
      },
      {
        path:'addAdmin',
        name:'AddAdmin',
        component: ()=>import('@/views/admin/add.vue')
      },
      {
        path:'editAdmin',
        name:'EditAdmin',
        component: ()=>import('@/views/admin/Edit.vue')
      },
    ]
  },
  {
    path: "*",
    component:()=>import('@/views/404')
  },

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})
router.beforeEach((to, from, next) => {
  if (to.path === '/login') next()
  const admin = Cookies.get("admin")
  if (!admin && to.path !== '/login') return next("/login")
  next()
})
export default router
