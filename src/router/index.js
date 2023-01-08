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
        //-----category-----
      {
        path:'categoryList',
        name:'CategoryList',
        component: ()=>import('@/views/category/list.vue')
      },
      {
        path:'addCategory',
        name:'AddCategory',
        component: ()=>import('@/views/category/add.vue')
      },
      {
        path:'editCategory',
        name:'EditCategory',
        component: ()=>import('@/views/category/Edit.vue')
      },
      //-----book-----
      {
        path:'bookList',
        name:'BookList',
        component: ()=>import('@/views/book/List')
      },
      {
        path:'addBook',
        name:'AddBook',
        component: ()=>import('@/views/book/Add')
      },
      {
        path:'editBook',
        name:'EditBook',
        component: ()=>import('@/views/book/Edit')
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
  if (!admin && to.path !== '/login') return next("/login")  // 强制退回到登录页面
  // 访问 /home 的时候，并且cookie里面存在数据，这个时候我就直接放行
  next()
})
export default router
